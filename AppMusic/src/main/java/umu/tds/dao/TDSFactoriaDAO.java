package umu.tds.dao;

public class TDSFactoriaDAO extends FactoriaDAO {

	public TDSFactoriaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return new AdaptadorCancionDAO();
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return new AdaptadorUsuarioDAO();
		//es abstracta ?
	}
	
	//@Override
	//public IAdaptadorListaCancionesDAO getListaCancionesDAO() {
	//	return new AdaptadorListaCancionesDAO();
	//}

}
