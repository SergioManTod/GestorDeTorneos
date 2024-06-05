import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CreaTorneo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomTorneo;
	private JLabel tagMsgeIntroNuevoTorneoBbDd;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaTorneo frame = new CreaTorneo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreaTorneo() {
		//llamar a la conexion de bbdd 
		BaseDeDatos baseDeDatos = BaseDeDatos.obtenerInstancia(null);
		// FORMATO DEL CONTENTPANE
				setForeground(new Color(255, 255, 255));
				setResizable(false);
				setType(Type.POPUP);
				setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
				setTitle("Gestor de Torneos V. Gamma  -  Crear nuevo torneo");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 600, 420);
				contentPane = new JPanel();
				contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
				contentPane.setPreferredSize(new Dimension(600, 420));
		        contentPane.setBackground(new Color(152, 180, 216));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				setLocationRelativeTo(null);
				// FIN DEL FORMATO DEL CONTENTPANE
		
		//JLABEL MENSAJE DE RESEULTADO DE INSERCION EN LA BBDD
	    tagMsgeIntroNuevoTorneoBbDd = new JLabel();
	    tagMsgeIntroNuevoTorneoBbDd.setToolTipText("");
	    tagMsgeIntroNuevoTorneoBbDd.setHorizontalAlignment(SwingConstants.CENTER);
	    tagMsgeIntroNuevoTorneoBbDd.setForeground(Color.WHITE);
	    tagMsgeIntroNuevoTorneoBbDd.setFont(new Font("Tahoma", Font.BOLD, 12));
	    tagMsgeIntroNuevoTorneoBbDd.setBounds(26, 260, 350, 75);
	    contentPane.add(tagMsgeIntroNuevoTorneoBbDd);
		
		JLabel tagInstrucciones = new JLabel("RELLENA LOS CAMPOS TAL COMO SE PIDE PARA QUE EL FORMULARIO DE INSCRIPCIÓN SE CONSTRUYA CON EXITO");
		tagInstrucciones.setToolTipText("");
		tagInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 9));
		tagInstrucciones.setForeground(new Color(255, 255, 255));
		tagInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		tagInstrucciones.setBounds(10, 25, 564, 25);
		contentPane.add(tagInstrucciones);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 75, 584, 2);
		contentPane.add(separator);
		
		// SECCION PARA CREAR NUEVO TORNEO
		
		// NOMBRE DEL TORNEO
		JLabel tagNomTorneo = new JLabel("NOMBRE DEL TORNEO:");
		tagNomTorneo.setHorizontalAlignment(SwingConstants.LEFT);
		tagNomTorneo.setForeground(new Color(255, 255, 255));
		tagNomTorneo.setBounds(100, 125, 278, 25);
		contentPane.add(tagNomTorneo);
		
		textFieldNomTorneo = new JTextField();
		textFieldNomTorneo.setBounds(260, 125, 276, 25);
		contentPane.add(textFieldNomTorneo);
		textFieldNomTorneo.setColumns(10);
		
		// CANTIDAD DE EQUIPOS QUE PARTICIPAN EN EL TORNEO
		JLabel tagCantEquipos = new JLabel("CANTIDAD DE EQUIPOS QUE PARTICIPARAN:");
		tagCantEquipos.setHorizontalAlignment(SwingConstants.LEFT);
		tagCantEquipos.setForeground(Color.WHITE);
		tagCantEquipos.setBounds(100, 175, 288, 25);
		contentPane.add(tagCantEquipos);
		
		JSpinner jSpinnerCantEquipos = new JSpinner();
		jSpinnerCantEquipos.setModel(new SpinnerNumberModel(3, 3, 10, 1));
		jSpinnerCantEquipos.setBounds(400, 175, 40, 25);
		contentPane.add(jSpinnerCantEquipos);
		
		// CANTIDAD MINIMA DE JUGADORES QUE PARTICIPAN EN EL EQUIPO
		JLabel tagCantJugadores = new JLabel("CANTIDAD DE JUGADORES QUE PARTICIPARAN:");
		tagCantJugadores.setHorizontalAlignment(SwingConstants.LEFT);
		tagCantJugadores.setForeground(Color.WHITE);
		tagCantJugadores.setBounds(100, 225, 288, 25);
		contentPane.add(tagCantJugadores);
		
		JSpinner jSpinnerCantJugadores = new JSpinner();
		jSpinnerCantJugadores.setModel(new SpinnerNumberModel(5, 5, 15, 1));
		jSpinnerCantJugadores.setBounds(400, 225, 40, 25);
		contentPane.add(jSpinnerCantJugadores);
		
		// BOTON PARA CREAR EN TORNEO Y GENERAR EL PDF DE INSCRIPCION
		JButton botonCrearTorneo = new JButton("CREAR TORNEO");
		botonCrearTorneo.setBounds(389, 295, 150, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonCrearTorneo.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonCrearTorneo.setBackground(Color.LIGHT_GRAY);
		    	botonCrearTorneo.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonCrearTorneo.setBackground(Color.WHITE);
		    	botonCrearTorneo.setForeground(Color.BLACK);
		    }
		});
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonCrearTorneo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        tagMsgeIntroNuevoTorneoBbDd.setText("");
		        String nombreTorneo = textFieldNomTorneo.getText();
		        int cantEquipos = (int) jSpinnerCantEquipos.getValue();
		        int cantJugadores = (int) jSpinnerCantJugadores.getValue();
		        JLabel tagMsgeConBbDd = new JLabel();
				Torneo nuevoTorneo = new Torneo(nombreTorneo, cantEquipos, cantJugadores, BaseDeDatos.obtenerInstancia(tagMsgeConBbDd).obtenerConexion());
		        String mensajeError = null;
		        try {
		            boolean nombreTorneoValido = nuevoTorneo.comprobarNombreTorneo(nombreTorneo);
		            if (nombreTorneo.length() == 0) {
		                mensajeError = "No puede estar el campo nombre vacío.";
		            } else if (!nombreTorneoValido) {
		                mensajeError = "<html>El torneo "+nombreTorneo+" ya existe en la base de datos.<br>Intentalo asignando otro nombre.</html>";
		            }
		            if (mensajeError == null) {
		                baseDeDatos.insertaTorneoNuevo(nuevoTorneo);
		                tagMsgeIntroNuevoTorneoBbDd.setText("<html>El Torneo " + nuevoTorneo.getNombTorneo() + "<br>se ha guardado correctamente en la Base de Datos.<br>Con un mínimo de " + nuevoTorneo.getCantEquipos() + " equipos participantes <br>y un mínimo de " + nuevoTorneo.getCantJugadores() + " jugadores por equipo.</html>");
		                PdfCrear inscripcion = new PdfCrear();
		               
		                CreaDirectorios nuevoArbolDeDirectorios = new CreaDirectorios();
		                nuevoArbolDeDirectorios.arbolDeCarpetas(nombreTorneo);
		                inscripcion.crearpdfFormulario(nombreTorneo, cantJugadores);
		            } else {
		                tagMsgeIntroNuevoTorneoBbDd.setText(mensajeError);
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(contentPane, "Error al hacer la consulta en la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        contentPane.revalidate();
		        contentPane.repaint();
		    }
		});
		contentPane.add(botonCrearTorneo);
	
		
		// BOTON PARA VOLVER AL MENU PRINCIPAL
		JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
		botonMenuPrincipal.setBounds(389, 340, 150, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonMenuPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonMenuPrincipal.setBackground(Color.LIGHT_GRAY);
		    	botonMenuPrincipal.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonMenuPrincipal.setBackground(Color.WHITE);
		    	botonMenuPrincipal.setForeground(Color.BLACK);
		    }
		});
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		//AQUI HACEMOS EL LINK A LA OTRA CLASE
		botonMenuPrincipal.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                setVisible(false); // OCULTAMOS LA CLASE EN LA QUE ESTAMOS
		                
		                Gestor gestor = new Gestor(null);
		                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA LA CLASE EN LA QUE ESTAMOS
		                gestor.setVisible(true);
		            }
		        });
		contentPane.add(botonMenuPrincipal);
		 
		// IMAGEN DE FONDO
        JLabel tagImagenDeFondo = new JLabel();
        // SE CREA UN IMAGEICON CON LA IMAGEN DE FONDO
        ImageIcon balon = new ImageIcon("img\\balon.png");
        
        
        // SE LE ASIGNA EL IMAGEICON A LA ETIQUETA
        tagImagenDeFondo.setIcon(balon);
        tagImagenDeFondo.setBounds(300, 150, 512, 512);
        getContentPane().add(tagImagenDeFondo);
	}
}


