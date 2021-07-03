package umu.tds.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public class CatalogoCanciones {
	private FactoriaDAO factoria;

	//Patron Singleton de unica instanciaa
	private static CatalogoCanciones unicaInstancia;
	private ArrayList<Cancion> canciones; 
	
	private CatalogoCanciones() {
		canciones = new ArrayList<Cancion>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Cancion> listaCancionesDAO = factoria.getCancionDAO().getAllCanciones();
			
			for(Cancion c : listaCancionesDAO){
				canciones.add(c);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CatalogoCanciones getUnicaInstancia() {
		if(unicaInstancia==null) unicaInstancia= new CatalogoCanciones();
		return unicaInstancia;
	}
	
	public void newCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	public List<Cancion> getCanciones() {
		return new ArrayList<Cancion>(canciones); //se devuelve una copia
	}
	
	public Set<String> getEstilos() {
		return canciones.stream().map(c -> c.getEstilo()).collect(Collectors.toSet());
	}
}
