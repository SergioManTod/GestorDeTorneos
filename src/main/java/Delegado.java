import java.time.LocalDate;


public class Delegado extends Jugador{
	

	// CONSTRUCTORES
	public Delegado() {
		super();// TODO Auto-generated constructor stub
	}
	
	
	public Delegado(String nombre2, boolean esDelegado) {
		super(nombre2);
	
	}


	public Delegado(String nombre, String apellidoUno, String apellidoDos, LocalDate fechaNacimiento, String dni) {
		super(nombre, apellidoUno, apellidoDos, fechaNacimiento, dni);
		// TODO Auto-generated constructor stub
	}

	// GETTERS Y SETTERS
	

	

	

	

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}

	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}

	@Override
	public String getApellidoUno() {
		// TODO Auto-generated method stub
		return super.getApellidoUno();
	}

	@Override
	public void setApellidoUno(String apellidoUno) {
		// TODO Auto-generated method stub
		super.setApellidoUno(apellidoUno);
	}

	@Override
	public String getApellidoDos() {
		// TODO Auto-generated method stub
		return super.getApellidoDos();
	}

	@Override
	public void setApellidoDos(String apellidoDos) {
		// TODO Auto-generated method stub
		super.setApellidoDos(apellidoDos);
	}

	@Override
	public LocalDate getFechaNacimiento() {
		// TODO Auto-generated method stub
		return super.getFechaNacimiento();
	}

	@Override
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		// TODO Auto-generated method stub
		super.setFechaNacimiento(fechaNacimiento);
	}

	@Override
	public String getDni() {
		// TODO Auto-generated method stub
		return super.getDni();
	}

	@Override
	public void setDni(String dni) {
		// TODO Auto-generated method stub
		super.setDni(dni);
	}

	@Override
	public boolean isEsMayor() {
		// TODO Auto-generated method stub
		return super.isEsMayor();
	}

	@Override
	public void setEsMayor(boolean esMayor) {
		// TODO Auto-generated method stub
		super.setEsMayor(esMayor);
	}

	@Override
	public int getPuntosJugador() {
		// TODO Auto-generated method stub
		return super.getPuntosJugador();
	}

	@Override
	public void setPuntosJugador(int puntosJugador) {
		// TODO Auto-generated method stub
		super.setPuntosJugador(puntosJugador);
	}

	@Override
	public int getAmarillaJugador() {
		// TODO Auto-generated method stub
		return super.getAmarillaJugador();
	}

	@Override
	public void setAmarillaJugador(int amarillaJugador) {
		// TODO Auto-generated method stub
		super.setAmarillaJugador(amarillaJugador);
	}

	@Override
	public int getRojasJugador() {
		// TODO Auto-generated method stub
		return super.getRojasJugador();
	}

	
	
	// TOSTRING
	
	
	
}
