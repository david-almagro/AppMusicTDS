package umu.tds.dominio;

public class Cancion {
	private String nombre;
	private Interprete interprete;
	private EstiloMusical estilo;
	private String rutaFichero;
	private int numReproducciones;
	
	public Cancion(String nombre, Interprete interprete, EstiloMusical estilo, String rutafichero) {
		this.nombre = nombre;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutafichero;
		numReproducciones = 0;
	
	
	}

	public String getNombre() {
		return nombre;
	}

	public Interprete getInterprete() {
		return interprete;
	}

	public EstiloMusical getEstilo() {
		return estilo;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
}
