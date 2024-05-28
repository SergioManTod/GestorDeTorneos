import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDeDatos {
	private String txtConfiguracion;
	Connection con = null;

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
	
	
	
}
