package main.java.negocio.controllers;

import main.java.negocio.entities.*;
import main.java.persistencia.CursoPropioDAO;
import main.java.persistencia.MatriculaDAO;

public class GestorPropuestasCursos {
	
	static CursoPropioDAO cursoDAO ;

	public static void realizarPropuestaCurso(CursoPropio curso) {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.crearNuevoCurso(curso);
	}

	public static void editarEstadoCurso(CursoPropio curso, EstadoCurso est) {
		 cursoDAO = new CursoPropioDAO();
		 cursoDAO.editarCurso(curso, est, "");
	}

	/**
	 * 
	 * @param curso
	 */
	public static void aceptarPropuesta(CursoPropio curso) {
		 cursoDAO = new CursoPropioDAO();
		 cursoDAO.editarCurso(curso, EstadoCurso.VALIDADO, "");
		
	}

	
	public static void rechazarPropuesta(CursoPropio curso, String mensaje) {
		 cursoDAO = new CursoPropioDAO();
		 cursoDAO.editarCurso(curso, EstadoCurso.PROPUESTA_RECHAZADA, mensaje);
		
	}

	/**
	 * 
	 * @param curso
	 */
	public void altaCursoAprobado(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.altaCursoAprobado
		throw new UnsupportedOperationException();
	}
	public static void eliminarCurso(CursoPropio curso) {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.eliminarCurso(curso);
	}
	
}