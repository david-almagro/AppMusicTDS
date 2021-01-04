package umu.tds.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogoCanciones {
	
	//Patron Singleton de unica instanciaa
	private static CatalogoCanciones unicaInstancia;
	private ArrayList<Cancion> canciones;  //mapa??? o Set
	
	private CatalogoCanciones() {
		canciones = new ArrayList<Cancion>();
		
	}
	
	public static CatalogoCanciones getUnicaInstancia() {
		if(unicaInstancia==null) unicaInstancia= new CatalogoCanciones();
		return unicaInstancia;
	}
	
	/*
	public Cancion getCancion(String cancion) {
		return canciones.get(cancion);
	}
	*/
	
	public void newCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	// ??? se puede ?    //devolver lista o arraylist?
	public List<Cancion> getCanciones() {
		return new ArrayList<Cancion>(canciones); //se devuelve una copia
	}
	
	public Set<String> getEstilos() {
		return canciones.stream().map(c -> c.getEstilo()).collect(Collectors.toSet());
	}
}
