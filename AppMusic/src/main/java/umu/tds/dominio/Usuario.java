package umu.tds.dominio;

import java.util.ArrayList;

public class Usuario {
	
	private String user;
	private String password;
	
	private String email;
	private String nombre;
	private String apellidos;
	private String fechaNac;
	
	private ArrayList<ListaCanciones> listas;
	private ListaCanciones recientes;


	public Usuario(String user, String password, String email, String nombre, String apellidos, String fechaNac) {
		this.user = user;
		this.password = password;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.listas = new ArrayList<ListaCanciones>();
		this.recientes = new ListaCanciones("Recientes");
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getFechaNac() {
		return fechaNac;
	}
	
	public ArrayList<ListaCanciones> getListas() {
		return listas;
	}

	public ListaCanciones getRecientes() {
		return recientes;
	}
	
	
}
