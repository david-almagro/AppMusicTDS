package umu.tds.dominio;

import java.util.HashMap;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public class CatalogoUsuarios {
	private FactoriaDAO factoria;
	
	//Patron Singleton de unica instanciaa
	private static CatalogoUsuarios unicaInstancia;
	private HashMap<String, Usuario> mapaUsuarios;
	
	private CatalogoUsuarios() {
		mapaUsuarios = new HashMap<String, Usuario>();
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Usuario> listaUsuariosDAO = factoria.getUsuarioDAO().getAllUsuarios();
			
			for(Usuario u : listaUsuariosDAO){
				mapaUsuarios.put(u.getUser(),u);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
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
	
	public HashMap<String, Usuario> getMapa() {
		return new HashMap<String, Usuario>(mapaUsuarios); 
	}
}
