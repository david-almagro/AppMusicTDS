package umu.tds.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import umu.tds.dominio.FactoriaDescuentos;
import umu.tds.dominio.ListaCanciones;
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
		String listasCanciones = servicioPersistencia.recuperarPropiedadEntidad(entidad, "listaCanciones");
		
		LinkedList<ListaCanciones> listaListas = new LinkedList<ListaCanciones>();
		if(listasCanciones != null) {
			for(String l : listasCanciones.trim().split(" ")) {
				try {
					if(!l.equals("")) {
						listaListas.add(umu.tds.dao.FactoriaDAO.getInstancia().getListaCancionesDAO().getListaCanciones(Integer.parseInt(l)));
					}
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		LocalDate fechaNac = null;
		if(!fechaNacimiento.equals("d MMM y")) {
			fechaNac = LocalDate.parse(fechaNacimiento);
		}
		return new Usuario(nombre, apellidos, email, user, password, fechaNac, FactoriaDescuentos.getInstancia().crearDescuento(descuento),listaListas, Boolean.parseBoolean(premium), id);
		
	}

	@Override
	public List<Usuario> getAllUsuarios() { //No es necesario devolver LinkedList, con List basta, porque la implementación es indiferente
		
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		ArrayList<Entidad> entidades = null;

		entidades = servicioPersistencia.recuperarEntidades("Usuario");

				
		for(Entidad ent : entidades) {
			usuarios.add(getUsuario(ent.getId()));
		}
		
		return usuarios;
	}


	@Override
	public void createUsuario(Usuario usuario) {

		//Añadimos las listas de canciones antes de añadir el usuario
		for(ListaCanciones l : usuario.getListas()) {
			try {
				FactoriaDAO.getInstancia().getListaCancionesDAO().createListaCancion(l);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		LinkedList<Propiedad> propiedades = new LinkedList<Propiedad>();
		propiedades.add(new Propiedad("nombre", usuario.getNombre()));
		propiedades.add(new Propiedad("apellidos", usuario.getApellidos()));
		propiedades.add(new Propiedad("email", usuario.getEmail()));
		propiedades.add(new Propiedad("user", usuario.getUser()));
		propiedades.add(new Propiedad("password", usuario.getPassword()));
		propiedades.add(new Propiedad("fechaNacimiento", usuario.getFechaNac().toString()));
		propiedades.add(new Propiedad("descuento", usuario.getDescuento().getClass().getName()));
		propiedades.add(new Propiedad("premium", String.valueOf(usuario.isPremium())));
		String listaString = usuario.getListas().stream().map(lc -> String.valueOf(lc.getId())).reduce("", (c1, c2) -> c1.concat(" ".concat(c2)).trim());													
		propiedades.add(new Propiedad("listaCanciones", listaString));
		System.out.println("Lista en create usuario lol del dao saes: " + listaString);
		Entidad entidad = new Entidad();
		entidad.setNombre("Usuario");
		entidad.setPropiedades(propiedades);
		
		entidad = servicioPersistencia.registrarEntidad(entidad);
		usuario.setId(entidad.getId());
	}
	
	public void updateUsuario(Usuario usuario) {
		this.deleteUsuario(usuario);
		this.createUsuario(usuario);
	}
	
	public void hacerPremium(int id) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(id);
		servicioPersistencia.eliminarPropiedadEntidad(entidad, "premium");
		servicioPersistencia.anadirPropiedadEntidad(entidad, "premium", String.valueOf(true));
	}
}


