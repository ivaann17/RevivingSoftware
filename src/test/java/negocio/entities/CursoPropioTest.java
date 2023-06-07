package test.java.negocio.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import main.java.negocio.entities.Centro;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.TipoCurso;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CursoPropioTest {
	// CP1
	CursoPropio curso() {
		return new CursoPropio(new Centro("Centro Ejemplo").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345678P", "98765432S", 1, "Curso Test", 120, new Date(), new Date(), 100.0, 1, "Mensaje 1");
	}

	// D1
	@Test
	void testGetCentro() {
		CursoPropio curso = curso();

		assertEquals("Centro Ejemplo", curso.getCentro());
	}

	// D2
	@Test
	void testGetEstado() {
		CursoPropio curso = curso();

		assertEquals(EstadoCurso.PROPUESTO, curso.getEstado());
	}

	// D3
	@Test
	void testGetTipo() {
		CursoPropio curso = curso();

		assertEquals(TipoCurso.MASTER, curso.getTipo());
	}

	// D4
	@Test
	void testGetDniDirector() {
		CursoPropio curso = curso();

		assertEquals("12345678P", curso.getDniDirector());
	}

	// D5
	@Test
	void testGetDniSecretario() {
		CursoPropio curso = curso();

		assertEquals("98765432S", curso.getDniSecretario());
	}

	// D6
	@Test
	void testGetId() {
		CursoPropio curso = curso();

		assertEquals(1, curso.getId());
	}

	// D7
	@Test
	void testGetNombre() {
		CursoPropio curso = curso();

		assertEquals("Curso Test", curso.getNombre());
	}

	// D8
	@Test
	void testGetECTS() {
		CursoPropio curso = curso();

		assertEquals(120, curso.getECTS());
	}

	// D9
	@Test
	void testGetFechaInicio() {
		Date fechaInicio = new Date();
		CursoPropio curso = curso();

		assertEquals(fechaInicio, curso.getFechaInicio());
	}

	// D10
	@Test
	void testGetFechaFin() {
		Date fechaFin = new Date();
		CursoPropio curso = curso();

		assertEquals(fechaFin, curso.getFechaFin());
	}

	// D11
	@Test
	void testGetTasaMatricula() {
		CursoPropio curso = curso();

		assertEquals(100.0, curso.getTasaMatricula());
	}

	// D12
	@Test
	void testGetEdicion() {
		CursoPropio curso = curso();

		assertEquals(1, curso.getEdicion());
	}

	// D13
	@Test
	void testGetMensaje() {
		CursoPropio curso = curso();

		assertEquals("Mensaje 1", curso.getMensaje());
	}

	// D14
	@Test
	void testToString() {
		CursoPropio curso = curso();

		String expectedString = "ID del curso: {1}  Nombre del curso: {Curso Test}  Estado del curso: {PROPUESTO}  Edicion: {1}";
		assertEquals(expectedString, curso.toString());
	}
}