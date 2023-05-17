package main.java.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EdicionDAO {
	GestorBD gestorBD = new GestorBD();

	public int crearNuevaEdicion(int id, String nombre, int edi, int id_curso) throws SQLException {

		int i = 0;
		String sql = "INSERT INTO edicion (ID, nombre_curso, edicion_curso, ID_curso) VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setInt(3, edi);
			ps.setInt(4, id_curso);
			gestorBD.insert(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				i = rs.getInt(1);
			}

		} 
		return i;
	}

	public boolean existeEdicion(int edi, String nombre) throws Exception {

		String sql = "SELECT COUNT(*) FROM edicion WHERE edicion_curso = ? AND nombre_curso = ? ";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, edi);
			ps.setString(2, nombre);

			ResultSet rs = gestorBD.operation(ps);
			
			    return main.java.persistencia.CursoPropioDAO.isResultSetMayorCero(rs);
			
			
		}

	}

}
