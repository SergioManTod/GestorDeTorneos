import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import com.toedter.calendar.JCalendar;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class IniciaTorneo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public IniciaTorneo() {
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
		String[] columnNames = {"Nº","NOMBRE DEL TORNEO"};
		Object[][] data = {
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA BOMBEROS"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA BOMBEROS"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA BOMBEROS"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {1,"TORNEO lA MARINA"},
		    {2,"TORNEO DEL ANIVERSARIO" },
		    {3,"TORNEO VIEJAS GLORIAS"},
		    {4,"TORNEO POLICIAS CONTRA BOMBEROS"},
		    {5,"TORNEO DE INVIERNO"},
		    {6,"TORNEO DE VERANO"},
		    {17,"TORNEO DE DESPEDIDA"}
		    };
		// Crear el modelo de la tabla con los datos y nombres de columna
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
		// Crear la JTable con el modelo
        JTable table = new JTable(model);
        table.setSurrendersFocusOnKeystroke(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactivar el ajuste automático del ancho de las columnas

        // Ajustar el ancho de cada columna según su contenido
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int width = (int) table.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(table, column.getHeaderValue(), false, false, -1, i)
                    .getPreferredSize().getWidth();
            for (int j = 0; j < table.getRowCount(); j++) {
                int preferedWidth = (int) table.getCellRenderer(j, i)
                        .getTableCellRendererComponent(table, table.getValueAt(j, i), false, false, j, i)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            column.setPreferredWidth(width + 10); // Añadir un pequeño espacio adicional
        }
     // Ajustar el ancho de la última columna para que ocupe el espacio restante
        TableColumn lastColumn = table.getColumnModel().getColumn(table.getColumnCount() - 1);
        lastColumn.setPreferredWidth(260); // Un valor grande para que ocupe todo el espacio restante
        // Crear un JScrollPane y agregar la tabla a él
        // Crear un JScrollPane y agregar la tabla a él
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(300, 1, 285, 360); // Establecer la posición y tamaño del JScrollPane

        // Agregar el JScrollPane al contentPane
        contentPane.add(scrollPane);
        
        JLabel etiquetaSelTorneo = new JLabel("INGRESA EL N\u00BA DEL TORNEO SELECCIONADO");
        etiquetaSelTorneo.setBackground(new Color(240, 240, 240));
        etiquetaSelTorneo.setHorizontalAlignment(SwingConstants.LEFT);
        etiquetaSelTorneo.setForeground(new Color(255, 255, 255));
        etiquetaSelTorneo.setFont(new Font("Tahoma", Font.BOLD, 10));
        etiquetaSelTorneo.setBounds(10, 15, 240, 25);
        contentPane.add(etiquetaSelTorneo);
        
        JSpinner spinnerSelTorneo = new JSpinner();
        spinnerSelTorneo.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spinnerSelTorneo.setBounds(249, 15, 40, 25);
        contentPane.add(spinnerSelTorneo);
        
        JLabel etiquetaSelFecha = new JLabel("SELECCIONE FECHA DE INICIO:");
        etiquetaSelFecha.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaSelFecha.setForeground(Color.WHITE);
        etiquetaSelFecha.setFont(new Font("Tahoma", Font.BOLD, 10));
        etiquetaSelFecha.setBackground(UIManager.getColor("Button.background"));
        etiquetaSelFecha.setBounds(10, 45, 280, 25);
        contentPane.add(etiquetaSelFecha);
        
        JCalendar calendario = new JCalendar();
        calendario.setBounds(0, 75, 300, 190);
        contentPane.add(calendario);
        calendario.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{calendario.getMonthChooser(), calendario.getMonthChooser().getSpinner(), calendario.getMonthChooser().getComboBox(), calendario.getYearChooser(), calendario.getYearChooser().getSpinner(), calendario.getDayChooser(), calendario.getDayChooser().getDayPanel()}));
        
        JLabel lblCalendarioCreadoCon = new JLabel("calendario creado con exito");
        lblCalendarioCreadoCon.setHorizontalAlignment(SwingConstants.LEFT);
        lblCalendarioCreadoCon.setForeground(Color.WHITE);
        lblCalendarioCreadoCon.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblCalendarioCreadoCon.setBackground(UIManager.getColor("Button.background"));
        lblCalendarioCreadoCon.setBounds(10, 275, 280, 35);
        contentPane.add(lblCalendarioCreadoCon);
        
        JButton cotonCrearCalendario = new JButton("CREAR CALENDARIO");
        cotonCrearCalendario.setFont(new Font("Tahoma", Font.PLAIN, 9));
        cotonCrearCalendario.setBounds(10, 320, 135, 23);
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
        contentPane.add(cotonCrearCalendario);
        
        JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
        botonMenuPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 9));
		botonMenuPrincipal.setBounds(155, 320, 135, 23);
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
	}
}
