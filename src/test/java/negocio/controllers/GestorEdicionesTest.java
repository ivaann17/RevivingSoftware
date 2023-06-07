package test.java.negocio.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.negocio.controllers.GestorEdiciones;
import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.Centro;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.TipoCurso;
import main.java.persistencia.GestorBD;

class GestorEdicionesTest {
	// CP1
	private boolean existeEdicion;
	private boolean noExisteEdicion;
	final int id = 4;
	final String nombre = "Curso Test";
	final int edi = 1;
	final int idCurso = 67;
	
	CursoPropio curso1() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345678P", "98765432S", 67, "Curso Test", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023), 100.0,
				1, "Mensaje 1");
	}

	@BeforeEach
	void setUp() {
		GestorBD.conectarBD();
	}

	// D1
	@Test
	void testCrearEdicion() throws SQLException {
		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		GestorEdiciones.crearEdicion(id, nombre, edi, idCurso);

		boolean existeEdicion = GestorEdiciones.existeEdicion(edi, nombre);
		assertTrue(existeEdicion);
		GestorEdiciones.eliminarEdicion(id, nombre);
		GestorPropuestasCursos.eliminarCurso(c);
	}

	// D2
	@Test
	void testExisteEdicion() throws SQLException {
		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		GestorEdiciones.crearEdicion(id, nombre, edi, idCurso);

		existeEdicion = GestorEdiciones.existeEdicion(edi, nombre);
		assertTrue(existeEdicion);
		
		// B
		GestorEdiciones.eliminarEdicion(id, nombre);
		GestorPropuestasCursos.eliminarCurso(c);
		
		noExisteEdicion = GestorEdiciones.existeEdicion(edi, nombre);
		assertFalse(noExisteEdicion);
		
	}

	// D3
	@Test
	void testEliminarEdicion() throws SQLException {
		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		GestorEdiciones.crearEdicion(id, nombre, edi, idCurso);

		boolean existeEdicionAntes = GestorEdiciones.existeEdicion(edi, nombre);
		assertTrue(existeEdicionAntes);
		// B
		GestorEdiciones.eliminarEdicion(id, nombre);
		GestorPropuestasCursos.eliminarCurso(c);

		boolean existeEdicionDespues = GestorEdiciones.existeEdicion(edi, nombre);
		assertFalse(existeEdicionDespues);
	}
}