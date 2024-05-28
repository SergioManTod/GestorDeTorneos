import java.io.FileInputStream;
<<<<<<< HEAD
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.InputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Properties;
=======
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

>>>>>>> branch 'main' of https://github.com/SergioManTod/GestorDeTorneos.git
public class BaseDeDatos {
<<<<<<< HEAD
	Properties prop = new Properties(); 
	InputStream is = null;
	Connection con = null; 
	ResultSet rs = null;
	public void conectar() {
		
		
		try {
			is = new FileInputStream("src/main/resources/bd.properties");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String user = prop.getProperty("user", "");
		String password = prop.getProperty("password", ""); 
		String url = prop.getProperty("url", "");
		String driver = prop.getProperty("driver", "");
=======
	private String txtConfiguracion;
	Connection con = null;
>>>>>>> branch 'main' of https://github.com/SergioManTod/GestorDeTorneos.git

<<<<<<< HEAD
			try {
				Class.forName(driver).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			try {
				con = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			// TODO Auto-generated catch block
			
			System.out.println("conexion  ok");
		
	}
	public void insertar(Torneo nuevoTorneo ) {
		 String nombre=nuevoTorneo.getNombTorneo();
		 int numero_jugadores=nuevoTorneo.getCantJugadores();
		 int numero_equipos=nuevoTorneo.getCantEquipos();
		PreparedStatement statement = null; 
		try {
			statement = con.prepareStatement("INSERT INTO torneos (nombre,numero_jugadores,numero_equipos) "
					+ "VALUES (?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			statement.setString(1,nombre);
			statement.setInt(2, numero_jugadores);
			statement.setInt(3, numero_equipos);
			
			statement.executeUpdate(); 
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
=======
	//CONSTRUCTORES
	public BaseDeDatos(String txtConfiguracion) {
		super();
		this.txtConfiguracion = txtConfiguracion;
	}

	public BaseDeDatos() {
		super();
		// TODO Esbozo de constructor generado automáticamente
	}
	
	// GETTERS Y SETTERS
	public String getTxtConfiguracion() {
		return txtConfiguracion;
	}

	public void setTxtConfiguracion(String txtConfiguracion) {
		this.txtConfiguracion = txtConfiguracion;
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		BaseDeDatos.con = con;
	}	

	//TOSTRING
	@Override
	public String toString() {
		return "BaseDeDatos [txtConfiguracion=" + txtConfiguracion + "]";
	}



	//METODOS DE CONEXION
	
	public Connection conectarBd() {
		
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("src/main/resources/bd.propertiedades_casa_sergio");
			prop.load(is);
			
			String user = prop.getProperty("user","");
			String password = prop.getProperty("password","");
			String url = prop.getProperty("url","");
			String driver = prop.getProperty("driver","");
			
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, user, password);
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (InstantiationException e) {
			System.out.println("No se ha podido instanciar");
		} catch (IllegalAccessException e) {
			System.out.println("Acceso no autorizado");
		} catch (ClassNotFoundException e) {
			System.out.println("Clase no encontrada");
		} catch (IOException e) {
			System.out.println("Error al conectarse a la base de datos.");
		} catch (SQLException e) {
			System.out.println("Error al conectarse a la base de datos.");
		}
		return con;
	}
	
	
>>>>>>> branch 'main' of https://github.com/SergioManTod/GestorDeTorneos.git
	
}
