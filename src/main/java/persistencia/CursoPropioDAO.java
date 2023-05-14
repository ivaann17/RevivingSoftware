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

public class CursoPropioDAO {
	GestorBD gestorBD = new GestorBD();

	public int crearNuevoCurso(CursoPropio curso) {

		int id = 0;
		try {
			String sql = "INSERT INTO cursos (ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, curso.getId());
			ps.setString(2, curso.getNombre().toString());
			ps.setString(3, curso.getDniDirector().toString());
			ps.setString(4, curso.getDniSecretario().toString());
			ps.setDate(5, new java.sql.Date(curso.getFechaInicio().getTime()));
			ps.setDate(6, new java.sql.Date(curso.getFechaFin().getTime()));
			ps.setInt(7, curso.getECTS());
			ps.setDouble(8, curso.getTasaMatricula());
			ps.setString(9, curso.getTipo().toString());
			ps.setString(10, curso.getEstado().toString());
			ps.setString(11, curso.getCentro().toString());
			ps.setInt(12, curso.getEdicion());
			ps.setString(13, curso.getMensaje());

			gestorBD.insert(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public int getId(CursoPropio curso) throws Exception {

		int id = 0;
		try {
			String sql = "SELECT ID FROM cursos WHERE nombre = ? AND tipo = ?";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, curso.getNombre());
			ps.setString(2, curso.getTipo().toString());

			GestorBD.select(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean existeCurso(CursoPropio curso) throws Exception {

		try {
			String sql = "SELECT COUNT(*) FROM edicion WHERE nombre = ? AND tipo = ? ";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, curso.getNombre());
			ps.setString(2, curso.getTipo().toString());

			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public int eliminarCurso(CursoPropio curso) {

		int eli = 0;
		try {
			String sql = "DELETE FROM cursos WHERE ID = ?";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, curso.getId());

			gestorBD.delete(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				eli = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eli;
	}

	public int editarCurso(CursoPropio curso, EstadoCurso est, String mensaje) {

		int mod = 0;
		try {
			String sql = "UPDATE cursos SET estado = ?, mensaje = ? WHERE ID = ?";
			String estado = est.toString();

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, estado);
			ps.setString(2, mensaje);
			ps.setInt(3, curso.getId());

			gestorBD.update(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				mod = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mod;
	}

	public List<CursoPropio> listarCursosMatriculados(String dni) throws Exception {
		List<CursoPropio> cursos = new ArrayList<>();
		try {

			String sql = "SELECT cursos.* FROM cursos INNER JOIN matricula ON cursos.ID = matricula.curso WHERE matricula.DNI = ?";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql);
			ps.setString(1, dni);
			Vector<Object> resultado = GestorBD.select(ps);

			for (int i = 0; i < resultado.size(); i++) {
				Vector<Object> fila = (Vector<Object>) resultado.get(i);
				int id = (int) fila.get(0);
				String nombre = (String) fila.get(1);
				String dniDirector = (String) fila.get(2);
				String dniSecretario = (String) fila.get(3);
				Date fechaInicioCurso = (Date) fila.get(4);
				Date fechaFinCurso = (Date) fila.get(5);
				int creditos = (int) fila.get(6);
				double precio = Double.valueOf(fila.get(7).toString()).doubleValue();
				TipoCurso tipo = TipoCurso.valueOf((String) fila.get(8));
				EstadoCurso estadoCurso = EstadoCurso.valueOf((String) fila.get(9));
				String facultad = (String) fila.get(10);
				int edicion = (int) fila.get(11);
				String mensaje = (String) fila.get(12);

				CursoPropio curso = new CursoPropio(facultad, estadoCurso, tipo, dniDirector, dniSecretario, id, nombre,
						creditos, fechaInicioCurso, fechaFinCurso, precio, edicion, mensaje);
				cursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}

	public List<CursoPropio> listarHistorialCursos(String dni) throws Exception {
		List<CursoPropio> cursos = new ArrayList<>();
		try {
			String sql = "SELECT ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje FROM cursos WHERE dniDirector = ? ORDER BY fechaInicio";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql);
			ps.setString(1, dni);
			Vector<Object> resultado = GestorBD.select(ps);

			for (int i = 0; i < resultado.size(); i++) {
				Vector<Object> fila = (Vector<Object>) resultado.get(i);
				int id = (int) fila.get(0);
				String nombre = (String) fila.get(1);
				String dniDirector = (String) fila.get(2);
				String dniSecretario = (String) fila.get(3);
				Date fechaInicioCurso = (Date) fila.get(4);
				Date fechaFinCurso = (Date) fila.get(5);
				int creditos = (int) fila.get(6);
				double precio = Double.valueOf(fila.get(7).toString()).doubleValue();
				TipoCurso tipo = TipoCurso.valueOf((String) fila.get(8));
				EstadoCurso estadoCurso = EstadoCurso.valueOf((String) fila.get(9));
				String facultad = (String) fila.get(10);
				int edicion = (int) fila.get(11);
				String mensaje = (String) fila.get(12);

				CursoPropio curso = new CursoPropio(facultad, estadoCurso, tipo, dniDirector, dniSecretario, id, nombre,
						creditos, fechaInicioCurso, fechaFinCurso, precio, edicion, mensaje);
				cursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}

	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws Exception {
		List<CursoPropio> cursos = new ArrayList<>();
		try {
			String sql = "SELECT ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje\r\n"
					+ "FROM cursos\r\n" + "WHERE estado = ? " + "ORDER BY fechaInicio\r\n" + "";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql);
			ps.setString(1, estado.toString());
			Vector<Object> resultado = GestorBD.select(ps);

			for (int i = 0; i < resultado.size(); i++) {
				Vector<Object> fila = (Vector<Object>) resultado.get(i);
				int id = (int) fila.get(0);
				String nombre = (String) fila.get(1);
				String dniDirector = (String) fila.get(2);
				String dniSecretario = (String) fila.get(3);
				Date fechaInicioCurso = (Date) fila.get(4);
				Date fechaFinCurso = (Date) fila.get(5);
				int creditos = (int) fila.get(6);
				double precio = Double.valueOf(fila.get(7).toString()).doubleValue();
				TipoCurso tipo = TipoCurso.valueOf((String) fila.get(8));
				EstadoCurso estadoCurso = EstadoCurso.valueOf((String) fila.get(9));
				String facultad = (String) fila.get(10);
				int edicion = (int) fila.get(11);
				String mensaje = (String) fila.get(12);

				CursoPropio curso = new CursoPropio(facultad, estadoCurso, tipo, dniDirector, dniSecretario, id, nombre,
						creditos, fechaInicioCurso, fechaFinCurso, precio, edicion, mensaje);
				cursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}

	public List<CursoPropio> listarCursosPorEdicion() throws Exception {
		List<CursoPropio> cursos = new ArrayList<>();
		try {
			String sql = "SELECT * " + "FROM cursos " + "WHERE nombre IN ( " + "    SELECT nombre " + "    FROM cursos "
					+ "    GROUP BY nombre " + "    HAVING COUNT(*) > 1 " + ") " + "AND tipo IN ( " + "    SELECT tipo "
					+ "    FROM cursos " + "    GROUP BY tipo " + "    HAVING COUNT(*) > 1 " + ") "
					+ "ORDER BY nombre,edicion";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql);
			Vector<Object> resultado = GestorBD.select(ps);

			for (int i = 0; i < resultado.size(); i++) {
				Vector<Object> fila = (Vector<Object>) resultado.get(i);
				int id = (int) fila.get(0);
				String nombre = (String) fila.get(1);
				String dniDirector = (String) fila.get(2);
				String dniSecretario = (String) fila.get(3);
				Date fechaInicioCurso = (Date) fila.get(4);
				Date fechaFinCurso = (Date) fila.get(5);
				int creditos = (int) fila.get(6);
				double precio = Double.valueOf(fila.get(7).toString()).doubleValue();
				TipoCurso tipo = TipoCurso.valueOf((String) fila.get(8));
				EstadoCurso estadoCurso = EstadoCurso.valueOf((String) fila.get(9));
				String facultad = (String) fila.get(10);
				int edicion = (int) fila.get(11);
				String mensaje = (String) fila.get(12);

				CursoPropio curso = new CursoPropio(facultad, estadoCurso, tipo, dniDirector, dniSecretario, id, nombre,
						creditos, fechaInicioCurso, fechaFinCurso, precio, edicion, mensaje);
				cursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}

	public List<CursoPropio> listarCursos() throws Exception {
		List<CursoPropio> cursos = new ArrayList<>();
		try {
			String sql = "SELECT * FROM cursos";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql);
			Vector<Object> resultado = GestorBD.select(ps);

			for (int i = 0; i < resultado.size(); i++) {
				Vector<Object> fila = (Vector<Object>) resultado.get(i);
				int id = (int) fila.get(0);
				String nombre = (String) fila.get(1);
				String dniDirector = (String) fila.get(2);
				String dniSecretario = (String) fila.get(3);
				Date fechaInicioCurso = (Date) fila.get(4);
				Date fechaFinCurso = (Date) fila.get(5);
				int creditos = (int) fila.get(6);
				double precio = Double.valueOf(fila.get(7).toString()).doubleValue();
				TipoCurso tipo = TipoCurso.valueOf((String) fila.get(8));
				EstadoCurso estadoCurso = EstadoCurso.valueOf((String) fila.get(9));
				String facultad = (String) fila.get(10);
				int edicion = (int) fila.get(11);
				String mensaje = (String) fila.get(12);

				CursoPropio curso = new CursoPropio(facultad, estadoCurso, tipo, dniDirector, dniSecretario, id, nombre,
						creditos, fechaInicioCurso, fechaFinCurso, precio, edicion, mensaje);
				cursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}

}