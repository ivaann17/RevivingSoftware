package main.java.negocio.entities;

public class Matricula {
	int idMatricula;
	String nombre;
	String apellido;
	ModoPago tipoPago;
	private java.util.Date fecha;
	String dni;
	double precio;
	int idCurso;

	public Matricula(int idMatricula, String nombre, String apellido, ModoPago tipoPago, java.util.Date fechaActual,
			String dni, double precio, int idCurso) {
		super();
		this.idMatricula = idMatricula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoPago = tipoPago;
		this.fecha = fechaActual;
		this.dni = dni;
		this.precio = precio;
		this.idCurso = idCurso;
	}

	public Matricula(int idMatricula) {
		super();
		this.idMatricula = idMatricula;
	}

	public int getIDMatricula() {
		return idMatricula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public ModoPago getTipoPago() {
		return tipoPago;
	}

	public java.util.Date getFecha() {
		return fecha;
	}

	public String getDni() {
		return dni;
	}

	public double getPrecio() {
		return precio;
	}

	public int getIDCurso() {
		return idCurso;
	}

	@Override
	public String toString() {
		return "Matricula [ID_Matricula=" + idMatricula + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", tipoPago=" + tipoPago + ", fecha=" + fecha + ", dni=" + dni + ", precio=" + precio + ", ID_Curso="
				+ idCurso + "]";
	}

}