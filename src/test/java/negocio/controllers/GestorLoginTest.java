package test.java.negocio.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.java.negocio.controllers.GestorLogin;
import main.java.persistencia.GestorBD;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GestorLoginTest {

	private static final Map<String, String> RESULTADOS_ESPERADOS = new HashMap<>();

	static {
		
		RESULTADOS_ESPERADOS.put("profesor", "[profesor, profesor, Profesor, 12345678R, Manuel, Gonzalez]");
		RESULTADOS_ESPERADOS.put("estudiante", "[estudiante, estudiante, Estudiante, 98765432L, Ivan, Muñoz]");
		RESULTADOS_ESPERADOS.put("vicerrector", "[vicerrector, vicerrector, Vicerrector, 89765643W, Jose, Perez]");
		RESULTADOS_ESPERADOS.put("jefe", "[jefe, jefe, Jefe_Gabinete, 89897123P, Ana, Gomez]");
	}

	private String usuario;

	public GestorLoginTest(String usuario) {
		this.usuario = usuario;
	}

	@Before
	public void setUp() throws Exception {
		GestorBD.conectarBD();
	}

	@Parameterized.Parameters
	public static Collection<Object[]> usuarios() {
		// CASOS DE PRUEBA
		return Arrays.asList(new Object[][] { { "profesor" }, { "estudiante" }, { "vicerrector" }, { "jefe" } });
	}

	@Test
	public void testLoginUsuario() throws SQLException {
		Vector<Object> result = GestorLogin.loginUsuario(usuario);
		String resultString = result.get(0).toString();

		String expected = RESULTADOS_ESPERADOS.get(usuario);
		assertEquals(expected, resultString);
	}

	@Test
	public void testLoginContra() throws SQLException {
		Vector<Object> result = GestorLogin.loginContra(usuario);
		String resultString = result.get(0).toString();


		String expected = RESULTADOS_ESPERADOS.get(usuario);
		assertEquals(expected, resultString);
	}
}
