import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
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
    	PdfLeer nuevalectura =new PdfLeer(); 
    	List<String> nombreArchivos=nombreArchivos(nom);
    	String path="src/main/torneos/torneo_"+nom+"/Actas_Procesadas/";
    
    	for(int i=0;i<nombreArchivos.size();i++) {
    		String nombreArchivo=nombreArchivos.get(i);
    		System.out.println("nombre archivo"+ nombreArchivo);
    		nuevalectura.leerActa(nombreArchivo,baseDeDatos,nom);
    		copiarYBorrar(nombreArchivo, nom);
    		
    	}
    	
    	
    	
    	List<Object[]> clasificacion = baseDeDatos.listaClasificacion(nom);
        modelo.setRowCount(0);
        for (Object[] row : clasificacion) {
            modelo.addRow(row);
        }
    }
    public static List<String> nombreArchivos(String nombreTorneo){
    	System.out.println("leemos carpeta");
    	List<String> archivos=new ArrayList<String>();
    	File carpeta=new File("src/main/torneos/torneo_"+nombreTorneo+"/Actas_rellenas/");
    	File[]af =carpeta.listFiles();
    	if(af!=null) {
    		for (int i=0 ;i <af.length;i++) {
    			File afa=af[i];
    			if (afa.isFile()) {
    				
    				archivos.add(i,afa.getName());
    				
    				
    			}
    		}
    	}
		return archivos;
}
    public void copiarYBorrar(String nombreArchivo, String nombretorneo) {
    	
    	// Ruta del archivo original
        String rutaActa = "src/main/torneos/torneo_"+nombretorneo+"/Actas_rellenas/"+nombreArchivo;
        // Ruta del archivo de destino
        String rutaDestino = "src/main/torneos/torneo_"+nombretorneo+"/Actas_Procesadas/"+nombreArchivo;

        // Crear objetos File para el archivo original y el archivo de destino
        File sourceFile = new File(rutaActa);
        File destinationFile = new File(rutaDestino);

        try {
            // Copiar el archivo a la nueva ubicación
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado exitosamente.");

            // Borrar el archivo original
            if (sourceFile.delete()) {
                System.out.println("Archivo original borrado exitosamente.");
            } else {
                System.out.println("No se pudo borrar el archivo original.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

