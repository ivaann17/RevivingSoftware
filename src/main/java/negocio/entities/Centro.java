package main.java.negocio.entities;

import java.util.*;

public class Centro {

	Collection<CursoPropio> cursoPropios;
	Collection<ProfesorUCLM> plantilla;
	public Centro(String nombre) {
		super();
		this.nombre = nombre;
	}
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	private String localizacion;
	private int attribute;

}