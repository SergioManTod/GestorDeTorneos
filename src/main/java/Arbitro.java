import java.util.Date;

public class Arbitro extends Jugador{
	private boolean esArbitro=true;
	
	// CONSTRUCTORES
	public Arbitro(String nombre, String apellidoUno, String apellidoDos, Date fechaNacimiento, String dni,
			boolean esMayor, int puntosJugador, int amarillaJugador, int rojasJugador, boolean esArbitro) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni, esMayor, puntosJugador, amarillaJugador,
				rojasJugador);
		this.esArbitro = esArbitro;
	}
	
	public Arbitro() {
		super();
		
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
