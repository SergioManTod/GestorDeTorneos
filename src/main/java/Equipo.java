
public class Equipo {
	 private int id;
	private String nombre;
	private String email;
	private Delegado responsable;
	private  Arbitro arbitro;
	private Torneo troneo;
	private int idTorneo;
	public Jugador jugadores[];
	public int goles;
	
	 
	
	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	public Equipo(int id, String nombre, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}


	public Equipo( String nombre, String email, Delegado responsable, Arbitro arbitro, Jugador[] jugadores) {
		super();
		
		this.nombre = nombre;
		this.email = email;
		this.responsable = responsable;
		this.arbitro = arbitro;
		this.jugadores = jugadores!= null ? jugadores : new Jugador[0];
	}
	public Equipo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getGoles() {
		return goles;
	}
	public void setGoles(int goles) {
		this.goles = goles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdTorneo() {
		return idTorneo;
	}
	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Delegado getResponsable() {
		return responsable;
	}
	public void setResponsable(Delegado responsable) {
		this.responsable = responsable;
	}
	public Arbitro getArbitro() {
		return arbitro;
	}
	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}
	public Torneo getTroneo() {
		return troneo;
	}
	public void setTroneo(Torneo troneo) {
		this.troneo = troneo;
	}
	public Jugador[] getJugadores() {
		return jugadores;
	}
	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}
	
	
	
	
	
}
