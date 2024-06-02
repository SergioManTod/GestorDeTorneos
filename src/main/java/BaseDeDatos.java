import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JLabel;

public class BaseDeDatos {
    private static BaseDeDatos instancia;
    private Connection con;
    private JLabel mensajeLabel;

    // Constructor privado para evitar la instanciaci�n externa
    public BaseDeDatos(JLabel mensajeLabel) {
        this.mensajeLabel = mensajeLabel;
        conectar();
    }

    // M�todo est�tico para obtener la instancia �nica de la clase
    public static BaseDeDatos obtenerInstancia(JLabel tagMsgeConBbDd) {
        if (instancia == null) {
            instancia = new BaseDeDatos(tagMsgeConBbDd);
        }
        return instancia;
    }

    // M�todo para conectar a la base de datos
    private void conectar() {
        if (con != null) {
            return; // Si la conexión ya está establecida, no hacer nada
        }

        Properties prop = new Properties();

        try (InputStream is = new FileInputStream("src/main/resources/bd.properties")) {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            mensajeLabel.setText("Error al cargar el archivo de propiedades: " + e.getMessage());
            return;
        }

        String user = prop.getProperty("user", "");
        String password = prop.getProperty("password", ""); 
        String url = prop.getProperty("url", "");
        String driver = prop.getProperty("driver", "");

        try {
            Class.forName(driver);
            mensajeLabel.setText("Driver cargado correctamente.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            mensajeLabel.setText("Error al cargar el driver: " + e.getMessage());
            return;
        }

        try {
            con = DriverManager.getConnection(url, user, password);
            mensajeLabel.setText("Conexión establecida correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            mensajeLabel.setText("Error al establecer la conexión: " + e.getMessage());
        }
    }
    
    public Connection obtenerConexion() {
        return con;
    }
    
 // Método para cerrar la conexión a la base de datos
    public void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
                mensajeLabel.setText("Conexión cerrada correctamente.");
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
                mensajeLabel.setText("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

	// INSERTAR EQUIPO NUEVO
	public void insertaTorneoNuevo(Torneo nuevoTorneo ) {
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
	
	//CONSULTA PARA LISTAR TODOS LOS TORNEOS
	public List<Object[]> listarTorneos() throws SQLException {
        List<Object[]> torneos = new ArrayList<>();
        Statement st = con.createStatement();
        String sql = "SELECT id, nombre FROM torneos ORDER BY id ASC;";
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            torneos.add(new Object[]{id, nombre});
        }
        
        return torneos;
    }
	
	// listar torneos inactivos
	public List<String> listarTorneosInactivos(){
		List<String> torneos=new ArrayList<String>();
		
		try {
			Statement st = con.createStatement();
	        String sql = "SELECT nombre FROM torneos where estaActivo=0  ORDER BY id ASC;";
	        ResultSet rs;
			rs = st.executeQuery(sql);
			while (rs.next()) {
		           
	            String nombre = rs.getString("nombre");
	            torneos.add( nombre);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		return torneos;
		
	}
	//CONSULTA PARA CAMBIAR ESTADO DE TORNEO
	public List<Object[]> listarIdTorIna() throws SQLException {
		List<Object[]> selTorIna = new ArrayList<>();
		Statement st = con.createStatement();
		String sql = "SELECT * FROM torneos;";
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			int id = rs.getInt("id");
			String nombre=rs.getString("nombre");
			int numJug = rs.getInt("numero_jugadores");
			int numEquip = rs.getInt("numero_equipos");
			int estaActivo = rs.getInt("estaActivo");
			selTorIna.add(new Object[] {id,numJug, numEquip,estaActivo});
		}
				
		return selTorIna;
	}
	public List<String[]> consultaTorneo(String nombre) throws SQLException {
		List<String[]> selTorNom = new ArrayList<>();
		Statement st = con.createStatement();
		String sql = "SELECT * FROM torneos where nombre=nombre;";
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			int id = rs.getInt("id");
			nombre=rs.getString("nombre");
			int numJug = rs.getInt("numero_jugadores");
			int numEquip = rs.getInt("numero_equipos");
			int estaActivo = rs.getInt("estaActivo");
			selTorNom.add(new String[] {String.valueOf(id),nombre,String.valueOf(numJug),String.valueOf(numEquip),String.valueOf(estaActivo)});
		}
				
		return selTorNom;
	}
	public void insertarNuevoEquipo(Torneo nuevoTorneo ) {
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