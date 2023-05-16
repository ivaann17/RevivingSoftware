package main.java.negocio.controllers;

import java.util.List;
import javax.swing.DefaultListModel;
import main.java.negocio.entities.*;
import main.java.persistencia.CursoPropioDAO;
import main.java.persistencia.LoginDAO;
import main.java.persistencia.MatriculaDAO;

public class GestorConsultas {

	static CursoPropioDAO cursoDAO;
	static MatriculaDAO matDAO;
	static LoginDAO logDAO;

    private GestorConsultas() {
    }


	public static double consultarIngresos(CursoPropio curso) throws Exception {
		matDAO = new MatriculaDAO();
		return matDAO.ingresosCurso(curso);
	}

	public static int getIdCurso(CursoPropio curso) throws Exception {
		cursoDAO = new CursoPropioDAO();
		return cursoDAO.getId(curso);
	}
	
	public static String getDNILog(String usu) throws Exception {
		logDAO = new LoginDAO();
		return logDAO.getDNI(usu);
	}
	
	public static String getNombreLog(String usu) throws Exception {
		logDAO = new LoginDAO();
		return logDAO.getNombre(usu).toUpperCase();
	}
	
	public static String getApellidoLog(String usu) throws Exception {
		logDAO = new LoginDAO();
		return logDAO.getApellidosLog(usu).toUpperCase();
	}
	
	public static String getTipoUsuLog(String usu) throws Exception {
		logDAO = new LoginDAO();
		return logDAO.getTipoUsuLog(usu).toUpperCase();
	}


	public static int getNumMatricula(CursoPropio curso) throws Exception {
		matDAO = new MatriculaDAO();
		return matDAO.getNumMatriculas(curso);
	}

	public static void listarCursosPorEdiciones(DefaultListModel<CursoPropio> modelo) throws Exception {
		cursoDAO = new CursoPropioDAO();
		List<CursoPropio> cursos = cursoDAO.listarCursosPorEdicion();
		for (CursoPropio curso : cursos) {
			modelo.addElement(curso);
		}
	}

	public static void listarCursos(DefaultListModel<CursoPropio> modelo) throws Exception {
		cursoDAO = new CursoPropioDAO();
		List<CursoPropio> cursos = cursoDAO.listarCursos();
		for (CursoPropio curso : cursos) {
			modelo.addElement(curso);
		}
	}

	public static void listarCursosPorEstado(DefaultListModel<CursoPropio> modelo, EstadoCurso estado)
			throws Exception {
		cursoDAO = new CursoPropioDAO();
		List<CursoPropio> cursos = cursoDAO.listarCursosPorEstado(estado);
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