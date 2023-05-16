package main.java.negocio.controllers;

import main.java.persistencia.EdicionDAO;

public class GestorEdiciones {

	static EdicionDAO ediDAO;

	public static void crearEdicion(int id, String nombre, int edi, int id_cur) {
		ediDAO = new EdicionDAO();
		ediDAO.crearNuevaEdicion(id, nombre, edi, id_cur);
	}

	public static boolean existeEdicion(int edi, String nombre) throws Exception {
		ediDAO = new EdicionDAO();
		return ediDAO.existeEdicion(edi, nombre);
	}
}
