package main.java.negocio.controllers;

import main.java.negocio.entities.*;
import main.java.persistencia.MatriculaDAO;

public class GestorMatriculacion {

	static MatriculaDAO matDAO ;
	
	public static void realizarMatriculacion(Matricula mat) {
		matDAO = new MatriculaDAO();
		matDAO.crearNuevaMatricula(mat);
	}
	
	
	public static boolean existe(int id_curso, String dni) throws Exception {
		matDAO = new MatriculaDAO();
		return matDAO.existeMatricula(id_curso, dni);
	}
	
/*	public static void cursoMatriculado(int id_curso) {
		matDAO = new MatriculaDAO();
		matDAO.(id_curso);
	}*/
	

	/**
	 * 
	 * @param curso
	 * @param estudiante
	 */
	public void realizarPagoMatricula(CursoPropio curso, Estudiante estudiante) {
		// TODO - implement GestorMatriculacion.realizarPagoMatricula
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 * @param estudiante
	 */
	private void realizarPagoTarjeta(CursoPropio curso, Estudiante estudiante) {
		// TODO - implement GestorMatriculacion.realizarPagoTarjeta
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 * @param estudiante
	 */
	private void realizarPagoTransferencia(CursoPropio curso, Estudiante estudiante) {
		// TODO - implement GestorMatriculacion.realizarPagoTransferencia
		throw new UnsupportedOperationException();
	}

	private void operation() {
		// TODO - implement GestorMatriculacion.operation
		throw new UnsupportedOperationException();
	}

}