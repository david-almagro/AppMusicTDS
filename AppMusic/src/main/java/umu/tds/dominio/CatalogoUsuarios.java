package umu.tds.dominio;

import java.util.HashMap;

public class CatalogoUsuarios {
	
	//Patron Singleton de unica instanciaa
	private static CatalogoUsuarios unicaInstancia;
	private HashMap<String, Usuario> mapaUsuarios;
	
	private CatalogoUsuarios() {
		mapaUsuarios = new HashMap<String, Usuario>();
		
	}
	
	public static CatalogoUsuarios getUnicaInstancia() {
		if(unicaInstancia==null) unicaInstancia= new CatalogoUsuarios();
		return unicaInstancia;
	}
	
	public Usuario getUsuario(String user) {
		return mapaUsuarios.get(user);
	}
	
	public void newUsuario(Usuario usuario) {
		mapaUsuarios.put(usuario.getUser(),usuario);
	}
	
	// ??? se puede ?
	public HashMap<String, Usuario> getMapa() {
		return new HashMap<String, Usuario>(mapaUsuarios); //se devuelve una copia
	}
}
