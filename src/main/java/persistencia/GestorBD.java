package main.java.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import javax.swing.JOptionPane;
import main.java.persistencia.Excepciones.ConfigFileException;

public class GestorBD {

	protected static GestorBD mInstancia = null;
	public static Connection mBD;

	public static void conectarBD() {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("config.properties")) {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ConfigFileException("Error al cargar el archivo de configuración: " + e.getMessage());
		}

		String urlEncriptada = prop.getProperty("url");
		String driverEncriptado = prop.getProperty("driver");
		String usuarioEncriptado = prop.getProperty("user");
		String contraseñaEncriptada = prop.getProperty("password");

		try {
			Class.forName(desencriptarContraseña(driverEncriptado));
			mBD = DriverManager.getConnection(desencriptarContraseña(urlEncriptada),
					desencriptarContraseña(usuarioEncriptado), desencriptarContraseña(contraseñaEncriptada));
			mBD.setAutoCommit(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexión con la base de datos.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	public static String desencriptarContraseña(String contraseñaEncriptada) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("proyectoISO2");
		return encryptor.decrypt(contraseñaEncriptada);
	}

	public static void desconectarBD() {
		try {
			if (mBD != null && !mBD.isClosed()) {
				mBD.close();
				System.out.println("Conexión cerrada correctamente.");
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido un error al cerrar la conexión: " + e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * 
	 * @param sql
	 */
	public static Vector<Object> select(PreparedStatement ps) throws Exception {
		Vector<Object> vectoradevolver = new Vector<Object>();
		ResultSet res = ps.executeQuery();
		ResultSetMetaData rsmd = res.getMetaData();
		int columns = rsmd.getColumnCount();

		while (res.next()) {
			Vector<Object> v = new Vector<Object>();
			for (int i = 1; i <= columns; i++) {
				try {
					v.add(res.getObject(i));
				} catch (SQLException ex) {
					continue;
				}
			}
			vectoradevolver.add(v);
		}
		return vectoradevolver;
	}

	public int insert(PreparedStatement sql) {
		try {
			int rows = sql.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("Se ha producido un error al ejecutar la inserción: " + e.getMessage());
			return 0;
		}
	}

	public int update(PreparedStatement ps) {
		try {
			int rows = ps.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("Se ha producido un error al ejecutar la actualización: " + e.getMessage());
			return 0;
		}
	}

	public int delete(PreparedStatement ps) {
		try {
			int rows = ps.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("Se ha producido un error al ejecutar el borrado: " + e.getMessage());
			return 0;
		}
	}

	public ResultSet operation(PreparedStatement ps) throws SQLException {
		return ps.executeQuery();
	}

}
