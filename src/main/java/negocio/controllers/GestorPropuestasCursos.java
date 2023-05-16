package main.java.negocio.controllers;

import main.java.negocio.entities.*;
import main.java.persistencia.CursoPropioDAO;
import main.java.persistencia.MatriculaDAO;

public class GestorPropuestasCursos {

	static CursoPropioDAO cursoDAO;
	static MatriculaDAO matDAO;

	private GestorPropuestasCursos() {
	}

	public static void realizarPropuestaCurso(CursoPropio curso) {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.crearNuevoCurso(curso);
	}

	public static void editarEstadoCurso(CursoPropio curso, EstadoCurso est) {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.editarCurso(curso, est, "");
	}

	public static void aceptarPropuesta(CursoPropio curso) {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.editarCurso(curso, EstadoCurso.VALIDADO, "");

	}

	public static void rechazarPropuesta(CursoPropio curso, String mensaje) {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.editarCurso(curso, EstadoCurso.PROPUESTA_RECHAZADA, mensaje);

	}

	public static boolean existeCurso(CursoPropio curso) throws Exception {
		cursoDAO = new CursoPropioDAO();
		return cursoDAO.existeCurso(curso);
	}

	public static boolean existeCursoConMatricula(CursoPropio curso) throws Exception {
		matDAO = new MatriculaDAO();
		return matDAO.cursoConMatricula(curso);
	}

	public static void eliminarCurso(CursoPropio curso) {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.eliminarCurso(curso);
	}

}