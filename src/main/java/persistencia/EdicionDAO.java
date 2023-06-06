package main.java.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.negocio.entities.CursoPropio;

public class EdicionDAO {
	GestorBD gestorBD = new GestorBD();

	public int crearNuevaEdicion(int id, String nombre, int edi, int idCurso) throws SQLException {

		int i = 0;
		String sql = "INSERT INTO edicion (ID, nombre_curso, edicion_curso, ID_curso) VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setInt(3, edi);
			ps.setInt(4, idCurso);
			gestorBD.insert(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				i = rs.getInt(1);
			}

		} 
		return i;
	}

	public boolean existeEdicion(int edi, String nombre) throws SQLException  {

		String sql = "SELECT COUNT(*) FROM edicion WHERE edicion_curso = ? AND nombre_curso = ? ";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, edi);
			ps.setString(2, nombre);

			ResultSet rs = gestorBD.operation(ps);
			
			    return main.java.persistencia.CursoPropioDAO.isResultSetMayorCero(rs);
			
			
		}

	}
	public int eliminarEdi(int edi, String nombre) throws SQLException {

		int eli = 0;

		String sql = "DELETE FROM edicion WHERE ID = ? AND nombre_curso = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, edi);
			ps.setString(2, nombre);

			gestorBD.delete(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				eli = 1;
			}

		}
		return eli;
	}

}
