package test.java.negocio.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.negocio.controllers.GestorMatriculacion;
import main.java.negocio.entities.Matricula;
import main.java.negocio.entities.ModoPago;
import main.java.persistencia.GestorBD;

public class GestorMatriculacionTest {

	private Matricula mat1() {
        return new Matricula(1, "Ivan", "Muñoz", ModoPago.TRANSFERENCIA, Date.valueOf("2023-05-01"), "02023784L",
                1233.08, 86);
    }

	private boolean matCorrecta(Matricula mat) {
        if (mat.getDni().equals("02023784L") && mat.getIDMatricula() == 1) {
            return true;
        } else {
            return false;
        }
    }

	private Matricula mat2() {
        return new Matricula(2, "Ivan", "Muñoz", ModoPago.TARJETA_CREDITO, Date.valueOf("2023-05-01"), "02023784L",
                1233.08, 85);
    }

	private Matricula mat3() {
        return new Matricula(-15, "Ivan", "Muñoz", ModoPago.TRANSFERENCIA, Date.valueOf("2023-05-01"), "02023784L",
                1233.08, 86);
    }

    @BeforeEach
    private void setUp() {
        GestorBD.conectarBD();
    }

    @Test
    private void testRealizarMatriculacion() throws SQLException {
        // CP1
        Matricula mat = mat1();
        GestorMatriculacion.realizarMatriculacion(mat);
        assertEquals(matCorrecta(mat), GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
        GestorMatriculacion.eliminarMat(mat.getIDCurso(), mat.getDni());

        // CP2
        mat = mat2();
        GestorMatriculacion.realizarMatriculacion(mat);
        assertEquals(matCorrecta(mat), GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));

        // CP3
        mat = mat3();
        GestorMatriculacion.realizarMatriculacion(mat);
        assertEquals(matCorrecta(mat), GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
    }

    @Test
    private void testExisteMatricula() throws SQLException {
        // CP1
        Matricula mat = mat1();
        GestorMatriculacion.realizarMatriculacion(mat);
        assertTrue(GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
        GestorMatriculacion.eliminarMat(mat.getIDCurso(), mat.getDni());

        // CP2
        mat = mat2();
        GestorMatriculacion.realizarMatriculacion(mat);
        assertFalse(GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));

        // CP3
        mat = mat3();
        GestorMatriculacion.realizarMatriculacion(mat);
        assertFalse(GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni()));
    }

    @Test
    private void testEliminarMatricula() throws SQLException {
        // CP1
        Matricula mat = mat1();
        GestorMatriculacion.realizarMatriculacion(mat);

        boolean existeMatAntes = GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni());
        assertTrue(existeMatAntes);

        GestorMatriculacion.eliminarMat(mat.getIDCurso(), mat.getDni());

        boolean existeMatDespues = GestorMatriculacion.existe(mat.getIDCurso(), mat.getDni());
        assertFalse(existeMatDespues);
    }
}
