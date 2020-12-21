package umu.tds.dominio;

public class ListaRecientes extends ListaCanciones {

	public ListaRecientes(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addCancion(Cancion cancion) {  //Borra la canción en el índice 0 (solo si está llena) y mete una nueva
		if((canciones.size()==10)&&!(canciones.contains(cancion)))
			canciones.remove(0);
		canciones.add(cancion);
	}

}
