package umu.tds.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import umu.tds.dao.AdaptadorCancionDAO;
import umu.tds.dao.AdaptadorUsuarioDAO;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorUsuarioDAO;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.CatalogoCanciones;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.Descuento;
import umu.tds.dominio.DescuentoBase;
import umu.tds.dominio.DescuentoLimitado;
import umu.tds.dominio.DescuentoJovenes;
import umu.tds.dominio.ListaCanciones;
import umu.tds.dominio.Usuario;
import umu.tds.componente.Canciones;
import umu.tds.componente.CancionesEvent;
import umu.tds.componente.CancionesListener;
import umu.tds.componente.CargadorCanciones;
//Añadido en classpath
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;


public class Controlador implements CancionesListener{

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

	private CargadorCanciones cargaCancionesXML = new CargadorCanciones();
	
	private Controlador() {
		//necesitamos tener el factoria para registrar y eso
		try {
			factoriaDAO = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		cargaCancionesXML.addCancionListener(this);
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
	
	public double getDescuento(double precio){
		return user.getDescuento().aplicarDescuento(precio);
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
	
	public boolean registrar(String user, String password, String email, String nombre, String apellidos, LocalDate fechaNac) {
		if (!existeUsuario(user))
		{
			Descuento descuentoUsuario;
			if(Period.between(fechaNac, LocalDate.now()).getYears() <= 20) {
				descuentoUsuario = new DescuentoJovenes();
			} else if(LocalDate.now().isBefore(LocalDate.of(2024, 7, 22))){
				descuentoUsuario = new DescuentoLimitado();
			} else  {
				descuentoUsuario = new DescuentoBase();
			}

			Usuario usuario = new Usuario(user, password, email, nombre, apellidos, fechaNac, descuentoUsuario);
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
		cancion.aumentarNumReproducciones();
		
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
	
	public Boolean isMediaPlayerPlaying(){
		if (this.mediaPlayer == null)
			return false;
		return this.mediaPlayer.getStatus().equals(Status.PLAYING);
	}
	

	
	public LinkedList<Cancion> getCancionesLocales(){
		return cancionesLocales;
	}
	
	public LinkedList<String> getTiposCanciones(){
		return tiposCancion;
	}
	
	public boolean crearPlaylist(String nombre, List<Cancion> canciones) {
		return user.crearPlaylist(nombre, canciones);	
	}
	
	public boolean usuarioPremium() {
		return user.isPremium();
	}
	
	public List<Cancion> top10(){
		List<Cancion> listaTop10 = cancionesLocales.stream()
			.sorted(Comparator.comparingInt(Cancion::getNumReproducciones).reversed()).limit(10).collect(Collectors.toList());
		return listaTop10;
		
	}
	
	public void exportarPDF() throws FileNotFoundException, DocumentException {
		String path = "C:\\Users\\malwt\\git\\AppMusicTDS\\Listas.pdf";
		File f = new File(path);
		if(f.exists()) f.delete();
		FileOutputStream file = new FileOutputStream(path);
		Document doc = new Document();
		PdfWriter.getInstance(doc,file);
		doc.open();
        doc.newPage();
		doc.add(new Phrase("Usuario: ".concat(user.getUser().concat("\n\n")),FontFactory.getFont(FontFactory.COURIER,25,Font.BOLD,new BaseColor(0,0,0))));
		for(ListaCanciones l:getListas()) {
			doc.add(new Phrase("Playlist: ".concat(l.getNombre().concat("\n")),FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new BaseColor(0,0,0))));
		for(Cancion c:l.getCanciones()) {
			doc.add(new Phrase("\tNombre: ".concat(c.getNombre().concat("\n")),FontFactory.getFont(FontFactory.COURIER,15,Font.NORMAL,new BaseColor(0,0,0))));
			doc.add(new Phrase("\tAutor: ".concat(c.getInterprete().concat("\n\n")),FontFactory.getFont(FontFactory.COURIER,15,Font.NORMAL,new BaseColor(0,0,0))));
		}
		doc.add(new Phrase("\n",FontFactory.getFont(FontFactory.COURIER,15,Font.BOLD,new BaseColor(0,0,0))));
		}
		
		doc.close();

		
	}
	
	public void hacerPremium() {
		user.hacerPremium();
	}

	public List<Descuento> getDescuentosAplicables() {
		LinkedList<Descuento> aplicables = new LinkedList<Descuento>();

		
		return null;
	}
	
	@Override
	public void enteradoCargaCancion(CancionesEvent arg0) {
		System.out.println("soy imbecil");
		Canciones canciones = arg0.getNuevoCanciones();
		for(umu.tds.componente.Cancion c : canciones.getCancion()) {
			
			String path = "resources/canciones";
			Path fichCancion = Paths.get(path + "/" + c.getEstilo().toUpperCase() + "/" + c.getInterprete() + "-" + c.getTitulo() + ".mp3");
			
			//Si no existe la canción
			if(!fichCancion.toFile().exists()) {
				//Si no existe la carpeta del estilo, se crea
				File theDir = new File( path + "/" + c.getEstilo().toUpperCase());
				if (!theDir.exists()){
				    theDir.mkdirs();
				}
							
				try { //Intentamos cargar la canción
					InputStream inStream = new URL(c.getURL()).openStream();
					Files.copy(inStream, fichCancion);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			Cancion cancion = new Cancion(c.getTitulo(), c.getInterprete(), c.getEstilo(), fichCancion.toString()); //nombre, interprete, estilo, rutafichero
			cancionesLocales.add(cancion);
		}	
	}
	
	public void cargarCanciones(File rutaCanciones) {
		cargarCanciones(rutaCanciones.getAbsolutePath());
	}
	
	public void cargarCanciones(String rutaCanciones) {
		cargaCancionesXML.setArchivoCanciones(rutaCanciones);
	}
	
}

	

