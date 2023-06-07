package test.java.negocio.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import main.java.negocio.entities.Centro;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CentroTest {

	@Test
	private void testGetNombre() {

		Centro centro = new Centro("Centro de Prueba");

		assertEquals("Centro de Prueba", centro.getNombre());
	}
}
