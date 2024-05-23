import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JMenuBar;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Window.Type;

public class Gestor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestor frame = new Gestor();
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
	public Gestor() {
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Acer\\OneDrive - Educacyl\\Programacion\\eclipse\\GestorDeTorneos\\img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
	
		contentPane.setPreferredSize(new Dimension(400, 300));
        contentPane.setBackground(new Color(152, 180, 216));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel creaFromularioInscripcion = new JLabel("Crear nuevo formulario de inscripci\u00F3n:");
		creaFromularioInscripcion.setForeground(new Color(255, 255, 255));
		creaFromularioInscripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		creaFromularioInscripcion.setBounds(10, 25, 400, 25);
		contentPane.add(creaFromularioInscripcion);
		
		JButton botonCrearFormulario = new JButton("CREAR");
		botonCrearFormulario.setBounds(420, 25, 120, 25);
		botonCrearFormulario.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonCrearFormulario.setBackground(Color.LIGHT_GRAY);
		    	botonCrearFormulario.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonCrearFormulario.setBackground(Color.WHITE);
		    	botonCrearFormulario.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonCrearFormulario);
		
		JLabel listarSolicitudes = new JLabel("Listar Formularios de inscrpci\u00F3n recibidos:");
		listarSolicitudes.setForeground(new Color(255, 255, 255));
		listarSolicitudes.setHorizontalAlignment(SwingConstants.RIGHT);
		listarSolicitudes.setBounds(10, 75, 400, 25);
		contentPane.add(listarSolicitudes);
		
		JButton botonListarFormulariosRecibidos = new JButton("LISTAR");
		botonListarFormulariosRecibidos.setBounds(420, 75, 120, 25);
		botonListarFormulariosRecibidos.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonListarFormulariosRecibidos.setBackground(Color.LIGHT_GRAY);
		    	botonListarFormulariosRecibidos.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonListarFormulariosRecibidos.setBackground(Color.WHITE);
		    	botonListarFormulariosRecibidos.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonListarFormulariosRecibidos);
		
		JLabel crearNuevoTorneo = new JLabel("Crear nuevo torneo:");
		crearNuevoTorneo.setForeground(new Color(255, 255, 255));
		crearNuevoTorneo.setHorizontalAlignment(SwingConstants.RIGHT);
		crearNuevoTorneo.setBounds(150, 125, 260, 25);
		contentPane.add(crearNuevoTorneo);
		
		JButton botonCrearNuevoTorneo = new JButton("CREAR");
		botonCrearNuevoTorneo.setBounds(420, 125, 120, 25);
		botonCrearNuevoTorneo.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonCrearNuevoTorneo.setBackground(Color.LIGHT_GRAY);
		    	botonCrearNuevoTorneo.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonCrearNuevoTorneo.setBackground(Color.WHITE);
		    	botonCrearNuevoTorneo.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonCrearNuevoTorneo);
		
		JLabel modificarDatosDeEquipo = new JLabel("Modificar datos de un equipo:");
		modificarDatosDeEquipo.setForeground(new Color(255, 255, 255));
		modificarDatosDeEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
		modificarDatosDeEquipo.setBounds(150, 175, 260, 25);
		contentPane.add(modificarDatosDeEquipo);
		
		JButton botonModificarEquipo = new JButton("OPCIONES");
		botonModificarEquipo.setBounds(420, 175, 120, 25);
		botonModificarEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonModificarEquipo.setBackground(Color.LIGHT_GRAY);
		    	botonModificarEquipo.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonModificarEquipo.setBackground(Color.WHITE);
		    	botonModificarEquipo.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonModificarEquipo);
		
		JLabel verClasificacion = new JLabel("Ver clasificaci\u00F3n:");
		verClasificacion.setForeground(new Color(255, 255, 255));
		verClasificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		verClasificacion.setBounds(150, 225, 260, 25);
		contentPane.add(verClasificacion);
		
		JButton botonVerClasificacion = new JButton("VER");
		botonVerClasificacion.setBounds(420, 225, 120, 25);
		botonVerClasificacion.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonVerClasificacion.setBackground(Color.LIGHT_GRAY);
		    	botonVerClasificacion.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonVerClasificacion.setBackground(Color.WHITE);
		    	botonVerClasificacion.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonVerClasificacion);
		
		JLabel verTablaDeGoleadores = new JLabel("Ver tabla de goleadores:");
		verTablaDeGoleadores.setForeground(new Color(255, 255, 255));
		verTablaDeGoleadores.setHorizontalAlignment(SwingConstants.RIGHT);
		verTablaDeGoleadores.setBounds(150, 275, 260, 25);
		contentPane.add(verTablaDeGoleadores);
		
		JButton botonVerTablaGoleadores = new JButton("VER");
		botonVerTablaGoleadores.setBounds(420, 275, 120, 25);
		botonVerTablaGoleadores.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonVerTablaGoleadores.setBackground(Color.LIGHT_GRAY);
		    	botonVerTablaGoleadores.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonVerTablaGoleadores.setBackground(Color.WHITE);
		    	botonVerTablaGoleadores.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonVerTablaGoleadores);
		
		
	}
}
