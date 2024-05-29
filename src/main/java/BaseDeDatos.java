import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDeDatos {

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

			try {
				Class.forName(driver).newInstance();System.out.println("driverok");
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
				System.out.println("conexion  ok");
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
}