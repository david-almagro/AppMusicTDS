package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Cancion;

public interface IAdaptadorCancionDAO {
	void createCancion(Cancion cancion);
	boolean deleteCancion(Cancion cancion);
	Cancion getCancion(int id);
	List<Cancion> getAllCanciones();
}
