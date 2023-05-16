package main.java.negocio.controllers;

import java.util.Vector;

import main.java.persistencia.LoginDAO;

public class GestorLogin {
	static LoginDAO logDAO;
	
	public static Vector<Object> loginUsuario(String usu) throws Exception {
		logDAO = new LoginDAO();
		return logDAO.loginUsuario(usu);
	}
	
	public static Vector<Object> loginContra(String contra) throws Exception {
		logDAO = new LoginDAO();
		return logDAO.loginContra(contra);
	}
	
}
