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
	
	public static void listarCursos(DefaultListModel<CursoPropio> modelo) throws Exception {
	    cursoDAO = new CursoPropioDAO();
	    List<CursoPropio> cursos = cursoDAO.listarCursos();
	    for (CursoPropio curso : cursos) {
	        modelo.addElement(curso);
	    }
	}
	public static void listarCursosPropuestos(DefaultListModel<CursoPropio> modelo) throws Exception {
	    cursoDAO = new CursoPropioDAO();
	    List<CursoPropio> cursos = cursoDAO.listarCursosPropuestos();
	    for (CursoPropio curso : cursos) {
	        modelo.addElement(curso);
	    }
	}
	public static void listarCursosRechazados(DefaultListModel<CursoPropio> modelo) throws Exception {
	    cursoDAO = new CursoPropioDAO();
	    List<CursoPropio> cursos = cursoDAO.listarCursosRechazados();
	    for (CursoPropio curso : cursos) {
	        modelo.addElement(curso);
	    }
	}

}