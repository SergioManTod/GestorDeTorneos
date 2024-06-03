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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class InscripcionesRecibidas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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

	
	public InscripcionesRecibidas() {
		//llamar a la conexion de bbdd 
				BaseDeDatos baseDeDatos = BaseDeDatos.obtenerInstancia(null);
		// FORMATO DEL CONTENTPANE
				setForeground(new Color(255, 255, 255));
				setResizable(false);
				setType(Type.POPUP);
				setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
				setTitle("Gestor de Torneos 1.0  -  Inscripciones recibidas");
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
		
		// ARRAY DE OBJETOS PARA PRUEBA
		String[] nomColumnas = {"Nº","EQUIPO", "ESTADO", "MOTIVOS"};
		Object[][] equipos = {
		    {1,"La marina social club", "valido"},
		    {2,"club amigos de la cerveza", "valido"},
		    {3,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {4,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {5,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {6,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {7,"La marina social club", "valido"},
		    {8,"club amigos de la cerveza", "valido", },
		    {9,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {10,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {11,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {12,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {13,"La marina social club", "valido"},
		    {14,"club amigos de la cerveza", "valido", },
		    {15,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {16,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {17,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {18,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {19,"La marina social club", "valido"},
		    {20,"club amigos de la cerveza", "valido", },
		    {21,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {22,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {23,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {24,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"},
		    {25,"La marina social club", "valido"},
		    {26,"club amigos de la cerveza", "valido", },
		    {27,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {28,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {29,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {30,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadoresNo llega al minimo de jugadoresNo llega al minimo de jugadores"},
		    {31,"La marina social club", "valido"},
		    {32,"club amigos de la cerveza", "valido", },
		    {33,"Pelota vieja", "rechazado", "Menores de edad alineados."},
		    {34,"La viga de trapo", "rechazado", "No tiene arbitro asignado"},
		    {35,"Cuatro locos", "rechazado", "No tiene arbitro asignado"},
		    {36,"Los amigo de Maria", "rechazado", "No llega al minimo de jugadores"}
		    };
		
		// TABLA PARA MOSTRAR LOS EQUIPOS QUE ENVIARON INSCRIPCION
        DefaultTableModel modelo = new DefaultTableModel(equipos, nomColumnas);// CREA EL MODELO DE LA TABLA CON LOES EQUIPOS Y LOS NOMBRE DE LAS COLUMNAS

        
        JTable tablaEquipos = new JTable(modelo);// CREA LA TABLA CON EL MODELO
        tablaEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        for (int i = 0; i < tablaEquipos.getColumnCount(); i++) {// AJUSTA EL ANCHO DE LAS COLUMNAS A SU CONTENIDO
            TableColumn columna = tablaEquipos.getColumnModel().getColumn(i);
            int width = (int) tablaEquipos.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(tablaEquipos, columna.getHeaderValue(), false, false, -1, i)
                    .getPreferredSize().getWidth();
            for (int j = 0; j < tablaEquipos.getRowCount(); j++) {
                int preferedWidth = (int) tablaEquipos.getCellRenderer(j, i)
                        .getTableCellRendererComponent(tablaEquipos, tablaEquipos.getValueAt(j, i), false, false, j, i)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            columna.setPreferredWidth(width + 10);
        }
        TableColumn ultimaColumna = tablaEquipos.getColumnModel().getColumn(tablaEquipos.getColumnCount() - 1);// AJUSTA EL ANCHO DE LA ULTIMA COLUMNA PARA QUE LLEGUE AL FINAL
        ultimaColumna.setPreferredWidth(320); 
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 1, 585, 305); 
        contentPane.add(scrollPane);
        
        // BOTON PARA SOLO LISTAR LAS INSCRIPCIONES RECHAZADAS
		JButton botonRechazados = new JButton("INSC. RECHAZADAS");
		botonRechazados.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonRechazados.setBounds(8, 320, 140, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
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
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		//AVISO DE OPCION NO DISPONIBLE
		botonRechazados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               FueraDeServicio.avisoNoDisponible();
            }
        });
		contentPane.add(botonRechazados);
		
		// BOTON PARA LISTAR SOLO LAS INSCRIPCIONES VALIDAS
		JButton botonValidas = new JButton("INSC. ACEPTADAS");
		botonValidas.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonValidas.setBounds(151, 320, 140, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
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
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		//AVISO DE OPCION NO DISPONIBLE
		botonValidas.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		               FueraDeServicio.avisoNoDisponible();
		            }
		        });
		contentPane.add(botonValidas);
		
		//BOTON PARA GENERAR MAIL O ARCHIVO QUE NOTIFIQUE A LOS EQUIPOS RECHAZADOS
		JButton botonNotificar = new JButton("NOTIFICAR RECH.");
		botonNotificar.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonNotificar.setBounds(294, 320, 140, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
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
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		// AVISO DE OPCION NO DISPONIBLE
		botonNotificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FueraDeServicio.avisoNoDisponible();
			}
		});
		contentPane.add(botonNotificar);
		
		// BOTON PARA VOLVER AL MENU PRINCIPAL
		JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
		botonMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonMenuPrincipal.setBounds(438, 320, 140, 25);
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
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		//aqui linkeamos a la otra clase
		botonMenuPrincipal.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                setVisible(false); // SE OCULTA LA CLASE EN LA QUE ESTAMOS
		                
		                Gestor gestor = new Gestor(null);
		                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA LA APLICACI�N AL CERRAR ESTA VENTANA
		                gestor.setVisible(true);
		            }
		        });
		contentPane.add(botonMenuPrincipal);
		
		//METODOS
		
		
	}
}
