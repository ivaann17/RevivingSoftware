package test.java.negocio.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.controllers.GestorMatriculacion;
import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.Centro;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.Matricula;
import main.java.negocio.entities.ModoPago;
import main.java.negocio.entities.TipoCurso;
import main.java.persistencia.GestorBD;

class GestorConsultasTest {

	Matricula matricula1() {
		return new Matricula(1, "Iván", "Muñoz", ModoPago.TARJETA_CREDITO, Date.valueOf("2023-05-01"), "12345678P",
				100.0, 91);
	}

	CursoPropio curso1() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.PROPUESTO, TipoCurso.MASTER,
				"12345675P", "98765432S", 91, "Curso Test", 120, Date.valueOf("2023-05-01"), Date.valueOf("2023-06-01"),
				100.0, 1, "Mensaje 1");
	}

	CursoPropio curso2() {
		return new CursoPropio(new Centro("CAMPUS_TALAVERA").getNombre(), EstadoCurso.EN_IMPARTICION, TipoCurso.MASTER,
				"12345677P", "98765432S", 90, "Curso Test", 120, Date.valueOf("2023-05-01"), Date.valueOf("2023-06-01"),
				100.0, 2, "Mensaje 1");
	}

	static final Map<String, String> DNI_ESPERADO = new HashMap<>();

	static {
		DNI_ESPERADO.put("profesor", "12345678R");
		DNI_ESPERADO.put("estudiante", "98765432L");
		DNI_ESPERADO.put("vicerrector", "89765643W");
		DNI_ESPERADO.put("jefe", "89897123P");
	}

	static final Map<String, String> NOMBRE_ESPERADO = new HashMap<>();

	static {
		NOMBRE_ESPERADO.put("profesor", "MANUEL");
		NOMBRE_ESPERADO.put("estudiante", "IVAN");
		NOMBRE_ESPERADO.put("vicerrector", "JOSE");
		NOMBRE_ESPERADO.put("jefe", "ANA");
	}
	static final Map<String, String> APELLIDO_ESPERADO = new HashMap<>();

	static {
		APELLIDO_ESPERADO.put("profesor", "GONZALEZ");
		APELLIDO_ESPERADO.put("estudiante", "MUÑOZ");
		APELLIDO_ESPERADO.put("vicerrector", "PEREZ");
		APELLIDO_ESPERADO.put("jefe", "GOMEZ");
	}
	static final Map<String, String> TIPO_USU_ESPERADO = new HashMap<>();

	static {
		TIPO_USU_ESPERADO.put("profesor", "PROFESOR");
		TIPO_USU_ESPERADO.put("estudiante", "ESTUDIANTE");
		TIPO_USU_ESPERADO.put("vicerrector", "VICERRECTOR");
		TIPO_USU_ESPERADO.put("jefe", "JEFE_GABINETE");
	}

	@BeforeAll
	static void setUpClass() {
		GestorBD.conectarBD();
	}

	static Stream<String> usuarios() {
		return Stream.of("profesor", "estudiante", "vicerrector", "jefe");
	}

	@ParameterizedTest
	@MethodSource("usuarios")
	void testGetDNILog(String usuario) throws SQLException {
		String actual = GestorConsultas.getDNILog(usuario);
		String expected = DNI_ESPERADO.get(usuario);
		assertEquals(expected, actual);
	}

	@Test
	void testConsultarIngresos() throws SQLException {
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		Matricula m = matricula1();
		GestorMatriculacion.realizarMatriculacion(m);
		double expectedIngresos = 100.0;
		double actualIngresos = GestorConsultas.consultarIngresos(c);
		assertEquals(expectedIngresos, actualIngresos, 2);
		GestorMatriculacion.eliminarMat(m.getIDCurso(), m.getDni());
		GestorPropuestasCursos.eliminarCurso(c);
	}

	@Test
	void testGetIdCurso() throws SQLException {
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		int expectedId = c.getId();
		int idActual = GestorConsultas.getIdCurso(c);
		GestorPropuestasCursos.eliminarCurso(c);
		assertEquals(expectedId, idActual);
	}

	@ParameterizedTest
	@MethodSource("usuarios")
	void testGetNombreLog(String usuario) throws SQLException {
		String actual = GestorConsultas.getNombreLog(usuario);
		String expected = NOMBRE_ESPERADO.get(usuario);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("usuarios")
	void testGetApellidoLog(String usuario) throws SQLException {
		String actual = GestorConsultas.getApellidoLog(usuario);
		String expected = APELLIDO_ESPERADO.get(usuario);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("usuarios")
	void testGetTipoUsuLog(String usuario) throws SQLException {
		String actual = GestorConsultas.getTipoUsuLog(usuario);
		String expected = TIPO_USU_ESPERADO.get(usuario);
		assertEquals(expected, actual);
	}

	@Test
	void testGetNumMatriculas() throws SQLException {
		CursoPropio c = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c);
		Matricula m = matricula1();
		GestorMatriculacion.realizarMatriculacion(m);
		GestorMatriculacion.realizarMatriculacion(m);
		double expectedNum = 2;
		double actualNum = GestorConsultas.getNumMatricula(c);
		assertEquals(expectedNum, actualNum);
		GestorMatriculacion.eliminarMat(m.getIDCurso(), m.getDni());
		GestorPropuestasCursos.eliminarCurso(c);
	}

	@Test
	void testListarCursosPorEdiciones() throws SQLException {
		DefaultListModel<CursoPropio> modelo = new DefaultListModel<>();
		GestorConsultas.listarCursosPorEdiciones(modelo);
		int inicialSize = modelo.getSize();
		modelo.removeAllElements();
		CursoPropio c1 = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c1);
		CursoPropio c2 = curso2();
		GestorPropuestasCursos.realizarPropuestaCurso(c2);
		GestorConsultas.listarCursosPorEdiciones(modelo);
		int expectedSize = modelo.getSize();

		GestorPropuestasCursos.eliminarCurso(c1);
		GestorPropuestasCursos.eliminarCurso(c2);
		assertEquals(expectedSize, inicialSize + 2);

	}

	@Test
	void testListarCursos() throws SQLException {
		DefaultListModel<CursoPropio> modelo = new DefaultListModel<>();

		GestorConsultas.listarCursos(modelo);
		int inicialSize = modelo.getSize();

		CursoPropio c1 = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c1);
		modelo.removeAllElements();
		GestorConsultas.listarCursos(modelo);
		int expectedSize = modelo.getSize();

		GestorPropuestasCursos.eliminarCurso(c1);

		assertEquals(expectedSize, inicialSize + 1);
	}

	@Test
	void testListarCursosPorEstado() throws SQLException {
		DefaultListModel<CursoPropio> modelo = new DefaultListModel<>();
		GestorConsultas.listarCursosPorEstado(modelo, EstadoCurso.PROPUESTO);
		int inicialSize = modelo.getSize();
		modelo.removeAllElements();
		CursoPropio c1 = curso1();
		GestorPropuestasCursos.realizarPropuestaCurso(c1);
		CursoPropio c2 = curso2();
		GestorPropuestasCursos.realizarPropuestaCurso(c2);
		GestorConsultas.listarCursosPorEstado(modelo, EstadoCurso.PROPUESTO);
		int expectedSize = modelo.getSize();

		GestorPropuestasCursos.eliminarCurso(c1);
		GestorPropuestasCursos.eliminarCurso(c2);
		assertEquals(expectedSize, inicialSize + 1);

	}

	@Test
	void testListarHistorial() throws SQLException {
		DefaultListModel<CursoPropio> modelo = new DefaultListModel<>();
		CursoPropio c1 = curso1();

		GestorConsultas.listarHistorial(modelo, c1.getDniDirector());
		int inicialSize = modelo.getSize();

		GestorPropuestasCursos.realizarPropuestaCurso(c1);
		modelo.removeAllElements();
		GestorConsultas.listarHistorial(modelo, c1.getDniDirector());
		int expectedSize = modelo.getSize();

		GestorPropuestasCursos.eliminarCurso(c1);

		assertEquals(expectedSize, inicialSize + 1);
	}

	@Test
	void testListarCursosMatriculados() throws SQLException {
		DefaultListModel<CursoPropio> modelo = new DefaultListModel<>();
		CursoPropio c1 = curso1();
		Matricula m = matricula1();
		GestorConsultas.listarCursosMatriculados(modelo, m.getDni());
		int inicialSize = modelo.getSize();
		modelo.removeAllElements();
		GestorPropuestasCursos.realizarPropuestaCurso(c1);
		GestorMatriculacion.realizarMatriculacion(m);

		GestorConsultas.listarCursosMatriculados(modelo, m.getDni());
		int expectedSize = modelo.getSize();
		GestorMatriculacion.eliminarMat(m.getIDCurso(), m.getDni());
		GestorPropuestasCursos.eliminarCurso(c1);
		assertEquals(expectedSize, inicialSize + 1);

	}
}
