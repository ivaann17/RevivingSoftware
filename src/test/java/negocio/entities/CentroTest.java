package test.java.negocio.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import main.java.negocio.entities.Centro;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CentroTest {
	// D1
	@Test
	void testGetNombre() {
		// CP1
		Centro centro = new Centro("Centro de Prueba");
		// A
		assertEquals("Centro de Prueba", centro.getNombre());
	}
}
