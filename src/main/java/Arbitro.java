import java.time.LocalDate;


public class Arbitro extends Jugador{
	private boolean esArbitro=true;
	
	// CONSTRUCTORES
	public Arbitro(String nombre, String apellidoUno, String apellidoDos, LocalDate fechaNacimiento, String dni) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	// GETTERS Y SETTERS
	public boolean isEsArbitro() {
		return esArbitro;
	}

	public void setEsArbitro(boolean esArbitro) {
		this.esArbitro = esArbitro;
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Arbitro [esArbitro=" + esArbitro + "]";
	}
	
}
