package main.java.negocio.controllers;

import main.java.negocio.entities.*;
import main.java.persistencia.CursoPropioDAO;

public class GestorPropuestasCursos {
	
	static CursoPropioDAO cursoDAO ;

	public CursoPropio realizarPropuestaCurso() {
		// TODO - implement GestorPropuestasCursos.realizarPropuestaCurso
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 */
	public void editarPropuestaCurso(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.editarPropuestaCurso
		throw new UnsupportedOperationException();
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

}