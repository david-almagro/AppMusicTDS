package umu.tds.controlador;

import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.Usuario;

public class Controlador {

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
		return CatalogoUsuarios.getCatalogo().getUsuario(usuario) != null;
	}
	
	public boolean registrar(String user, String password, String email, String nombre, String apellidos, String fechaNac) {
		if (!existeUsuario(user))
		{
			Usuario usuario = new Usuario(user, password, email, nombre, apellidos, fechaNac);
			CatalogoUsuarios.getCatalogo().newUsuario(usuario);
			return true;
		}
		else return false;
		
	}
	
	public boolean login(String user, String password) {
		Usuario usuario = CatalogoUsuarios.getCatalogo().getUsuario(user);
		if(usuario ==null || !usuario.getPassword().equals(password))
			return false;
		
		this.user=usuario;
		return true;
	}

}
