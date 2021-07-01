package umu.tds.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import umu.tds.dominio.FactoriaDescuentos;
import umu.tds.dominio.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;


//Adaptador de la nterfaz de UsuarioDAO basada en el esquema de "TDS-Guia-Desarrollo-CasoPractico-Presentacion.pdf" de la página 34

//quizás abstracta
public final class AdaptadorUsuarioDAO implements IAdaptadorUsuarioDAO {

	private ServicioPersistencia servicioPersistencia;
	
	public AdaptadorUsuarioDAO() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}


	//public void create(Usuario usuario) {

		
	//}

	@Override
	public boolean deleteUsuario(Usuario usuario) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(usuario.getId());
		return servicioPersistencia.borrarEntidad(entidad);
	}

	@Override
	public Usuario getUsuario(int id) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(id);
		
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(entidad, "nombre");
		String apellidos = servicioPersistencia.recuperarPropiedadEntidad(entidad, "apellidos");
		String email = servicioPersistencia.recuperarPropiedadEntidad(entidad, "email");
		String user = servicioPersistencia.recuperarPropiedadEntidad(entidad, "user");
		String password = servicioPersistencia.recuperarPropiedadEntidad(entidad, "password");
		String fechaNacimiento = servicioPersistencia.recuperarPropiedadEntidad(entidad, "fechaNacimiento");
		String descuento = servicioPersistencia.recuperarPropiedadEntidad(entidad, "descuento");
		String premium = servicioPersistencia.recuperarPropiedadEntidad(entidad, "premium");
		LocalDate fechaNac = null;
		if(!fechaNacimiento.equals("d MMM y")) {
			fechaNac = LocalDate.parse(fechaNacimiento);
		}
		return new Usuario(nombre, apellidos, email, user, password, fechaNac, FactoriaDescuentos.getInstancia().crearDescuento(descuento), Boolean.parseBoolean(premium), id);
		
	}

	@Override
	public List<Usuario> getAllUsuarios() { //No es necesario devolver LinkedList, con List basta, porque la implementación es indiferente
		boolean existe = false;
		
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		ArrayList<Entidad> entidades = null;
		try {
			entidades = servicioPersistencia.recuperarEntidades("Usuario");
		} catch (NullPointerException e) {
			existe = true;
		}
		
		// ?? if(existe || entidades==null) return usuarios;
		
		for(Entidad ent : entidades) {
			usuarios.add(getUsuario(ent.getId()));
		}
		
		return usuarios;
	}


	@Override
	public void createUsuario(Usuario usuario) {

		// servicioPersistencia.addPropiedad(ent,"nombre", usuario.getNombre());
		LinkedList<Propiedad> propiedades = new LinkedList<Propiedad>();
		propiedades.add(new Propiedad("nombre", usuario.getNombre()));
		propiedades.add(new Propiedad("apellidos", usuario.getApellidos()));
		propiedades.add(new Propiedad("email", usuario.getEmail()));
		propiedades.add(new Propiedad("user", usuario.getUser()));
		propiedades.add(new Propiedad("password", usuario.getPassword()));
		propiedades.add(new Propiedad("fechaNacimiento", usuario.getFechaNac().toString()));
		propiedades.add(new Propiedad("descuento", usuario.getDescuento().getClass().getName()));
		propiedades.add(new Propiedad("premium", String.valueOf(usuario.isPremium())));
		//FALTAN PARÁMETROS
		System.out.println("createusuario dao premium??? :"  +  String.valueOf(usuario.isPremium()));
		
		Entidad entidad = new Entidad();
		entidad.setNombre("Usuario");
		entidad.setPropiedades(propiedades);
		
		entidad = servicioPersistencia.registrarEntidad(entidad);
		usuario.setId(entidad.getId());
	}
	
	public void hacerPremium(int id) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(id);
		servicioPersistencia.eliminarPropiedadEntidad(entidad, "premium");
		servicioPersistencia.anadirPropiedadEntidad(entidad, "premium", String.valueOf(true));
	}
}


