import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

	// Método para conectar a la base de datos usando un archivo de propiedades
	// específico
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
	public void insertaTorneoNuevo(Torneo nuevoTorneo) {
		String nombre = nuevoTorneo.getNombTorneo();
		int numero_jugadores = nuevoTorneo.getCantJugadores();
		int numero_equipos = nuevoTorneo.getCantEquipos();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(
					"INSERT INTO torneos (nombre,numero_jugadores,numero_equipos,estaActivo) " + "VALUES (?,?,?,?)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			statement.setString(1, nombre);
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

	// CONSULTA PARA LISTAR TODOS LOS TORNEOS
	public List<Object[]> listarTorneos() throws SQLException {
		List<Object[]> torneos = new ArrayList<>();
		Statement st = con.createStatement();
		String sql = "SELECT id, nombre FROM torneos ORDER BY id ASC;";
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String nombre = rs.getString("nombre");
			torneos.add(new Object[] { id, nombre });
		}

		return torneos;
	}

	// listar torneos inactivos
	public List<String> listarTorneosInactivos() {
		List<String> torneos = new ArrayList<String>();

		try {
			Statement st = con.createStatement();
			String sql = "SELECT nombre FROM torneos where estaActivo=0  ORDER BY id ASC;";
			ResultSet rs;
			rs = st.executeQuery(sql);
			while (rs.next()) {

				String nombre = rs.getString("nombre");
				torneos.add(nombre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return torneos;

	}

	// listar torneos activos
	public List<String> listarTorneosActivos() {
	    List<String> torneosAct = new ArrayList<String>();

	    try {
	        Statement st = con.createStatement();
	        String sql = "SELECT nombre FROM torneos WHERE estaActivo = 1 ORDER BY id ASC;";
	        ResultSet rs = st.executeQuery(sql);
	        
	        if (!rs.next()) {
	            String vacio = "No hay torneos activos";
	            torneosAct.add(vacio);
	        } else {
	            do {
	                String nombre = rs.getString("nombre");
	                torneosAct.add(nombre);
	            } while (rs.next());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return torneosAct;
	}

	// CONSULTA PARA listar TORNEO
	public List<Object[]> listarIdTorIna() throws SQLException {
		List<Object[]> selTorIna = new ArrayList<>();
		Statement st = con.createStatement();
		String sql = "SELECT * FROM torneos;";
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {

			int id = rs.getInt("id");
			String nombre = rs.getString("nombre");
			int numJug = rs.getInt("numero_jugadores");
			int numEquip = rs.getInt("numero_equipos");
			int estaActivo = rs.getInt("estaActivo");
			selTorIna.add(new Object[] { id, numJug, numEquip, estaActivo });
		}

		return selTorIna;
	}

	public Torneo consultaTorneo(String nombre) throws SQLException {
	    Torneo nuevoTorneo = null;
	    String sql = "SELECT * FROM torneos WHERE nombre = ?";
	    
	    try (PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, nombre);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                nuevoTorneo = new Torneo();
	                nuevoTorneo.setId(rs.getInt("id"));
	                nuevoTorneo.setNombTorneo(rs.getString("nombre"));
	                nuevoTorneo.setCantJugadores(rs.getInt("numero_jugadores"));
	                nuevoTorneo.setCantEquipos(rs.getInt("numero_equipos"));
	                nuevoTorneo.setActivo(rs.getInt("estaActivo"));
	            }
	        }
	    }
	    
	    return nuevoTorneo;
	}


	
	// listado clasificación
	public List<Object[]> listaClasificacion(String nom) throws SQLException {
		List<Object[]> clasificacion = new ArrayList<>();
		Statement st = con.createStatement();
		String sql = "SELECT e.nombre, e.puntos FROM equipos e JOIN torneos t ON e.torneo = t.id WHERE t.nombre = '"
				+ nom + "' ORDER BY e.puntos DESC;";
		ResultSet rs = st.executeQuery(sql);

		int cont = 1;
		while (rs.next()) {
			String nombreEquipo = rs.getString("nombre");
			int puntos = rs.getInt("puntos");
			clasificacion.add(new Object[] { cont, nombreEquipo, puntos });
			cont++;
		}

		return clasificacion;
	}
	   // listado goleador
    public List<Object[]> listaGoleadores(int idTorneo) throws SQLException {
        List<Object[]> goleadores = new ArrayList<>();
        
           
        String sql = "SELECT j.nombre, j.apellido1, j.apellido2, e.nombre, j.gol FROM jugadores j JOIN equipos e ON j.equipo = e.id WHERE e.torneo = ? AND j.gol > 0 ORDER BY j.gol DESC";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idTorneo); // Establecemos el valor del parámetro
        ResultSet rs = stmt.executeQuery(); 

        int cont = 1;
        while (rs.next()) {
            String nombreEquipo = rs.getString(4);
            String nombreJugador = rs.getString(1);
            String apellido1Jugador = rs.getString(2);
            String apellido2Jugador = rs.getString(3);
            String jugador = nombreJugador + " " + apellido1Jugador + " " + apellido2Jugador;
            int totalGolesJugador = rs.getInt(5);
            goleadores.add(new Object[]{cont, jugador, nombreEquipo, totalGolesJugador});
            cont++;
        }

        rs.close();
        stmt.close();

        return goleadores;
    }



    public void insertarNuevoEquipo(Equipo nuevoEquipo, int idTorneoactual) {
		String nombre = nuevoEquipo.getNombre();
		String email=nuevoEquipo.getEmail();
		int idTorneo=idTorneoactual;
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(
					"INSERT INTO equipos (nombre,email,torneo) " + "VALUES (?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.setString(1, nombre);
			statement.setString(2, email);
			statement.setInt(3,idTorneo );
			statement.executeUpdate() ;
			statement.close();
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

    public void activaTorneo(String nombreTorneocombo) {
        String sql = "UPDATE torneos SET estaActivo = 1 WHERE nombre = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, nombreTorneocombo);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int consultaIdEquipo(String nombre) throws SQLException {
        int idEquipo = 0;
        String sql = "SELECT id FROM equipos WHERE nombre = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                idEquipo = rs.getInt("id");
            }
        }
        return idEquipo;
    }
    public void insertarNuevoJugador(Delegado nuevoDelegado , int idEquipo) {
		
		
		
	      
		 LocalDate fecha = nuevoDelegado.getFechaNacimiento(); // Reemplaza esto con tu objeto LocalDate
	     // Crear un formateador con el patrón deseado
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     // Formatear la fecha
	      String fechaFormateada = fecha.format(formatter);
	     
	        
	        
	        
        
        
		int Equipo=idEquipo;
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(
					"INSERT INTO jugadores(nombre,apellido1,apellido2,dni,fecha_nacimiento,equipo,delegado) " + "VALUES (?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.setString(1, nuevoDelegado.getNombre());
			statement.setString(2, nuevoDelegado.getApellidoUno());
			statement.setString(3,nuevoDelegado.getApellidoDos());
			statement.setString(4,nuevoDelegado.getDni());
			statement.setString(5,fechaFormateada);
			statement.setInt(6,idEquipo);
			statement.setInt(7,1);
			statement.executeUpdate();
				statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
public void insertarNuevoJugador(Arbitro nuevoArbitro, int idEquipo) {
		
	LocalDate fecha = nuevoArbitro.getFechaNacimiento(); // Reemplaza esto con tu objeto LocalDate
    // Crear un formateador con el patrón deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Formatear la fecha
     String fechaFormateada = fecha.format(formatter);
    
		int Equipo=idEquipo;
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(
					"INSERT INTO jugadores(nombre,apellido1,apellido2,dni,fecha_nacimiento,equipo,arbitro) " + "VALUES (?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.setString(1, nuevoArbitro.getNombre());
			statement.setString(2, nuevoArbitro.getApellidoUno());
			statement.setString(3,nuevoArbitro.getApellidoDos());
			statement.setString(4,nuevoArbitro.getDni());
			statement.setString(5,fechaFormateada);
			statement.setInt(6,idEquipo);
			statement.setInt(7, 1);
			statement.executeUpdate();
				statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
public void insertarNuevoJugador(Jugador nuevoJugador, int idEquipo) {
	
	LocalDate fecha=nuevoJugador.getFechaNacimiento();
	
	// Crear un formateador con el patrón deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Formatear la fecha
     String fechaFormateada = fecha.format(formatter);
   
	int Equipo=idEquipo;
	PreparedStatement statement = null;
	try {
		statement = con.prepareStatement(
				"INSERT INTO jugadores(nombre,apellido1,apellido2,dni,fecha_nacimiento,equipo) " + "VALUES (?,?,?,?,?,?)");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		statement.setString(1, nuevoJugador.getNombre());
		statement.setString(2, nuevoJugador.getApellidoUno());
		statement.setString(3,nuevoJugador.getApellidoDos());
		statement.setString(4,nuevoJugador.getDni());
		statement.setString(5,fechaFormateada);
		statement.setInt(6,idEquipo);
		
		statement.executeUpdate();
			statement.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}

	 public void InsertarPartidos(Partido partido, BaseDeDatos baseDeDatos) {
		 int idArbitro=0;
		 try {
			 idArbitro=consultaIdArbitro(partido.getArbitroPartido().getIdEquipo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 PreparedStatement statement = null;
			try {
				statement = con.prepareStatement(
						"INSERT INTO partidos(e_visitante,e_local,arbitro) " + "VALUES (?,?,?)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
					statement.setInt(1,partido.getEquipoLocal().getId());
					statement.setInt(2, partido.getEquipoVisitante().getId());
					statement.setInt(3,idArbitro);
					
					
					
					statement.executeUpdate();
				
				
					statement.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 public int  consultaIdArbitro(int idEquipo) throws SQLException {
		 int idArbitro=0;
		 String sql = "SELECT id FROM jugadores WHERE equipo =? and arbitro=1";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            try {
					stmt.setInt(1, idEquipo);
					ResultSet rs = stmt.executeQuery();

		            while (rs.next()) {
		                idArbitro = rs.getInt("id");
		            }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        }
		
		 return idArbitro;
	 }
	public Equipo consultarEquipo(String nombreTorneo, String nombreEquipo) throws SQLException {
		Equipo Equipo1 = null;
	    String sql = "SELECT equipos.email, equipos.id FROM equipos, torneos WHERE equipos.torneo = torneos.id AND torneos.nombre = ? AND equipos.nombre = ?";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, nombreTorneo);
	        stmt.setString(2, nombreEquipo);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            Equipo1 = new Equipo(); // Inicializar el objeto aquí
	            String email = rs.getString("email");
	            int idEquipo = rs.getInt("id");
	            System.out.println("id equipo: " + idEquipo);
	            Equipo1.setId(idEquipo);
	            Equipo1.setNombre(nombreEquipo);
	            Equipo1.setEmail(email);
	            System.out.println("nombre equipo: " + Equipo1.getNombre());
	        } else {
	            System.out.println("No se encontró el equipo con el nombre: " + nombreEquipo);
	        }
	    }
	    return Equipo1;
	}
	public Jugador[]  consultaJugadores(int idEquipo) throws SQLException {
	    ArrayList<Jugador> listaJugadores = new ArrayList<>();
	    String sql = "SELECT id, nombre FROM jugadores WHERE equipo = ?";
	    
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setInt(1, idEquipo);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            System.out.println("nombre rs"+ nombre);
	            System.out.println("id rs"+ id);
	            Jugador nuevoJugador = new Jugador(nombre, id);
	            listaJugadores.add(nuevoJugador);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error al consultar los jugadores: " + e.getMessage());
	    }
	    Jugador[] jugadoresLocal=listaJugadores.toArray(new Jugador[0] );
	    return jugadoresLocal;
	}
	
	
	
	 public void actualizaPuntos(String nombreEquipo) {
	        String sql = "UPDATE equipos SET puntos = puntos+3 WHERE nombre = ?";
	        try (PreparedStatement st = con.prepareStatement(sql)) {
	            st.setString(1, nombreEquipo);
	            st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void actualizaGoles(int id, int goles) {
		 
	        String sql = "UPDATE jugadores SET gol = gol+? WHERE id = ?";
	        try (PreparedStatement st = con.prepareStatement(sql)) {
	            st.setInt(2, id);
	            st.setInt(1, goles);
	            st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}