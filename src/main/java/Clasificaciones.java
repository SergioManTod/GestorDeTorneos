import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Clasificaciones extends EstilosFrame {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelo;
    private JTable tablaEquipos;
    private BaseDeDatos baseDeDatos;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Clasificaciones frame = new Clasificaciones();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Clasificaciones() {
        super("Gestor de Torneos V. Gamma - Clasificaciones");
        baseDeDatos = BaseDeDatos.obtenerInstancia(null);

        JLabel tagInstrucciones = new JLabel("SELECCIONA EL TORNEO DEL QUE QUIERES VER LA CLASIFICACIÓN");
        tagInstrucciones.setToolTipText("");
        tagInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 15));
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

        List<String> torneosAct = baseDeDatos.listarTorneosActivos();
        torneosAct.add(0, "SELECCIONE UN TORNEO");
        JComboBox<String> comboBoxTorneo = new JComboBox<>(torneosAct.toArray(new String[0]));
        comboBoxTorneo.setBounds(70, 60, 190, 25);
        contentPane.add(comboBoxTorneo);

        String[] tituloColumna = { "POS.", "EQUIPO", "PUNTOS" };

        modelo = new DefaultTableModel(tituloColumna, 0);
        tablaEquipos = new JTable(modelo);
        tablaEquipos.setSurrendersFocusOnKeystroke(true);
        tablaEquipos.setAutoResizeMode(JTable.WIDTH);

        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(300, 48, 285, 360);
        contentPane.add(scrollPane);

        comboBoxTorneo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = (String) comboBoxTorneo.getSelectedItem();
                if (!"SELECCIONE UN TORNEO".equals(nom)) {
                    try {
                        actualizarTablaClasificacion(nom);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
        configurarBoton(botonMenuPrincipal, 50, 280, 200, 25);
        botonMenuPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Gestor gestor = new Gestor(null);
                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gestor.setVisible(true);
            }
        });

     // IMAGEN DE FONDO
        agregarEtiquetaConImagen("img\\icono_trofeo.png", -250, 5, 512, 512);
    }

    private void actualizarTablaClasificacion(String nom) throws SQLException {
        List<Object[]> clasificacion = baseDeDatos.listaClasificacion(nom);
        modelo.setRowCount(0);
        for (Object[] row : clasificacion) {
            modelo.addRow(row);
        }
    }
}

