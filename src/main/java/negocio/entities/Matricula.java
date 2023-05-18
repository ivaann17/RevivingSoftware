package main.java.negocio.entities;

import java.sql.Date;

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

	public int getID_Matricula() {
		return idMatricula;
	}

	public void setID_Matricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public ModoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(ModoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getID_Curso() {
		return idCurso;
	}

	public void setID_Curso(int idCurso) {
		this.idCurso = idCurso;
	}

	@Override
	public String toString() {
		return "Matricula [ID_Matricula=" + idMatricula + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", tipoPago=" + tipoPago + ", fecha=" + fecha + ", dni=" + dni + ", precio=" + precio + ", ID_Curso="
				+ idCurso + "]";
	}

}