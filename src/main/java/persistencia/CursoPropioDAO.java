package main.java.persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import main.java.negocio.entities.*;

public class CursoPropioDAO  {
	GestorBD gestorBD = new GestorBD();
	public int crearNuevoCurso(CursoPropio curso) {
	    

	    int id = 0;
	    try {
	        String sql = "INSERT INTO cursos (ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	       
	        ps.setInt(1, curso.getId());
	        ps.setString(2, curso.getNombre().toString());
	        ps.setString(3, curso.getDniDirector().toString());
	        ps.setString(4, curso.getDniSecretario().toString());
	        ps.setDate(5, new java.sql.Date(curso.getFechaInicio().getTime()));
	        ps.setDate(6, new java.sql.Date(curso.getFechaFin().getTime()));
	        ps.setInt(7, curso.getECTS());
	        ps.setDouble(8, curso.getTasaMatricula());
	        ps.setString(9, curso.getTipo().toString() );
	        ps.setString(10, curso.getEstado().toString());
	        ps.setString(11, curso.getCentro().toString());
	        ps.setInt(12, curso.getEdicion());
	        ps.setString(13, curso.getMensaje());
	        
	        gestorBD.insert(ps);

	      
	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            id = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return id;
	}


	/**
	 * 
	 * @param curso
	 */
	public CursoPropio seleccionarCurso(CursoPropio curso) {
		// TODO - implement CursoPropioDAO.seleccionarCurso
		throw new UnsupportedOperationException();
	}
	
	public int eliminarCurso(CursoPropio curso) {
		
		  int eli = 0;
		    try {
		        String sql = "DELETE FROM cursos WHERE ID = ?";

		        PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		       
		        ps.setInt(1, curso.getId());
		 
		        gestorBD.delete(ps);

		      
		        ResultSet rs = ps.getGeneratedKeys();
		        if (rs.next()) {
		            eli = 1;
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return eli;
		}

	/**
	 * 
	 * @param curso
	 */
	public CursoPropio editarCurso(CursoPropio curso) {
		// TODO - implement CursoPropioDAO.editarCurso
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param estado
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws Exception 
	 */
	public List<CursoPropio> listarCursos() throws Exception {
	    List<CursoPropio> cursos = new ArrayList<>();
	    try {
	    	String sql = "SELECT ID, nombre, dniDirector, dniSecretario, fechaInicio, fechaFin, creditos, precio, tipo, estado, facultad, edicion, mensaje FROM cursos ORDER BY fechaInicio";

	        PreparedStatement ps = GestorBD.mBD.prepareStatement(sql);
	        Vector<Object> resultado = GestorBD.select(ps);

	        for (int i = 0; i < resultado.size(); i++) {
	            Vector<Object> fila = (Vector<Object>) resultado.get(i);
	            int id = (int) fila.get(0);
	            String nombre = (String) fila.get(1);
	            String dniDirector = (String) fila.get(2);
	            String dniSecretario = (String) fila.get(3);
	            Date fechaInicioCurso = (Date) fila.get(4);
	            Date fechaFinCurso = (Date) fila.get(5);
	            int creditos = (int) fila.get(6);
	            double precio = Double.valueOf(fila.get(7).toString()).doubleValue();
	            TipoCurso tipo = TipoCurso.valueOf((String) fila.get(8));
	            EstadoCurso estadoCurso = EstadoCurso.valueOf((String) fila.get(9));
	            String facultad = (String) fila.get(10);
	            int edicion = (int) fila.get(11);
	            String mensaje = (String) fila.get(12);

	            CursoPropio curso = new CursoPropio(facultad, estadoCurso, tipo, dniDirector, dniSecretario, id, nombre, creditos, fechaInicioCurso, fechaFinCurso, precio, edicion, mensaje);
	            cursos.add(curso);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cursos;
	}
	        
	public double listarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarIngresos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public void listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarEdicionesCursos
		throw new UnsupportedOperationException();
	}

}