package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import negocio.entities.*;

public class CursoPropioDAO  {
	public int crearNuevoCurso(CursoPropio curso) {
	    GestorBD gestorBD = new GestorBD();

	    int idCurso = 0;
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
	            idCurso = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return idCurso;
	}


	/**
	 * 
	 * @param curso
	 */
	public CursoPropio seleccionarCurso(CursoPropio curso) {
		// TODO - implement CursoPropioDAO.seleccionarCurso
		throw new UnsupportedOperationException();
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
	 */
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado, Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarCursosPorEstado
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 */
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