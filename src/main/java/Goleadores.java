import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

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
    private JTable tablaEquipos;
    private DefaultTableModel modelo;
    private JComboBox<String> comboBoxTorneo;
    private BaseDeDatos baseDeDatos;

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
        baseDeDatos = BaseDeDatos.obtenerInstancia(null);
        
        // FORMATO DEL CONTENTPANE
        setForeground(new Color(255, 255, 255));
        setResizable(false);
        setType(Type.POPUP);
        setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
        setTitle("Gestor de Torneos V. Gamma  -  Goleadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 420);
        contentPane = new JPanel();
        contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
        contentPane.setPreferredSize(new Dimension(600, 420));
        contentPane.setBackground(new Color(152, 180, 216));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel tagInstrucciones = new JLabel("SELECCIONA EL TORNEO DEL QUE QUIERES VER LA CLASIFICACIÓN DE GOLES");
        tagInstrucciones.setToolTipText("");
        tagInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 12));
        tagInstrucciones.setForeground(new Color(255, 255, 255));
        tagInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
        tagInstrucciones.setBounds(10, 11, 564, 25);
        contentPane.add(tagInstrucciones);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 47, 584, 2);
        contentPane.add(separator);

        JLabel tagSelTorneo = new JLabel("TORNEO:");
        tagSelTorneo.setForeground(new Color(255, 255, 255));
        tagSelTorneo.setBounds(10, 60, 68, 25);
        contentPane.add(tagSelTorneo);

        // COMBOBOX DEL LISTADO DE TORNEOS
        List<String> torneosAct = baseDeDatos.listarTorneosActivos();
        torneosAct.add(0, "SELECCIONE UN TORNEO");
        comboBoxTorneo = new JComboBox<>(torneosAct.toArray(new String[0]));
        comboBoxTorneo.setBounds(70, 60, 190, 25);
        contentPane.add(comboBoxTorneo);

        // EVENTO PARA CARGAR LA LISTA DE GOLEADORES CUANDO SE SELECCIONA UN TORNEO
        comboBoxTorneo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = (String) comboBoxTorneo.getSelectedItem();
                if (!nom.equals("SELECCIONE UN TORNEO")) {
                    try {
                    	Torneo nuevoTorneo= baseDeDatos.consultaTorneo(nom);
                    	int idTorneo=nuevoTorneo.getId();
                        List<Object[]> goleadores = baseDeDatos.listaGoleadores(idTorneo);
                        actualizarTablaGoleadores(goleadores);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // TITULOS DE COLUMNAS
        String[] tituloColumna = {"POS.", "JUGADOR", "EQUIPO", "GOLES"};
        
        // TABLA PARA MOSTRAR LOS GOLEADORES
        modelo = new DefaultTableModel(new Object[0][0], tituloColumna);
        tablaEquipos = new JTable(modelo);
        tablaEquipos.setSurrendersFocusOnKeystroke(true);
        tablaEquipos.setAutoResizeMode(JTable.WIDTH);

        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(75, 90, 450, 265);
        contentPane.add(scrollPane);

        // BOTON PARA VOLVER AL MENU PRINCIPAL
        JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
        botonMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 10));
        botonMenuPrincipal.setBounds(330, 60, 200, 25);
        
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

        botonMenuPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Gestor gestor = new Gestor(null);
                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gestor.setVisible(true);
            }
        });
        contentPane.add(botonMenuPrincipal);

        // IMAGEN DE FONDO
        JLabel tagImagenDeFondo = new JLabel();
        ImageIcon copa = new ImageIcon("img\\balon.png");
        tagImagenDeFondo.setIcon(copa);
        tagImagenDeFondo.setBounds(80, 35, 512, 512);
        getContentPane().add(tagImagenDeFondo);
    }

    private void actualizarTablaGoleadores(List<Object[]> goleadores) {
        // LIMPIAR LA TABLA ACTUAL
        modelo.setRowCount(0);

        // AGREGAR NUEVAS FILAS A LA TABLA
        for (Object[] goleador : goleadores) {
            modelo.addRow(goleador);
        }

        // AJUSTAR EL ANCHO DE CADA COLUMNA
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
        TableColumn ultimaColumna = tablaEquipos.getColumnModel().getColumn(tablaEquipos.getColumnCount() - 1);
        ultimaColumna.setPreferredWidth(55);
    }
}
