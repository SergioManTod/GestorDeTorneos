import java.time.LocalDate;



public class Jugador{
	private String nombre;
	private String apellidoUno;
	private String apellidoDos;
	private LocalDate fechaNacimiento;
	private String dni;
	private boolean esMayor=true;
	private int puntosJugador=0;
	private int amarillaJugador=0;
	private int rojasJugador=0;
	private int id;
	private String NombreEquipo;
	private int idEquipo;
	private int goles;
	// CONSTRUCTORES
	

	
	
	public Jugador() {
		super();
	}

	public Jugador(String nombre2) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Jugador(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	

	public Jugador(String nombre, String apellidoUno, String apellidoDos, LocalDate fechaNacimiento, String dni) {
		super();
		this.nombre = nombre;
		this.apellidoUno = apellidoUno;
		this.apellidoDos = apellidoDos;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
	
		
	}
	
	
	







	


	// GETTERS Y SETTERS
	
	
	public String getNombre() {
		return nombre;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreEquipo() {
		return NombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		NombreEquipo = nombreEquipo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoUno() {
		return apellidoUno;
	}

	public void setApellidoUno(String apellidoUno) {
		this.apellidoUno = apellidoUno;
	}

	public String getApellidoDos() {
		return apellidoDos;
	}

	public void setApellidoDos(String apellidoDos) {
		this.apellidoDos = apellidoDos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public boolean isEsMayor() {
		return esMayor;
	}

	public void setEsMayor(boolean esMayor) {
		this.esMayor = esMayor;
	}

	public int getPuntosJugador() {
		return puntosJugador;
	}

	public void setPuntosJugador(int puntosJugador) {
		this.puntosJugador = puntosJugador;
	}

	public int getAmarillaJugador() {
		return amarillaJugador;
	}

	public void setAmarillaJugador(int amarillaJugador) {
		this.amarillaJugador = amarillaJugador;
	}

	public int getRojasJugador() {
		return rojasJugador;
	}

	
	

	
	
	
}
