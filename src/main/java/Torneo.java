import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Torneo {
	private String nombTorneo;
	private int cantEquipos;
	private int cantJugadores;
	
	
	//CONSTRUCTORES
	public Torneo(String nombTorneo, int cantEquipos, int cantJugadores) {
		super();
		this.nombTorneo = comprobarNombreTorneo(nombTorneo);
		this.cantEquipos = cantEquipos;
		this.cantJugadores = cantJugadores;
	}


	public Torneo() {
		super();
		// TODO Auto-generated constructor stub
	}
// GETTERS Y SETTERS

	public String getNombTorneo() {
		return nombTorneo;
	}


	public void setNombTorneo(String nombTorneo) {
		this.nombTorneo = nombTorneo;
	}


	public int getCantEquipos() {
		return cantEquipos;
	}


	public void setCantEquipos(int cantEquipos) {
		this.cantEquipos = cantEquipos;
	}


	public int getCantJugadores() {
		return cantJugadores;
	}


	public void setCantJugadores(int cantJugadores) {
		this.cantJugadores = cantJugadores;
	}
// TOSTRING

	@Override
	public String toString() {
		return "Torneo [nombTorneo=" + nombTorneo + ", cantEquipos=" + cantEquipos + ", cantJugadores=" + cantJugadores
				+ "]";
	}
	public String comprobarNombreTorneo(String nombTorneo) {
		Connection con = null;
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
		try {
			Statement st = con.createStatement();
			String sql="SELECT nombre FROM torneos WHERE nombre='"+nombTorneo+"'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				if (nombre == nombTorneo) {
					System.out.println("");
					System.out.println(" eL Nombre: "+nombTorneo+" ya existe en la base de datos");
					System.out.println("");
				}
				this.nombTorneo = nombTorneo;
				System.out.println("");
				System.out.println(" eL Nombre: "+nombTorneo+" esta ok");
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("Error al hacer la consulta en la base de datos.");
		}
		return nombTorneo;
	}
}
