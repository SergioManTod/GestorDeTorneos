
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;

public class ModificaDatosEquipo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombEquipo;
	private JTextField textFieldEmail;
	private JTextField textFieldNombJugador;
	private JTextField textFieldPrApellido;
	private JTextField textFieldSegApellido;
	private JTextField textFieldDni;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaDatosEquipo frame = new ModificaDatosEquipo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ModificaDatosEquipo() {

		// FORMATO DEL CONTENTPANE
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
		setTitle("Gestor de Torneos 1.0  -  Modificar datos de equipo o jugador");
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

		// MENU DATOS DEL EQUIPO
		JLabel tagTitEquipo = new JLabel("DATOS DEL EQUIPO");
		tagTitEquipo.setFont(new Font("Tahoma", Font.BOLD, 13));
		tagTitEquipo.setForeground(Color.WHITE);
		tagTitEquipo.setBounds(50, 5, 286, 25);
		contentPane.add(tagTitEquipo);

		JLabel tagSelEquipo = new JLabel("EQUIPO:");
		tagSelEquipo.setForeground(new Color(255, 255, 255));
		tagSelEquipo.setBounds(10, 30, 68, 25);
		contentPane.add(tagSelEquipo);

		// COMOBOX DEL LISTADO DE EQUIPOS
		JComboBox comboBoxEquipo = new JComboBox();
		comboBoxEquipo.setModel(new DefaultComboBoxModel(new String[] { "-->SELECCIONE EQUIPO<--", "Los gauchitos",
				"Diseminados por el viento", "LA MARINA", "Cuatro locos" }));// AQUI VA LA CONEXION CON LA BBDD
		comboBoxEquipo.setBounds(70, 30, 190, 25);
		contentPane.add(comboBoxEquipo);

		// DATOS DEL NOMBRE DEL EQUIPO
		JLabel tagNombEquipo = new JLabel("NOMBRE:");
		tagNombEquipo.setForeground(Color.WHITE);
		tagNombEquipo.setBounds(10, 65, 80, 25);
		contentPane.add(tagNombEquipo);

		// EN ESTE TEXTFIEL, DEBE MOSTRARSE EL NOMBRE DEL EQUIPO PILLADO DE LA BASE DE
		// DATOS Y TAMBIEN SE PUEDE SOBRE ESCRIBIR Y HACER UNA EDIT EN LA BBDD
		textFieldNombEquipo = new JTextField();
		textFieldNombEquipo.setBounds(70, 65, 230, 25);
		contentPane.add(textFieldNombEquipo);
		textFieldNombEquipo.setColumns(10);

		JLabel tagDescNom = new JLabel("Introduce el nuevo nombre del equipo");
		tagDescNom.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescNom.setForeground(Color.WHITE);
		tagDescNom.setBounds(80, 85, 220, 25);
		contentPane.add(tagDescNom);

		// DATOS MAIL DE CONTACTO DEL EQUIPO
		JLabel tagMailEquipo = new JLabel("MAIL:");
		tagMailEquipo.setForeground(Color.WHITE);
		tagMailEquipo.setBounds(305, 65, 55, 25);
		contentPane.add(tagMailEquipo);

		// EN ESTE TEXTFIEL, DEBE MOSTRARSE EL MAIL DEL EQUIPO PILLADO DE LA BASE DE
		// DATOS Y TAMBIEN SE PUEDE SOBRE ESCRIBIR Y HACER UNA EDIT EN LA BBDD
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(340, 65, 240, 25);
		contentPane.add(textFieldEmail);

		JLabel tagDescMail = new JLabel("Introduce el nuevo e-mail del equipo");
		tagDescMail.setForeground(Color.WHITE);
		tagDescMail.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescMail.setBounds(355, 85, 220, 25);
		contentPane.add(tagDescMail);

		// CON ESTE BOTON SE MODIFICAN LOS DATOS DEL EQUIPO EN LA BBDD
		JButton botonModificaEquipo = new JButton("MODIFICAR EQUIPO");
		botonModificaEquipo.setFont(new Font("Tahoma", Font.BOLD, 8));
		botonModificaEquipo.setBounds(270, 30, 150, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonModificaEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonModificaEquipo.setBackground(Color.LIGHT_GRAY);
				botonModificaEquipo.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonModificaEquipo.setBackground(Color.WHITE);
				botonModificaEquipo.setForeground(Color.BLACK);
			}
		});
		// FIN DE CODIGO PARA DAR ESTILO HOVER AL BOTON
		// AVISO DE OPCION NO DISPONIBLE
		botonModificaEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FueraDeServicio.avisoNoDisponible();
			}
		});
		contentPane.add(botonModificaEquipo);

		// CON ESTE BOTON SE ELIMINA AL EQUIPO Y TODOS SUS JUGADORES DE LAS BBDD
		// (�DEBERIAMOS TAMBIEN ELIMINAR TODOS LOS ARCHIVOS GENERADOS?)
		JButton botonEliminaEquipo = new JButton("ELIMINAR  EQUIPO");
		botonEliminaEquipo.setFont(new Font("Tahoma", Font.BOLD, 8));
		botonEliminaEquipo.setBounds(430, 30, 150, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonEliminaEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonEliminaEquipo.setBackground(Color.LIGHT_GRAY);
				botonEliminaEquipo.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonEliminaEquipo.setBackground(Color.WHITE);
				botonEliminaEquipo.setForeground(Color.BLACK);
			}
		});
		// FIN DE CODIGO PARA DAR ESTILO HOVER AL BOTON
		// AVISO DE OPCION NO DISPONIBLE
		botonEliminaEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FueraDeServicio.avisoNoDisponible();
			}
		});
		contentPane.add(botonEliminaEquipo);
		// FIN MENU DATOS DEL EQUIPO

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 110, 600, 2);
		contentPane.add(separator);

		// MENU DATOS DEL JUGADOR
		JLabel tagTitJugador = new JLabel("DATOS DEL JUGADOR");
		tagTitJugador.setForeground(Color.WHITE);
		tagTitJugador.setFont(new Font("Tahoma", Font.BOLD, 13));
		tagTitJugador.setBounds(50, 115, 286, 25);
		contentPane.add(tagTitJugador);

		JLabel tagSelJugador = new JLabel("SELECCIONA JUGADOR:");
		tagSelJugador.setForeground(Color.WHITE);
		tagSelJugador.setBounds(10, 140, 150, 25);
		contentPane.add(tagSelJugador);

		// COMBOBOX DE JUGADORES DE EQUIPO SELECCIONADO ANTES
		JComboBox comboBoxJugador = new JComboBox();
		comboBoxJugador
				.setModel(new DefaultComboBoxModel(new String[] { ">SELECCIONE JUGADOR<", "jose alberto Medina Cuesta",
						"Erik Johan Khol Gutierrez", "Gabriel Michael Willers", "Bruno Edgardo Todaro" }));// AQUI VA
																											// CONEXION
																											// A BB DD
		comboBoxJugador.setBounds(163, 140, 190, 25);
		contentPane.add(comboBoxJugador);

		// DATOS NOMBRE DEL JUGADOR
		JLabel tagNombJugador = new JLabel("NOMBRE:");
		tagNombJugador.setForeground(Color.WHITE);
		tagNombJugador.setBounds(10, 175, 80, 25);
		contentPane.add(tagNombJugador);

		// EN ESTE TEXTFIEL, DEBE MOSTRARSE EL NOMBRE DEL JUGADOR PILLADO DE LA BASE DE
		// DATOS Y TAMBIEN SE PUEDE SOBRE ESCRIBIR Y HACER UNA EDIT EN LA BBDD
		textFieldNombJugador = new JTextField();
		textFieldNombJugador.setColumns(10);
		textFieldNombJugador.setBounds(70, 175, 230, 25);
		contentPane.add(textFieldNombJugador);

		JLabel tagDescNombJugador = new JLabel("Introduce el nuevo nombre del jugador");
		tagDescNombJugador.setForeground(Color.WHITE);
		tagDescNombJugador.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescNombJugador.setBounds(80, 195, 220, 25);
		contentPane.add(tagDescNombJugador);

		// DATOS DE FECHA DE NACIMIENTO DEL JUGADOR
		JLabel tagFecNacJugador = new JLabel("FECHA DE NACIMIENTO:");
		tagFecNacJugador.setForeground(Color.WHITE);
		tagFecNacJugador.setBounds(305, 175, 164, 25);
		contentPane.add(tagFecNacJugador);

		// EN ESTE JDateChooser, DEBE MOSTRARSE LA FECHA DE NACIMIENTO DEL JUGADOR
		// PILLADO DE LA BASE DE DATOS Y TAMBIEN SE PUEDE SELECCIONAR EN EL CALENDARIO Y
		// HACER UNA EDIT EN LA BBDD
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(450, 175, 130, 25);
		contentPane.add(dateChooser);

		JLabel tagDescFecNacJugador = new JLabel("Selecciona la nueva decha de nacimitento del jugador");
		tagDescFecNacJugador.setForeground(Color.WHITE);
		tagDescFecNacJugador.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescFecNacJugador.setBounds(330, 195, 220, 25);
		contentPane.add(tagDescFecNacJugador);

		// DATOS PRIMER APELLIDO JUGADOR
		JLabel tagPriApJugador = new JLabel("1º APELLIDO:");
		tagPriApJugador.setForeground(Color.WHITE);
		tagPriApJugador.setBounds(10, 220, 80, 25);
		contentPane.add(tagPriApJugador);

		// EN ESTE TEXTFIEL, DEBE MOSTRARSE EL 1� APELLIDO DEL JUGADOR PILLADO DE LA
		// BASE DE DATOS Y TAMBIEN SE PUEDE SOBRE ESCRIBIR Y HACER UNA EDIT EN LA BBDD
		textFieldPrApellido = new JTextField();
		textFieldPrApellido.setColumns(10);
		textFieldPrApellido.setBounds(90, 220, 200, 25);
		contentPane.add(textFieldPrApellido);

		JLabel tagDescPriAp = new JLabel("Introduce el nuevo 1º apellido del jugador");
		tagDescPriAp.setForeground(Color.WHITE);
		tagDescPriAp.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescPriAp.setBounds(100, 240, 220, 25);
		contentPane.add(tagDescPriAp);

		// DATOS SEGUNDO APELLIDO
		JLabel tagSegApJugador = new JLabel("2º APELLIDO:");
		tagSegApJugador.setForeground(Color.WHITE);
		tagSegApJugador.setBounds(300, 220, 80, 25);
		contentPane.add(tagSegApJugador);

		// EN ESTE TEXTFIEL, DEBE MOSTRARSE EL 1� APELLIDO DEL JUGADOR PILLADO DE LA
		// BASE DE DATOS Y TAMBIEN SE PUEDE SOBRE ESCRIBIR Y HACER UNA EDIT EN LA BBDD
		textFieldSegApellido = new JTextField();
		textFieldSegApellido.setColumns(10);
		textFieldSegApellido.setBounds(380, 220, 200, 25);
		contentPane.add(textFieldSegApellido);

		JLabel tagDescSegAp = new JLabel("Introduce el nuevo 2º apellido del jugador");
		tagDescSegAp.setForeground(Color.WHITE);
		tagDescSegAp.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescSegAp.setBounds(390, 240, 180, 25);
		contentPane.add(tagDescSegAp);

		// DATOS DNI JUGADOR
		JLabel tagDni = new JLabel("D.N.I.:");
		tagDni.setForeground(Color.WHITE);
		tagDni.setBounds(40, 265, 80, 25);
		contentPane.add(tagDni);

		// EN ESTE TEXTFIEL, DEBE MOSTRARSE EL 1� APELLIDO DEL JUGADOR PILLADO DE LA
		// BASE DE DATOS Y TAMBIEN SE PUEDE SOBRE ESCRIBIR Y HACER UNA EDIT EN LA BBDD
		textFieldDni = new JTextField();
		textFieldDni.setColumns(10);
		textFieldDni.setBounds(78, 265, 80, 25);
		contentPane.add(textFieldDni);

		JLabel tagDescDni = new JLabel("Introduce el nuevo D.N.I.");
		tagDescDni.setForeground(Color.WHITE);
		tagDescDni.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescDni.setBounds(70, 285, 112, 25);
		contentPane.add(tagDescDni);

		// DATOS SOBRE SI EL JUGADOR ES TAMBIEN DELEGADO
		JLabel tagEsDelegado = new JLabel("ES EL DELEGADO? ");
		tagEsDelegado.setForeground(Color.WHITE);
		tagEsDelegado.setBounds(180, 265, 140, 25);
		contentPane.add(tagEsDelegado);

		// ESTE CHECKBOX DEBE APARECER SELECCIONADO SI EN LOS DATOS DE LA BB DD DICE QUE
		// ES EL DELEGADO, Y TAMBIEN SE PUEDE MODIFICAR CON UN EDIT
		JCheckBox checkBoxDelegado = new JCheckBox("SI");
		checkBoxDelegado.setBounds(300, 265, 40, 25);
		contentPane.add(checkBoxDelegado);

		JLabel tagDescDelegado = new JLabel("Selecciona solo si es el delegado");
		tagDescDelegado.setForeground(Color.WHITE);
		tagDescDelegado.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescDelegado.setBounds(200, 285, 160, 25);
		contentPane.add(tagDescDelegado);

		// DATOS SOBRE SI ES EL ARBITRO
		JLabel tagEsArbitro = new JLabel("ES EL ARBITRO? ");
		tagEsArbitro.setForeground(Color.WHITE);
		tagEsArbitro.setBounds(400, 265, 112, 25);
		contentPane.add(tagEsArbitro);

		// ESTE CHECKBOX DEBE APARECER SELECCIONADO SI EN LOS DATOS DE LA BB DD DICE QUE
		// ES EL ARBITRO, Y TAMBIEN SE PUEDE MODIFICAR CON UN EDIT
		JCheckBox checkBoxArbitro = new JCheckBox("SI");
		checkBoxArbitro.setBounds(507, 265, 40, 25);
		contentPane.add(checkBoxArbitro);

		JLabel tagDescArbitro = new JLabel("Selecciona solo si es el arbitro");
		tagDescArbitro.setForeground(Color.WHITE);
		tagDescArbitro.setFont(new Font("Tahoma", Font.PLAIN, 8));
		tagDescArbitro.setBounds(420, 285, 160, 25);
		contentPane.add(tagDescArbitro);
		// MENU DATOS DEL JUGADOR

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 310, 600, 2);
		contentPane.add(separator_1);

		// BOTON PARA A�ADIR A UN JUGADOR NUEVO
		JButton botonNuevoJugador = new JButton("AÑADIR JUGADOR");
		botonNuevoJugador.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonNuevoJugador.setBounds(20, 325, 130, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonNuevoJugador.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonNuevoJugador.setBackground(Color.LIGHT_GRAY);
				botonNuevoJugador.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonNuevoJugador.setBackground(Color.WHITE);
				botonNuevoJugador.setForeground(Color.BLACK);
			}
		});
		// FIN DE CODIGO PARA DAR ESTILO HOVER AL BOTON
		// AVISO DE OPCION NO DISPONIBLE
		botonNuevoJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FueraDeServicio.avisoNoDisponible();
			}
		});
		contentPane.add(botonNuevoJugador);

		// BOTON PARA MODIFICAR AL JUGADOR SELECCIONADO
		JButton botonEditaJugador = new JButton("EDITAR JUGADOR");
		botonEditaJugador.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonEditaJugador.setBounds(160, 325, 130, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonEditaJugador.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonEditaJugador.setBackground(Color.LIGHT_GRAY);
				botonEditaJugador.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonEditaJugador.setBackground(Color.WHITE);
				botonEditaJugador.setForeground(Color.BLACK);
			}
		});
		// FIN DE CODIGO PARA DAR ESTILO HOVER AL BOTON
		// AVISO DE OPCION NO DISPONIBLE
		botonEditaJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FueraDeServicio.avisoNoDisponible();
			}
		});
		contentPane.add(botonEditaJugador);

		// BOTON PARA ELIMINAR JUGADOR SELECCIONADO (CUIDADO, SE DEBEN ELIMINAR TODOS
		// LOS DATOS DE EL JUGADOR PERO ESTO NO DEBE AFECTAR A LOS PUNTOS DEL EQUIPO)
		JButton botonEliminaJugador = new JButton("ELIMINAR JUGADOR");
		botonEliminaJugador.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonEliminaJugador.setBounds(300, 325, 130, 25);
		// INICIO DEL CODIGO PARA DAR ESTILO AL BOTON CUANDO HACEMOS HOVER
		botonEliminaJugador.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonEliminaJugador.setBackground(Color.LIGHT_GRAY);
				botonEliminaJugador.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonEliminaJugador.setBackground(Color.WHITE);
				botonEliminaJugador.setForeground(Color.BLACK);
			}
		});
		// FIN DE CODIGO PARA DAR ESTILO HOVER AL BOTON
		// AVISO DE OPCION NO DISPONIBLE
		botonEliminaJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FueraDeServicio.avisoNoDisponible();
			}
		});
		contentPane.add(botonEliminaJugador);

		// BOTON PARA VOLVER AL MENU PRINCIPAL
		JButton botonMenuPrincipal = new JButton("MENÚ PRINCIPAL");
		botonMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 9));
		botonMenuPrincipal.setBounds(440, 325, 130, 25);
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

				Gestor gestor = new Gestor(null);
				gestor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA EL JFRAME ACTUAL
				gestor.setVisible(true);
			}
		});
		contentPane.add(botonMenuPrincipal);

	}
}
