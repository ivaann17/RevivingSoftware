package main.java.negocio.controllers;

import java.sql.Date;
import java.util.List;

import javax.swing.DefaultListModel;

import main.java.negocio.entities.*;
import main.java.persistencia.CursoPropioDAO;

public class GestorConsultas {

	static CursoPropioDAO cursoDAO ;
	
	public List<CursoPropio> consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.consultarIngresos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param estadoCurso
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.consultarEstadoCursos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.listarEdicionesCursos
		throw new UnsupportedOperationException();
	}
	
	public static void listarCursos(DefaultListModel<CursoPropio> modelo, EstadoCurso estado) throws Exception {
	    cursoDAO = new CursoPropioDAO();
	    List<CursoPropio> cursos = cursoDAO.listarCursos(estado);
	    for (CursoPropio curso : cursos) {
	        modelo.addElement(curso);
	    }
	}

	public static void listarHistorial(DefaultListModel<CursoPropio> modelo, String dni) throws Exception {
	    cursoDAO = new CursoPropioDAO();
	    List<CursoPropio> cursos = cursoDAO.listarHistorialCursos(dni);
	    for (CursoPropio curso : cursos) {
	        modelo.addElement(curso);
	    }
	}
		public static void listarCursosMatriculados(DefaultListModel<CursoPropio> modelo, String dni) throws Exception {
		    cursoDAO = new CursoPropioDAO();
		    List<CursoPropio> cursos = cursoDAO.listarCursosMatriculados(dni);
		    for (CursoPropio curso : cursos) {
		        modelo.addElement(curso);
		    }
	    }


}