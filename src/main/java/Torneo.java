
public class Torneo {
	private String nombTorneo;
	private int cantEquipos;
	private int cantJugadores;
	
	
	//CONSTRUCTORES
	public Torneo(String nombTorneo, int cantEquipos, int cantJugadores) {
		super();
		this.nombTorneo = comprobarNombredeTorneo(nombTorneo);
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
	public void comprobarNombreTorneo(String nomTorneo) {
		
	}
}
