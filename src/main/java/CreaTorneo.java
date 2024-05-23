import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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
	private JTextField introNombreTorneo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaFormulario frame = new CreaFormulario();
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
	public CreaTorneo() {
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
		
		JLabel instrucciones = new JLabel("RELLENA LOS CAMPOS TAL COMO SE PIDE \r\nPARA QUE EL FORMULARIO DE INSCRIPCI\u00D3N SE CONSTRUYA CON EXITO");
		instrucciones.setToolTipText("");
		instrucciones.setFont(new Font("Tahoma", Font.BOLD, 9));
		instrucciones.setForeground(new Color(255, 255, 255));
		instrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		instrucciones.setBounds(10, 11, 564, 25);
		contentPane.add(instrucciones);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 47, 584, 2);
		contentPane.add(separator);
		
		JLabel etiquetaNombreTorneo = new JLabel("NOMBRE DEL TORNEO:");
		etiquetaNombreTorneo.setHorizontalAlignment(SwingConstants.RIGHT);
		etiquetaNombreTorneo.setForeground(new Color(255, 255, 255));
		etiquetaNombreTorneo.setBounds(10, 74, 278, 25);
		contentPane.add(etiquetaNombreTorneo);
		
		introNombreTorneo = new JTextField();
		introNombreTorneo.setBounds(298, 74, 276, 25);
		contentPane.add(introNombreTorneo);
		introNombreTorneo.setColumns(10);
		
		JLabel etiquetaCantidadDeEquipos = new JLabel("CANTIDAD DE EQUIPOS QUE PARTICIPARAN:");
		etiquetaCantidadDeEquipos.setHorizontalAlignment(SwingConstants.RIGHT);
		etiquetaCantidadDeEquipos.setForeground(Color.WHITE);
		etiquetaCantidadDeEquipos.setBounds(0, 129, 288, 25);
		contentPane.add(etiquetaCantidadDeEquipos);
		
		JSpinner cantidadDeEquipos = new JSpinner();
		cantidadDeEquipos.setModel(new SpinnerNumberModel(3, null, 10, 1));
		cantidadDeEquipos.setBounds(306, 129, 40, 25);
		contentPane.add(cantidadDeEquipos);
		
		JLabel etiquetaCantidadDeJugadores = new JLabel("CANTIDAD DE JUGADORES QUE PARTICIPARAN:");
		etiquetaCantidadDeJugadores.setHorizontalAlignment(SwingConstants.RIGHT);
		etiquetaCantidadDeJugadores.setForeground(Color.WHITE);
		etiquetaCantidadDeJugadores.setBounds(0, 190, 288, 25);
		contentPane.add(etiquetaCantidadDeJugadores);
		
		JSpinner cantidadDeJugadores = new JSpinner();
		cantidadDeJugadores.setModel(new SpinnerNumberModel(0, null, 15, 1));
		cantidadDeJugadores.setBounds(308, 188, 40, 25);
		contentPane.add(cantidadDeJugadores);
		
		JButton botonCrearTorneo = new JButton("CREAR TORNEO");
		botonCrearTorneo.setBounds(225, 250, 150, 25);
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
		contentPane.add(botonCrearTorneo);
		setLocationRelativeTo(null);
		 // Crea un JLabel para mostrar la imagen
        JLabel imagenFondo = new JLabel();
        
        // Carga la imagen desde un archivo (asegúrate de tener la imagen en el mismo directorio que tu código)
        ImageIcon icono = new ImageIcon("img\\balon.png");
        
        // Asigna el icono al JLabel
        imagenFondo.setIcon(icono);
        imagenFondo.setBounds(300, 150, 512, 512);
        
        // Agrega el JLabel al JFrame
        add(imagenFondo);
	}
}
