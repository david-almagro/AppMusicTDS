package umu.tds.dao;

import umu.tds.dominio.Usuario;
import java.util.List;

//Interfaz de UsuarioDAO basada en el esquema de "TDS-Guia-Desarrollo-CasoPractico-Presentacion.pdf" de la página 34

public interface UsuarioDAO {
	void create(Usuario usuario);
	boolean delete(Usuario usuario);
	Usuario get(int id);
	List<Usuario> getUsuarios();
}
