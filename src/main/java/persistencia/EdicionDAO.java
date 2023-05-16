package main.java.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EdicionDAO {
	GestorBD gestorBD = new GestorBD();

	public int crearNuevaEdicion(int id, String nombre, int edi, int id_curso) {

		int i = 0;
		try {
			String sql = "INSERT INTO edicion (ID, nombre_curso, edicion_curso, ID_curso) VALUES (?, ?, ?, ?)";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setInt(3, edi);
			ps.setInt(4, id_curso);
			gestorBD.insert(ps);

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public boolean existeEdicion(int edi, String nombre) throws Exception {

		try {
			String sql = "SELECT COUNT(*) FROM edicion WHERE edicion_curso = ? AND nombre_curso = ? ";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, edi);
			ps.setString(2, nombre);

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

}