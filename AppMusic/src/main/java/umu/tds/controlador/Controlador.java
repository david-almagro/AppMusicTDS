package umu.tds.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.CatalogoCanciones;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.Usuario;

public class Controlador {

	//patron Singletonn
	private static Controlador controlador;
	private Usuario user;
	
	private Controlador() {
		
	}
	
	public static Controlador getControlador() {
		if(controlador==null) controlador = new Controlador();
		return controlador;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public boolean existeUsuario(String usuario) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(usuario) != null;
	}
	
	public boolean existeEmail(String email) {
		HashMap<String, Usuario> mapa = CatalogoUsuarios.getUnicaInstancia().getMapa();
		for(Usuario u :mapa.values())
		{
			if(u.getEmail().equals(email)) return true;
		}
		return false; //si no encuentra ninguno que sea igual al que se pasa, se retorna false, no existe el email
	}
	
	public boolean registrar(String user, String password, String email, String nombre, String apellidos, String fechaNac) {
		if (!existeUsuario(user))
		{
			Usuario usuario = new Usuario(user, password, email, nombre, apellidos, fechaNac);
			CatalogoUsuarios.getUnicaInstancia().newUsuario(usuario);
			return true;
		}
		else return false;
		
	}
	
	public boolean login(String user, String password) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(user);
		if(usuario ==null || !usuario.getPassword().equals(password))
			return false;
		
		this.user=usuario;
		return true;
	}

	//está bien? basado en ejercicio 6 boletin 4
	public List<Cancion> busqueda(String nombre, String interprete, String estilo){
		List<Cancion> listaBusqueda = CatalogoCanciones.getUnicaInstancia().getCanciones().stream()
											.filter(x -> nombre == null || x.getNombre().contentEquals(nombre))
											.filter(x -> estilo == null || x.getNombre().contentEquals(estilo))
											.filter(x -> interprete == null || x.getNombre().contentEquals(interprete)).collect(Collectors.toList());
		return listaBusqueda;
	}
	
}
