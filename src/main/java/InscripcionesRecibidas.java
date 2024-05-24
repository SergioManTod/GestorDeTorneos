import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;

public class InscripcionesRecibidas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscripcionesRecibidas frame = new InscripcionesRecibidas();
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
	public InscripcionesRecibidas() {
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos  -  Inscripciones recibidas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.setBackground(new Color(152, 180, 216));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonRechazados = new JButton("INSC. RECHAZADAS");
		botonRechazados.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonRechazados.setBounds(8, 320, 140, 25);
		botonRechazados.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonRechazados.setBackground(Color.LIGHT_GRAY);
		    	botonRechazados.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonRechazados.setBackground(Color.WHITE);
		    	botonRechazados.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonRechazados);
		
		JButton botonValidas = new JButton("INSC. ACEPTADAS");
		botonValidas.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonValidas.setBounds(151, 320, 140, 25);
		botonValidas.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonValidas.setBackground(Color.LIGHT_GRAY);
		    	botonValidas.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonValidas.setBackground(Color.WHITE);
		    	botonValidas.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonValidas);
		
		JButton botonNotificar = new JButton("NOTIFICAR RECH.");
		botonNotificar.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonNotificar.setBounds(294, 320, 140, 25);
		botonNotificar.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonNotificar.setBackground(Color.LIGHT_GRAY);
		    	botonNotificar.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonNotificar.setBackground(Color.WHITE);
		    	botonNotificar.setForeground(Color.BLACK);
		    }
		});
		contentPane.add(botonNotificar);
		
		JButton botonMenuPrincipal = new JButton("MEN\u00DA PRINCIPAL");
		botonMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonMenuPrincipal.setBounds(438, 320, 140, 25);
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
		//aqui linkeamos a la otra clase
		botonMenuPrincipal.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                setVisible(false); // Ocultar la clase Gestor
		                
		                Gestor gestor = new Gestor();
		                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra toda la aplicación al cerrar esta ventana
		                gestor.setVisible(true);
		            }
		        });
		contentPane.add(botonMenuPrincipal);
		
		
		
		
		String[] columnNames = {"Nº","EQUIPO", "ESTADO", "MOTIVOS"};
		Object[][] data = {
		    {1,"La marina social club", "valido", },
		    {2,"club amigos de la cerveza", "valido", },
		    {3,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {4,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {5,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {6,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {7,"La marina social club", "valido", },
		    {8,"club amigos de la cerveza", "valido", },
		    {9,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {10,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {11,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {12,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {13,"La marina social club", "valido", },
		    {14,"club amigos de la cerveza", "valido", },
		    {15,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {16,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {17,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {18,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {19,"La marina social club", "valido", },
		    {20,"club amigos de la cerveza", "valido", },
		    {21,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {22,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {23,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {24,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {25,"La marina social club", "valido", },
		    {26,"club amigos de la cerveza", "valido", },
		    {27,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {28,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {29,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {30,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {31,"La marina social club", "valido", },
		    {32,"club amigos de la cerveza", "valido", },
		    {33,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {34,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {35,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {36,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"}
		    };
		table_1 = new JTable();
		table_1.setRowSelectionAllowed(false);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(23);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(110);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(130);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(216);
		table_1.setBounds(0, 0, 600, 300);
		contentPane.add(table_1);
	}
}
