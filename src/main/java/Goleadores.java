import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Goleadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Goleadores frame = new Goleadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Goleadores() {
		// FORMATO DEL CONTENTPANE
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos  -  Goleadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.setBackground(new Color(152, 180, 216));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		// FIN DEL FORMATO DEL CONTENTPANE
		
		JLabel tagInstrucciones = new JLabel("SELECCIONA EL TORNEO DEL QUE QUIERES VER LA CLASIFICACÍON DE GOLES");
		tagInstrucciones.setToolTipText("");
		tagInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 12));
		tagInstrucciones.setForeground(new Color(255, 255, 255));
		tagInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		tagInstrucciones.setBounds(10, 11, 564, 25);
		contentPane.add(tagInstrucciones);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 47, 584, 2);
		contentPane.add(separator);
		
		// SELECCION DE TORNEO
		JLabel tagSelTorneo = new JLabel("TORNEO:");
		tagSelTorneo.setForeground(new Color(255, 255, 255));
		tagSelTorneo.setBounds(10, 60, 68, 25);
		contentPane.add(tagSelTorneo);
		
		// COMOBOX DEL LISTADO DE TORNEOS
		JComboBox comboBoxTorneo = new JComboBox();
		comboBoxTorneo.setModel(new DefaultComboBoxModel(new String[] {"-->SELECCIONE TORNEO<--", "Los gauchitos", "Diseminados por el viento", "LA MARINA","Los gauchitos", "Diseminados por el viento", "LA MARINA","Los gauchitos", "Diseminados por el viento", "LA MARINA", "Cuatro locos"}));//AQUI VA LA CONEXION CON LA BBDD
		comboBoxTorneo.setBounds(70, 60, 190, 25);
		contentPane.add(comboBoxTorneo);
		
		//ARRAY DE OBJETOS DE PRUEBA CON LOS NOMBRES DE LOS EQUIPOS CLASIFICACION
		String[] tituloColumna = {"POS.","JUGADOR","EQUIPO", "GOLES"};
		Object[][] equipos = {
				{1,"Paco Martinez","TORNEO lA MARINA",52},
			    {2,"Paco Martinez","TORNEO DEL ANIVERSARIO",50 },
			    {3,"Paco Martinez","TORNEO VIEJAS GLORIAS",50},
			    {4,"Paco Martinez","TORNEO POLICIAS CONTRA",40},
			    {5,"Paco Martinez","TORNEO DE INVIERNO",39},
			    {6,"Paco Martinez","TORNEO DE VERANO",38},
			    {1,"Jose Alberto Medina Cuesta","TORNEO lA MARINA",38},
			    {1,"Paco Martinez","TORNEO lA MARINA",52},
			    {2,"Paco Martinez","TORNEO DEL ANIVERSARIO",50 },
			    {3,"Paco Martinez","TORNEO VIEJAS GLORIAS",50},
			    {4,"Paco Martinez","TORNEO POLICIAS CONTRA",40},
			    {5,"Paco Martinez","TORNEO DE INVIERNO",39},
			    {6,"Paco Martinez","TORNEO DE VERANO",38},
			    {1,"Paco Martinez","TORNEO lA MARINA",52},
			    {2,"Paco Martinez","TORNEO DEL ANIVERSARIO",50 },
			    {3,"Paco Martinez","TORNEO VIEJAS GLORIAS",50},
			    {4,"Paco Martinez","TORNEO POLICIAS CONTRA",40},
			    {5,"Paco Martinez","TORNEO DE INVIERNO",39},
			    {6,"Paco Martinez","TORNEO DE VERANO",38},
			    {1,"Jose Alberto Medina Cuesta","TORNEO lA MARINA",38},
			    {1,"Paco Martinez","TORNEO lA MARINA",52},
			    {2,"Paco Martinez","TORNEO DEL ANIVERSARIO",50 },
			    {3,"Paco Martinez","TORNEO VIEJAS GLORIAS",50},
			    {4,"Paco Martinez","TORNEO POLICIAS CONTRA",40},
			    {5,"Paco Martinez","TORNEO DE INVIERNO",39},
			    {6,"Paco Martinez","TORNEO DE VERANO",38},
			    {1,"Jose Alberto Medina Cuesta","TORNEO lA MARINA",38}
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
        // AJUSTA EL ANCHO DE LA ULTIMA COLUMNA PARA QUE OCUPE TODO
        TableColumn ultimaColumna = tablaEquipos.getColumnModel().getColumn(tablaEquipos.getColumnCount() - 1);
        ultimaColumna.setPreferredWidth(55); 
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(75, 90,450, 265); 
        contentPane.add(scrollPane);
        
     // BOTON PARA VOLVER AL MENU PRINCIPAL
		JButton botonMenuPrincipal = new JButton("MEN\u00DA PRINCIPAL");
		botonMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 10));
		botonMenuPrincipal.setBounds(330, 60, 200, 25);
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
		// FIN DE CODIGO PARA DAR ESTILO HOVER AL BOTON
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
		
		// IMAGEN DE FONDO
        JLabel tagImagenDeFondo = new JLabel();
        // SE CREA UN IMAGEICON CON LA IMAGEN DE FONDO
        ImageIcon copa = new ImageIcon("img\\balon.png");
        // SE LE ASIGNA EL IMAGEICON A LA ETIQUETA
        tagImagenDeFondo.setIcon(copa);
        tagImagenDeFondo.setBounds(80, 35, 512, 512); 
        getContentPane().add(tagImagenDeFondo);
	}

}
