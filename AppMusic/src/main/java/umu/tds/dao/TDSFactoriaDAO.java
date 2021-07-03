package umu.tds.dao;

public class TDSFactoriaDAO extends FactoriaDAO {

	public TDSFactoriaDAO() {
	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return new AdaptadorCancionDAO();
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return new AdaptadorUsuarioDAO();
	}

	@Override
	public IAdaptadorListaCancionesDAO getListaCancionesDAO() {
		return new AdaptadorListaCancionesDAO();
	}
}
