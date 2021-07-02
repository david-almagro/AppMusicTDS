package umu.tds.dominio;

public class Cancion {
	private String nombre;
	private String interprete;
	private String estilo;
	private String rutaFichero;
	private int numReproducciones;
	private int id; 
	
	public Cancion(String nombre, String interprete, String estilo, String rutafichero) {
		this.nombre = nombre;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutafichero;
		numReproducciones = 0;
		id = 0;  // se establece al meterlo en el servicio de persistencia;
	}
	
	public Cancion(String nombre, String interprete, String estilo, String rutafichero, int id, int numReproducciones) {
		this(nombre,interprete,estilo,rutafichero);
		this.numReproducciones = numReproducciones;
		this.id = id;
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
	
	public void aumentarNumReproducciones() {
		numReproducciones++;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
