import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseDeDatos {
    private static BaseDeDatos instancia;
    private Connection con;

    // Constructor privado para evitar la instanciaci�n externa
    private BaseDeDatos() {
        conectar();
    }

    // M�todo est�tico para obtener la instancia �nica de la clase
    public static BaseDeDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    // M�todo para conectar a la base de datos
    private void conectar() {
        if (con != null) {
            return; // Si la conexi�n ya est� establecida, no hacer nada
        }

        Properties prop = new Properties();

        try (InputStream is = new FileInputStream("src/main/resources/bd.propertiedades_casa_sergio")) {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String user = prop.getProperty("user", "");
        String password = prop.getProperty("password", ""); 
        String url = prop.getProperty("url", "");
        String driver = prop.getProperty("driver", "");

        try {
            Class.forName(driver);
            System.out.println("Driver cargado correctamente.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexi�n establecida correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // M�todo para obtener la conexi�n
    public Connection obtenerConexion() {
        return con;
    }
 // M�todo para cerrar la conexi�n a la base de datos
    public void cerrarConexion() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Conexi�n cerrada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	// INSERTAR EQUIPO NUEVO
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