package umu.tds.dominio;

import java.util.HashMap;

public class CatalogoCanciones {
	
	//Patron Singleton de unica instanciaa
	private static CatalogoCanciones unicaInstancia;
	private HashMap<String, Cancion> mapaCanciones;  //mapa??? o Set
	
	private CatalogoCanciones() {
		mapaCanciones = new HashMap<String, Cancion>();
		
	}
	
	public static CatalogoCanciones getUnicaInstancia() {
		if(unicaInstancia==null) unicaInstancia= new CatalogoCanciones();
		return unicaInstancia;
	}
	
	public Cancion getCancion(String cancion) {
		return mapaCanciones.get(cancion);
	}
	
	public void newCancion(Cancion cancion) {
		mapaCanciones.put(cancion.getNombre(),cancion);
	}
	
	// ??? se puede ?
	public HashMap<String, Cancion> getMapa() {
		return new HashMap<String, Cancion>(mapaCanciones); //se devuelve una copia
	}
}
