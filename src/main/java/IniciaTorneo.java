import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JCalendar;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ScrollPaneConstants;

public class IniciaTorneo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField numPartidosDia;

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
        setTitle("Aplicación Gestora de Torneos  -  Iniciar torneos");
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

        // Títulos de las columnas
        String[] tituloColumna = {"Nº", "NOMBRE DEL TORNEO"};
        DefaultTableModel modelo = new DefaultTableModel(tituloColumna, 0);

        // Obtener los datos de la base de datos y llenar el modelo
        BaseDeDatos baseDeDatos = BaseDeDatos.obtenerInstancia(null);
//        try {
//            List<Object[]> torneos = baseDeDatos.listarTorneosInactivos();
//            for (Object[] torneo : torneos) {
//                modelo.addRow(torneo);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // Ajustar el ancho de las columnas
//        for (int i = 0; i < tablaEquipos.getColumnCount(); i++) {
//            TableColumn columna = tablaEquipos.getColumnModel().getColumn(i);
//            int width = (int) tablaEquipos.getTableHeader().getDefaultRenderer()
//                    .getTableCellRendererComponent(tablaEquipos, columna.getHeaderValue(), false, false, -1, i)
//                    .getPreferredSize().getWidth();
//            for (int j = 0; j < tablaEquipos.getRowCount(); j++) {
//                int preferedWidth = (int) tablaEquipos.getCellRenderer(j, i)
//                        .getTableCellRendererComponent(tablaEquipos, tablaEquipos.getValueAt(j, i), false, false, j, i)
//                        .getPreferredSize().getWidth();
//                width = Math.max(width, preferedWidth);
//            }
//            columna.setPreferredWidth(width + 10);
//        }
//        ultimaColumna.setPreferredWidth(260);
//
//        // AQUI SE SELECCIONA AL EQUIPO
//        JLabel tagSelTorneo = new JLabel("selecciona torneo");
//        tagSelTorneo.setBackground(new Color(240, 240, 240));
//        tagSelTorneo.setHorizontalAlignment(SwingConstants.LEFT);
//        tagSelTorneo.setForeground(new Color(255, 255, 255));
//        tagSelTorneo.setFont(new Font("Tahoma", Font.BOLD, 9));
//        tagSelTorneo.setBounds(10, 15, 114, 25);
//        contentPane.add(tagSelTorneo);
//
//        // Configurar el spinner después de inicializarlo
//        configurarJSpinner();

        // AQUI SELECCIONAMOS LA FECHA DE INICIO DEL TORNEO
        JLabel tagSelFecha = new JLabel("Seleccione una fecha y pulse el botón \"Añadir fecha\"");
        tagSelFecha.setHorizontalAlignment(SwingConstants.CENTER);
        tagSelFecha.setForeground(Color.WHITE);
        tagSelFecha.setFont(new Font("Tahoma", Font.BOLD, 10));
        tagSelFecha.setBackground(UIManager.getColor("Button.background"));
        tagSelFecha.setBounds(10, 50, 290, 25);
        contentPane.add(tagSelFecha);

        // ES EN ESTE CALENDARIO DONDE SE SELECCIONA LA FECHA
        JCalendar calendario = new JCalendar();
        calendario.setBounds(10, 80, 290, 200);
        contentPane.add(calendario);

        // ESTA ETIQUETA VA VACIA Y SOLO MUESTRA EL MENSAJE SI SE CREO CON EXITO O SI NO SE PUDO CREAR
        JLabel tagStatusCal = new JLabel("");
        tagStatusCal.setHorizontalAlignment(SwingConstants.LEFT);
        tagStatusCal.setForeground(Color.WHITE);
        tagStatusCal.setFont(new Font("Tahoma", Font.BOLD, 10));
        tagStatusCal.setBackground(UIManager.getColor("Button.background"));
        tagStatusCal.setBounds(10, 285, 290, 44);
        contentPane.add(tagStatusCal);

        // BOTON PARA CREAR EL CALENDARIO
        JButton botonCrearCalendario = new JButton("CREAR CALENDARIO");
        botonCrearCalendario.setFont(new Font("Tahoma", Font.PLAIN, 9));
        botonCrearCalendario.setBounds(230, 350, 140, 25);
        // INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        botonCrearCalendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCrearCalendario.setBackground(Color.LIGHT_GRAY);
                botonCrearCalendario.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCrearCalendario.setBackground(Color.WHITE);
                botonCrearCalendario.setForeground(Color.BLACK);
            }
        });
        // FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        botonCrearCalendario.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       /* tagMsgeActTorneoBbDd.setText("");
		        String nombreTorneo = textFieldNomTorneo.getText();
		        int cantEquipos = (int) jSpinnerCantEquipos.getValue();
		        int cantJugadores = (int) jSpinnerCantJugadores.getValue();
		        JLabel tagMsgeConBbDd = new JLabel();
				Torneo nuevoTorneo = new Torneo(nombreTorneo, cantEquipos, cantJugadores, BaseDeDatos.obtenerInstancia(tagMsgeConBbDd).obtenerConexion());
		        String mensajeError = null;
		        try {
		            boolean nombreTorneoValido = nuevoTorneo.comprobarNombreTorneo(nombreTorneo);
		            if (nombreTorneo.length() == 0) {
		                mensajeError = "No puede estar el campo nombre vacío.";
		            } else if (!nombreTorneoValido) {
		                mensajeError = "<html>El torneo "+nombreTorneo+" ya existe en la base de datos.<br>Intentalo asignando otro nombre.</html>";
		            }
		            if (mensajeError == null) {
		                BaseDeDatos baseDeDatos = new BaseDeDatos(tagMsgeConBbDd);
		                baseDeDatos.insertaTorneoNuevo(nuevoTorneo);
		                tagMsgeActTorneoBbDd.setText("<html>El Torneo " + nuevoTorneo.getNombTorneo() + "<br>se ha guardado correctamente en la Base de Datos.<br>Con un mínimo de " + nuevoTorneo.getCantEquipos() + " equipos participantes <br>y un mínimo de " + nuevoTorneo.getCantJugadores() + " jugadores por equipo.</html>");
		                PdfCrear inscripcion = new PdfCrear();
		                inscripcion.crearpdfFormulario(nombreTorneo, cantJugadores);
		                CreaDirectorios nuevoArbolDeDirectorios = new CreaDirectorios();
		                nuevoArbolDeDirectorios.arbolDeCarpetas(nombreTorneo);
		            } else {
		            	tagMsgeActTorneoBbDd.setText(mensajeError);
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(contentPane, "Error al hacer la consulta en la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        contentPane.revalidate();
		        contentPane.repaint();*/
		    	
		    }
		});
        contentPane.add(botonCrearCalendario);

        // BOTON PARA VOLVER AL MENU PRINCIPAL
        JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
        botonMenuPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 9));
        botonMenuPrincipal.setBounds(400, 350, 140, 25);
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

                Gestor gestor = new Gestor(null);
                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
                gestor.setVisible(true);
            }
        });
        contentPane.add(botonMenuPrincipal);
        
        List<String> torneos =baseDeDatos.listarTorneosInactivos();
        JComboBox comboBox = new JComboBox<>(torneos.toArray(new String[0]));
        comboBox.setBounds(10, 20, 290, 25);
        contentPane.add(comboBox);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(310, 55, 265, 283);
        contentPane.add(scrollPane);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        List<String> lista = new ArrayList<>();
       
        JButton botonAniadirFecha = new JButton("AÑADIR FECHA");
        botonAniadirFecha.setBounds(50, 350, 140, 25);
     // INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        botonAniadirFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	botonAniadirFecha.setBackground(Color.LIGHT_GRAY);
            	botonAniadirFecha.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	botonAniadirFecha.setBackground(Color.WHITE);
            	botonAniadirFecha.setForeground(Color.BLACK);
            }
        });
        // FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        botonAniadirFecha.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Date selectedDate = calendario.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(selectedDate);
                boolean fechaValida=true;
                for (int i = 0; i < listModel.size(); i++) {
                	System.out.println(listModel.getElementAt(i));
                   if(formattedDate.equals(listModel.getElementAt(i))) {
                	   fechaValida=false;
                   }
                }
                if(fechaValida) {
                	listModel.addElement(formattedDate);
                }else {
                	//aqui va un cartel de aviso de que esta repetida la fecha
                }
                
                
        	}
        });
        contentPane.add(botonAniadirFecha);
        
        JLabel lblNewLabel = new JLabel("Número de partidos por dia");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel.setBounds(330, 20, 160, 25);
        contentPane.add(lblNewLabel);
        
        numPartidosDia = new JTextField();
        numPartidosDia.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char car = e.getKeyChar();
        	    if ((car < '0' || car > '9')) {
        	        e.consume(); // Evita que se ingrese el carácter no permitido
        	    }
        	}
        });
        numPartidosDia.setBounds(500, 20, 50, 25);
        contentPane.add(numPartidosDia);
        numPartidosDia.setColumns(10);
        JList<String> list= new JList<>(listModel);
        list.setBounds(330, 80, 112, 239);
        contentPane.add(list);
    }

    
    // METODO PARA CONFIGUAR EL JSPINNER
//    private void configurarJSpinner() {
//        BaseDeDatos baseDeDatos = BaseDeDatos.obtenerInstancia(new JLabel());
//        List<Object[]> torneosInactivos = null;
//        try {
//            torneosInactivos = baseDeDatos.listarIdTorIna();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (torneosInactivos != null && !torneosInactivos.isEmpty()) {
//            Integer[] ids = torneosInactivos.stream()
//                    .map(torneo -> (Integer) torneo[0])
//                    .toArray(Integer[]::new);
//
//            SpinnerListModel model = new SpinnerListModel(ids);
//            JspinnerSelTorneo.setModel(model);
//        } else {
//            JOptionPane.showMessageDialog(this, "No hay torneos inactivos disponibles.");
//        }
//    }
}