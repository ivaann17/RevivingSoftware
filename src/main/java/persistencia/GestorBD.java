package main.java.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class GestorBD {
	//Instancia 
	protected static GestorBD mInstancia = null;
	// Conexion con la base de datos
	public static Connection mBD;
	// Identificador ODBC de la base de datos
	private static String url = "jdbc:mysql://db4free.net:3306/revivingsoftware";
	// Driven para conectar con bases de datos MySQL
	private static String driver= "com.mysql.cj.jdbc.Driver";
	private static String user= "ivaann17_";
	private static String password="Kikasuperbruja1";
	
		
	public static void conectarBD() {
		try {
			Class.forName(driver);
			mBD = DriverManager.getConnection(url, user, password);
			mBD.setAutoCommit(true);
		        
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se ha podido establecer la conexion con la base de datos.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			 System.exit(1);
		}
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
	        for(int i=1; i<=columns; i++) {
	            try {
	                v.add(res.getObject(i));
	            }
	            catch(SQLException ex) {
	                continue;
	            }
	        }
	        vectoradevolver.add(v);
	    }
	    ps.close();
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



	public int update(String sql) {
	    try {
	        Statement stmt = mBD.createStatement();
	        int rows = stmt.executeUpdate(sql);
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


	public void operation(String sql) {
	    try {
	        Statement stmt = mBD.createStatement();
	        stmt.execute(sql);
	    } catch (SQLException e) {
	        System.out.println("Se ha producido un error al ejecutar la operación: " + e.getMessage());
	    }
	}

}