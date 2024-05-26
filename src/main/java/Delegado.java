import java.util.Date;

public class Delegado extends Jugador{
	private boolean esDelegado=true;

	// CONSTRUCTORES
	public Delegado(String nombre, String apellidoUno, String apellidoDos, Date fechaNacimiento, String dni,
			boolean esMayor, int puntosJugador, int amarillaJugador, int rojasJugador, boolean esDelegado) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni, esMayor, puntosJugador, amarillaJugador,
				rojasJugador);
		this.esDelegado = esDelegado;
	}

	public Delegado() {
		super();
		
	}

	// GETTERS Y SETTERS
	public boolean isEsDelegado() {
		return esDelegado;
	}

	public void setEsDelegado(boolean esDelegado) {
		this.esDelegado = esDelegado;
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Delegado [esDelegado=" + esDelegado + "]";
	}
	
}
