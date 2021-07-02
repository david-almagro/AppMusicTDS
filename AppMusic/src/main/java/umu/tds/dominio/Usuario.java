package umu.tds.dominio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String user;
	private String password;
	private String email;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNac;
	private Descuento descuento;

	private boolean premium;
	
	private LinkedList<ListaCanciones> listas;
	private ListaRecientes recientes;



	public Usuario(String user, String password, String email, String nombre, String apellidos, LocalDate fechaNac, Descuento descuento) {
		this.user = user;
		this.password = password;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.listas = new LinkedList<ListaCanciones>();
		this.recientes = new ListaRecientes("Recientes");
		this.descuento = descuento;
		this.premium = false;
		id = 0; // se establece al meterlo en el servicio de persistencia;
	}
	
	public Usuario(String user, String password, String email, String nombre, String apellidos, LocalDate fechaNac, Descuento descuento,LinkedList<ListaCanciones> listas, Boolean premium, int id) {
		this(user, password, email, nombre, apellidos, fechaNac, descuento);
		this.listas = listas;
		this.premium = premium;
		this.id = id;
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

	public boolean removeListaCancion(String nombre) {
		for(ListaCanciones lc : listas) {
			if(lc.getNombre().equals(nombre)) {
				listas.remove(lc);
				return true;
			}
		}
		return false;
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

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	
	public Descuento getDescuento() {
		return descuento;
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
	
	public boolean crearPlaylist(String nombre, List<Cancion> canciones) {
		ListaCanciones lista = new ListaCanciones(nombre);
		for(Cancion c:canciones) lista.addCancion(c);
		return listas.add(lista);
	}
	
	public void hacerPremium() {
		premium=true;
	}
	
}
