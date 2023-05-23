package main.java.negocio.controllers;

import java.sql.SQLException;

import main.java.persistencia.EdicionDAO;

public class GestorEdiciones {

	static EdicionDAO ediDAO;

	private GestorEdiciones() {
	}

	public static void crearEdicion(int id, String nombre, int edi, int idCurso) throws SQLException {
		ediDAO = new EdicionDAO();
		ediDAO.crearNuevaEdicion(id, nombre, edi, idCurso);
	}

	public static boolean existeEdicion(int edi, String nombre) throws SQLException {
		ediDAO = new EdicionDAO();
		return ediDAO.existeEdicion(edi, nombre);
	}
}
