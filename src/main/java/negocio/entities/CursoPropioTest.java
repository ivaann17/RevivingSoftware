package main.java.negocio.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CursoPropioTest {

	public CursoPropio curso() {
		return new CursoPropio(new Centro("Centro Ejemplo").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345678P", "98765432S", 1, "Curso Test", 120, new Date(), new Date(), 100.0, 1, "Mensaje 1");
	}

	@Test
	public void testGetCentro() {
		CursoPropio curso = curso();

		assertEquals("Centro Ejemplo", curso.getCentro());
	}

	@Test
	public void testGetEstado() {
		CursoPropio curso = curso();

		assertEquals(EstadoCurso.PROPUESTO, curso.getEstado());
	}

	@Test
	public void testGetTipo() {
		CursoPropio curso = curso();

		assertEquals(TipoCurso.MASTER, curso.getTipo());
	}

	@Test
	public void testGetDniDirector() {
		CursoPropio curso = curso();

		assertEquals("12345678P", curso.getDniDirector());
	}

	@Test
	public void testGetDniSecretario() {
		CursoPropio curso = curso();

		assertEquals("98765432S", curso.getDniSecretario());
	}

	@Test
	public void testGetId() {
		CursoPropio curso = curso();

		assertEquals(1, curso.getId());
	}

	@Test
	public void testGetNombre() {
		CursoPropio curso = curso();

		assertEquals("Curso Test", curso.getNombre());
	}

	@Test
	public void testGetECTS() {
		CursoPropio curso = curso();

		assertEquals(120, curso.getECTS());
	}

	@Test
	public void testGetFechaInicio() {
		Date fechaInicio = new Date();
		CursoPropio curso = curso();

		assertEquals(fechaInicio, curso.getFechaInicio());
	}

	@Test
	public void testGetFechaFin() {
		Date fechaFin = new Date();
		CursoPropio curso = curso();

		assertEquals(fechaFin, curso.getFechaFin());
	}

	@Test
	public void testGetTasaMatricula() {
		CursoPropio curso = curso();

		assertEquals(100.0, curso.getTasaMatricula(), 0.001);
	}

	@Test
	public void testGetEdicion() {
		CursoPropio curso = curso();

		assertEquals(1, curso.getEdicion());
	}

	@Test
	public void testGetMensaje() {
		CursoPropio curso = curso();

		assertEquals("Mensaje 1", curso.getMensaje());
	}

	@Test
	public void testToString() {
		CursoPropio curso = curso();

		String expectedString = "ID del curso: {1}  Nombre del curso: {Curso Test}  Estado del curso: {PROPUESTO}  Edicion: {1}";
		assertEquals(expectedString, curso.toString());
	}
}