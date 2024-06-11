
public class Partido {

	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private Arbitro arbitroPartido;
	
	//constructores
	public Partido() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Partido(Equipo equipoLocal, Equipo equipoVisitante, Arbitro arbitroPartido) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.arbitroPartido = arbitroPartido;
		
		
	}
	//getters y setters
	public Equipo getEquipoLocal() {
		return equipoLocal;
	}
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}
	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}
	public Arbitro getArbitroPartido() {
		 
		return arbitroPartido;
	}
	public void setArbitroPartido(Arbitro arbitroPartido) {
		this.arbitroPartido = arbitroPartido;
	}
	
	//tostring
	@Override
	public String toString() {
		return " equipoLocal=" + equipoLocal.getNombre() + " vs equipoVisitante=" + equipoVisitante.getNombre() + "  arbitroPartido="
				+ arbitroPartido.getNombre() ;
	}

	
}
