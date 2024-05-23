import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class CreaFormulario extends JFrame {

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
	public CreaFormulario() {
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos  -  Creac\u00EDon de nuevo formulario de inscripc\u00EDon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.setBackground(new Color(152, 180, 216));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel instrucciones = new JLabel("RELLENA LOS CAMPOS TAL COMO SE PIDE PARA QUE EL FORMULARIO SE CONSTRUYA CON EXITO");
		instrucciones.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		etiquetaNombreTorneo.setBounds(10, 60, 278, 25);
		contentPane.add(etiquetaNombreTorneo);
		
		introNombreTorneo = new JTextField();
		introNombreTorneo.setBounds(298, 60, 276, 25);
		contentPane.add(introNombreTorneo);
		introNombreTorneo.setColumns(10);
		setLocationRelativeTo(null);
	}
}
