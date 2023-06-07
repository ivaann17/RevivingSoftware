package test.java.negocio.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.negocio.controllers.GestorEdiciones;
import main.java.persistencia.GestorBD;

class GestorEdicionesTest {
	// CP1
	private boolean existeEdicion;
	private boolean noExisteEdicion;
	final int id = 1;
	final String nombre = "Edición prueba";
	final int edi = 2023;
	final int idCurso = 86;

	@BeforeEach
	void setUp() {
		GestorBD.conectarBD();
	}

	// D1
	@Test
	void testCrearEdicion() throws SQLException {
		// A
		GestorEdiciones.crearEdicion(id, nombre, edi, idCurso);

		boolean existeEdicion = GestorEdiciones.existeEdicion(edi, nombre);
		assertTrue(existeEdicion);
		GestorEdiciones.eliminarEdicion(id, nombre);
	}

	// D2
	@Test
	void testExisteEdicion() throws SQLException {
		// A
		GestorEdiciones.crearEdicion(id, nombre, edi, idCurso);

		existeEdicion = GestorEdiciones.existeEdicion(edi, nombre);
		assertTrue(existeEdicion);
		// B
		noExisteEdicion = GestorEdiciones.existeEdicion(2022, "Edición inexistente");
		assertFalse(noExisteEdicion);
		GestorEdiciones.eliminarEdicion(id, nombre);
	}

	// D3
	@Test
	void testEliminarEdicion() throws SQLException {
		// A
		GestorEdiciones.crearEdicion(id, nombre, edi, idCurso);

		boolean existeEdicionAntes = GestorEdiciones.existeEdicion(edi, nombre);
		assertTrue(existeEdicionAntes);
		// B
		GestorEdiciones.eliminarEdicion(id, nombre);

		boolean existeEdicionDespues = GestorEdiciones.existeEdicion(edi, nombre);
		assertFalse(existeEdicionDespues);
	}
}
