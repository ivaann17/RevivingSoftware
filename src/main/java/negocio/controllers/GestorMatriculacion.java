package main.java.negocio.controllers;

import main.java.negocio.entities.*;
import main.java.persistencia.MatriculaDAO;

public class GestorMatriculacion {

	static MatriculaDAO matDAO;

	public static void realizarMatriculacion(Matricula mat) {
		matDAO = new MatriculaDAO();
		matDAO.crearNuevaMatricula(mat);
	}

	public static boolean existe(int id_curso, String dni) throws Exception {
		matDAO = new MatriculaDAO();
		return matDAO.existeMatricula(id_curso, dni);
	}

}