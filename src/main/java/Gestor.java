import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;


public class Gestor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BaseDeDatos baseDeDatos;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestor frame = new Gestor();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gestor() {
		// CONEXION A LA BB DD
		baseDeDatos = BaseDeDatos.obtenerInstancia();
		
		// FORMATO DEL CONTENTPANE
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Aplicac\u00EDon Gestora de Torneos  -  Menú principal");
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
		
		// CREAR NUEVO TORNEO
		JLabel tagNuevoTorneo = new JLabel("Crear nuevo torneo:");
		tagNuevoTorneo.setForeground(new Color(255, 255, 255));
		tagNuevoTorneo.setHorizontalAlignment(SwingConstants.RIGHT);
		tagNuevoTorneo.setBounds(10, 25, 400, 25);
		contentPane.add(tagNuevoTorneo);
		
		JButton botonCreaTorneo = new JButton("CREAR");
		botonCreaTorneo.setBounds(420, 25, 120, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonCreaTorneo.addMouseListener(new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	botonCreaTorneo.setBackground(Color.LIGHT_GRAY);
				    	botonCreaTorneo.setForeground(Color.WHITE);
				    }

				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	botonCreaTorneo.setBackground(Color.WHITE);
				    	botonCreaTorneo.setForeground(Color.BLACK);
				    }
				});
				// FIN DE CODIGO PARA DAR ESTILO HOVER AL BOTON
		// AQUI LINKEAMOS A OTRA CLASE
		botonCreaTorneo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // SE OCULTA LA CLASE EN LA QUE ESTAMOS
                
                CreaTorneo creaTorneo = new CreaTorneo();
                creaTorneo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA LA APLICACIÓN AL CERRAR ESTA VENTANA
                creaTorneo.setVisible(true);
            }
        });
		contentPane.add(botonCreaTorneo);
		
		// LISTA LOS EQUIPOS QUE HAN ENVIADO SOLICITUD DE INSCRIPCION
		JLabel taglistsolicitudes = new JLabel("Listar Formularios de inscrpci\u00F3n recibidos:");
		taglistsolicitudes.setForeground(new Color(255, 255, 255));
		taglistsolicitudes.setHorizontalAlignment(SwingConstants.RIGHT);
		taglistsolicitudes.setBounds(10, 75, 400, 25);
		contentPane.add(taglistsolicitudes);
		
		JButton botonListarFormulariosRecibidos = new JButton("LISTAR");
		botonListarFormulariosRecibidos.setBounds(420, 75, 120, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonListarFormulariosRecibidos.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonListarFormulariosRecibidos.setBackground(Color.LIGHT_GRAY);
		    	botonListarFormulariosRecibidos.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonListarFormulariosRecibidos.setBackground(Color.WHITE);
		    	botonListarFormulariosRecibidos.setForeground(Color.BLACK);
		    }
		});
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		//AQUI LINKEAMOS A OTRA CLASE
		botonListarFormulariosRecibidos.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                setVisible(false); // SE OCULTA LA CLASE EN LA QUE ESTAMOS
		                
		                InscripcionesRecibidas inscripcionesRecibidas = new InscripcionesRecibidas();
		                inscripcionesRecibidas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// CIERRA LA APLICACIÓN AL CERRAR ESTA VENTANA
		                inscripcionesRecibidas.setVisible(true);
		            }
		        });
		contentPane.add(botonListarFormulariosRecibidos);
		
		// SECCION PARA LA CREACION DE NUEVOS CALENDARIOS DE PARTIDOS
		JLabel tagIniciaTorneo = new JLabel("Inicia un torneo:");
		tagIniciaTorneo.setForeground(new Color(255, 255, 255));
		tagIniciaTorneo.setHorizontalAlignment(SwingConstants.RIGHT);
		tagIniciaTorneo.setBounds(150, 125, 260, 25);
		contentPane.add(tagIniciaTorneo);
		
		JButton botonIniciaTorneo = new JButton("INICIAR");
		botonIniciaTorneo.setBounds(420, 125, 120, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonIniciaTorneo.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonIniciaTorneo.setBackground(Color.LIGHT_GRAY);
		    	botonIniciaTorneo.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonIniciaTorneo.setBackground(Color.WHITE);
		    	botonIniciaTorneo.setForeground(Color.BLACK);
		    }
		});
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		// AQUI LINKEAMOS A OTRA CLASE
		botonIniciaTorneo.addActionListener(new ActionListener() {
				            @Override
				            public void actionPerformed(ActionEvent e) {
				                setVisible(false); // SE OCULTA LA CLASE EN LA QUE ESTAMOS
				                
				                IniciaTorneo iniciaTorneo = new IniciaTorneo();
				                iniciaTorneo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA LA APLICACIÓN AL CERRAR ESTA VENTANA
				                iniciaTorneo.setVisible(true);
				            }
				        });
		contentPane.add(botonIniciaTorneo);
		
		// SECCION PARA MODIFICAR O ELIMINAR  EQUIPOS Y AÑADIR, MODIFICAR O ELIMINAR JUGADORES
		JLabel tagModEquipos = new JLabel("Modificar datos de un equipo:");
		tagModEquipos.setForeground(new Color(255, 255, 255));
		tagModEquipos.setHorizontalAlignment(SwingConstants.RIGHT);
		tagModEquipos.setBounds(150, 175, 260, 25);
		contentPane.add(tagModEquipos);
		
		JButton botonModEquipo = new JButton("OPCIONES");
		botonModEquipo.setBounds(420, 175, 120, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonModEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonModEquipo.setBackground(Color.LIGHT_GRAY);
		    	botonModEquipo.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonModEquipo.setBackground(Color.WHITE);
		    	botonModEquipo.setForeground(Color.BLACK);
		    }
		});
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		// AQUI LINKEAMOS A OTRA CLASE
		botonModEquipo.addActionListener(new ActionListener() {
						            @Override
						            public void actionPerformed(ActionEvent e) {
						                setVisible(false); // SE OCULTA LA CLASE EN LA QUE ESTAMOS
						                
						                ModificaDatosEquipo modificaDatosEquipo = new ModificaDatosEquipo();
						                modificaDatosEquipo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA LA APLICACIÓN AL CERRAR ESTA VENTANA
						                modificaDatosEquipo.setVisible(true);
						            }
						        });
		contentPane.add(botonModEquipo);
		
		// SECCION PARA VER LAS CLASIFICACIONES DE LOS TORNEOS
		JLabel verClasificacion = new JLabel("Ver clasificaci\u00F3n:");
		verClasificacion.setForeground(new Color(255, 255, 255));
		verClasificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		verClasificacion.setBounds(150, 225, 260, 25);
		contentPane.add(verClasificacion);
		
		JButton botonVerClasificacion = new JButton("VER");
		botonVerClasificacion.setBounds(420, 225, 120, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonVerClasificacion.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonVerClasificacion.setBackground(Color.LIGHT_GRAY);
		    	botonVerClasificacion.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonVerClasificacion.setBackground(Color.WHITE);
		    	botonVerClasificacion.setForeground(Color.BLACK);
		    }
		});
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		// AQUI LINKEAMOS A OTRA CLASE
		botonVerClasificacion.addActionListener(new ActionListener() {
								            @Override
								            public void actionPerformed(ActionEvent e) {
								                setVisible(false); // SE OCULTA LA CLASE EN LA QUE ESTAMOS
								                
								                Clasificaciones clasificaciones = new Clasificaciones();
								                clasificaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA LA APLICACIÓN AL CERRAR ESTA VENTANA
								                clasificaciones.setVisible(true);
								            }
								        });
		contentPane.add(botonVerClasificacion);
		
		// SECCION PARA VER LOS GOLEADORES DE LOS TORNEOS
		JLabel verTablaDeGoleadores = new JLabel("Ver tabla de goleadores:");
		verTablaDeGoleadores.setForeground(new Color(255, 255, 255));
		verTablaDeGoleadores.setHorizontalAlignment(SwingConstants.RIGHT);
		verTablaDeGoleadores.setBounds(150, 275, 260, 25);
		contentPane.add(verTablaDeGoleadores);
		
		JButton botonVerTablaGoleadores = new JButton("VER");
		botonVerTablaGoleadores.setBounds(420, 275, 120, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonVerTablaGoleadores.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	botonVerTablaGoleadores.setBackground(Color.LIGHT_GRAY);
		    	botonVerTablaGoleadores.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	botonVerTablaGoleadores.setBackground(Color.WHITE);
		    	botonVerTablaGoleadores.setForeground(Color.BLACK);
		    }
		});
		// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
				// AQUI LINKEAMOS A OTRA CLASE
		botonVerTablaGoleadores.addActionListener(new ActionListener() {
										            @Override
										            public void actionPerformed(ActionEvent e) {
										                setVisible(false); // SE OCULTA LA CLASE EN LA QUE ESTAMOS
										                
										                Goleadores goleadores = new Goleadores();
										                goleadores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA LA APLICACIÓN AL CERRAR ESTA VENTANA
										                goleadores.setVisible(true);
										            }
										        });
		contentPane.add(botonVerTablaGoleadores);
		
		// BOTON PARA CERRAR LA APLICACION
		JButton botonCerrar = new JButton("CERRAR APLICACI\u00D3N");
	        botonCerrar.setBounds(200, 320, 200, 25);
	     // INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
	        botonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	botonCerrar.setBackground(Color.LIGHT_GRAY);
			    	botonCerrar.setForeground(Color.WHITE);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	botonCerrar.setBackground(Color.WHITE);
			    	botonCerrar.setForeground(Color.BLACK);
			    }
			});
			// FIN DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
			// AQUI LINKEAMOS A OTRA CLASE
	        botonCerrar.addActionListener(new ActionListener() {
							            @Override
							            public void actionPerformed(ActionEvent e) {
							            	 // Cerrar la conexión a la base de datos al cerrar la aplicación
							                baseDeDatos.cerrarConexion();
							                setVisible(false); // OCULTAMOS LA CLASE EN LA QUE ESTAMOS
							            }
							        });
	        contentPane.add(botonCerrar);

		// IMAGEN DE FONDO
        JLabel tagImagenDeFondo = new JLabel();
        // SE CREA UN IMAGEICON CON LA IMAGEN DE FONDO
        ImageIcon copa = new ImageIcon("img\\icono_trofeo.png");
        // SE LE ASIGNA EL IMAGEICON A LA ETIQUETA
        tagImagenDeFondo.setIcon(copa);
        tagImagenDeFondo.setBounds(-250, 5, 512, 512);
        getContentPane().add(tagImagenDeFondo);
    
	}
}
