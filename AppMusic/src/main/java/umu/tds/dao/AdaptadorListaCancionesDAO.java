package umu.tds.dao;

import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.ListaCanciones;

public abstract class AdaptadorListaCancionesDAO {//implements FactoriaDAO{


	private ServicioPersistencia servicioPersistencia;
	
	public AdaptadorListaCancionesDAO() {
		//servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}


	public void createListaCancion(ListaCanciones listaCanciones) {

	}
	
	public boolean deleteListaCancion(ListaCanciones listaCanciones) {
		return false;
		
	}
	
	
	public ListaCanciones getListaCanciones(int id) {
		return null;
		
	}
	
	
	public List<ListaCanciones> getAllListaCanciones(){
		return null;
		
	}
}




