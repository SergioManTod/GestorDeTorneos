import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class Iniciatorneo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iniciatorneo frame = new Iniciatorneo();
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
	public Iniciatorneo() {
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
        scrollPane.setBounds(300, 1, 285, 360); // Establecer la posición y tamaño del JScrollPane

        // Agregar el JScrollPane al contentPane
        contentPane.add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("INGRESA EL N\u00BA DEL TORNEO SELECCIONADO");
        lblNewLabel.setBackground(new Color(240, 240, 240));
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel.setBounds(10, 15, 240, 25);
        contentPane.add(lblNewLabel);
        
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spinner.setBounds(249, 15, 40, 25);
        contentPane.add(spinner);
        
        JLabel lblNewLabel_1 = new JLabel("FECHA DE INICIO:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel_1.setBackground(UIManager.getColor("Button.background"));
        lblNewLabel_1.setBounds(10, 50, 122, 25);
        contentPane.add(lblNewLabel_1);
        // Crea un JLabel para mostrar la imagen
        JLabel imagenFondo = new JLabel();
        
        // Carga la imagen desde un archivo (asegúrate de tener la imagen en el mismo directorio que tu código)
        ImageIcon icono = new ImageIcon("img\\LOGO.png");
        
        JSpinner spinner_1 = new JSpinner();
        spinner_1.setModel(new SpinnerDateModel(new Date(1716501600000L), null, null, Calendar.DAY_OF_WEEK_IN_MONTH));
        spinner_1.setBounds(142, 52, 148, 20);
        contentPane.add(spinner_1);
        
        // Asigna el icono al JLabel
        imagenFondo.setIcon(icono);
        imagenFondo.setBounds(22, 122, 256, 256);
        
        // Agrega el JLabel al JFrame
        getContentPane().add(imagenFondo);
        
        JButton btnNewButton = new JButton("CREAR CALENDARIO");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton.setBounds(15, 122, 135, 23);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("MENU\u00DA PRINCIPAL");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton_1.setBounds(155, 122, 135, 23);
        contentPane.add(btnNewButton_1);
	}
}
