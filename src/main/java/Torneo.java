import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Torneo {
	private String nombTorneo;
	private int cantEquipos;
	private int cantJugadores;
	private Connection con;
	
	
	//CONSTRUCTORES
	public Torneo(String nombTorneo, int cantEquipos, int cantJugadores) {
		super();
		boolean resul = comprobarNombreTorneo(nombTorneo);
		if (resul) {this.nombTorneo=nombTorneo;}
		this.cantEquipos = cantEquipos;
		this.cantJugadores = cantJugadores;
	}

	public Torneo(String nombTorneo, int cantEquipos, int cantJugadores, Connection con) {
	    super();
	    this.con = con; // Establece la conexi�n
	    this.nombTorneo = nombTorneo;
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
	
	
	//METODOS
	
	//COMPROBAR QUE EL TORNEO NO EXISTE EN LA BBDD
	public Boolean comprobarNombreTorneo(String nombTorneo) {
        boolean resul = false;
        if (nombTorneo.length() > 0 ) {
        String sql = "SELECT nombre FROM torneos WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombTorneo);
            try (ResultSet rs = ps.executeQuery()) {
                resul = !rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al hacer la consulta en la base de datos: " + e.getMessage());
        }
	}System.out.println("No puede estar el campo nombre vacio");
        return resul;
    }
        
}
