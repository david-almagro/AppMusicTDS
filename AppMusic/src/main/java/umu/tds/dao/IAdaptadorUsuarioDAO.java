package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Usuario;

public interface IAdaptadorUsuarioDAO{
	void createUsuario(Usuario usuario);
	boolean deleteUsuario(Usuario usuario);
	Usuario getUsuario(int id);
	List<Usuario> getAllUsuarios();
	void hacerPremium(int id);

}