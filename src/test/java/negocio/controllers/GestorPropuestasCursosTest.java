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

class GestorPropuestasCursosTest {

	@BeforeEach
	void setUp() {
		GestorBD.conectarBD();
	}

	// CP1
	CursoPropio curso1() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345678P", "98765432S", 1, "Curso Test", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023), 100.0,
				1, "Mensaje 1");
	}

	// CP2
	CursoPropio curso2() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345678P", "98765432S", -12, "Curso Test2", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023),
				100.0, 1, "Mensaje 1");
	}

	// CP3
	CursoPropio curso3() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.EN_IMPARTICION, TipoCurso.MASTER,
				"12345678P", "98765432S", 123, "Curso Test3", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023),
				-100.0, 1, "Mensaje 1");
	}

	// CP4
	CursoPropio curso4() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.EN_IMPARTICION, TipoCurso.MASTER,
				"12345678P", "98765432S", 1234, "Curso Test4", 120, new Date(1 / 5 / 2023), new Date(1 / 6 / 2023),
				100.0, -1, "Mensaje 1");
	}

	boolean cursoCorrecto(CursoPropio curso) {
		if (curso.getNombre().equals("Curso Test") && curso.getId() == 1) {
			return true;
		} else
			return false;
	}

	// D1
	@Test
	void testRealizarPropuestaCurso() throws SQLException {
		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		GestorPropuestasCursos.eliminarCurso(c);

		// B
		c = curso2();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));

		// C
		c = curso3();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));
		// D
		c = curso4();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));
	}

	// D2
	@Test
	void testEditarPropuestaCurso() throws SQLException {

		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		// B
		GestorPropuestasCursos.editarEstadoCurso(c, EstadoCurso.TERMINADO);
		assertEquals(GestorPropuestasCursos.estadoCurso(c), EstadoCurso.TERMINADO.toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}

	// D3
	@Test
	void testAceptarPropuestaCurso() throws SQLException {

		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		// B
		GestorPropuestasCursos.aceptarPropuesta(c);
		assertEquals(GestorPropuestasCursos.estadoCurso(c), EstadoCurso.VALIDADO.toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}

	// D4
	@Test
	void testRechazarPropuestaCurso() throws SQLException {

		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.existeSoloCurso(c), cursoCorrecto(c));
		// B
		GestorPropuestasCursos.rechazarPropuesta(c, "TESTING");
		assertEquals(GestorPropuestasCursos.estadoCurso(c), EstadoCurso.PROPUESTA_RECHAZADA.toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}

	// D5
	@Test
	void testExisteSoloCurso() throws SQLException {

		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertTrue(GestorPropuestasCursos.existeSoloCurso(c));
		GestorPropuestasCursos.eliminarCurso(c);
		// B
		c = curso2();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));

	}

	// D6
	@Test
	void testEstadoCurso() throws SQLException {

		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertEquals(GestorPropuestasCursos.estadoCurso(c), c.getEstado().toString());
		GestorPropuestasCursos.eliminarCurso(c);

	}

	// D7
	@Test
	void testEliminarCurso() throws SQLException {

		// A
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		assertTrue(GestorPropuestasCursos.existeSoloCurso(c));
		// B
		GestorPropuestasCursos.eliminarCurso(c);
		assertFalse(GestorPropuestasCursos.existeSoloCurso(c));

	}

}
