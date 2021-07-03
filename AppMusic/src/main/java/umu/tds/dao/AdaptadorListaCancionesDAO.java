package umu.tds.dao;

import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.ListaCanciones;

public class AdaptadorListaCancionesDAO implements IAdaptadorListaCancionesDAO{

	private final String NOMBRE = "nombre";
	private final String CANCIONES = "cancion";

	private ServicioPersistencia servicioPersistencia;
	
	public AdaptadorListaCancionesDAO() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}


	public void createListaCancion(ListaCanciones listaCanciones) {
		LinkedList<Propiedad> propiedades = new LinkedList<Propiedad>();
		propiedades.add(new Propiedad(NOMBRE, listaCanciones.getNombre()));

			//String s = listaCanciones.getCanciones().stream().map(c -> String.valueOf(c.getId()).reduce("", (c1, c2) -> c1.))));
		String listaIdCanciones = listaCanciones.getCanciones().stream()
				.map(c -> c.getId().toString())
				.reduce("", (c1, c2) -> c1.concat(" ".concat(c2)).trim());
			
		propiedades.add(new Propiedad(CANCIONES, listaIdCanciones));

		
		Entidad entidad = new Entidad();
		entidad.setNombre("ListaCanciones");
		entidad.setPropiedades(propiedades);
		entidad = servicioPersistencia.registrarEntidad(entidad);
		listaCanciones.setId(entidad.getId());
	}
	
	public boolean deleteListaCancion(ListaCanciones listaCanciones) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(listaCanciones.getId());
		return servicioPersistencia.borrarEntidad(entidad);
	}
	
	
	public ListaCanciones getListaCanciones(int id) {
		//obtenemos datos
		Entidad entidad = servicioPersistencia.recuperarEntidad(id);
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(entidad, NOMBRE);
		String cancionesId = servicioPersistencia.recuperarPropiedadEntidad(entidad, CANCIONES);
		LinkedList<Cancion> canciones = new LinkedList<>();
		
		//Por cada id de canción asociado
		if(cancionesId != null) {
			System.out.println("AdaptadorListaCanciones::GetListaCanciones\n" + "lista cancion carga from Dao: ");
			for(String c : cancionesId.trim().split(" ")) {
				System.out.println(c);
				try { //lo metemos en la lista
					if(!c.equals("")) {
						Cancion cancionBd = umu.tds.dao.FactoriaDAO.getInstancia().getCancionDAO().getCancion(Integer.parseInt(c));
						if (cancionBd != null)
							canciones.add(cancionBd);
					}
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} //creamos objeto y devolvemos
		}

		return new ListaCanciones(nombre, canciones, id);
	}
	
	
	public void updateListaCanciones(ListaCanciones lista) {
		Entidad entidad = servicioPersistencia.recuperarEntidad(lista.getId());
		servicioPersistencia.eliminarPropiedadEntidad(entidad, NOMBRE);
		servicioPersistencia.anadirPropiedadEntidad(entidad, NOMBRE, lista.getNombre());
		servicioPersistencia.eliminarPropiedadEntidad(entidad, CANCIONES);
		for(Cancion c : lista.getCanciones()) {
			servicioPersistencia.anadirPropiedadEntidad(entidad, CANCIONES, c.getId().toString());
		}
		
	}
	
	public List<ListaCanciones> getAllListaCanciones(){
		return null;
	}
}




