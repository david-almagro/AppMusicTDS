package umu.tds.dao;



//Interfaz de Factoria DAO basada en el esquema de "TDS-Guia-Desarrollo-CasoPractico-Presentacion.pdf" de la página 34
// Se especifican las funciones y en cada claseDAO adaptadora, se implementan
public abstract class FactoriaDAO {

	
	protected FactoriaDAO() {}
	private static FactoriaDAO unicaInstancia = null;
	

	public static FactoriaDAO getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { 
				unicaInstancia=(FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
		} 
		return unicaInstancia;
	}
	

	public static FactoriaDAO getInstancia() throws DAOException{
		return getInstancia("umu.tds.dao.TDSFactoriaDAO");
		//return getInstancia("TDSFactoriaDAO");

	}

	public abstract IAdaptadorCancionDAO getCancionDAO();
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
	//public abstract IAdaptadorListaCancionesDAO getListaCanciones();

	
}
