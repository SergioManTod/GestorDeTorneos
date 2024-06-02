import java.time.LocalDate;


public class Delegado extends Jugador{
	private boolean esDelegado=true;

	// CONSTRUCTORES
	public Delegado(String nombre, String apellidoUno, String apellidoDos, LocalDate fechaNacimiento, String dni) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni);
		// TODO Auto-generated constructor stub
	}

	// GETTERS Y SETTERS
	

	

	public void setEsDelegado(boolean esDelegado) {
		this.esDelegado = esDelegado;
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Delegado [esDelegado=" + esDelegado + "]";
	}
	
}
