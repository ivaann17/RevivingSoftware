package test.java.negocio.controllers;

import main.java.negocio.controllers.GestorLogin;
import main.java.persistencia.GestorBD;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLoginTest {

    private static final Map<String, String> RESULTADOS_ESPERADOS = new HashMap<>();

    static {
        RESULTADOS_ESPERADOS.put("profesor", "[profesor, profesor, Profesor, 12345678R, Manuel, Gonzalez]");
        RESULTADOS_ESPERADOS.put("estudiante", "[estudiante, estudiante, Estudiante, 98765432L, Ivan, Muñoz]");
        RESULTADOS_ESPERADOS.put("vicerrector", "[vicerrector, vicerrector, Vicerrector, 89765643W, Jose, Perez]");
        RESULTADOS_ESPERADOS.put("jefe", "[jefe, jefe, Jefe_Gabinete, 89897123P, Ana, Gomez]");
    }

    @BeforeAll
    public static void setUpClass() {
        GestorBD.conectarBD();
    }

    public static Stream<String> usuarios() {
        return Stream.of("profesor", "estudiante", "vicerrector", "jefe");
    }

    @ParameterizedTest
    @MethodSource("usuarios")
    public void testLoginUsuario(String usuario) throws SQLException {
        Vector<Object> result = GestorLogin.loginUsuario(usuario);
        String resultString = result.get(0).toString();

        String expected = RESULTADOS_ESPERADOS.get(usuario);
        assertEquals(expected, resultString);
    }

    @ParameterizedTest
    @MethodSource("usuarios")
    public void testLoginContra(String usuario) throws SQLException {
        Vector<Object> result = GestorLogin.loginContra(usuario);
        String resultString = result.get(0).toString();

        String expected = RESULTADOS_ESPERADOS.get(usuario);
        assertEquals(expected, resultString);
    }
}
