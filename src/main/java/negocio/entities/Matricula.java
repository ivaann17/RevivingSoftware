package main.java.negocio.entities;

import java.sql.Date;

public class Matricula {
	int ID_Matricula;
	String nombre;
	String apellido;
	ModoPago tipoPago;
	private java.util.Date fecha;
	String dni;
	double precio;
	int ID_Curso;

	public Matricula(int iD_Matricula, String nombre, String apellido, ModoPago tipoPago, java.util.Date fechaActual,
			String dni, double precio, int iD_Curso) {
		super();
		ID_Matricula = iD_Matricula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoPago = tipoPago;
		this.fecha = fechaActual;
		this.dni = dni;
		this.precio = precio;
		ID_Curso = iD_Curso;
	}

	public Matricula(int iD_Matricula) {
		super();
		ID_Matricula = iD_Matricula;
	}

	public int getID_Matricula() {
		return ID_Matricula;
	}

	public void setID_Matricula(int iD_Matricula) {
		ID_Matricula = iD_Matricula;
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
		return ID_Curso;
	}

	public void setID_Curso(int iD_Curso) {
		ID_Curso = iD_Curso;
	}

	@Override
	public String toString() {
		return "Matricula [ID_Matricula=" + ID_Matricula + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", tipoPago=" + tipoPago + ", fecha=" + fecha + ", dni=" + dni + ", precio=" + precio + ", ID_Curso="
				+ ID_Curso + "]";
	}

}