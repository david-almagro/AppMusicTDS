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

//import umu.tds.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Principal {
	private static final int minHeight = 400;
	private static final int minWidth = 650;

	private JFrame frame;
	private JTextField txtTitulo;
	private JTextField txtInterprete;
	private JTable table_1;

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
    	JLayeredPane layeredPane = new JLayeredPane();
    	frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
    	layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.Y_AXIS));
    	
    	JPanel panel_1 = new JPanel();
    	layeredPane.add(panel_1);
		panel_1.setVisible(false);
    	panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.PAGE_AXIS));
    	
    	String[] columns = {"título", "intérprete"};
    	DefaultTableModel modelo = new DefaultTableModel();
    	for (String s : columns) {modelo.addColumn(s);}
    	
    	JPanel panel_2 = new JPanel();
    	panel_2.setAlignmentX(0.0f);
    	panel_2.setAlignmentY(0.0f);
    	panel_1.add(panel_2);
    	GridBagLayout gbl_panel_2 = new GridBagLayout();
    	gbl_panel_2.columnWidths = new int[]{24, 117, 114, 123, 0, 0};
    	gbl_panel_2.rowHeights = new int[]{19, 0, 0, 0, 0};
    	gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
    	gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
    	panel_2.setLayout(gbl_panel_2);
    	
    	txtTitulo = new JTextField();
    	txtTitulo.setText("Título");
    	GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
    	gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
    	gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtTitulo.gridx = 1;
    	gbc_txtTitulo.gridy = 0;
    	panel_2.add(txtTitulo, gbc_txtTitulo);
    	txtTitulo.setColumns(10);
    	
    	txtInterprete = new JTextField();
    	txtInterprete.setText("Intérprete");
    	GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
    	gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
    	gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtInterprete.gridx = 2;
    	gbc_txtInterprete.gridy = 0;
    	panel_2.add(txtInterprete, gbc_txtInterprete);
    	txtInterprete.setColumns(10);
    	
    	String[] EjemploEstilos = {"-","Rock", "Pop", "Electrónica", "Trap Refachero"};
    	JComboBox<String> comboBox = new JComboBox<String>(EjemploEstilos);											//TODO: JComboBox con estilos automáticos | ¿Quiźa función getEstilos()?
    	GridBagConstraints gbc_comboBox = new GridBagConstraints();
    	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
    	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
    	gbc_comboBox.gridx = 3;
    	gbc_comboBox.gridy = 0;
    	panel_2.add(comboBox, gbc_comboBox);  	
    	JButton btnBuscar = new JButton("Buscar");
  
    	GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
    	gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
    	gbc_btnBuscar.gridwidth = 2;
    	gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
    	gbc_btnBuscar.gridx = 1;
    	gbc_btnBuscar.gridy = 1;
    	panel_2.add(btnBuscar, gbc_btnBuscar);
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
    	gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
    	gbc_btnCancelar.gridx = 3;
    	gbc_btnCancelar.gridy = 1;
    	panel_2.add(btnCancelar, gbc_btnCancelar);
    	
    	JPanel Music_Player = new JPanel();
    	GridBagConstraints gbc_Music_Player = new GridBagConstraints();
    	gbc_Music_Player.gridwidth = 3;
    	gbc_Music_Player.insets = new Insets(0, 0, 5, 5);
    	gbc_Music_Player.fill = GridBagConstraints.BOTH;
    	gbc_Music_Player.gridx = 1;
    	gbc_Music_Player.gridy = 2;
    	panel_2.add(Music_Player, gbc_Music_Player);
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
    	
    	table_1 = new JTable();
    	table_1.setModel(new DefaultTableModel(
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
    			"Título", "Intérprete"
    		}
    	));
    	scrollPane.setViewportView(table_1);
    	
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
			public void mouseClicked(MouseEvent e) {										//< - - - - TODO: Lógica para reproducir canciones y tal...

    			if(isPlaying) {
					isPlaying = false;
					btnPlay.setIcon(playIcon);

				}
				else {
					isPlaying = true;
					btnPlay.setIcon(stopIcon);

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
        panel_1.setOpaque(true);

        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{0};
        gbl_panel_3.rowHeights = new int[]{0};
        gbl_panel_3.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{Double.MIN_VALUE};
        panel_3.setLayout(gbl_panel_3);

    	
    	//Botonera izquierda
    	JPanel panel = new JPanel();
    	frame.getContentPane().add(panel, BorderLayout.WEST);
    	GridBagLayout gbl_panel = new GridBagLayout();
    	gbl_panel.columnWidths = new int[]{80, 0};
    	gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
    	gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
    	gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    	panel.setLayout(gbl_panel);
    	
    	//Botón "Explorar"
    	JButton btnNewButton = new JButton("Explorar");
    	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    	gbc_btnNewButton.fill = GridBagConstraints.BOTH;
    	gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
    	gbc_btnNewButton.gridx = 0;
    	gbc_btnNewButton.gridy = 0;
    	panel.add(btnNewButton, gbc_btnNewButton);
    	btnNewButton.setIcon(new ImageIcon("resources/iconos/Explorar.png"));
    	//On Click
    	btnNewButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			panel_1.setVisible(true);
    		}
    	});
    	
    	//Nueva lista"
		JButton btnNewButton_1 = new JButton("Nueva lista");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon("resources/iconos/Nueva_lista.png"));
		//On Click
    	btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
    			panel_1.setVisible(false);
			}
		});
    	
    	//Botón "Reciente"
		JButton btnNewButton_2 = new JButton("Reciente");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 2;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon("resources/iconos/Reciente.png"));
		//On Click
    	btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
    			panel_1.setVisible(false);
			}
		});
    	
    	//Botón "Mis listas"
		JButton btnNewButton_3 = new JButton("Mis listas");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 3;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		btnNewButton_3.setIcon(new ImageIcon("resources/iconos/Mis_listas.png"));
		//On Click
    	btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
    			panel_1.setVisible(false);
			}
		});
    	


	}
}
