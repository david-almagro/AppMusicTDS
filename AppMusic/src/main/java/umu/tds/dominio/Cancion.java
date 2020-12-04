package umu.tds.dominio;

public class Cancion {
	private String nombre;
	private String interprete;
	private String estilo;
	private String rutaFichero;
	private int numReproducciones;
	
	public Cancion(String nombre, String interprete, String estilo, String rutafichero) {
		this.nombre = nombre;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutafichero;
		numReproducciones = 0;
	
	
	}

	public String getNombre() {
		return nombre;
	}

	public String getInterprete() {
		return interprete;
	}

	public String getEstilo() {
		return estilo;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
}
