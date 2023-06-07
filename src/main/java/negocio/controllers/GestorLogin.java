package main.java.negocio.controllers;

import java.sql.SQLException;
import java.util.Vector;

import main.java.persistencia.LoginDAO;

public class GestorLogin {
	static LoginDAO logDAO;

	private GestorLogin() {
	}

	public static Vector<Object> loginUsuario(String usu) throws SQLException {
		logDAO = new LoginDAO();
		return logDAO.loginUsuario(usu);
	}

	public static Vector<Object> loginContra(String contra) throws SQLException {
		logDAO = new LoginDAO();
		return logDAO.loginContra(contra);
	}

}
