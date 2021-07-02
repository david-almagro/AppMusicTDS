package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.ListaCanciones;

public interface IAdaptadorListaCancionesDAO {
	
	void createListaCancion(ListaCanciones listaCanciones);
	boolean deleteListaCancion(ListaCanciones listaCanciones);
	ListaCanciones getListaCanciones(int id);
	void updateListaCanciones(ListaCanciones lista);
	List<ListaCanciones> getAllListaCanciones();
}
