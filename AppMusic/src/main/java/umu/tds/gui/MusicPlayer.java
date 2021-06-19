package umu.tds.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MusicPlayer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4154531517301626422L;
	private JTable table;
	private JLabel TituloLista;
	/**
	 * Create the panel.
	 */
	public MusicPlayer() {
		setLayout(new BorderLayout(0, 0));
		
		TituloLista = new JLabel("Canciones");
		TituloLista.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TituloLista.setHorizontalAlignment(SwingConstants.CENTER);
		add(TituloLista, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnterior.setIcon(new ImageIcon("resources\\iconos\\Anterior.png"));
		panel.add(btnAnterior);
		
		JButton btnCentral = new JButton("");
		btnCentral.addMouseListener(new MouseAdapter() {
			boolean isPlaying = false;
    		@Override
			public void mouseClicked(MouseEvent e) {					
    			if(isPlaying) {
					isPlaying = false;
					btnCentral.setIcon(new ImageIcon("resources\\iconos\\Play.png"));
					Controlador.getControlador().pararCancion();
				}
				else {
					isPlaying = true;
					btnCentral.setIcon(new ImageIcon("resources\\iconos\\Stop.png"));
					int selectedRow = table.getSelectedRow();

					//TODO: en un futuro se pedirá "reproducción por id" al controlador
					// La idea es tener los id escondidos dentro de la tabla.
					// estas siguientes lineas están mal pero representan funcionalidad por ahora.
					Controlador.getControlador().reproducirCancionPorNombre( (String) table.getValueAt(selectedRow, 0));


				}
			}
		});
		
		
		btnCentral.setIcon(new ImageIcon("resources\\iconos\\Play.png"));
		panel.add(btnCentral);
		
		JButton btnSiguiente = new JButton("");
		btnSiguiente.setIcon(new ImageIcon("resources\\iconos\\Siguiente.png"));
		panel.add(btnSiguiente);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Cancion", "Autor", "Id"
			} 
		));
		
		
		/* Se esconde la tercera columna de la tabla para establecer la relación
		 * dentro de la tabla entre canciones, artistas e Id de canciones.
		 * Éste Id no tiene utilidad de cara al usuario final pero sí a nivel de implementación
		 * para obtener el Id de una canción a partir de esa columna oculta.
		 */
		 table.removeColumn(table.getColumnModel().getColumn(2));

		scrollPane.setViewportView(table);

	}
	
	//TODO: esto no se si es necesario pero por ahora funciona
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(500, 500);
	}
	
	//Cambiado a List para poder usar Arraylist de la busqueda
	public void setCanciones(List<Cancion> canciones) {
		DefaultTableModel modeloTablaCanciones = (DefaultTableModel) table.getModel();
		modeloTablaCanciones.setRowCount(0);
		for(Cancion s : canciones) {
			modeloTablaCanciones.addRow(new Object[] {s.getNombre(), s.getInterprete(), s.getId()});
			//System.out.println("added: "+ s.getNombre() + " a reproducciones recientes");
		}
	}
	
	public void setListaTitulo(String titulo) {
		TituloLista.setText(titulo);
	}
	
	
	
	
	
	


}
