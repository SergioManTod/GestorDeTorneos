import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.toedter.calendar.JCalendar;

public class IniciaTorneo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JSpinner JspinnerSelTorneo; // Mover a una variable de instancia

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
        try {
            List<Object[]> torneos = baseDeDatos.listarTorneosInactivos();
            for (Object[] torneo : torneos) {
                modelo.addRow(torneo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Crear la tabla con el modelo
        JTable tablaEquipos = new JTable(modelo);
        tablaEquipos.setSurrendersFocusOnKeystroke(true);
        tablaEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Ajustar el ancho de las columnas
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
        // Ajustar el ancho de la última columna
        TableColumn ultimaColumna = tablaEquipos.getColumnModel().getColumn(tablaEquipos.getColumnCount() - 1);
        ultimaColumna.setPreferredWidth(260);

        // Crear el JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(300, 1, 285, 360);
        contentPane.add(scrollPane);

        // AQUI SE SELECCIONA AL EQUIPO
        JLabel tagSelTorneo = new JLabel("INGRESA EL NÚMERO DEL TORNEO SELECCIONADO");
        tagSelTorneo.setBackground(new Color(240, 240, 240));
        tagSelTorneo.setHorizontalAlignment(SwingConstants.LEFT);
        tagSelTorneo.setForeground(new Color(255, 255, 255));
        tagSelTorneo.setFont(new Font("Tahoma", Font.BOLD, 9));
        tagSelTorneo.setBounds(10, 15, 250, 25);
        contentPane.add(tagSelTorneo);

        // EN ESTE SPINNER SELECCIONAMOS EL NUMERO, EN REFERENCIA A LA TABLA
        JspinnerSelTorneo = new JSpinner();
        JspinnerSelTorneo.setBounds(249, 15, 50, 25);
        contentPane.add(JspinnerSelTorneo);

        // Configurar el spinner después de inicializarlo
        configurarJSpinner();

        // AQUI SELECCIONAMOS LA FECHA DE INICIO DEL TORNEO
        JLabel tagSelFecha = new JLabel("SELECCIONE FECHA DE INICIO:");
        tagSelFecha.setHorizontalAlignment(SwingConstants.CENTER);
        tagSelFecha.setForeground(Color.WHITE);
        tagSelFecha.setFont(new Font("Tahoma", Font.BOLD, 10));
        tagSelFecha.setBackground(UIManager.getColor("Button.background"));
        tagSelFecha.setBounds(10, 45, 280, 25);
        contentPane.add(tagSelFecha);

        // ES EN ESTE CALENDARIO DONDE SE SELECCIONA LA FECHA
        JCalendar calendario = new JCalendar();
        calendario.setBounds(5, 80, 290, 197);
        contentPane.add(calendario);

        // ESTA ETIQUETA VA VACIA Y SOLO MUESTRA EL MENSAJE SI SE CREO CON EXITO O SI NO SE PUDO CREAR
        JLabel tagStatusCal = new JLabel("");
        tagStatusCal.setHorizontalAlignment(SwingConstants.LEFT);
        tagStatusCal.setForeground(Color.WHITE);
        tagStatusCal.setFont(new Font("Tahoma", Font.BOLD, 10));
        tagStatusCal.setBackground(UIManager.getColor("Button.background"));
        tagStatusCal.setBounds(10, 275, 280, 35);
        contentPane.add(tagStatusCal);

        // BOTON PARA CREAR EL CALENDARIO
        JButton botonCrearCalendario = new JButton("CREAR CALENDARIO");
        botonCrearCalendario.setFont(new Font("Tahoma", Font.PLAIN, 9));
        botonCrearCalendario.setBounds(10, 320, 135, 23);
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
                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
                gestor.setVisible(true);
            }
        });
        contentPane.add(botonMenuPrincipal);
    }

    // METODO PARA CONFIGUAR EL JSPINNER
    private void configurarJSpinner() {
        BaseDeDatos baseDeDatos = BaseDeDatos.obtenerInstancia(new JLabel());
        List<Object[]> torneosInactivos = null;
        try {
            torneosInactivos = baseDeDatos.listarIdTorIna();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (torneosInactivos != null && !torneosInactivos.isEmpty()) {
            Integer[] ids = torneosInactivos.stream()
                    .map(torneo -> (Integer) torneo[0])
                    .toArray(Integer[]::new);

            SpinnerListModel model = new SpinnerListModel(ids);
            JspinnerSelTorneo.setModel(model);
        } else {
            JOptionPane.showMessageDialog(this, "No hay torneos inactivos disponibles.");
        }
    }
}