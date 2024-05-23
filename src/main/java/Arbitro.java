import java.util.Date;


public class Arbitro extends Persona{
	private boolean esArbitro=true;
	
	//---- constructores
	public Arbitro(String nombre, String apellidoUno, String apellidoDos, Date fechaNacimiento, String dni,
			boolean esMayor, boolean esArbitro) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni, esMayor);
		this.esArbitro = esArbitro;
	}

	public Arbitro() {
		super();
		// TODO Esbozo de constructor generado automáticamente
	}

	public Arbitro(String nombre, String apellidoUno, String apellidoDos, Date fechaNacimiento, String dni,
			boolean esMayor) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni, esMayor);
		// TODO Esbozo de constructor generado automáticamente
	}

	
	
	//--- getters y setters 
	
	
	public boolean isEsArbitro() {
		return esArbitro;
	}

	public void setEsArbitro(boolean esArbitro) {
		this.esArbitro = esArbitro;
	}

	@Override
	public String toString() {
		return "Arbitro [esArbitro=" + esArbitro + "]";
	}
	
	
	
}
