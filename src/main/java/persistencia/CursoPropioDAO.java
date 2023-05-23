package main.java.persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import main.java.negocio.entities.*;
import main.java.persistencia.Excepciones.SelectException;

public class CursoPropioDAO {
	GestorBD gestorBD = new GestorBD();

	public int crearNuevoCurso(CursoPropio curso) throws SQLException {

		int id = 0;
		String sql = "INSERT INTO cursos (ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, curso.getId());
			ps.setString(2, curso.getNombre());
			ps.setString(3, curso.getDniDirector());
			ps.setString(4, curso.getDniSecretario());
			ps.setDate(5, new Date(curso.getFechaInicio().getTime()));
			ps.setDate(6, new Date(curso.getFechaFin().getTime()));
			ps.setInt(7, curso.getECTS());
			ps.setDouble(8, curso.getTasaMatricula());
			ps.setString(9, curso.getTipo().toString());
			ps.setString(10, curso.getEstado().toString());
			ps.setString(11, curso.getCentro());
			ps.setInt(12, curso.getEdicion());
			ps.setString(13, curso.getMensaje());

			gestorBD.insert(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		}

		return id;
	}

	public int getId(CursoPropio curso) throws SQLException {

		int id = 0;

		String sql = "SELECT ID FROM cursos WHERE nombre = ? AND tipo = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, curso.getNombre());
			ps.setString(2, curso.getTipo().toString());

			GestorBD.select(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		}
		return id;
	}

	public boolean existeCurso(CursoPropio curso) throws SQLException {

		String sql = "SELECT COUNT(*) FROM edicion WHERE nombre = ? AND tipo = ? ";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, curso.getNombre());
			ps.setString(2, curso.getTipo().toString());

			ResultSet rs = gestorBD.operation(ps);

			return isResultSetMayorCero(rs);

		}

	}

	public int eliminarCurso(CursoPropio curso) throws SQLException {

		int eli = 0;

		String sql = "DELETE FROM cursos WHERE ID = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, curso.getId());

			gestorBD.delete(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				eli = 1;
			}

		}
		return eli;
	}

	public int editarCurso(CursoPropio curso, EstadoCurso est, String mensaje) throws SQLException {

		int mod = 0;

		String sql = "UPDATE cursos SET estado = ?, mensaje = ? WHERE ID = ?";
		String estado = est.toString();

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, estado);
			ps.setString(2, mensaje);
			ps.setInt(3, curso.getId());

			gestorBD.update(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				mod = 1;
			}

		}
		return mod;
	}

	public List<CursoPropio> listarCursosMatriculados(String dni) throws SQLException {
		List<CursoPropio> cursos = new ArrayList<>();

		String sql = "SELECT cursos.* FROM cursos INNER JOIN matricula ON cursos.ID = matricula.curso WHERE matricula.DNI = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql)) {
			ps.setString(1, dni);
			Vector<Object> resultado = GestorBD.select(ps);
			cursos = convertirResultadoEnCursos(resultado);
		}

		return cursos;
	}

	public List<CursoPropio> listarHistorialCursos(String dni) throws SQLException {
		List<CursoPropio> cursos = new ArrayList<>();

		String sql = "SELECT ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje FROM cursos WHERE dniDirector = ? ORDER BY fechaInicio";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql)) {
			ps.setString(1, dni);
			Vector<Object> resultado = GestorBD.select(ps);
			cursos = convertirResultadoEnCursos(resultado);

		}
		return cursos;
	}

	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws SQLException {
		List<CursoPropio> cursos = new ArrayList<>();
		String sql = "SELECT ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje\r\n"
				+ "FROM cursos\r\n" + "WHERE estado = ? " + "ORDER BY fechaInicio\r\n" + "";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql)) {
			ps.setString(1, estado.toString());
			Vector<Object> resultado = GestorBD.select(ps);
			cursos = convertirResultadoEnCursos(resultado);
		}
		return cursos;
	}

	public List<CursoPropio> listarCursosPorEdicion() throws SQLException {
		List<CursoPropio> cursos = new ArrayList<>();

		String sql = "SELECT * " + "FROM cursos " + "WHERE nombre IN ( " + "    SELECT nombre " + "    FROM cursos "
				+ "    GROUP BY nombre " + "    HAVING COUNT(*) > 1 " + ") " + "AND tipo IN ( " + "    SELECT tipo "
				+ "    FROM cursos " + "    GROUP BY tipo " + "    HAVING COUNT(*) > 1 " + ") "
				+ "ORDER BY nombre,edicion";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql)) {
			Vector<Object> resultado = GestorBD.select(ps);
			cursos = convertirResultadoEnCursos(resultado);
		}
		return cursos;
	}

	public List<CursoPropio> listarCursos() throws SQLException {
		List<CursoPropio> cursos = new ArrayList<>();

		String sql = "SELECT * FROM cursos";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql)) {
			Vector<Object> resultado = GestorBD.select(ps);
			cursos = convertirResultadoEnCursos(resultado);
		}
		return cursos;
	}

	protected static boolean isResultSetMayorCero(ResultSet rs) throws SQLException {
		if (rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		}
		return false;
	}

	public List<CursoPropio> convertirResultadoEnCursos(Vector<Object> resultado) {
		List<CursoPropio> cursos = new ArrayList<>();

		for (Object obj : resultado) {
			Vector<Object> fila = (Vector<Object>) obj;
			int id = (int) fila.get(0);
			String nombre = (String) fila.get(1);
			String dniDirector = (String) fila.get(2);
			String dniSecretario = (String) fila.get(3);
			Date fechaInicioCurso = (Date) fila.get(4);
			Date fechaFinCurso = (Date) fila.get(5);
			int creditos = (int) fila.get(6);
			double precio = Double.parseDouble(fila.get(7).toString());
			TipoCurso tipo = TipoCurso.valueOf((String) fila.get(8));
			EstadoCurso estadoCurso = EstadoCurso.valueOf((String) fila.get(9));
			String facultad = (String) fila.get(10);
			int edicion = (int) fila.get(11);
			String mensaje = (String) fila.get(12);

			CursoPropio curso = new CursoPropio(facultad, estadoCurso, tipo, dniDirector, dniSecretario, id, nombre,
					creditos, fechaInicioCurso, fechaFinCurso, precio, edicion, mensaje);
			cursos.add(curso);
		}

		return cursos;
	}

}