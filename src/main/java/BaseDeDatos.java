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
    
    
    public BaseDeDatos(Connection con) {
        this.con = con;
    }
    // Constructor privado para evitar la instanciación externa
    public BaseDeDatos(JLabel mensajeLabel) {
        this.mensajeLabel = mensajeLabel;
    }

    // Método estático para obtener la instancia única de la clase
    public static BaseDeDatos obtenerInstancia(JLabel mensajeLabel) {
        if (instancia == null) {
            instancia = new BaseDeDatos(mensajeLabel);
        } else {
            instancia.mensajeLabel = mensajeLabel; // Actualizar el mensajeLabel en caso de que cambie
        }
        return instancia;
    }

    // Método para conectar a la base de datos usando un archivo de propiedades específico
    public Connection conectar(String propertiesFileName) {
        if (con != null) {
            return con; // Si la conexión ya está establecida, no hacer nada
        }

        Properties prop = new Properties();

        try (InputStream is = new FileInputStream("src/main/resources/" + propertiesFileName)) {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            if (mensajeLabel != null) {
                mensajeLabel.setText("Error al cargar el archivo de propiedades: " + e.getMessage());
            }
            return null;
        }

        String user = prop.getProperty("user", "");
        String password = prop.getProperty("password", ""); 
        String url = prop.getProperty("url", "");
        String driver = prop.getProperty("driver", "");

        try {
            Class.forName(driver);
            if (mensajeLabel != null) {
                mensajeLabel.setText("Driver cargado correctamente.");
            }
        } catch (ClassNotFoundException e) {
            if (mensajeLabel != null) {
                mensajeLabel.setText("Error al cargar el driver.");
            }
            return null;
        }

        try {
            con = DriverManager.getConnection(url, user, password);
            if (mensajeLabel != null) {
                mensajeLabel.setText("Conexión establecida correctamente.");
            }
            return con;
        } catch (SQLException e) {
            if (mensajeLabel != null) {
                mensajeLabel.setText("Error al establecer la conexión.");
            }
            return null;
        }
    }

    // Método para obtener la conexión
    public Connection obtenerConexion() {
        return con;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
                if (mensajeLabel != null) {
                    mensajeLabel.setText("Conexión cerrada correctamente.");
                }
                con = null; // Eliminar la referencia a la conexión cerrada
            } catch (SQLException e) {
                e.printStackTrace();
                if (mensajeLabel != null) {
                    mensajeLabel.setText("Error al cerrar la conexión: " + e.getMessage());
                }
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
 			statement = con.prepareStatement("INSERT INTO torneos (nombre,numero_jugadores,numero_equipos,estaActivo) "
 					+ "VALUES (?,?,?,?)");
 		} catch (SQLException e) {
 			
 			e.printStackTrace();
 		} 
 		try {
 			statement.setString(1,nombre);
 			statement.setInt(2, numero_jugadores);
 			statement.setInt(3, numero_equipos);
 			statement.setInt(4, 0);
 			
 			statement.executeUpdate(); 
 			statement.close();
 		} catch (SQLException e) {
 			System.out.println("Aqui es donde falla");
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

<<<<<<< HEAD
=======
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
>>>>>>> rama «main» de https://github.com/SergioManTod/GestorDeTorneos.git
