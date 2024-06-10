import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Inicio extends JFrame {
    private BaseDeDatos baseDeDatos;
    private JLabel tagMsgeConBbDd;
    private JPanel contentPane;
    private JPanel panelDatosConexion;
    private JComboBox<String> comboBoxConexion;

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
    	baseDeDatos = BaseDeDatos.obtenerInstancia(tagMsgeConBbDd);
    	
        tagMsgeConBbDd = new JLabel();
        tagMsgeConBbDd.setToolTipText("");
        tagMsgeConBbDd.setHorizontalAlignment(SwingConstants.CENTER);
        tagMsgeConBbDd.setForeground(Color.WHITE);
        tagMsgeConBbDd.setFont(new Font("Tahoma", Font.BOLD, 15));
        tagMsgeConBbDd.setBounds(20, 250, 560, 100);

        

        setForeground(new Color(255, 255, 255));
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/icono_trofeo.png"));
        setTitle("Gestor de Torneos V. Gamma  -  Bienvenido!");
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

        comboBoxConexion = new JComboBox<>();
        comboBoxConexion.setFont(new Font("Tahoma", Font.PLAIN, 12));  
        comboBoxConexion.setBounds(80, 100, 190, 25);
        contentPane.add(comboBoxConexion);
        comboBoxConexion.addItem("SELECCIONA BASE DE DATOS");
        File folder = new File("src/main/resources/");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".properties"));
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                comboBoxConexion.addItem(file.getName());
            }
        }
        comboBoxConexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = (String) comboBoxConexion.getSelectedItem();
                if (selectedFile != null && !selectedFile.equals("SELECCIONA BASE DE DATOS")) {
                    Connection con = baseDeDatos.conectar(selectedFile);
                    if (con != null) {
                        tagMsgeConBbDd.setText("Conexión establecida correctamente");

                        Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                setVisible(false); 
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
        
        JButton botonNuevaConexion = new JButton("Crear nueva conexión");
        botonNuevaConexion.setBounds(280, 100, 250, 25);
        botonNuevaConexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDatosConexion.setVisible(true);
            }
        });
        contentPane.add(botonNuevaConexion);

        panelDatosConexion = new JPanel();
        panelDatosConexion.setBounds(10, 150, 600, 150);
        panelDatosConexion.setBackground(new Color(152, 180, 216));
        panelDatosConexion.setLayout(null);
        panelDatosConexion.setVisible(false);
        contentPane.add(panelDatosConexion);
        
        JButton btnDescargarSQL = new JButton("Descargar BB DD");
        btnDescargarSQL.setBounds(10, 0, 140, 25);
        btnDescargarSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("src/main/resources/GestorDeTorneosBbDd.sql");
                    if (!file.exists()) {
                        throw new IOException("El archivo no existe");
                    }
                    // Abre el archivo GestorDeTorneosBbDd.sql
                    Desktop.getDesktop().open(file);
                    tagMsgeConBbDd.setText("Archivo descargado correctamente");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    // Maneja la excepción si el archivo no se encuentra
                    tagMsgeConBbDd.setText("Error al descargar el archivo SQL: " + ex.getMessage());
                }
            }
        });
        panelDatosConexion.add(btnDescargarSQL);

        JLabel tagUser = new JLabel("Usuario:");
        tagUser.setBounds(160, 0, 50, 25);
        panelDatosConexion.add(tagUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(220, 0, 120, 25);
        panelDatosConexion.add(txtUser);

        JLabel tagPassword = new JLabel("Contraseña:");
        tagPassword.setBounds(350, 0, 70, 25);
        panelDatosConexion.add(tagPassword);

        JTextField txtPassword = new JTextField();
        txtPassword.setBounds(430, 0, 135, 25);
        panelDatosConexion.add(txtPassword);

        JLabel tagNombreBbDd = new JLabel("Nombre de la BB DD:");
        tagNombreBbDd.setBounds(10, 30, 120, 25);
        panelDatosConexion.add(tagNombreBbDd);

        JTextField txtNombreBbDd = new JTextField();
        txtNombreBbDd.setBounds(140, 30, 140, 25);
        panelDatosConexion.add(txtNombreBbDd);
        
        JLabel tagUrl = new JLabel("URL:");
        tagUrl.setBounds(290, 30, 30, 25);
        panelDatosConexion.add(tagUrl);

        JTextField txtUrl = new JTextField();
        txtUrl.setBounds(330, 30, 235, 25);
        panelDatosConexion.add(txtUrl);

        JButton btnCrearConfiguracion = new JButton("Crear archivo de configuración");
        btnCrearConfiguracion.setBounds(150, 60, 300, 25);
        btnCrearConfiguracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUser.getText();
                String password = txtPassword.getText();
                String nomBbDd = txtNombreBbDd.getText();
                String url = txtUrl.getText();

                if (user.isEmpty() || password.isEmpty() || nomBbDd.isEmpty() || url.isEmpty()) {
                    tagMsgeConBbDd.setText("Todos los campos son obligatorios");
                    return;
                }

                Properties properties = new Properties();
                properties.setProperty("user", user);
                properties.setProperty("password", password);
                properties.setProperty("url", url);
                properties.setProperty("driver", "com.mysql.cj.jdbc.Driver");

                try {
                    File file = new File("src/main/resources/bd_" + nomBbDd + ".properties");
                    if (file.exists()) {
                        throw new IOException("El archivo ya existe");
                    }
                    properties.store(new FileWriter(file), null);
                    tagMsgeConBbDd.setText("Archivo de configuración creado correctamente");

                    // Actualizar elementos del JComboBox
                    comboBoxConexion.removeAllItems();
                    comboBoxConexion.addItem("SELECCIONA BASE DE DATOS");
                    File[] listOfFiles = new File("src/main/resources/").listFiles((dir, name) -> name.endsWith(".properties"));
                    if (listOfFiles != null) {
                        for (File f : listOfFiles) {
                            comboBoxConexion.addItem(f.getName());
                        }
                    }
                } catch (IOException ex) {
                    tagMsgeConBbDd.setText("Error al crear el archivo de configuración: " + ex.getMessage());
                }
            }
        });
        panelDatosConexion.add(btnCrearConfiguracion);

        setLocationRelativeTo(null);
    }
}

