package umu.tds.controlador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.dao.AdaptadorCancionDAO;
import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorUsuarioDAO;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.CatalogoCanciones;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.ListaCanciones;
import umu.tds.dominio.Usuario;

public class Controlador {

	//patron Singletonn
	private static Controlador controlador;
	private Usuario user;
	
	//Igual esto no va aquí
	private LinkedList<Cancion> cancionesLocales;
	private LinkedList<String> tiposCancion;
	//private JFrame frmReproductorDeCanciones;
	//private JTextField textURL;
	private FactoriaDAO factoriaDAO;
	
	private MediaPlayer mediaPlayer;
	private String tempPath = "temp";

	
	private Controlador() {
//necesitamos tener el factoria para registrar y eso
		try {
			factoriaDAO = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	//No me acuerdo de donde me he sacado esta función
	private void inicializarAdaptadores() {
		AdaptadorUsuarioDAO adaptadorUsuario = new AdaptadorUsuarioDAO();
		AdaptadorCancionDAO adaptadorCancion = new AdaptadorCancionDAO();
	}

	public static Controlador getControlador() {
		if(controlador==null) controlador = new Controlador();
		return controlador;
	} 
	
	public Usuario getUser() {
		return user;
	}
	
	
	public LinkedList<ListaCanciones> getListas() {
		return user.getListas();
	}
	
	public ListaCanciones getReciente() {
		return user.getRecientes();
	}
	
	
	public boolean existeUsuario(String usuario) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(usuario) != null;
	}
	
	public boolean existeEmail(String email) {
		HashMap<String, Usuario> mapa = CatalogoUsuarios.getUnicaInstancia().getMapa();
		for(Usuario u :mapa.values())
		{
			if(u.getEmail().equals(email)) return true;
		}
		return false; //si no encuentra ninguno que sea igual al que se pasa, se retorna false, no existe el email
	}
	
	public boolean existeLista(String lista) {
		return user.getListas().stream().anyMatch(x -> x.getNombre().contentEquals(lista));
	}
	
	public boolean registrar(String user, String password, String email, String nombre, String apellidos, String fechaNac) {
		if (!existeUsuario(user))
		{
			Usuario usuario = new Usuario(user, password, email, nombre, apellidos, fechaNac);
			CatalogoUsuarios.getUnicaInstancia().newUsuario(usuario);
			IAdaptadorUsuarioDAO udao = factoriaDAO.getUsuarioDAO();
			udao.createUsuario(usuario);
			return true;
		}
		else return false;
		
	}
	
	public boolean login(String user, String password) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(user);
		if(usuario == null || !usuario.getPassword().equals(password))
			return false;
		
		//
		this.user=usuario; //nos guardamos el usuario que se ha logueado en el controlador
		return true;
	}

	//está bien? basado en ejercicio 6 boletin 4
	public List<Cancion> busqueda(String nombre, String interprete, String estilo){
		
		System.out.println("Busqueda || nombre: " + nombre + " || interprete: " + interprete + "  ||  estilo: " + estilo);
		//List<Cancion> listaBusqueda = CatalogoCanciones.getUnicaInstancia().getCanciones().stream()
		List<Cancion> listaBusqueda = getCancionesLocales().stream()
											.filter(x -> nombre == "" || x.getNombre().toLowerCase().contains(nombre.toLowerCase()))  //Contains para una búsqueda "inteligente"
											.filter(x -> estilo == "" || x.getEstilo().toLowerCase().contains(estilo.toLowerCase()))  // TolowerCase para buscar sin discriminar mayusculas
											.filter(x -> interprete == "-" || x.getInterprete().toLowerCase().contains(interprete.toLowerCase())).collect(Collectors.toList());
		
		return listaBusqueda;
	}
	

	public void reproducirCancion(Cancion cancion) { //placeholder que solo guarda en recientes
		// TODO: esta línea da null pointer exception
		user.addRecientes(cancion); //Cuando se reproduce una canción se añade a la lista de recientes
		
		URL uri = null;
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
			
			//Transformación del filepath a objeto URL
			File fich = new File(cancion.getRutaFichero());
			uri = fich.toURI().toURL();
			System.out.println();
			System.setProperty("java.io.tmpdir", tempPath);
			Path mp3 = Files.createTempFile("now-playing", ".mp3");

			System.out.println(mp3.getFileName());
			try (InputStream stream = uri.openStream()) {
				Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
			}
			System.out.println("finished-copy: " + mp3.getFileName());

			Media media = new Media(mp3.toFile().toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			mediaPlayer.play();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	public void reproducirCancionPorId(int cancionId) { 
		//Esto esta mal si o también
		for(Cancion s : cancionesLocales) {
			if (s.getId().equals(cancionId))
				reproducirCancion(s);
		}
		
	}
	
	
	public void reproducirCancionPorNombre(String tituloCancion) { 
		//Esto esta mal si o también
		System.out.println("SysoutControlador.java -> intentando reproducir canción: " + tituloCancion);
		for(Cancion s : cancionesLocales) {
			if (s.getNombre().equals(tituloCancion))
				reproducirCancion(s);
		}
		
	}
	

	public void pararCancion() {
		if (mediaPlayer != null) mediaPlayer.stop();
		File directorio = new File(tempPath);
		String[] files = directorio.list();
		for (String archivo : files) {
			File fichero = new File(tempPath + File.separator + archivo);
			fichero.delete();
		}
	}
	
	public void inicializarCancionesLocales() throws IOException {
		//TODO: Esto va aquí?
		tiposCancion = new LinkedList<String>();

		File srcCanciones = new File("resources/canciones");
		File[] carpetasCanciones = srcCanciones.listFiles();
		cancionesLocales = new LinkedList<Cancion>();
		
		for (File f : carpetasCanciones) {
			File[] cancionesPorEstilo = f.listFiles();
			tiposCancion.add(f.getName());
			for (File s : cancionesPorEstilo) {
				String[] autorTitulo = s.getName().split("-");
				Cancion cancion = new Cancion(autorTitulo[1], autorTitulo[0], f.getName().toLowerCase(), s.getPath());
				cancionesLocales.add(cancion);
			}
		}
	}
	
	public LinkedList<Cancion> getCancionesLocales(){
		return cancionesLocales;
	}
	
	public LinkedList<String> getTiposCanciones(){
		return tiposCancion;
	}
	
}

	

