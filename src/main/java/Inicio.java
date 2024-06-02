import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Inicio extends JFrame {
    private BaseDeDatos baseDeDatos;
    private JLabel tagMsgeConBbDd;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Inicio frame = new Inicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Inicio() {
        // JLABLE MENSAJE DE CONEXION EN LA BBDD
        tagMsgeConBbDd = new JLabel();
        tagMsgeConBbDd.setToolTipText("");
        tagMsgeConBbDd.setHorizontalAlignment(SwingConstants.CENTER);
        tagMsgeConBbDd.setForeground(Color.WHITE);
        tagMsgeConBbDd.setFont(new Font("Tahoma", Font.BOLD, 15));
        tagMsgeConBbDd.setBounds(20, 250, 560, 100);

        baseDeDatos = BaseDeDatos.obtenerInstancia(tagMsgeConBbDd);

        // FORMATO DEL CONTENTPANE
        setForeground(new Color(255, 255, 255));
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/icono_trofeo.png"));
        setTitle("Aplicación Gestora de Torneos  -  Bienvenido!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 420);
        contentPane = new JPanel();
        contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
        contentPane.setPreferredSize(new Dimension(600, 420));
        contentPane.setBackground(new Color(152, 180, 216));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(tagMsgeConBbDd);

        JLabel tagBienvenida = new JLabel("<html><p align='center'>Bienvenido al Gestor de Torneos 1.0<br>¿En qué Base de Datos quieres trabajar?</p></html>");
        tagBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        tagBienvenida.setForeground(new Color(255, 255, 255));
        tagBienvenida.setFont(new Font("Tahoma", Font.BOLD, 20));
        tagBienvenida.setBounds(10, 20, 580, 50);
        contentPane.add(tagBienvenida);

        JLabel tagElige = new JLabel("Elige:");
        tagElige.setHorizontalAlignment(SwingConstants.RIGHT);
        tagElige.setForeground(new Color(255, 255, 255));
        tagElige.setBounds(10, 100, 60, 25);
        contentPane.add(tagElige);

        JComboBox<String> comboBoxConexion = new JComboBox<>();
        comboBoxConexion.setFont(new Font("Tahoma", Font.PLAIN, 12));  
        comboBoxConexion.setBounds(80, 100, 190, 25);
        contentPane.add(comboBoxConexion);

        // Añadir el mensaje inicial en el JComboBox
        comboBoxConexion.addItem("SELECCIONA BASE DE DATOS");

        // Cargar archivos desde la carpeta de recursos
        File folder = new File("src/main/resources/");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".properties"));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                comboBoxConexion.addItem(file.getName());
            }
        }

        // Agregar un ActionListener al JComboBox
        comboBoxConexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el archivo seleccionado
                String selectedFile = (String) comboBoxConexion.getSelectedItem();
                if (selectedFile != null && !selectedFile.equals("SELECCIONA BASE DE DATOS")) {
                    // CONEXION A LA BBDD
                    Connection con = baseDeDatos.conectar(selectedFile);
                    if (con != null) {
                        tagMsgeConBbDd.setText("Conexión establecida correctamente");

                        // Espera 2 segundos y luego abre la clase Gestor
                        Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                setVisible(false); // Oculta la ventana actual

                                // Abre la clase Gestor pasando la conexión
                                BaseDeDatos baseDeDatos = new BaseDeDatos(con);
                                Gestor gestor = new Gestor(con);
                                gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                gestor.setVisible(true);
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    } else {
                        tagMsgeConBbDd.setText("Error al conectar con la base de datos");
                    }
                }
            }
        });
        
        JButton botonNuevaConexion = new JButton("O crea una nueva Base de Datos");
        botonNuevaConexion.setBounds(280, 100, 250, 25);
     // INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        botonNuevaConexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	botonNuevaConexion.setBackground(Color.LIGHT_GRAY);
            	botonNuevaConexion.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	botonNuevaConexion.setBackground(Color.WHITE);
            	botonNuevaConexion.setForeground(Color.BLACK);
            }
        });
        // FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
        contentPane.add(botonNuevaConexion);
        setLocationRelativeTo(null);
    }
}
