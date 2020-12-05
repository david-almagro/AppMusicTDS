package umu.tds.dominio;

import java.util.ArrayList;
import java.util.LinkedList;

public class Usuario {
	
	private String user;
	private String password;
	
	private String email;
	private String nombre;
	private String apellidos;
	private String fechaNac;
	
	private boolean premium;
	
	private LinkedList<ListaCanciones> listas;
	private ListaRecientes recientes;


	public Usuario(String user, String password, String email, String nombre, String apellidos, String fechaNac) {
		this.user = user;
		this.password = password;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.listas = new LinkedList<ListaCanciones>();
		this.recientes = new ListaRecientes("Recientes");
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
	
	public LinkedList<ListaCanciones> getListas() {
		return listas;
	}

	public ListaCanciones getRecientes() { //no hace falta devolver ListaReciente, pues solo cambia la implementación, y eso no afecta en nada a su uso
		return recientes;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	//añadir cancion a recientes
	public void addRecientes(Cancion cancion) {
		recientes.addCancion(cancion);
	}
	
}
