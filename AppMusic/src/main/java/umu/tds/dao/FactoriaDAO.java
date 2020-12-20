package umu.tds.dao;



//Interfaz de Factoria DAO basada en el esquema de "TDS-Guia-Desarrollo-CasoPractico-Presentacion.pdf" de la página 34
// Se especifican las funciones y en cada claseDAO adaptadora, se implementan
public abstract class FactoriaDAO {

	
	
	
	protected FactoriaDAO() {}
	
	public abstract IAdaptadorCancionDAO getCancionDAO();
	public abstract IAdaptadorUsuarioDAO getUsuario();
	//public abstract IAdaptadorListaCancionesDAO getListaCanciones();
	
	
	
}
