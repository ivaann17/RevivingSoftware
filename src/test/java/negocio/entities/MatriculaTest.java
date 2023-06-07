package test.java.negocio.entities;

import org.junit.jupiter.api.Test;

import main.java.negocio.entities.Matricula;
import main.java.negocio.entities.ModoPago;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaTest {

	private Matricula matricula() {

		return new Matricula(1, "Ivan", "Muñoz", ModoPago.TARJETA_CREDITO, new Date(), "12345678P", 100.0, 2);
	}

	@Test
	private void testGetID() {
		Matricula m = matricula();

		assertEquals(1, m.getIDMatricula());
	}

	@Test
	private void testGetNombre() {
		Matricula m = matricula();

		assertEquals("Ivan", m.getNombre());
	}

	@Test
	private void testGetApellido() {
		Matricula m = matricula();

		assertEquals("Muñoz", m.getApellido());
	}

	@Test
	private void testGetTipoPago() {
		Matricula m = matricula();

		assertEquals(ModoPago.TARJETA_CREDITO, m.getTipoPago());
	}

	@Test
	private void testGetFecha() {
		Matricula m = matricula();
		Date fecha = new Date();

		assertEquals(fecha, m.getFecha());
	}

	@Test
	private void testGetDni() {
		Matricula m = matricula();

		assertEquals("12345678P", m.getDni());
	}

	@Test
	private void testGetPrecio() {
		Matricula m = matricula();
		assertEquals(100.0, m.getPrecio());
	}

	@Test
	private void testGetIdCurso() {
		Matricula m = matricula();

		assertEquals(2, m.getIDCurso());
	}

	@Test
	private void testToString() {
		Matricula m = matricula();
		String expectedToString = "Matricula [ID_Matricula=1, nombre=Ivan, apellido=Muñoz, tipoPago=TARJETA_CREDITO, fecha="
				+ new Date() + ", dni=12345678P, precio=100.0, ID_Curso=2]";

		assertEquals(expectedToString, m.toString());
	}
}
