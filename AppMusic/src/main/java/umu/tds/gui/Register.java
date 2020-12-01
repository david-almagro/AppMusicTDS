package umu.tds.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.Controlador;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Register {

	private JFrame frame;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEmail;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordField_2;
	private JDateChooser dateFechaNac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	public void hacerVisible() {
		//custom
		frame.setVisible(true);
	}

	private boolean validar() {
		
		boolean valido = false;
		String pw1 = passwordField.getPassword().toString();
		String pw2 = passwordField_2.getPassword().toString();
		
		if(textUsuario.getText().isEmpty()) {
			
			
		}
		

		if(pw1.isEmpty()) {
			
			
		}
			
		if(pw2.isEmpty()) {
			
		}
			
		if(textNombre.getText().isEmpty()) {
			
		}
			
		if(textApellidos.getText().isEmpty()) {
			
		}

		if(dateFechaNac.getDateFormatString().isEmpty()) {
			
		}

		if(textEmail.getText().isEmpty()) {
			
		}

		if(!pw1.equals(pw2)) {
			
			
		}
		return valido;
			
	
		
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 663, 380);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 0, 185, 70, 185, 30, 0};
		gbl_panel.rowHeights = new int[]{55, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 3;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		panel.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 2;
		panel.add(lblApellidos, gbc_lblApellidos);
		
		textApellidos = new JTextField();
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.gridwidth = 3;
		gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.gridx = 2;
		gbc_textApellidos.gridy = 2;
		panel.add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha Nacimiento: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 3;
		panel.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		dateFechaNac = new JDateChooser();
		GridBagConstraints gbc_dateFechaNac = new GridBagConstraints();
		gbc_dateFechaNac.gridwidth = 3;
		gbc_dateFechaNac.insets = new Insets(0, 0, 5, 5);
		gbc_dateFechaNac.fill = GridBagConstraints.BOTH;
		gbc_dateFechaNac.gridx = 2;
		gbc_dateFechaNac.gridy = 3;
		panel.add(dateFechaNac, gbc_dateFechaNac);
		
		JLabel lblNewLabel_1 = new JLabel("Email: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.gridwidth = 2;
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.gridx = 2;
		gbc_textEmail.gridy = 5;
		panel.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.gridwidth = 2;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 6;
		panel.add(textUsuario, gbc_textUsuario);
		
		JLabel lblNewLabel_2_1 = new JLabel("Contraseña: ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 1;
		gbc_lblNewLabel_2_1.gridy = 7;
		panel.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 7;
		panel.add(passwordField, gbc_passwordField);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Repetir: ");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1.gridx = 3;
		gbc_lblNewLabel_2_1_1.gridy = 7;
		panel.add(lblNewLabel_2_1_1, gbc_lblNewLabel_2_1_1);
		
		passwordField_2 = new JPasswordField();
		GridBagConstraints gbc_passwordField_2 = new GridBagConstraints();
		gbc_passwordField_2.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_2.gridx = 4;
		gbc_passwordField_2.gridy = 7;
		panel.add(passwordField_2, gbc_passwordField_2);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.anchor = GridBagConstraints.WEST;
		gbc_btnRegistrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistrar.gridx = 2;
		gbc_btnRegistrar.gridy = 9;
		panel.add(btnRegistrar, gbc_btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//boolean registrado = false;
				//llamada al controlador, registrado = Controlador...
				//boolean valido = validar();
				boolean registrado = Controlador.getControlador().registrar(textUsuario.getText(), new String(passwordField.getPassword()),
						textEmail.getText(), textNombre.getText(), textApellidos.getText(), dateFechaNac.getDateFormatString());
				if(registrado) {
					JOptionPane.showMessageDialog(frame, "Usuario registrado correctamente..\n","Registro realizado", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					Login login = new Login();
					login.hacerVisible();
				}
				else
				JOptionPane.showMessageDialog(frame, "No se ha podido llevar a cabo el registro.\n","Error de registro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 9;
		panel.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				frame.dispose();
				Login log = new Login();
				log.hacerVisible();
			}
		});
		

	}

}
