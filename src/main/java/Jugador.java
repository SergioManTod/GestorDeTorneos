
public class Jugador {
	private boolean esJugador=true;
	private int puntosJugador=0;
	private int amarillaJugador=0;
	private int rojasJugador=0;
	
	
	//------constructores
	
	public Jugador(boolean esJugador, int puntosJugador, int amarillaJugador, int rojasJugador) {
		super();
		this.esJugador = esJugador;
		this.puntosJugador = puntosJugador;
		this.amarillaJugador = amarillaJugador;
		this.rojasJugador = rojasJugador;
	}


	public Jugador() {
		super();
		// TODO Esbozo de constructor generado automáticamente
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
}
