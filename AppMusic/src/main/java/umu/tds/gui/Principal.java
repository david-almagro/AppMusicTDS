package umu.tds.gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;

//import umu.tds.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JToolBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;


public class Principal {
	private static final int minHeight = 400;
	private static final int minWidth = 650;

	private JFrame frame;
	private JTextField txtTitulo;
	private JTextField txtInterprete;
	private JTable tablaCanciones;
	private JTextField textField;
	private JTextField textInterpreteNuevaLista;
	private JTextField textTituloNuevaLista;
	private JTable table;
	private JTable table_2;

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
		
		//Obtención dinámica del nombre de usuario a través de controlador
		JLabel lblHola = new JLabel("Hola " + controlador.getUser().getNombre());
		
		panel_top.add(lblHola);
		lblHola.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHola.setHorizontalAlignment(SwingConstants.CENTER);
		lblHola.setAlignmentX(1.0f);
		
		JButton btnHaztePremium = new JButton("Hazte premium");
		panel_top.add(btnHaztePremium);
		btnHaztePremium.setAlignmentX(1.0f);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setAlignmentX(1.0f);
		panel_top.add(btnLogout);
		
		//Si el usuario es premium, desactivar botón de hacerse premium
		if(controlador.getUser().isPremium())
			btnHaztePremium.setEnabled(false);
    	
    	
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
    	
    	JPanel panel_Explorar_centro = new JPanel();
    	panel_Explorar_centro.setAlignmentX(0.0f);
    	panel_Explorar_centro.setAlignmentY(0.0f);
    	panel_Explorar.add(panel_Explorar_centro);
    	GridBagLayout gbl_panel_Explorar_centro = new GridBagLayout();
    	gbl_panel_Explorar_centro.columnWidths = new int[]{24, 117, 114, 123, 0, 0};
    	gbl_panel_Explorar_centro.rowHeights = new int[]{19, 0, 0, 0, 0};
    	gbl_panel_Explorar_centro.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    	gbl_panel_Explorar_centro.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
    	panel_Explorar_centro.setLayout(gbl_panel_Explorar_centro);
    	
    	txtTitulo = new JTextField();
    	txtTitulo.setText("Título");
    	GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
    	gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
    	gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtTitulo.gridx = 1;
    	gbc_txtTitulo.gridy = 0;
    	panel_Explorar_centro.add(txtTitulo, gbc_txtTitulo);
    	txtTitulo.setColumns(10);
    	
    	txtInterprete = new JTextField();
    	txtInterprete.setText("Intérprete");
    	GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
    	gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
    	gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtInterprete.gridx = 2;
    	gbc_txtInterprete.gridy = 0;
    	panel_Explorar_centro.add(txtInterprete, gbc_txtInterprete);
    	txtInterprete.setColumns(10);
    	
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Jazz", "Pop", "Electronica", "Punk"}));
    	GridBagConstraints gbc_comboBox = new GridBagConstraints();
    	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
    	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
    	gbc_comboBox.gridx = 3;
    	gbc_comboBox.gridy = 0;
    	panel_Explorar_centro.add(comboBox, gbc_comboBox);
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
    	
    	JPanel Music_Player = new JPanel();
    	GridBagConstraints gbc_Music_Player = new GridBagConstraints();
    	gbc_Music_Player.gridwidth = 3;
    	gbc_Music_Player.insets = new Insets(0, 0, 5, 5);
    	gbc_Music_Player.fill = GridBagConstraints.BOTH;
    	gbc_Music_Player.gridx = 1;
    	gbc_Music_Player.gridy = 2;
    	panel_Explorar_centro.add(Music_Player, gbc_Music_Player);
    	GridBagLayout gbl_Music_Player = new GridBagLayout();
    	gbl_Music_Player.columnWidths = new int[]{0, 0, 0, 0};
    	gbl_Music_Player.rowHeights = new int[]{0, 0, 0, 0};
    	gbl_Music_Player.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
    	gbl_Music_Player.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
    	Music_Player.setLayout(gbl_Music_Player);
    	Music_Player.setVisible(false);
    	
      	btnBuscar.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {											//TODO: Probablemente reordenar todo
    	    	Music_Player.setVisible(true);
    	    	//TODO: Lógica del boton Buscar
    		}
    	});
    	
    	
    	JScrollPane scrollPane = new JScrollPane();
    	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
    	gbc_scrollPane.gridwidth = 3;
    	gbc_scrollPane.gridheight = 2;
    	gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
    	gbc_scrollPane.fill = GridBagConstraints.BOTH;
    	gbc_scrollPane.gridx = 0;
    	gbc_scrollPane.gridy = 0;
    	Music_Player.add(scrollPane, gbc_scrollPane);
    	
    	//####################################
    	// 		todo esto no va aquí pero mola ir viendo la tabla con algo en vez de vacía
    	//####################################
    	//	TODO: Cambiar la tabla para que contenga una columna oculta con el id de la canción
    	//	para así luego pedir el id a la tabla y pedir su reproducción al controlador.
    	// 	Mejor que pedirle una canción por el título.
    	//  https://stackoverflow.com/questions/25975459/how-to-hide-the-jtable-column-data

    	LinkedList<Cancion> c = new LinkedList<Cancion>();
    	try {
			c = (LinkedList<Cancion>) controlador.inicializarCancionesLocales();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	tablaCanciones = new JTable();
    	tablaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tablaCanciones.setModel(new DefaultTableModel(
    		new String[] {
    			"Título", "Intérprete"
    		}, 0
    	));
    	
    	//Añadir filas por canción
    	DefaultTableModel modeloTablaCanciones = (DefaultTableModel) tablaCanciones.getModel();
    	for(Cancion s : c) {
    		modeloTablaCanciones.addRow(new Object[] {s.getNombre(), s.getInterprete()});
    	}
    	
    	//####################################
    	// 		mover todo esto
    	//####################################
    			
    	scrollPane.setViewportView(tablaCanciones);
    	
    	//Botón retroceder
    	JButton btnBack = new JButton();
    	GridBagConstraints gbc_btnBack = new GridBagConstraints();
    	gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
    	gbc_btnBack.insets = new Insets(0, 0, 0, 5);
    	gbc_btnBack.gridx = 0;
    	gbc_btnBack.gridy = 2;
    	Music_Player.add(btnBack, gbc_btnBack);
    	btnBack.setIcon(new ImageIcon("resources/iconos/Anterior.png"));
    	
    	//Botón Reproducir / Parar
    	JButton btnPlay = new JButton();
    	GridBagConstraints gbc_btnPlay = new GridBagConstraints();
    	gbc_btnPlay.fill = GridBagConstraints.HORIZONTAL;
    	gbc_btnPlay.insets = new Insets(0, 0, 0, 5);
    	gbc_btnPlay.gridx = 1;
    	gbc_btnPlay.gridy = 2;
    	Music_Player.add(btnPlay, gbc_btnPlay);
    	ImageIcon playIcon = new ImageIcon("resources/iconos/Play.png");
    	ImageIcon stopIcon = new ImageIcon("resources/iconos/Stop.png");
    	btnPlay.setIcon(playIcon);

    	//On Click
    	btnPlay.addMouseListener(new MouseAdapter() {
			boolean isPlaying = false;
    		@Override
			public void mouseClicked(MouseEvent e) {					

    			if(isPlaying) {
					isPlaying = false;
					btnPlay.setIcon(playIcon);
					controlador.pararCancion();
				}
				else {
					isPlaying = true;
					btnPlay.setIcon(stopIcon);
					int selectedRow = tablaCanciones.getSelectedRow();
					System.out.println("Cancion seleccionada : " + modeloTablaCanciones.getValueAt(selectedRow, 0));  //TODO: quitar el sysout (?)

					//TODO: en un futuro se pedirá "reproducción por id" al controlador
					// La idea es tener los id escondidos dentro de la tabla.
					// estas siguientes lineas están mal pero representan funcionalidad por ahora.
					Cancion playedSong;
					try {
						playedSong = controlador.inicializarCancionesLocales().get(selectedRow);
					} catch (IOException e1) {
						playedSong = null;
						e1.printStackTrace();
					} 
					controlador.reproducirCancion(controlador.getUser(), playedSong);

				}
			}
		});
    	
    	// Botón Siguiente
    	JButton btnForward = new JButton();
    	GridBagConstraints gbc_btnForward = new GridBagConstraints();
    	gbc_btnForward.fill = GridBagConstraints.HORIZONTAL;
    	gbc_btnForward.gridx = 2;
    	gbc_btnForward.gridy = 2;
    	Music_Player.add(btnForward, gbc_btnForward);
    	btnForward.setIcon(new ImageIcon("resources/iconos/Siguiente.png"));
        panel_Explorar.setOpaque(true);
        
        JPanel panel_NuevaLista = new JPanel();
        panelCentral.add(panel_NuevaLista, "card_NuevaLista");
        panel_NuevaLista.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel_NuevaLista.add(panel, BorderLayout.NORTH);
        
        textField = new JTextField();
        textField.setText("Lista Nueva");
        textField.setColumns(25);
        panel.add(textField);
        
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
        		String nuevalistaNombre = textField.getText();
        		if (controlador.existeLista(textField.getText())) {
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
        
        textInterpreteNuevaLista = new JTextField();
        GridBagConstraints gbc_textInterpreteNuevaLista = new GridBagConstraints();
        gbc_textInterpreteNuevaLista.insets = new Insets(0, 0, 5, 5);
        gbc_textInterpreteNuevaLista.fill = GridBagConstraints.HORIZONTAL;
        gbc_textInterpreteNuevaLista.gridx = 1;
        gbc_textInterpreteNuevaLista.gridy = 1;
        panel_CreacionLista.add(textInterpreteNuevaLista, gbc_textInterpreteNuevaLista);
        textInterpreteNuevaLista.setColumns(10);
        
        textTituloNuevaLista = new JTextField();
        GridBagConstraints gbc_textTituloNuevaLista = new GridBagConstraints();
        gbc_textTituloNuevaLista.insets = new Insets(0, 0, 5, 5);
        gbc_textTituloNuevaLista.fill = GridBagConstraints.HORIZONTAL;
        gbc_textTituloNuevaLista.gridx = 2;
        gbc_textTituloNuevaLista.gridy = 1;
        panel_CreacionLista.add(textTituloNuevaLista, gbc_textTituloNuevaLista);
        textTituloNuevaLista.setColumns(10);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Jazz", "Pop", "Rock", "Punk", "Electronica"}));
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
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        	},
        	new String[] {
        		"New column", "New column"
        	}
        ));
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
        
        table_2 = new JTable();
        table_2.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        		{null, null},
        	},
        	new String[] {
        		"New column", "New column"
        	}
        ));
        scrollPane_2.setViewportView(table_2);
        
        JButton btnleftleft = new JButton("<<");
        GridBagConstraints gbc_btnleftleft = new GridBagConstraints();
        gbc_btnleftleft.insets = new Insets(0, 0, 5, 5);
        gbc_btnleftleft.gridx = 3;
        gbc_btnleftleft.gridy = 3;
        panel_CreacionLista.add(btnleftleft, gbc_btnleftleft);
        
        JButton btnRightRight = new JButton(">>");
        GridBagConstraints gbc_btnRightRight = new GridBagConstraints();
        gbc_btnRightRight.insets = new Insets(0, 0, 5, 5);
        gbc_btnRightRight.gridx = 3;
        gbc_btnRightRight.gridy = 4;
        panel_CreacionLista.add(btnRightRight, gbc_btnRightRight);
        
        JButton btnAceptarNuevaLista = new JButton("Aceptar");
        GridBagConstraints gbc_btnAceptarNuevaLista = new GridBagConstraints();
        gbc_btnAceptarNuevaLista.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptarNuevaLista.gridx = 2;
        gbc_btnAceptarNuevaLista.gridy = 7;
        panel_CreacionLista.add(btnAceptarNuevaLista, gbc_btnAceptarNuevaLista);
        
        JButton btnCancelarNuevaLista = new JButton("Cancelar");
        GridBagConstraints gbc_btnCancelarNuevaLista = new GridBagConstraints();
        gbc_btnCancelarNuevaLista.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancelarNuevaLista.gridx = 4;
        gbc_btnCancelarNuevaLista.gridy = 7;
        panel_CreacionLista.add(btnCancelarNuevaLista, gbc_btnCancelarNuevaLista);
    	panel_NuevaLista.setVisible(false);
		
    	
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
    	    	Music_Player.setVisible(false);

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
		btnExplorar.setIcon(new ImageIcon("resources\\iconos\\Nueva_Lista.png"));
		toolBar.add(btnNuevaLista);
		
		JButton btnReciente = new JButton("Reciente");
		btnReciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnExplorar.setIcon(new ImageIcon("resources\\iconos\\Reciente.png"));
		toolBar.add(btnReciente);
		
		JButton btnMisListas = new JButton("Mis listas");
		btnMisListas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnExplorar.setIcon(new ImageIcon("resources\\iconos\\Mis_Listas.png"));
		toolBar.add(btnMisListas);
		
    	


	}
}
