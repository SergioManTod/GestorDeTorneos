import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	//DATOS A CAPTURAR
	
	
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
		
		// FORMATO DEL CONTENTPANE
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos  -  Creac\u00EDon de nuevo Torneo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.setBackground(new Color(152, 180, 216));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		// FIN FORMATO DEL CONTENTPANE
		
		
		JLabel tagInstrucciones = new JLabel("RELLENA LOS CAMPOS TAL COMO SE PIDE \r\nPARA QUE EL FORMULARIO DE INSCRIPCI\u00D3N SE CONSTRUYA CON EXITO");
		tagInstrucciones.setToolTipText("");
		tagInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 9));
		tagInstrucciones.setForeground(new Color(255, 255, 255));
		tagInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		tagInstrucciones.setBounds(10, 11, 564, 25);
		contentPane.add(tagInstrucciones);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 47, 584, 2);
		contentPane.add(separator);
		
		// SECCION PARA CREAR NUEVO TORNEO
		
		// NOMBRE DEL TORNEO
		JLabel tagNomTorneo = new JLabel("NOMBRE DEL TORNEO:");
		tagNomTorneo.setHorizontalAlignment(SwingConstants.RIGHT);
		tagNomTorneo.setForeground(new Color(255, 255, 255));
		tagNomTorneo.setBounds(10, 74, 278, 25);
		contentPane.add(tagNomTorneo);
		
		textFieldNomTorneo = new JTextField();
		textFieldNomTorneo.setBounds(298, 74, 276, 25);
		contentPane.add(textFieldNomTorneo);
		textFieldNomTorneo.setColumns(10);
		
		// CANTIDAD DE EQUIPOS QUE PARTICIPAN EN EL TORNEO
		JLabel tagCantEquipos = new JLabel("CANTIDAD DE EQUIPOS QUE PARTICIPARAN:");
		tagCantEquipos.setHorizontalAlignment(SwingConstants.RIGHT);
		tagCantEquipos.setForeground(Color.WHITE);
		tagCantEquipos.setBounds(0, 129, 288, 25);
		contentPane.add(tagCantEquipos);
		
		JSpinner jSpinnerCantEquipos = new JSpinner();
		jSpinnerCantEquipos.setModel(new SpinnerNumberModel(3, 3, 10, 1));
		jSpinnerCantEquipos.setBounds(306, 129, 40, 25);
		contentPane.add(jSpinnerCantEquipos);
		
		// CANTIDAD MINIMA DE JUGADORES QUE PARTICIPAN EN EL EQUIPO
		JLabel tagCantJugadores = new JLabel("CANTIDAD DE JUGADORES QUE PARTICIPARAN:");
		tagCantJugadores.setHorizontalAlignment(SwingConstants.RIGHT);
		tagCantJugadores.setForeground(Color.WHITE);
		tagCantJugadores.setBounds(0, 190, 288, 25);
		contentPane.add(tagCantJugadores);
		
		JSpinner jSpinnerCantJugadores = new JSpinner();
		jSpinnerCantJugadores.setModel(new SpinnerNumberModel(10, 10, 20, 1));
		jSpinnerCantJugadores.setBounds(308, 188, 40, 25);
		contentPane.add(jSpinnerCantJugadores);
		
		// BOTON PARA CREAR EN TORNEO Y GENERAR EL PDF DE INSCRIPCION
		JButton botonCrearTorneo = new JButton("CREAR TORNEO");
		botonCrearTorneo.setBounds(225, 250, 150, 25);
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
	                
	            	String nombreTorneo=textFieldNomTorneo.getText();
	                 int cantEquipos = (int) jSpinnerCantEquipos.getValue();
	                int cantJugadores = (int) jSpinnerCantJugadores.getValue();
	               
	                Torneo nuevoTorneo = new Torneo(nombreTorneo, cantEquipos, cantJugadores, BaseDeDatos.obtenerInstancia().obtenerConexion());
	                nuevoTorneo.comprobarNombreTorneo(nombreTorneo);
	               System.out.println(nuevoTorneo.getNombTorneo()); 
	            }
	        });
		contentPane.add(botonCrearTorneo);
		
		// BOTON PARA VOLVER AL MENU PRINCIPAL
		JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
		botonMenuPrincipal.setBounds(225, 300, 150, 25);
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
		                
		                Gestor gestor = new Gestor();
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
        add(tagImagenDeFondo);
	}
	//METODOS
	public void creandoTorneo(String nomTorneo, int cantEquipos, int cantJugadores){
		CreaTorneo creaTorneo = new CreaTorneo();
        System.out.println("Nombre del torneo: " + nomTorneo);
	}
}
