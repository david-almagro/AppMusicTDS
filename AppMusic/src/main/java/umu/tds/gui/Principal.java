package umu.tds.gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import javax.swing.JScrollPane;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.ListaCanciones;

//import umu.tds.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JToolBar;
import javax.swing.JFileChooser;


import pulsador.Luz;

public class Principal {
	private static final int minHeight = 400;
	private static final int minWidth = 650;

	private JFrame frame;
	private JTextField txtTitulo;
	private JTextField txtInterprete;
	private JTextField nombreNuevaLista;
	private JTextField textInterpreteNuevaLista;
	private JTextField textTituloNuevaLista;
	private JTable table;
	private JTable table_2;
	private JTable tablaPlaylists;
	private JTable tablaTop10;

    private ArrayList<Cancion> listaSeleccion;
    private ArrayList<Cancion> listaSeleccion2;
    private ArrayList<ListaCanciones> listaPlaylists;
    private ArrayList<Cancion> listaPlaylistCanciones;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	public void hacerVisible() {
		//custom
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//obtención del controlador
		Controlador controlador = Controlador.getControlador(); 
		
		try {
			controlador.inicializarCancionesLocales();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, minWidth, minHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.setMinimumSize(new Dimension(minWidth, minHeight));

		JPanel panel_top = new JPanel();
		frame.getContentPane().add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		Luz luz = new Luz();
		luz.setEncendido(false);
		luz.addEncendidoListener(ev -> {
			if(luz.isEncendido()) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				    "XML files", "xml");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(chooser);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Cargando canciones de: " +
							chooser.getSelectedFile());
					Controlador.getControlador().cargarCanciones(chooser.getSelectedFile().getAbsolutePath());
				}
				luz.setEncendido(false);
			}
		});
		panel_top.add(luz);
		
		//Obtención dinámica del nombre de usuario a través de controlador
		JLabel lblHola = new JLabel("Hola " + controlador.getUser().getNombre());
		
		panel_top.add(lblHola);
		lblHola.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHola.setHorizontalAlignment(SwingConstants.CENTER);
		lblHola.setAlignmentX(1.0f);
		

		JButton btnHaztePremium = new JButton("Hazte premium");
		panel_top.add(btnHaztePremium);
		btnHaztePremium.setAlignmentX(1.0f);
		btnHaztePremium.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			//Estilo musical 		
				frame.dispose();
				Premium premium = new Premium();
				premium.hacerVisible();
    		}
    	});
    	
		
		
		//Añadida funcionalidad logout
		JButton btnLogout = new JButton("Logout");
		btnLogout.setAlignmentX(1.0f);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controlador.getControlador().pararCancion();
				frame.dispose();
				Login l = new Login();
				l.hacerVisible();
			}
		});
		panel_top.add(btnLogout);
		
		//Si el usuario es premium, desactivar botón de hacerse premium

    	
    	//Layerede Panel central

		JPanel panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
    	CardLayout cardLayout = new CardLayout();
    	panelCentral.setLayout(cardLayout);
    	
    	JPanel panel_Explorar = new JPanel();
    	panelCentral.add(panel_Explorar, "card_Explorar");
		panel_Explorar.setVisible(false);
    	panel_Explorar.setLayout(new BoxLayout(panel_Explorar, BoxLayout.PAGE_AXIS));
    	
    	String[] columns = {"título", "intérprete"};
    	DefaultTableModel modelo = new DefaultTableModel();
    	for (String s : columns) {modelo.addColumn(s);}
    	
    	DefaultTableModel modelo2 = new DefaultTableModel();
    	for (String s : columns) {modelo2.addColumn(s);}
    	
    	DefaultTableModel modeloLista = new DefaultTableModel();
    	modeloLista.addColumn("Lista");
    	
    	DefaultTableModel modeloTop10 = new DefaultTableModel();{
    	modeloTop10.addColumn("Canción"); 
    	modeloTop10.addColumn("Reproducciones");
    	}


    	JPanel panel_Explorar_centro = new JPanel();
    	panel_Explorar_centro.setAlignmentX(0.0f);
    	panel_Explorar_centro.setAlignmentY(0.0f);
    	panel_Explorar.add(panel_Explorar_centro);
    	GridBagLayout gbl_panel_Explorar_centro = new GridBagLayout();
    	gbl_panel_Explorar_centro.columnWidths = new int[]{24, 117, 114, 123, 0, 0};
    	gbl_panel_Explorar_centro.rowHeights = new int[]{34, 0, 224, 0, 0};
    	gbl_panel_Explorar_centro.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    	gbl_panel_Explorar_centro.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
    	panel_Explorar_centro.setLayout(gbl_panel_Explorar_centro);
    	
    	txtTitulo = new JTextField();
    	txtTitulo.setToolTipText("Título");
    	GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
    	gbc_txtTitulo.anchor = GridBagConstraints.NORTH;
    	gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
    	gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtTitulo.gridx = 1;
    	gbc_txtTitulo.gridy = 0;
    	panel_Explorar_centro.add(txtTitulo, gbc_txtTitulo);
    	txtTitulo.setColumns(10);
    	
    	txtInterprete = new JTextField();
    	txtInterprete.setToolTipText("Intérprete");
    	GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
    	gbc_txtInterprete.anchor = GridBagConstraints.NORTH;
    	gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
    	gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtInterprete.gridx = 2;
    	gbc_txtInterprete.gridy = 0;
    	panel_Explorar_centro.add(txtInterprete, gbc_txtInterprete);
    	txtInterprete.setColumns(10);
    	
    	
    	JComboBox<String> comboBox_TipoCanciones = new JComboBox<String>();
    	for(String tipoCancion : controlador.getTiposCanciones()) {
    		comboBox_TipoCanciones.addItem(tipoCancion);
    	}
    	
    	GridBagConstraints gbc_comboBox_TipoCanciones = new GridBagConstraints();
    	gbc_comboBox_TipoCanciones.anchor = GridBagConstraints.NORTH;
    	gbc_comboBox_TipoCanciones.insets = new Insets(0, 0, 5, 5);
    	gbc_comboBox_TipoCanciones.fill = GridBagConstraints.HORIZONTAL;
    	gbc_comboBox_TipoCanciones.gridx = 3;
    	gbc_comboBox_TipoCanciones.gridy = 0;
    	panel_Explorar_centro.add(comboBox_TipoCanciones, gbc_comboBox_TipoCanciones);
    	JButton btnBuscar = new JButton("Buscar");
  
    	GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
    	gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
    	gbc_btnBuscar.gridwidth = 2;
    	gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
    	gbc_btnBuscar.gridx = 1;
    	gbc_btnBuscar.gridy = 1;
    	panel_Explorar_centro.add(btnBuscar, gbc_btnBuscar);
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
    	gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
    	gbc_btnCancelar.gridx = 3;
    	gbc_btnCancelar.gridy = 1;
    	panel_Explorar_centro.add(btnCancelar, gbc_btnCancelar);
    	
    	MusicPlayer musicPlayer_Explorar = new MusicPlayer();
    	musicPlayer_Explorar.setCanciones(controlador.getCancionesLocales()); //TODO: tratar las listas de canciones bien, no así 
    	
    	GridBagConstraints gbc_musicPlayer_Explorar = new GridBagConstraints();
    	gbc_musicPlayer_Explorar.gridwidth = 3;
    	gbc_musicPlayer_Explorar.insets = new Insets(0, 0, 5, 5);
    	gbc_musicPlayer_Explorar.fill = GridBagConstraints.BOTH;
    	gbc_musicPlayer_Explorar.gridx = 1;
    	gbc_musicPlayer_Explorar.gridy = 2;
    	panel_Explorar_centro.add(musicPlayer_Explorar, gbc_musicPlayer_Explorar);
    	
    	//TODO: boton buscar
      	btnBuscar.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			//Estilo musical 			
    			musicPlayer_Explorar.setCanciones(
    					controlador.busqueda(txtTitulo.getText(),
											txtInterprete.getText(),
									    	comboBox_TipoCanciones.getSelectedItem().toString()));
    		}
    	});
    	
        panel_Explorar.setOpaque(true);
        
        JPanel panel_NuevaLista = new JPanel();
        panelCentral.add(panel_NuevaLista, "card_NuevaLista");
        panel_NuevaLista.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel_NuevaLista.add(panel, BorderLayout.NORTH);
        
        nombreNuevaLista = new JTextField();
        nombreNuevaLista.setText("Lista Nueva");
        nombreNuevaLista.setColumns(25);
        panel.add(nombreNuevaLista);
        
        JButton CrearLista = new JButton("Crear");
        panel.add(CrearLista);
        
        JPanel panel_CreacionLista = new JPanel();
        panel_NuevaLista.add(panel_CreacionLista, BorderLayout.CENTER);
        GridBagLayout gbl_panel_CreacionLista = new GridBagLayout();
        gbl_panel_CreacionLista.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_CreacionLista.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_CreacionLista.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_CreacionLista.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_CreacionLista.setLayout(gbl_panel_CreacionLista);
		panel_CreacionLista.setVisible(false);

        CrearLista.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//Creamos las listas 
        		//listaSeleccion = new ArrayList<Cancion>();
        		listaSeleccion2 = new ArrayList<Cancion>();

        		
        		
    			//Vaciamos ambos modelos
    			while (modelo.getRowCount() > 0) {
    				modelo.removeRow(0); 
    			}
    			while (modelo2.getRowCount() > 0) {
    				modelo2.removeRow(0);
    			}
        		
        		String nuevalistaNombre = nombreNuevaLista.getText();
        		if (controlador.existeLista(nombreNuevaLista.getText())) {
        			JOptionPane.showMessageDialog(frame, "Ya existe una lista con ese nombre", "Error, nombre de lista no válido", JOptionPane.ERROR_MESSAGE);
        		}
        		else {
					int creacionLista = JOptionPane.showConfirmDialog(frame,"¿Crear la lista \"" + nuevalistaNombre +"\"?" ,"Nueva lista", JOptionPane.YES_NO_CANCEL_OPTION);
					if(creacionLista == JOptionPane.OK_OPTION) {
						panel_CreacionLista.setVisible(true);
					}
        		}
        	}
        });
        
        textTituloNuevaLista = new JTextField();
        GridBagConstraints gbc_textTituloNuevaLista = new GridBagConstraints();
        gbc_textTituloNuevaLista.insets = new Insets(0, 0, 5, 5);
        gbc_textTituloNuevaLista.fill = GridBagConstraints.HORIZONTAL;
        gbc_textTituloNuevaLista.gridx = 1;
        gbc_textTituloNuevaLista.gridy = 1;
        panel_CreacionLista.add(textTituloNuevaLista, gbc_textTituloNuevaLista);
        textTituloNuevaLista.setColumns(10);
         
        //Título e intérprete estaban al revés
        textInterpreteNuevaLista = new JTextField();
        GridBagConstraints gbc_textInterpreteNuevaLista = new GridBagConstraints();
        gbc_textInterpreteNuevaLista.insets = new Insets(0, 0, 5, 5);
        gbc_textInterpreteNuevaLista.fill = GridBagConstraints.HORIZONTAL;
        gbc_textInterpreteNuevaLista.gridx = 2;
        gbc_textInterpreteNuevaLista.gridy = 1;
        panel_CreacionLista.add(textInterpreteNuevaLista, gbc_textInterpreteNuevaLista);
        textInterpreteNuevaLista.setColumns(10);
        
        //$$CooooomOOO?
        JComboBox<String> comboBox_1 = new JComboBox<String>();
    	for(String tipoCancion : controlador.getTiposCanciones()) {
    		comboBox_1.addItem(tipoCancion);        //Cargar los tipos de canciones de forma dinámica
    	}        
       
        GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
        gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_1.gridx = 3;
        gbc_comboBox_1.gridy = 1;
        panel_CreacionLista.add(comboBox_1, gbc_comboBox_1);
        
        JButton BuscarNuevaLista = new JButton("Buscar");
        GridBagConstraints gbc_BuscarNuevaLista = new GridBagConstraints();
        gbc_BuscarNuevaLista.gridwidth = 2;
        gbc_BuscarNuevaLista.insets = new Insets(0, 0, 5, 5);
        gbc_BuscarNuevaLista.gridx = 4;
        gbc_BuscarNuevaLista.gridy = 1;
        panel_CreacionLista.add(BuscarNuevaLista, gbc_BuscarNuevaLista);

        
        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.gridwidth = 2;
        gbc_scrollPane_1.gridheight = 5;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 1;
        gbc_scrollPane_1.gridy = 2;
        panel_CreacionLista.add(scrollPane_1, gbc_scrollPane_1); 


        table = new JTable(modelo);
        scrollPane_1.setViewportView(table);

        
        //TODO: Falta que se vacíen al cancelar o crear nueva lista

        BuscarNuevaLista.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			
    			//Vaciamos el primero modelo y su lista
    			while (modelo.getRowCount() > 0) {
    				modelo.removeRow(0);
    			}
    	        listaSeleccion = new ArrayList<Cancion>();
    	        
    	        //listaSeleccion2 = new ArrayList<Cancion>(); 
    			//comboBox_TipoCanciones.getSelectedItem(); 
    			String titulo = textTituloNuevaLista.getText();
    			String interprete = textInterpreteNuevaLista.getText();
    			String estilo = comboBox_1.getSelectedItem().toString().toLowerCase();
    			List<Cancion> listaBusqueda = controlador.busqueda(titulo, interprete, estilo);
    	        for(Cancion c : listaBusqueda) {
    	        	Vector<String> v = new Vector<String>();
    	        	v.add(c.getNombre());
    	        	v.add(c.getInterprete());
    	            modelo.addRow(v);
    	            listaSeleccion.add(c);
    	            //de esta forma tendrán el mismo orden y con el mismo índice

    	        }
    	        table = new JTable(modelo);
    	        
    	        scrollPane_1.setViewportView(table);

    		}
    	});
        
        scrollPane_1.setViewportView(table);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.gridwidth = 2;
        gbc_scrollPane_2.gridheight = 5;
        gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 4;
        gbc_scrollPane_2.gridy = 2;
        panel_CreacionLista.add(scrollPane_2, gbc_scrollPane_2);
        
        //Creamos el model con las columnas y lo metemos en la tabla, posteriormente se añadirán con los >> y <<
       // DefaultTableModel model2 = new DefaultTableModel();
        //model2.addColumn("Título");
        //model2.addColumn("Intérprete");
        table_2 = new JTable(modelo2);

        scrollPane_2.setViewportView(table_2);
        
        JButton btnleftleft = new JButton("<<");
        GridBagConstraints gbc_btnleftleft = new GridBagConstraints();
        gbc_btnleftleft.insets = new Insets(0, 0, 5, 5);
        gbc_btnleftleft.gridx = 3;
        gbc_btnleftleft.gridy = 3;
        panel_CreacionLista.add(btnleftleft, gbc_btnleftleft);
        btnleftleft.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			
    			int row = table_2.getSelectedRow();
    			if(row>-1) {
    			String title = (String) table_2.getValueAt(row,0);
    			String interp = (String) table_2.getValueAt(row,1);
	        	Vector<String> v = new Vector<String>();
	        	v.add(title);
	        	v.add(interp);
	            modelo.addRow(v);
	            modelo2.removeRow(row);


	            //se saca de la misma posición de la lista
	            listaSeleccion.add(listaSeleccion2.get(row)); //addLast el que cogemos de la posición "row"
	            listaSeleccion2.remove(row);
    			}

    		}
    	});
        
        
        JButton btnRightRight = new JButton(">>");
        GridBagConstraints gbc_btnRightRight = new GridBagConstraints();
        gbc_btnRightRight.insets = new Insets(0, 0, 5, 5);
        gbc_btnRightRight.gridx = 3;
        gbc_btnRightRight.gridy = 4;
        panel_CreacionLista.add(btnRightRight, gbc_btnRightRight);
        btnRightRight.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			
    			int row = table.getSelectedRow();
    			if(row>-1) {
    			String title = (String) table.getValueAt(row,0);
    			String interp = (String) table.getValueAt(row,1);
	        	Vector<String> v = new Vector<String>();
	        	v.add(title);
	        	v.add(interp);
	            modelo2.addRow(v);
	            modelo.removeRow(row);

	            //se saca de la misma posición de la lista
	            listaSeleccion2.add(listaSeleccion.get(row)); //addLast el que cogemos de la posición "row"
	            listaSeleccion.remove(row);
    			}
    		}
    	});
        
        JButton btnAceptarNuevaLista = new JButton("Aceptar");
        GridBagConstraints gbc_btnAceptarNuevaLista = new GridBagConstraints();
        gbc_btnAceptarNuevaLista.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptarNuevaLista.gridx = 2;
        gbc_btnAceptarNuevaLista.gridy = 7;
        panel_CreacionLista.add(btnAceptarNuevaLista, gbc_btnAceptarNuevaLista);
        btnAceptarNuevaLista.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			
    			//PRUEBA; se printea la playlist cread
    			for(Cancion c:listaSeleccion2) {
    			System.out.printf(c.getNombre());
    			System.out.printf(" - ");
    			System.out.printf(c.getInterprete());
    			System.out.printf("\n");
    			}
    			JOptionPane.showMessageDialog(frame, "Lista creada satisfactoriamente", "Lista creada", JOptionPane.INFORMATION_MESSAGE);
    			controlador.crearPlaylist(nombreNuevaLista.getText(),listaSeleccion2);
				panel_CreacionLista.setVisible(false);

    		}
    	});
        
        JButton btnCancelarNuevaLista = new JButton("Cancelar");
        GridBagConstraints gbc_btnCancelarNuevaLista = new GridBagConstraints();
        gbc_btnCancelarNuevaLista.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancelarNuevaLista.gridx = 4;
        gbc_btnCancelarNuevaLista.gridy = 7;
        panel_CreacionLista.add(btnCancelarNuevaLista, gbc_btnCancelarNuevaLista);
        
        btnCancelarNuevaLista.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			
				panel_CreacionLista.setVisible(false);

    		}
    	});

        
        JPanel panel_Reciente = new JPanel();
        panelCentral.add(panel_Reciente, "panel_Reciente");
        panel_Reciente.setLayout(new BorderLayout(0, 0));
        
        MusicPlayer musicPlayer_Recientes = new MusicPlayer();
        musicPlayer_Recientes.setListaTitulo("Canciones Recientes");
        
        panel_Reciente.add(musicPlayer_Recientes);
    	panel_NuevaLista.setVisible(false);
		
        JPanel panel_MisListas = new JPanel();
        panelCentral.add(panel_MisListas, "card_MisListas");
        panel_MisListas.setLayout(new BorderLayout(0, 0));
        
        
        JPanel panel_Listas = new JPanel();
        panel_MisListas.add(panel_Listas, BorderLayout.CENTER);
        GridBagLayout gbl_panel_Listas = new GridBagLayout();
        gbl_panel_Listas.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_Listas.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_Listas.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_Listas.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_Listas.setLayout(gbl_panel_Listas);
        panel_Listas.setVisible(false);
    	

        
        JScrollPane scrollPaneListas = new JScrollPane();
        GridBagConstraints gbc_scrollPaneListas = new GridBagConstraints();
        gbc_scrollPaneListas.gridwidth = 2;
        gbc_scrollPaneListas.gridheight = 5;
        gbc_scrollPaneListas.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPaneListas.fill = GridBagConstraints.BOTH;
        gbc_scrollPaneListas.gridx = 1;
        gbc_scrollPaneListas.gridy = 2;
        panel_Listas.add(scrollPaneListas, gbc_scrollPaneListas); 
        
    	MusicPlayer musicPlayer_listas = new MusicPlayer();
    	GridBagConstraints gbc_musicPlayer_listas = new GridBagConstraints();
        gbc_musicPlayer_listas.gridwidth = 2;
        gbc_musicPlayer_listas.gridheight = 5;
        gbc_musicPlayer_listas.insets = new Insets(0, 0, 5, 5);
        gbc_musicPlayer_listas.fill = GridBagConstraints.BOTH;
        gbc_musicPlayer_listas.gridx = 4;
        gbc_musicPlayer_listas.gridy = 2;
        panel_Listas.add(musicPlayer_listas, gbc_musicPlayer_listas);

        
		/*
		 * GridBagConstraints gbc_musicPlayer_listas = new GridBagConstraints();
		 * gbc_musicPlayer_listas.gridwidth = 2; gbc_musicPlayer_listas.gridheight = 5;
		 * gbc_musicPlayer_listas.insets = new Insets(0, 0, 5, 5);
		 * gbc_musicPlayer_listas.fill = GridBagConstraints.BOTH;
		 * gbc_musicPlayer_listas.gridx = 4; gbc_musicPlayer_listas.gridy = 2;
		 * panel_CreacionLista.add(musicPlayer_listas, gbc_musicPlayer_listas);
		 */
        
        JButton btnCargarPlaylists = new JButton("Cargar");
        GridBagConstraints gbc_btnCargarPlaylists = new GridBagConstraints();
        gbc_btnCargarPlaylists.insets = new Insets(0, 0, 0, 5);
        gbc_btnCargarPlaylists.gridx = 2;
        gbc_btnCargarPlaylists.gridy = 7;
        panel_Listas.add(btnCargarPlaylists, gbc_btnCargarPlaylists);
        btnCargarPlaylists.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			
    			listaPlaylistCanciones = new ArrayList<Cancion>();

    			JOptionPane.showMessageDialog(frame, "Lista cargada", "Error, nombre de lista no válido", JOptionPane.INFORMATION_MESSAGE);
    			
    			
    			int row = tablaPlaylists.getSelectedRow();
    			System.out.printf("Playlist numero %d\n",row);
    			if(row>-1) {
    			ListaCanciones lista = listaPlaylists.get(row);
    			musicPlayer_listas.setCanciones(lista.getCanciones());    			
    			}
    			
    			//tablaCanciones = new JTable(modeloLista2);
    	        
    	        //scrollPaneListas2.setViewportView(tablaCanciones);


    		}
    	});

        table = new JTable(modelo);
        scrollPane_1.setViewportView(table);
        
        
        
        //TOP10
    	
    	JPanel panel_Top10 = new JPanel();
    	panelCentral.add(panel_Top10, "card_Top10");
    	panel_Top10.setVisible(false);
    	panel_Top10.setLayout(new BoxLayout(panel_Top10, BoxLayout.PAGE_AXIS));
    	
    	JPanel panel_Top10_centro = new JPanel();
    	panel_Top10_centro.setAlignmentX(0.0f);
    	panel_Top10_centro.setAlignmentY(0.0f);
    	panel_Top10.add(panel_Top10_centro);
    	GridBagLayout gbl_panel_Top10_centro = new GridBagLayout();
    	gbl_panel_Top10_centro.columnWidths = new int[]{24, 117, 114, 123, 0, 0};
    	gbl_panel_Top10_centro.rowHeights = new int[]{34, 0, 224, 0, 0};
    	gbl_panel_Top10_centro.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    	gbl_panel_Top10_centro.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
    	panel_Top10_centro.setLayout(gbl_panel_Top10_centro);
    	
    	
    	MusicPlayer musicPlayer_Top10 = new MusicPlayer();
    	
    	GridBagConstraints gbc_musicPlayer_Top10 = new GridBagConstraints();
    	gbc_musicPlayer_Top10.gridwidth = 3;
    	gbc_musicPlayer_Top10.insets = new Insets(0, 0, 5, 5);
    	gbc_musicPlayer_Top10.fill = GridBagConstraints.BOTH;
    	gbc_musicPlayer_Top10.gridx = 1;
    	gbc_musicPlayer_Top10.gridy = 2;
    	panel_Top10_centro.add(musicPlayer_Top10, gbc_musicPlayer_Top10);
    	
    	//BOTONERA
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setToolTipText("");
		toolBar.setOrientation(SwingConstants.VERTICAL);
		frame.getContentPane().add(toolBar, BorderLayout.WEST);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panelCentral, "card_Explorar");
				//TODO: Botón explorar
			}
		});
		btnExplorar.setIcon(new ImageIcon("resources\\iconos\\Explorar.png"));
		toolBar.add(btnExplorar);
		
		JButton btnNuevaLista = new JButton("Nueva lista");
		btnNuevaLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panelCentral, "card_NuevaLista");
				panel_CreacionLista.setVisible(false);
			}
		});
		btnNuevaLista.setIcon(new ImageIcon("resources\\iconos\\Nueva_Lista.png"));
		toolBar.add(btnNuevaLista);
		
		JButton btnReciente = new JButton("Reciente");
		btnReciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panelCentral, "panel_Reciente");
		        musicPlayer_Recientes.setCanciones(controlador.getReciente().getCanciones()); //TODO: tratar bien las listas de canciones
				//panel_CreacionLista.setVisible(false);
			}
		});

		btnReciente.setIcon(new ImageIcon("resources\\iconos\\Reciente.png"));
		toolBar.add(btnReciente);
		

    	
		JButton btnMisListas = new JButton("Mis listas");
		btnMisListas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panelCentral, "card_MisListas");
				//panel_MisListas.setVisible(true);
				panel_Listas.setVisible(true);
				
    			while (modeloLista.getRowCount() > 0) {
    				modeloLista.removeRow(0);
    			}
				List<ListaCanciones> lista = controlador.getListas();
				listaPlaylists = new ArrayList<ListaCanciones>();
				for(ListaCanciones l : lista) {
    	        	Vector<String> v = new Vector<String>();
    	        	v.add(l.getNombre());
    	            modeloLista.addRow(v);
    	            listaPlaylists.add(l);
    	            //de esta forma tendrán el mismo orden y con el mismo índice

    	        }
    	        tablaPlaylists = new JTable(modeloLista);
    	        scrollPaneListas.setViewportView(tablaPlaylists);

				
			}
		});
		btnMisListas.setIcon(new ImageIcon("resources\\iconos\\Mis_Listas.png"));
		toolBar.add(btnMisListas);
		
		
		JButton btnTop10 = new JButton("Top 10 canciones");
		btnTop10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
    			while (modelo.getRowCount() > 0) {
    				modelo.removeRow(0);
    			}
				cardLayout.show(panelCentral, "card_Top10");
				//panel_MisListas.setVisible(true);
				//panel_Listas.setVisible(true);
				List<Cancion> l = controlador.top10();
				musicPlayer_Top10.setCanciones(l);
				/*
				List<Cancion> l = controlador.top10();
				for(Cancion c:l) {
		        	Vector<String> v = new Vector<String>();
		        	v.add(c.getNombre());
		        	v.add(String.valueOf(c.getNumReproducciones()));
					modeloTop10.addRow(v);
				}
		        tablaTop10 = new JTable(modeloTop10);
		        panel_Top10_centro.setViewportView(tablaTop10);
				*/
				
			}
		});
		btnTop10.setIcon(new ImageIcon("resources\\iconos\\Mis_Listas.png"));
		toolBar.add(btnTop10);
		
		JButton btnPDF = new JButton("Generar PDF");
		btnPDF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.exportarPDF();
					JOptionPane.showMessageDialog(frame, "PDF creado satisfactoriamente","Exportación a PDF realizada", JOptionPane.INFORMATION_MESSAGE);

				} catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//cardLayout.show(panelCentral, "card_PDF");
				//panel_MisListas.setVisible(true);
				//panel_Listas.setVisible(true);
				
			}
		});
		btnPDF.setIcon(new ImageIcon("resources\\iconos\\Mis_Listas.png"));
		toolBar.add(btnPDF);
		
		
		
		if(controlador.getUser().isPremium())
			btnHaztePremium.setEnabled(false);
		else {
			btnTop10.setEnabled(false);
			btnPDF.setEnabled(false);
		}


	}
}
