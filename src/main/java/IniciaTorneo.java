import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class IniciaTorneo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField numPartidosDia;
    private static JLabel tagStatusCal;

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
        setTitle("Gestor de Torneos 1.0  -  Iniciar torneos");
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
        tagStatusCal = new JLabel();
        tagStatusCal.setHorizontalAlignment(SwingConstants.CENTER);
        tagStatusCal.setForeground(Color.WHITE);
        tagStatusCal.setFont(new Font("Tahoma", Font.PLAIN, 12));
        tagStatusCal.setBackground(UIManager.getColor("Button.background"));
        tagStatusCal.setToolTipText("");
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
        
        List<String> torneos = baseDeDatos.listarTorneosInactivos();
        torneos.add(0, "SELECCIONE UN TORNEO"); // Agrega el mensaje al principio de la lista
        JComboBox<String> comboBox = new JComboBox<>(torneos.toArray(new String[0]));
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
        botonAniadirFecha.setFont(new Font("Tahoma", Font.PLAIN, 9));
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
     // AVISO DE OPCION NO DISPONIBLE
        botonAniadirFecha.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) {
     				FueraDeServicio.avisoNoDisponible();
     			}
     		});
      /*  botonAniadirFecha.addActionListener(new ActionListener() {
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
        });*/
        contentPane.add(botonAniadirFecha);
        
        JLabel lblNewLabel = new JLabel("Número de partidos por dia");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
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


        
        botonCrearCalendario.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String nombreTorneoCombo =(String)comboBox.getSelectedItem();
		    	System.out.println("combo"+nombreTorneoCombo);
		      if(crearTorneo(nombreTorneoCombo,baseDeDatos)) {
		    	  
		      };
		    	
		    }
		});
    }

    public static boolean crearTorneo(String nombreTorneocombo, BaseDeDatos baseDeDatos) {
    	List <String[]> datosTorneo=new ArrayList<>();
    	PdfLeer nuevaLectura=new PdfLeer();
    	try {
			datosTorneo=baseDeDatos.consultaTorneo(nombreTorneocombo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int id=Integer.parseInt(datosTorneo.get(0)[0]);
    	System.out.println("id = "+id);
    	 String nombreTorneo=datosTorneo.get(1)[0];
    	 System.out.println("nombre torneo ="+nombreTorneo);
    	int numMinJugadores=Integer.parseInt(datosTorneo.get(2)[0]);
    	System.out.println("numero minimimos de jugaderos="+numMinJugadores);
    	int numMinEquipos=Integer.parseInt(datosTorneo.get(3)[0]);
    	System.out.println("numero minimimos de equipos ="+numMinEquipos);
//    	int activo=Integer.parseInt(datosTorneo.get(4)[0]);
//    	System.out.println("activo ="+activo);
    	
    	 ArrayList<Object[]> listaequipos = new ArrayList<Object[]>();
    	 List<String> nombreArchivos=nombreArchivos(nombreTorneocombo);
    	 for(int i=0;i<nombreArchivos.size();i++) {
    		 System.out.println(nombreArchivos.get(i));
    	 }
    	 Equipo arrayEquipos[]=new Equipo[nombreArchivos.size()];
    	 if(nombreArchivos.size()<numMinEquipos) {
    		 tagStatusCal.setText("<html><p align=center>No se puede iniciar el torneo "+nombreTorneocombo+".<br>"
    		 		+ "Hacen fallta al menos "+numMinEquipos+" equipos inscritos.</p></html>");;
    	 }else {
    		 int formulariosRechazados=0;
    		 for(int i=0;i<nombreArchivos.size();i++) {
        		 String nombreArchivo=nombreArchivos.get(i);
        		 if (nuevaLectura.leerPdf(numMinJugadores,nombreTorneocombo,nombreArchivo)==null) {
        			 System.out.println("pdf incorrecto");
        			 formulariosRechazados++;
        			 
        		 }else {
        			 arrayEquipos[i]=(nuevaLectura.leerPdf(numMinJugadores,nombreTorneocombo,nombreArchivo));
        			 
        		 }
        		 
        	
    	 
    	 

    		 }
    		 if(arrayEquipos.length-formulariosRechazados<numMinEquipos) {
    			 System.out.println("no hay suficientes equipos correctamente inscritos");
    		 }else {
    			 //consulta para extraer id de torneo
    			 for(int i=0;i<arrayEquipos.length;i++) {
    				 if(arrayEquipos[i]!=null) {
    					 String nombre=arrayEquipos[i].getNombre();
    					 String email=arrayEquipos[i].getEmail();
    					 // insertar en base de Datos id de torneo, nombre de equipo y correo electronico
    					 int idequipo=0;
    					 //consulta para estraer id de equipo y poder insertar los jugadores con su id de equipo
    					 Delegado nuevoDelegado=arrayEquipos[i].getResponsable();
    					 ///extraer campos de delegado y e insertar un nuevo jugador delegado trrue
    					 Arbitro nuevoArbitro=arrayEquipos[i].getArbitro();
    					 //extraer campos de arbitro e insertar un nuevo jugador arbitro true
    					 Jugador arrayJugadores[]=arrayEquipos[i].getJugadores();
    					 for(int j =0;j<arrayJugadores.length;j++) {
    						String  nombreJugador=arrayJugadores[j].getNombre();
    						//extraer todos los campos e insertar un nuevo jugador dentro del bucle
    					 }
    					
    					 //una vez subidos todos los jugadores obtenemos el id del delegado y el arbitro ylos aññadimos a la tabla equipo
    				 }
    				 
    			 }
    		 }
    	
    	///aqui generamoo fiuncion para partidos
    	
    	
		
    	
    }
    	 return true;
    }
    public static List<String> nombreArchivos(String nombreTorneo){
    	List<String> archivos=new ArrayList<String>();
    	File carpeta=new File("torneo_"+nombreTorneo+"/Inscripciones_recibidas/");
    	File[]af =carpeta.listFiles();
    	if(af!=null) {
    		for (int i=0 ;i <af.length;i++) {
    			File afa=af[i];
    			if (afa.isFile()) {
    				archivos.add(i,afa.getName());
    				System.out.println(afa.getName());
    			}
    		}
    	}
    	
    	

    		
        
    	
		return archivos;
    	
    }
    
}