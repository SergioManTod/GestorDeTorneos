import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


public class Jugador{
	private String nombre;
	private String apellidoUno;
	private String apellidoDos;
	private LocalDate fechaNacimiento;
	private String dni;
	private boolean esMayor=true;
	private int puntosJugador=0;
	private int amarillaJugador=0;
	private int rojasJugador=0;
	
	// CONSTRUCTORES
	
	public Jugador(String nombre, String apellidoUno, String apellidoDos, LocalDate fechaNacimiento, String dni) {
		super();
		this.nombre = nombre;
		this.apellidoUno = apellidoUno;
		this.apellidoDos = apellidoDos;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.esMayor = esMayor;
		
	}
	
	// GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoUno() {
		return apellidoUno;
	}

	public void setApellidoUno(String apellidoUno) {
		this.apellidoUno = apellidoUno;
	}

	public String getApellidoDos() {
		return apellidoDos;
	}

	public void setApellidoDos(String apellidoDos) {
		this.apellidoDos = apellidoDos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public boolean isEsMayor() {
		return esMayor;
	}

	public void setEsMayor(boolean esMayor) {
		this.esMayor = esMayor;
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

	//TOSTRING
	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", apellidoUno=" + apellidoUno + ", apellidoDos=" + apellidoDos
				+ ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + ", esMayor=" + esMayor + ", puntosJugador="
				+ puntosJugador + ", amarillaJugador=" + amarillaJugador + ", rojasJugador=" + rojasJugador + "]";
	}

	
	
	
}
