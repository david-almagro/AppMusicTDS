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
		
		//try.. catch DAOException
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Usuario> listaUsuariosDAO = factoria.getUsuarioDAO().getAllUsuarios();
			
			for(Usuario u : listaUsuariosDAO){
				mapaUsuarios.put(u.getUser(),u);
				//por id?
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
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
	
	// ??? se puede ?
	public HashMap<String, Usuario> getMapa() {
		return new HashMap<String, Usuario>(mapaUsuarios); //se devuelve una copia
	}
}
