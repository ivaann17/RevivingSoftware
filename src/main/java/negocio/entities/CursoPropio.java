package main.java.negocio.entities;

import java.util.*;

public class CursoPropio {

	String centro;
	EstadoCurso estado;
	TipoCurso tipo;
	private String dniDirector;
	private String dniSecretario;
	private int id;
	private String nombre;
	private int ECTS;
	private Date fechaInicio;
	private Date fechaFin;
	private double tasaMatricula;
	private int edicion;
	private String mensaje;

	public CursoPropio(String centro, EstadoCurso estado, TipoCurso tipo, String dniDirector, String dniSecretario,
			int id, String nombre, int eCTS, Date fechaInicio, Date fechaFin, double tasaMatricula, int edicion,
			String mensaje) {
		super();
		this.centro = centro;
		this.estado = estado;
		this.tipo = tipo;
		this.dniDirector = dniDirector;
		this.dniSecretario = dniSecretario;
		this.id = id;
		this.nombre = nombre;
		ECTS = eCTS;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tasaMatricula = tasaMatricula;
		this.edicion = edicion;
		this.mensaje = mensaje;
	}

	public String getCentro() {
		return centro;
	}

	public EstadoCurso getEstado() {
		return estado;
	}

	public TipoCurso getTipo() {
		return tipo;
	}

	public String getDniDirector() {
		return dniDirector;
	}

	public String getDniSecretario() {
		return dniSecretario;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getECTS() {
		return ECTS;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public double getTasaMatricula() {
		return tasaMatricula;
	}

	public int getEdicion() {
		return edicion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String toString() {
		return "ID del curso: {" + id + "}  Nombre del curso: {" + nombre + "}  Estado del curso: {" + estado
				+ "}  Edicion: {" + edicion + "}";
	}
}