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
	private int id;
	


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
		id = 0; // se establece al meterlo en el servicio de persistencia;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public LinkedList<ListaCanciones> getListas() {
		return listas;
	}

	public ListaCanciones getRecientes() { //no hace falta devolver ListaReciente, pues solo cambia la implementación, y eso no afecta en nada a su uso
		return recientes;
	}

	//añadir cancion a recientes
	public void addRecientes(Cancion cancion) {
		recientes.addCancion(cancion);
	}
	
}
