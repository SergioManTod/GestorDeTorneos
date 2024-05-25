package org.eclipse.wb.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;

public class ModificaDatosEquipo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombreEquipo;
	private JTextField textFieldEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaDatosEquipo frame = new ModificaDatosEquipo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModificaDatosEquipo() {
		
		// FORMATO DEL CONTENTPANE
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos  -  Modificar datos de un equipo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.setBackground(new Color(152, 180, 216));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// FIN DEL FORMATO DEL CONTENTPANE
		
		JLabel etiquetaSelEquipo = new JLabel("EQUIPO:");
		etiquetaSelEquipo.setForeground(new Color(255, 255, 255));
		etiquetaSelEquipo.setBounds(10, 10, 49, 25);
		contentPane.add(etiquetaSelEquipo);
		
		JComboBox comboBoxEquipo = new JComboBox();
		comboBoxEquipo.setModel(new DefaultComboBoxModel(new String[] {"-->SELECCIONE EQUIPO<--", "Los gauchitos", "Diseminados por el viento", "LA MARINA", "Cuatro locos"}));
		comboBoxEquipo.setBounds(60, 10, 200, 25);
		contentPane.add(comboBoxEquipo);
		setLocationRelativeTo(null);
		
		JLabel etiquetaNombreEquipo = new JLabel("NOMBRE:");
		etiquetaNombreEquipo.setForeground(Color.WHITE);
		etiquetaNombreEquipo.setBounds(10, 45, 80, 25);
		contentPane.add(etiquetaNombreEquipo);
		
		textFieldNombreEquipo = new JTextField();
		textFieldNombreEquipo.setBounds(70, 45, 230, 25);
		contentPane.add(textFieldNombreEquipo);
		textFieldNombreEquipo.setColumns(10);
		
		JLabel etiquetamMailEquipo = new JLabel("MAIL:");
		etiquetamMailEquipo.setForeground(Color.WHITE);
		etiquetamMailEquipo.setBounds(305, 45, 55, 25);
		contentPane.add(etiquetamMailEquipo);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(340, 45, 240, 25);
		contentPane.add(textFieldEmail);
		
		JButton botonModifica = new JButton("MODIFICAR EQUIPO");
		botonModifica.setFont(new Font("Tahoma", Font.BOLD, 8));
		botonModifica.setBounds(270, 10, 150, 25);
		contentPane.add(botonModifica);
		
		JButton botonElimina = new JButton("ELIMINAR  EQUIPO");
		botonElimina.setFont(new Font("Tahoma", Font.BOLD, 8));
		botonElimina.setBounds(430, 10, 150, 25);
		contentPane.add(botonElimina);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 80, 600, 2);
		contentPane.add(separator);
		
		JLabel etiquetaSelJugador = new JLabel("SELECCIONA JUGADOR:");
		etiquetaSelJugador.setHorizontalAlignment(SwingConstants.RIGHT);
		etiquetaSelJugador.setForeground(Color.WHITE);
		etiquetaSelJugador.setBounds(10, 90, 230, 25);
		contentPane.add(etiquetaSelJugador);
		
		JComboBox comboBoxJugador = new JComboBox();
		comboBoxJugador.setBounds(198, 151, 300, 25);
		contentPane.add(comboBoxJugador);
		setLocationRelativeTo(null);
		
	}
}
