package main.java.negocio.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaTest {

	@Test
	public Matricula matricula() {

		return new Matricula(1, "Ivan", "Muñoz", ModoPago.TARJETA_CREDITO, new Date(), "12345678P", 100.0, 2);
	}

	@Test
	public void testGetID() {
		Matricula m = matricula();

		assertEquals(1, m.getIDMatricula());
	}

	@Test
	public void testGetNombre() {
		Matricula m = matricula();

		assertEquals("Ivan", m.getNombre());
	}

	@Test
	public void testGetApellido() {
		Matricula m = matricula();

		assertEquals("Muñoz", m.getApellido());
	}

	@Test
	public void testGetTipoPago() {
		Matricula m = matricula();

		assertEquals(ModoPago.TARJETA_CREDITO, m.getTipoPago());
	}

	@Test
	public void testGetFecha() {
		Matricula m = matricula();
		Date fecha = new Date();

		assertEquals(fecha, m.getFecha());
	}

	@Test
	public void testGetDni() {
		Matricula m = matricula();

		assertEquals("12345678P", m.getDni());
	}

	@Test
	public void testGetPrecio() {
		Matricula m = matricula();
		assertEquals(100.0, m.getPrecio());
	}

	@Test
	public void testGetIdCurso() {
		Matricula m = matricula();

		assertEquals(2, m.getIDCurso());
	}

	@Test
	public void testToString() {
		Matricula m = matricula();
		String expectedToString = "Matricula [ID_Matricula=1, nombre=Ivan, apellido=Muñoz, tipoPago=TARJETA_CREDITO, fecha="
				+ new Date() + ", dni=12345678P, precio=100.0, ID_Curso=2]";

		assertEquals(expectedToString, m.toString());
	}
}
