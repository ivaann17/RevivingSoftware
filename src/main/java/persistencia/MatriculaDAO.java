package main.java.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.negocio.entities.*;

public class MatriculaDAO {
	GestorBD gestorBD = new GestorBD();

	public int crearNuevaMatricula(Matricula matricula) throws SQLException {

		int id = 0;
		String sql = "INSERT INTO matricula (ID, nombre, apellido, DNI, precio, tipo_pago, curso, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, matricula.getIDMatricula());
			ps.setString(2, matricula.getNombre());
			ps.setString(3, matricula.getApellido());
			ps.setString(4, matricula.getDni());
			ps.setDouble(5, matricula.getPrecio());
			ps.setString(6, matricula.getTipoPago().toString());
			ps.setInt(7, matricula.getIDCurso());
			ps.setDate(8, new java.sql.Date(matricula.getFecha().getTime()));
			gestorBD.insert(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} 
		return id;
	}

	public boolean existeMatricula(int curso, String dni) throws Exception {

		String sql = "SELECT COUNT(*) FROM matricula WHERE curso = ? AND DNI = ? ";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, curso);
			ps.setString(2, dni);

			ResultSet rs = gestorBD.operation(ps);
		
			    return main.java.persistencia.CursoPropioDAO.isResultSetMayorCero(rs);
			
		
		}

	}

	public double ingresosCurso(CursoPropio curso) throws Exception {

		double ingr = 0.0;

		String sql = "SELECT SUM(precio) FROM matricula WHERE curso = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, curso.getId());

			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				ingr = rs.getDouble(1);
			}

		} 
		return ingr;
	}

	public boolean cursoConMatricula(CursoPropio curso) throws Exception {

		String sql = "SELECT COUNT(curso) FROM matricula WHERE curso = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, curso.getId());

			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					return true;
				} else {
					return false;
				}
			}

		} 
		return false;

	}

	public int getNumMatriculas(CursoPropio curso) throws Exception {
		int count = 0;

		String sql = "SELECT COUNT(ID) FROM matricula WHERE curso = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, curso.getId());

			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				count = rs.getInt(1);

			}

		} 
		return count;

	}

}
