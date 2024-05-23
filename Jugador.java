import java.util.Date;

public class Jugador extends Persona {
	private boolean esJugador=true;
	private int puntosJugador=0;
	private int amarillaJugador=0;
	private int rojasJugador=0;
	
	
	//------constructores
	

	public Jugador() {
		super();
		// TODO Esbozo de constructor generado automáticamente
	}

	public Jugador(String nombre, String apellidoUno, String apellidoDos, Date fechaNacimiento, String dni,
			boolean esMayor, boolean esJugador, int puntosJugador, int amarillaJugador, int rojasJugador) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni, esMayor);
		this.esJugador = esJugador;
		this.puntosJugador = puntosJugador;
		this.amarillaJugador = amarillaJugador;
		this.rojasJugador = rojasJugador;
	}

	//---- getters y setters
	
	public boolean isEsJugador() {
		return esJugador;
	}


	public void setEsJugador(boolean esJugador) {
		this.esJugador = esJugador;
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


	public void setRojasJugador(int rojasJugador) {
		this.rojasJugador = rojasJugador;
	}

	@Override
	public String toString() {
		return "Jugador [esJugador=" + esJugador + ", puntosJugador=" + puntosJugador + ", amarillaJugador="
				+ amarillaJugador + ", rojasJugador=" + rojasJugador + "]";
	}
	
	
}
