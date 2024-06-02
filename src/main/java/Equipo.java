
public class Equipo {
	 private int id;
	private String nombre;
	private String email;
	private Jugador responsable;
	private  Jugador arbitro;
	private Torneo troneo;
	public Equipo( String nombre, String email, Jugador responsable, Jugador arbitro, Torneo troneo) {
		super();
		this.id = 0;
		this.nombre = nombre;
		this.email = email;
		this.responsable = responsable;
		this.arbitro = arbitro;
		this.troneo = troneo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Jugador getResponsable() {
		return responsable;
	}
	public void setResponsable(Jugador responsable) {
		this.responsable = responsable;
	}
	public Jugador getArbitro() {
		return arbitro;
	}
	public void setArbitro(Jugador arbitro) {
		this.arbitro = arbitro;
	}
	public Torneo getTroneo() {
		return troneo;
	}
	public void setTroneo(Torneo troneo) {
		this.troneo = troneo;
	}
	
	
	
	
	
}
