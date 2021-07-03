package umu.tds.dominio;

import java.util.LinkedList;

public class ListaCanciones {
	private int id;
	private String nombre;
	protected LinkedList<Cancion> canciones;
	
	public ListaCanciones(String nombre) {
		this.nombre = nombre;
		this.canciones = new LinkedList<Cancion>();
		this.id = 0;
		//Se le pasa una canción inicial?
	}
	
	public ListaCanciones(String nombre, LinkedList<Cancion> canciones, int id) {
		this(nombre);
		this.canciones = canciones;
		this.id = id;
	}
	
	public LinkedList<Cancion> getCanciones() {
		return new LinkedList<Cancion>(canciones);
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	public void setCanciones(LinkedList<Cancion> canciones) {
		this.canciones = canciones;
	}
}
