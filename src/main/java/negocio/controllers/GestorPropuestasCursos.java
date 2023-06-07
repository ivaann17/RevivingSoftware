package main.java.negocio.controllers;

import java.sql.SQLException;

import main.java.negocio.entities.*;
import main.java.persistencia.CursoPropioDAO;
import main.java.persistencia.MatriculaDAO;

public class GestorPropuestasCursos {

	static CursoPropioDAO cursoDAO;
	static MatriculaDAO matDAO;

	private GestorPropuestasCursos() {
	}

	public static void realizarPropuestaCurso(CursoPropio curso) throws SQLException {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.crearNuevoCurso(curso);
	}

	public static void editarEstadoCurso(CursoPropio curso, EstadoCurso est) throws SQLException {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.editarCurso(curso, est, "");
	}

	public static void aceptarPropuesta(CursoPropio curso) throws SQLException {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.editarCurso(curso, EstadoCurso.VALIDADO, "");

	}

	public static void rechazarPropuesta(CursoPropio curso, String mensaje) throws SQLException {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.editarCurso(curso, EstadoCurso.PROPUESTA_RECHAZADA, mensaje);

	}

	public static boolean existeCurso(CursoPropio curso) throws SQLException {
		cursoDAO = new CursoPropioDAO();
		return cursoDAO.existeCurso(curso);
	}
	public static boolean existeSoloCurso(CursoPropio curso) throws SQLException {
		cursoDAO = new CursoPropioDAO();
		return cursoDAO.existeSoloCurso(curso);
	}

	public static boolean existeCursoConMatricula(CursoPropio curso) throws SQLException  {
		matDAO = new MatriculaDAO();
		return matDAO.cursoConMatricula(curso);
	}
	public static String estadoCurso(CursoPropio curso) throws SQLException  {
		cursoDAO = new CursoPropioDAO();
		return cursoDAO.getEstado(curso);
	}
	
	public static void eliminarCurso(CursoPropio curso) throws SQLException {
		cursoDAO = new CursoPropioDAO();
		cursoDAO.eliminarCurso(curso);
	}

}