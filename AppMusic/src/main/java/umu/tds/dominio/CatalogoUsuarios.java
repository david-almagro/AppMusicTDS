package umu.tds.dominio;

import java.util.HashMap;

public class CatalogoUsuarios {
	
	private static CatalogoUsuarios catalogo;
	private HashMap<String, Usuario> mapaUsuarios;
	
	private CatalogoUsuarios() {
		mapaUsuarios = new HashMap<String, Usuario>();
		
	}
	
	public static CatalogoUsuarios getCatalogo() {
		if(catalogo==null) catalogo = new CatalogoUsuarios();
		return catalogo;
	}
	
	public Usuario getUsuario(String user) {
		return mapaUsuarios.get(user);
	}
	
	public void newUsuario(Usuario usuario) {
		mapaUsuarios.put(usuario.getUser(),usuario);
	}
}
