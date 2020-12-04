package umu.tds.gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import umu.tds.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;


public class Principal {
	private static final int minHeight = 300;
	private static final int minWidth = 450;

	private JFrame frame;

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
		frame.setBounds(100, 100, 450, 300);
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
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{80, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		//Botonera izquierda
		JButton btnNewButton = new JButton("Explorar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
    	btnNewButton.setIcon(new ImageIcon("resources/Explorar.png"));

		
		JButton btnNewButton_1 = new JButton("Nueva lista");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
    	btnNewButton_1.setIcon(new ImageIcon("resources/Nueva_lista.png"));

		
		JButton btnNewButton_2 = new JButton("Reciente");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 2;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
    	btnNewButton_2.setIcon(new ImageIcon("resources/Reciente.png"));

		JButton btnNewButton_3 = new JButton("Mis listas");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 3;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
    	btnNewButton_3.setIcon(new ImageIcon("resources/Mis_listas.png"));

	}
}
