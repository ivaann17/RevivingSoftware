package test.java.negocio.controllers;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.Centro;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.TipoCurso;
import main.java.persistencia.GestorBD;

public class GestorPropuestasCursosTest {
	
	@BeforeEach
	private void setUp() {
		GestorBD.conectarBD();
	}

	private CursoPropio curso1() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345678P", "98765432S", 1, "Curso Test", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023), 100.0,
				1, "Mensaje 1");
	}

	private CursoPropio curso2() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345678P", "98765432S", -12, "Curso Test2", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023),
				100.0, 1, "Mensaje 1");
	}

	private CursoPropio curso3() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.EN_IMPARTICION, TipoCurso.MASTER,
				"12345678P", "98765432S", 123, "Curso Test3", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023),
				-100.0, 1, "Mensaje 1");
	}

	private CursoPropio curso4() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.EN_IMPARTICION, TipoCurso.MASTER,
				"12345678P", "98765432S", 1234, "Curso Test4", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023),
				100.0, -1, "Mensaje 1");
	}

	private boolean cursoCorrecto(CursoPropio curso) {
		if (curso.getNombre().equals("Curso Test") && curso.getId() == 1) {
			return true;
		} else
			return false;
	}

	@Test
	private void testRealizarPropuestaCurso() throws SQLException {
		// CP1
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		GestorPropuestasCursos.eliminarCurso(c);

		// CP2
		c = curso2();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));

		// CP3
		c = curso3();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));
		// CP4
		c = curso4();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));
	}
	@Test
	private void testEditarPropuestaCurso() throws SQLException {

		// CP1
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		GestorPropuestasCursos.editarEstadoCurso(c,EstadoCurso.TERMINADO);
		assertEquals(GestorPropuestasCursos.estadoCurso(c),EstadoCurso.TERMINADO.toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}
	@Test
	private void testAceptarPropuestaCurso() throws SQLException {

		// CP1
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		GestorPropuestasCursos.aceptarPropuesta(c);
		assertEquals(GestorPropuestasCursos.estadoCurso(c),EstadoCurso.VALIDADO.toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}
	@Test
	private void testRechazarPropuestaCurso() throws SQLException {

		// CP1
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		GestorPropuestasCursos.rechazarPropuesta(c,"TESTING");
		assertEquals(GestorPropuestasCursos.estadoCurso(c),EstadoCurso.PROPUESTA_RECHAZADA.toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}
	@Test
	private void testExisteSoloCurso() throws SQLException {

		// CP1
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertTrue(GestorPropuestasCursos.existeSoloCurso(c));
		GestorPropuestasCursos.eliminarCurso(c);
		//CP2
		c= curso2();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));
		

	}
	@Test
	private void testEstadoCurso() throws SQLException {

		// CP1
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.estadoCurso(c),c.getEstado().toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}
	@Test
	private void testEliminarCurso() throws SQLException {

		// CP1
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertTrue(GestorPropuestasCursos.existeSoloCurso(c));
		GestorPropuestasCursos.eliminarCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));

	}

}
