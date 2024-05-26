import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.toedter.calendar.JCalendar;

public class IniciaTorneo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciaTorneo frame = new IniciaTorneo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public IniciaTorneo() {
		
		// FORMATO DEL CONTENTPANE
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos  -  Inicia Torneo");
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
		
		//ARRAY DE OBJETOS DE PRUEBA CON LOS NOMBRES DE LOS TORNEOS
		String[] tituloColumna = {"Nº","NOMBRE DEL TORNEO"};
		Object[][] equipos = {
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {17,"TORNEO DE DESPEDIDA"}
		    };
		
		//TABLA PARA MOSTRAR LOS EQUIPOS
		DefaultTableModel modelo = new DefaultTableModel(equipos, tituloColumna);// CREA UN MODELO DE LA TABLA CON LOS EQUIPOS Y LOS TITULOS DE LAS COLUMNAS
		JTable tablaEquipos = new JTable(modelo);// CREA LA TABLA CON EL MODELO
        tablaEquipos.setSurrendersFocusOnKeystroke(true);
        tablaEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // DESACTIVA EL ANCHO AUTOMATICO DE LAS COLUMNAS

        // AJUSTA EL ANCHO DE CADA COLUMNA A SU CONTENIDO
        for (int i = 0; i < tablaEquipos.getColumnCount(); i++) {
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
        // AJUSTA EL ANCHO DE LA ULTIMA COLUMNA PARA QUE OCUPE 
        TableColumn ultimaColumna = tablaEquipos.getColumnModel().getColumn(tablaEquipos.getColumnCount() - 1);
        ultimaColumna.setPreferredWidth(260); 
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(300, 1, 285, 360); 
        contentPane.add(scrollPane);
        
        // AQUI SE SELECCIONA AL EQUIPO
        JLabel tagSelTorneo = new JLabel("INGRESA EL N\u00BA DEL TORNEO SELECCIONADO");
        tagSelTorneo.setBackground(new Color(240, 240, 240));
        tagSelTorneo.setHorizontalAlignment(SwingConstants.LEFT);
        tagSelTorneo.setForeground(new Color(255, 255, 255));
        tagSelTorneo.setFont(new Font("Tahoma", Font.BOLD, 10));
        tagSelTorneo.setBounds(10, 15, 240, 25);
        contentPane.add(tagSelTorneo);
        
        //EN ESTE SPINNER SELECCIONAMOS EL NUMERO, EN REFERENCIA A LA TABLA
        JSpinner JspinnerSelTorneo = new JSpinner();
        JspinnerSelTorneo.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        JspinnerSelTorneo.setBounds(249, 15, 40, 25);
        contentPane.add(JspinnerSelTorneo);
        
        // AQUI SELECCIONAMOS LA FECHA DE INICIO DEL TORNEO
        JLabel tagSelFecha = new JLabel("SELECCIONE FECHA DE INICIO:");
        tagSelFecha.setHorizontalAlignment(SwingConstants.CENTER);
        tagSelFecha.setForeground(Color.WHITE);
        tagSelFecha.setFont(new Font("Tahoma", Font.BOLD, 10));
        tagSelFecha.setBackground(UIManager.getColor("Button.background"));
        tagSelFecha.setBounds(10, 45, 280, 25);
        contentPane.add(tagSelFecha);
        
        //ES EN ESTE CALENDARIO DONDE SE SELECCIONA LA FECHA
        JCalendar calendario = new JCalendar();
        calendario.setBounds(5, 80, 290, 197);
        contentPane.add(calendario);
        
        
        // ESTA ETIQUETA VA VACIA Y SOLO MUESTRA EL MENSAJE SI SE CREO CON EXITO O SI NO SE PUDO CREAR
        JLabel tagStatusCal = new JLabel("calendario creado con exito");
        tagStatusCal.setHorizontalAlignment(SwingConstants.LEFT);
        tagStatusCal.setForeground(Color.WHITE);
        tagStatusCal.setFont(new Font("Tahoma", Font.BOLD, 10));
        tagStatusCal.setBackground(UIManager.getColor("Button.background"));
        tagStatusCal.setBounds(10, 275, 280, 35);
        contentPane.add(tagStatusCal);
        
        //BOTON PARA CREAR EL CALENDARIO
        JButton cotonCrearCalendario = new JButton("CREAR CALENDARIO");
        cotonCrearCalendario.setFont(new Font("Tahoma", Font.PLAIN, 9));
        cotonCrearCalendario.setBounds(10, 320, 135, 23);
     // INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        cotonCrearCalendario.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	cotonCrearCalendario.setBackground(Color.LIGHT_GRAY);
		    	cotonCrearCalendario.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	cotonCrearCalendario.setBackground(Color.WHITE);
		    	cotonCrearCalendario.setForeground(Color.BLACK);
		    }
		});
     // FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        contentPane.add(cotonCrearCalendario);
        
     // BOTON PARA VOLVER AL MENU PRINCIPAL
        JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
        botonMenuPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 9));
		botonMenuPrincipal.setBounds(155, 320, 135, 23);
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
		// CODIGO PARA LINKEAR AL LA CLASE GESTOR
		botonMenuPrincipal.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                setVisible(false); // OCULTA LA CLASE ACTUAL
		                
		                Gestor gestor = new Gestor();
		                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA EL JFRAME ACTUAL
		                gestor.setVisible(true);
		            }
		        });
		contentPane.add(botonMenuPrincipal);
	}
}
