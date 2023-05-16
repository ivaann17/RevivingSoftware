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
		try {
			String sqlDNI = "SELECT DNI FROM usuarios WHERE usuario = ?";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sqlDNI, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				dni = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dni;

	}

	public String getNombre(String usu) throws Exception {

		String nom = null;
		try {
			String sqlDNI = "SELECT nombre FROM usuarios WHERE usuario = ?";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sqlDNI, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				nom = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nom;

	}

	public String getApellidosLog(String usu) throws Exception {

		String ape = null;
		try {
			String sqlDNI = "SELECT apellido FROM usuarios WHERE usuario = ?";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sqlDNI, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				ape = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ape;

	}

	public String getTipoUsuLog(String usu) throws Exception {

		String tipo = null;
		try {
			String sqlDNI = "SELECT tipo FROM usuarios WHERE usuario = ?";

			PreparedStatement ps = GestorBD.mBD.prepareStatement(sqlDNI, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, usu);
			ResultSet rs = gestorBD.operation(ps);
			if (rs.next()) {
				tipo = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;

	}

	public Vector<Object> loginUsuario(String usuario) {
		String sqlUser = "SELECT * FROM usuarios WHERE usuario = ?";

		Vector<Object> rUser = null;
		try {
			PreparedStatement psU = GestorBD.mBD.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);

			psU.setString(1, usuario);
			rUser = GestorBD.select(psU);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
		}

		return rUser;
	}

	public Vector<Object> loginContra(String contraseña) {

		String sqlPass = "SELECT * FROM usuarios WHERE contraseña = ?";

		Vector<Object> rPass = null;
		try {

			PreparedStatement psP = GestorBD.mBD.prepareStatement(sqlPass, Statement.RETURN_GENERATED_KEYS);

			psP.setString(1, contraseña);
			rPass = GestorBD.select(psP);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
		}

		return rPass;
	}

}
