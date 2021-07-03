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
		
		//Botón central play(>)   stop(||) 
		JButton btnCentral = new JButton("");
		
		
		//Botón anterior 
		JButton btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow > 0) {
					Controlador.getControlador().pararCancion();
					Controlador.getControlador().reproducirCancionPorNombre( (String) table.getValueAt(selectedRow-1, 0));
					table.changeSelection(selectedRow-1, 0, false, false);
					btnCentral.setIcon(new ImageIcon("resources\\iconos\\Stop.png"));
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon("resources\\iconos\\Anterior.png"));
		panel.add(btnAnterior);
		
		
		JButton btnSiguiente = new JButton("");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow < table.getRowCount()-1 && 0 < table.getRowCount()) {
					Controlador.getControlador().pararCancion();
					Controlador.getControlador().reproducirCancionPorNombre( (String) table.getValueAt(selectedRow+1, 0));
					table.changeSelection(selectedRow+1, 0, false, false);
					btnCentral.setIcon(new ImageIcon("resources\\iconos\\Stop.png"));

				}
			}
		});
		
		

		btnCentral.addMouseListener(new MouseAdapter() {
			Controlador cont = Controlador.getControlador();
    		@Override
			public void mouseClicked(MouseEvent e) {					
    			if(cont.isMediaPlayerPlaying()) {
					btnCentral.setIcon(new ImageIcon("resources\\iconos\\Play.png"));
					cont.pararCancion();
				}
				else {
					btnCentral.setIcon(new ImageIcon("resources\\iconos\\Stop.png"));
					int selectedRow = table.getSelectedRow();

					cont.reproducirCancionPorNombre( (String) table.getValueAt(selectedRow, 0));
				}
			}
		});		
		btnCentral.setIcon(new ImageIcon("resources\\iconos\\Play.png"));
		panel.add(btnCentral);
		btnSiguiente.setIcon(new ImageIcon("resources\\iconos\\Siguiente.png"));
		panel.add(btnSiguiente);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cancion", "Autor", "Id"
			} 
		));
		
		 table.removeColumn(table.getColumnModel().getColumn(2));

		scrollPane.setViewportView(table);

	}
	
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(500, 500);
	}
	
	public void setCanciones(List<Cancion> list) {
		DefaultTableModel modeloTablaCanciones = (DefaultTableModel) table.getModel();
		modeloTablaCanciones.setRowCount(0);
		for(Cancion s : list) {
			modeloTablaCanciones.addRow(new Object[] {s.getNombre(), s.getInterprete(), s.getId()});
			//System.out.println("added: "+ s.getNombre() + " a reproducciones recientes");
		}
	}
	public void setListaTitulo(String titulo) {
		TituloLista.setText(titulo);
	}
	
	
	
	
	
	


}
