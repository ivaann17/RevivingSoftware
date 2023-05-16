package main.java.negocio.controllers;

import main.java.persistencia.EdicionDAO;

public class GestorEdiciones {

	static EdicionDAO ediDAO;

	private GestorEdiciones() {
	}

	public static void crearEdicion(int id, String nombre, int edi, int idCurso) {
		ediDAO = new EdicionDAO();
		ediDAO.crearNuevaEdicion(id, nombre, edi, idCurso);
	}

	public static boolean existeEdicion(int edi, String nombre) throws Exception {
		ediDAO = new EdicionDAO();
		return ediDAO.existeEdicion(edi, nombre);
	}
}
