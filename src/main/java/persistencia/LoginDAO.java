package main.java.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import main.java.negocio.entities.CursoPropio;

public class LoginDAO {
	GestorBD gestorBD = new GestorBD();

	public String getDNI(String usu) throws Exception {

		String dni = null;

		String sqlDNI = "SELECT DNI FROM usuarios WHERE usuario = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sqlDNI, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				dni = rs.getString(1);

			}

		}
		return dni;

	}

	public String getNombre(String usu) throws Exception {

		String nom = null;
		String sql = "SELECT nombre FROM usuarios WHERE usuario = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				nom = rs.getString(1);

			}

		} 
		return nom;

	}

	public String getApellidosLog(String usu) throws Exception {

		String ape = null;
		String sql = "SELECT apellido FROM usuarios WHERE usuario = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				ape = rs.getString(1);

			}

		} 
		return ape;

	}

	public String getTipoUsuLog(String usu) throws Exception {

		String tipo = null;

		String sql = "SELECT tipo FROM usuarios WHERE usuario = ?";

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				tipo = rs.getString(1);

			}

		} 
		return tipo;

	}

	public Vector<Object> loginUsuario(String usuario) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE usuario = ?";

		Vector<Object> rUser = null;
		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, usuario);
			rUser = GestorBD.select(ps);
		} catch (Exception e) {

			throw new SQLException("Error al ejecutar la consulta: " + e.getMessage());
		}

		return rUser;
	}

	public Vector<Object> loginContra(String contrasena) throws SQLException {

		String sql = "SELECT * FROM usuarios WHERE contraseña = ?";

		Vector<Object> rPass = null;

		try (PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, contrasena);
			rPass = GestorBD.select(ps);
		} catch (Exception e) {
			throw new SQLException("Error al ejecutar la consulta: " + e.getMessage());
		}

		return rPass;
	}

}
