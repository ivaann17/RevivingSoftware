package test.java.negocio.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.negocio.controllers.GestorMatriculacion;
import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.Centro;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.Matricula;
import main.java.negocio.entities.ModoPago;
import main.java.negocio.entities.TipoCurso;
import main.java.persistencia.GestorBD;

class GestorMatriculacionTest {
	
	
	// CP1
	Matricula mat1() {
		return new Matricula(1, "Ivan", "Muñoz", ModoPago.TRANSFERENCIA, Date.valueOf("2023-05-01"), "02023784L",
				1233.08, 67);
	}

	boolean matCorrecta(Matricula mat) {
		if (mat.getDni().equals("02023784L") && mat.getIDMatricula() == 1) {
			return true;
		} else {
			return false;
		}
	}

	// CP2
	Matricula mat2() {
		return new Matricula(2, "Ivan", "Muñoz", ModoPago.TARJETA_CREDITO, Date.valueOf("2023-05-01"), "02023784L",
				1233.08, 85);
	}

	// CP3
	Matricula mat3() {
		return new Matricula(-15, "Ivan", "Muñoz", ModoPago.TRANSFERENCIA, Date.valueOf("2023-05-01"), "02023784L",
				1233.08, 67);
	}
	
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
	void testRealizarMatriculacion() throws SQLException {
		// A
		Matricula mat = mat1();
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		GestorMatriculacion.realizarMatriculacion(mat);
		assertEquals(matCorrecta(mat), GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
		GestorMatriculacion.eliminarMat(mat.getIDCurso(), mat.getDni());
		GestorPropuestasCursos.eliminarCurso(c);

		// B
		mat = mat2();
		GestorMatriculacion.realizarMatriculacion(mat);
		assertEquals(matCorrecta(mat), GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));

		// C
		mat = mat3();
		GestorMatriculacion.realizarMatriculacion(mat);
		assertEquals(matCorrecta(mat), GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
	}

	// D2
	@Test
	void testExisteMatricula() throws SQLException {
		// A
		Matricula mat = mat1();
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		GestorMatriculacion.realizarMatriculacion(mat);
		assertTrue(GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
		GestorMatriculacion.eliminarMat(mat.getIDCurso(), mat.getDni());
		GestorPropuestasCursos.eliminarCurso(c);

		// B
		mat = mat2();
		GestorMatriculacion.realizarMatriculacion(mat);
		assertFalse(GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));

		// C
		mat = mat3();
		GestorMatriculacion.realizarMatriculacion(mat);
		assertFalse(GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
	}

	// D3
	@Test
	void testEliminarMatricula() throws SQLException {
		// A
		Matricula mat = mat1();
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		GestorMatriculacion.realizarMatriculacion(mat);

		boolean existeMatAntes = GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni());
		assertTrue(existeMatAntes);
		// B
		GestorMatriculacion.eliminarMat(mat.getIDCurso(), mat.getDni());
		GestorPropuestasCursos.eliminarCurso(c);

		boolean existeMatDespues = GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni());
		assertFalse(existeMatDespues);
	}
}
