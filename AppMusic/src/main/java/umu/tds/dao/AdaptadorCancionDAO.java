package umu.tds.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;

public class AdaptadorCancionDAO implements IAdaptadorCancionDAO {


	private ServicioPersistencia servicioPersistencia;
	
	public AdaptadorCancionDAO() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		
	}

	@Override
	public void createCancion(Cancion cancion) {
		
		// servicioPersistencia.addPropiedad(ent,"nombre", cancion.getNombre());
		LinkedList<Propiedad> propiedades = new LinkedList<Propiedad>();
		propiedades.add(new Propiedad("nombre", cancion.getNombre()));
		propiedades.add(new Propiedad("interprete", cancion.getInterprete()));
		propiedades.add(new Propiedad("estilo", cancion.getEstilo()));
		propiedades.add(new Propiedad("rutaFichero", cancion.getRutaFichero()));
		// TODO: numReproducciones??
		propiedades.add(new Propiedad("numReproducciones", String.valueOf(cancion.getNumReproducciones())));
		System.out.println("AdaptadorCancion::createCancion(int id) -> " + String.valueOf(cancion.getNumReproducciones()));
		Entidad entidad = new Entidad();
		entidad.setNombre("cancion");
		entidad.setPropiedades(propiedades);
		
		entidad = servicioPersistencia.registrarEntidad(entidad);
		cancion.setId(entidad.getId());
		System.out.println(" - Cancion creada: " + cancion.getNombre() + "id: " + entidad.getId());
		
	}

	@Override
	public boolean deleteCancion(Cancion cancion) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(cancion.getId());
		return servicioPersistencia.borrarEntidad(entidad);
	}

	@Override
	public Cancion getCancion(int id) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(id);
		
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(entidad, "nombre");
		String interprete = servicioPersistencia.recuperarPropiedadEntidad(entidad, "interprete");
		String estilo = servicioPersistencia.recuperarPropiedadEntidad(entidad, "estilo");
		String rutaFichero = servicioPersistencia.recuperarPropiedadEntidad(entidad, "rutaFichero");
		
		String numRepro = servicioPersistencia.recuperarPropiedadEntidad(entidad, "numReproducciones");
		
		//System.out.println("AdaptadorCancion::getCancion(int id) -> id{" + id +"}" + nombre + "/"+ interprete +"/"+ estilo + "/"+ rutaFichero+"/");
		
		int numReproducciones = 0 ;
		if (numRepro != null) {
			numReproducciones = Integer.parseInt(numRepro);
		}
		if(id==0)
			return null;
		return new Cancion(nombre, interprete, estilo, rutaFichero, id, numReproducciones);
	}

	@Override
	public List<Cancion> getAllCanciones() {
		LinkedList<Cancion> cancions = new LinkedList<Cancion>();
		ArrayList<Entidad> entidades = servicioPersistencia.recuperarEntidades("cancion");
		
		for(Entidad ent : entidades) {
			cancions.add(getCancion(ent.getId()));
		}
		
		return cancions;
	}
}
