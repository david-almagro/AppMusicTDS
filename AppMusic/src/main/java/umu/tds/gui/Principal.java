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
import java.util.List;

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


public class Principal {
	private static final int minHeight = 400;
	private static final int minWidth = 650;

	private JFrame frame;
	private JTextField txtTitulo;
	private JTextField txtInterprete;
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
    	gbl_panel_Explorar_centro.rowHeights = new int[]{34, 0, 224, 0, 0};
    	gbl_panel_Explorar_centro.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    	gbl_panel_Explorar_centro.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
    	panel_Explorar_centro.setLayout(gbl_panel_Explorar_centro);
    	
    	txtTitulo = new JTextField();
    	txtTitulo.setText("Título");
    	GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
    	gbc_txtTitulo.anchor = GridBagConstraints.NORTH;
    	gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
    	gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtTitulo.gridx = 1;
    	gbc_txtTitulo.gridy = 0;
    	panel_Explorar_centro.add(txtTitulo, gbc_txtTitulo);
    	txtTitulo.setColumns(10);
    	
    	txtInterprete = new JTextField();
    	txtInterprete.setText("Intérprete");
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
    			//comboBox_TipoCanciones.getSelectedItem(); 
    			String titulo = txtTitulo.getText();
    			String interprete = txtInterprete.getText();
    			String estilo = comboBox_TipoCanciones.getSelectedItem().toString().toLowerCase();
    			List<Cancion> listaBusqueda = controlador.busqueda(titulo, interprete, estilo);
		        musicPlayer_Explorar.setCanciones(listaBusqueda); //TODO: tratar bien las listas de canciones

    		}
    	});
      	
      	btnCancelar.addActionListener(new ActionListener() {     										
    		public void actionPerformed(ActionEvent e) {
    			//Estilo musical
    			//comboBox_TipoCanciones.getSelectedItem();
		        musicPlayer_Explorar.setCanciones(controlador.getCancionesLocales()); //TODO: tratar bien las listas de canciones

    		}
    	});
    	
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
        
        JPanel panel_Reciente = new JPanel();
        panelCentral.add(panel_Reciente, "panel_Reciente");
        panel_Reciente.setLayout(new BorderLayout(0, 0));
        
        MusicPlayer musicPlayer_Recientes = new MusicPlayer();
        musicPlayer_Recientes.setListaTitulo("Canciones Recientes");
        
        panel_Reciente.add(musicPlayer_Recientes);
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
				//TODO: Botón explorar
			}
		});
		btnExplorar.setIcon(new ImageIcon("resources\\iconos\\Explorar.png"));
		toolBar.add(btnExplorar);
		
		JButton btnNuevaLista = new JButton("Nueva lista");
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
				cardLayout.show(panelCentral, "panel_Reciente");
		        musicPlayer_Recientes.setCanciones(controlador.getReciente().getCanciones()); //TODO: tratar bien las listas de canciones
				panel_CreacionLista.setVisible(false);
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
