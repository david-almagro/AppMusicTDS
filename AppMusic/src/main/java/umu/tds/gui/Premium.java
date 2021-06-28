package umu.tds.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


import umu.tds.controlador.Controlador;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Premium {

	private JFrame frmCompraAppmusicPremium;
	private JTextField textUser;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_4;
	private JPasswordField passwordField;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JLabel lblPrecio;
	private JLabel lblDescuento;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;

	private final double precio = 5.00;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Borrar después, temporal para no tener que registrar cada vez
					//Controlador.getControlador().registrar("d", "d",
							//"a@gmail.com", "Juan", "Perico", "a");
					
					Premium window = new Premium();
					window.frmCompraAppmusicPremium.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Premium() {
		initialize();
	}

	public void hacerVisible() {
		//custom
		frmCompraAppmusicPremium.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCompraAppmusicPremium = new JFrame();
		frmCompraAppmusicPremium.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frmCompraAppmusicPremium.setBounds(100, 100, 640, 400);
		frmCompraAppmusicPremium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCompraAppmusicPremium.getContentPane().setLayout(new BorderLayout(0, 0));
		frmCompraAppmusicPremium.setTitle("Compra AppMusic Premium");
		frmCompraAppmusicPremium.setResizable(false);
		
		JPanel panel = new JPanel();
		frmCompraAppmusicPremium.getContentPane().add(panel, BorderLayout.NORTH);
		
		lblNewLabel_4 = new JLabel("Hazte premium");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("List.noFocusBorder"));
		frmCompraAppmusicPremium.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{110, 0, 200, 0, 110, 0};
		gbl_panel_1.rowHeights = new int[]{80, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblNewLabel = new JLabel("Tarjeta de crédito:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		textUser = new JTextField();
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.gridwidth = 2;
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.insets = new Insets(0, 0, 5, 5);
		gbc_textUser.gridx = 2;
		gbc_textUser.gridy = 1;
		panel_1.add(textUser, gbc_textUser);
		textUser.setColumns(18);
		
		lblNewLabel_1 = new JLabel("Código de seguridad:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 2;
		panel_1.add(passwordField, gbc_passwordField);
		
		
		lblPrecio = new JLabel("Precio: ");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 1;
		gbc_lblPrecio.gridy = 4;
		panel_1.add(lblPrecio, gbc_lblPrecio);
		
		lblNewLabel_6 = new JLabel(String.valueOf(precio));
		lblNewLabel_6.setForeground(Color.black);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 4;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		btnRegistrar = new JButton("Aplicar descuento");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrar.setForeground(new Color(30, 144, 255));
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.anchor = GridBagConstraints.WEST;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 3;
		gbc_btnRegistrar.gridy = 4;
		panel_1.add(btnRegistrar, gbc_btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				frmCompraAppmusicPremium.dispose();
				Register reg = new Register();
				reg.hacerVisible();
			}
		});
		
		lblDescuento = new JLabel("Descuento:");
		lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 1;
		gbc_lblDescuento.gridy = 5;
		panel_1.add(lblDescuento, gbc_lblDescuento);
		
		lblNewLabel_5 = new JLabel(String.valueOf(precio-Controlador.getControlador().getDescuento(precio)));
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 5;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		lblNewLabel_2 = new JLabel("Precio final:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
								
								btnAceptar = new JButton("Aceptar");
								btnAceptar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
											Controlador.getControlador().hacerPremium();
											JOptionPane.showMessageDialog(frmCompraAppmusicPremium, "¡Enhorabuena!\nAhora eres usuario premium","Pago completado", JOptionPane.INFORMATION_MESSAGE);
											frmCompraAppmusicPremium.dispose();
											Principal principal = new Principal();
											principal.hacerVisible();
										
									}
								});
								
								lblNewLabel_3 = new JLabel(String.valueOf(Controlador.getControlador().getDescuento(precio)));
								lblNewLabel_3.setForeground(new Color(50, 205, 50));
								lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
								GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
								gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
								gbc_lblNewLabel_3.gridx = 2;
								gbc_lblNewLabel_3.gridy = 6;
								panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
								btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
								GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
								gbc_btnAceptar.anchor = GridBagConstraints.WEST;
								gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
								gbc_btnAceptar.gridx = 2;
								gbc_btnAceptar.gridy = 8;
								panel_1.add(btnAceptar, gbc_btnAceptar);
								
										btnCancelar = new JButton("Cancelar");
										btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
										GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
										gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
										gbc_btnCancelar.gridx = 3;
										gbc_btnCancelar.gridy = 8;
										panel_1.add(btnCancelar, gbc_btnCancelar);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						frmCompraAppmusicPremium.dispose();
						Principal principal = new Principal();
						principal.hacerVisible();
					}
				});
		
		
		
	}

}
