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

import javax.swing.JOptionPane;

public class GestorBD {

	protected static GestorBD mInstancia = null;
	public static Connection mBD;

	public static void conectarBD() {
	    Properties prop = new Properties();
	    try (InputStream input = new FileInputStream("config.properties")) {
	        prop.load(input);
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al cargar el archivo de configuraci�n: " + e.getMessage());
	    }
	    
	    String url = prop.getProperty("url");
	    String driver = prop.getProperty("driver");
	    String user = prop.getProperty("user");
	    String password = prop.getProperty("password");
	    
	    try {
	        Class.forName(driver);
	        mBD = DriverManager.getConnection(url, user, password);
	        mBD.setAutoCommit(true);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexi�n con la base de datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
	        System.exit(1);
	    }
	}

	public static void desconectarBD() {
		try {
			if (mBD != null && !mBD.isClosed()) {
				mBD.close();
				System.out.println("Conexi�n cerrada correctamente.");
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido un error al cerrar la conexi�n: " + e.getMessage());
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
			System.out.println("Se ha producido un error al ejecutar la inserci�n: " + e.getMessage());
			return 0;
		}
	}

	public int update(PreparedStatement ps) {
		try {
			int rows = ps.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("Se ha producido un error al ejecutar la actualizaci�n: " + e.getMessage());
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