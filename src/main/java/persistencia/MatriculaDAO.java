	package main.java.persistencia;

	import java.sql.Date;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Vector;

	import main.java.negocio.entities.*;

	public class MatriculaDAO {
		GestorBD gestorBD = new GestorBD();

		public int crearNuevaMatricula(Matricula matricula) {

			int id = 0;
			try {
				String sql = "INSERT INTO matricula (ID, nombre, apellido, DNI, precio, tipo_pago, curso, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				ps.setInt(1, matricula.getID_Matricula());
				ps.setString(2, matricula.getNombre());
				ps.setString(3, matricula.getApellido());
				ps.setString(4, matricula.getDni());
				ps.setDouble(5, matricula.getPrecio());
				ps.setString(6, matricula.getTipoPago().toString());
				ps.setInt(7, matricula.getID_Curso());
				ps.setDate(8, new java.sql.Date(matricula.getFecha().getTime()));
				gestorBD.insert(ps);

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id;
		}
		
		public boolean existeMatricula (int curso, String dni) throws Exception {

			int id = 0;
			try {
				String sql = "SELECT COUNT(*) FROM matricula WHERE curso = ? AND DNI = ? ";


				PreparedStatement ps = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				ps.setInt(1, curso);
				ps.setString(2, dni);
				

				ResultSet rs = gestorBD.operation(ps);
				if (rs.next()) {
				    int count = rs.getInt(1);
				    if (count > 0) {
				        return true;
				    } else {
				        return false;
				    }
				} 

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
			
		}
		/*public static void obtenerCursoDeMatricula(int idMatricula) {
		    try {
		        // Crear la consulta
		        String consulta = "SELECT cursos.* FROM matricula " +
		                          "JOIN cursos ON matricula.curso = cursos.id " +
		                          "WHERE matriculas.id = ?";
		        
		        // Crear el objeto PreparedStatement
		        PreparedStatement ps = conexion.prepareStatement(consulta);
		        ps.setInt(1, idMatricula);

		        // Ejecutar la consulta y obtener los resultados
		        ResultSet rs = ps.executeQuery();

		        // Procesar los resultados
		        if (rs.next()) {
		            int idCurso = rs.getInt("id");
		            String nombreCurso = rs.getString("nombre");
		            int duracionHoras = rs.getInt("duracion_horas");

		            // Hacer algo con los datos del curso obtenidos
		            System.out.println("El estudiante está matriculado en el curso con id " + idCurso + " y nombre " + nombreCurso + " (duración: " + duracionHoras + " horas)");
		        } else {
		            System.out.println("No se encontró ningún curso para la matrícula con id " + idMatricula);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}*/

}
