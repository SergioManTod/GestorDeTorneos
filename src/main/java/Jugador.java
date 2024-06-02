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

	
	//-----METODOS
	
	//--- METODO PARA VERIFICAR LA EDAD
		public static boolean validaEdad(Date fechaNacimiento) {
	        // OBTENEMOS AL FECHA ACTUAL
	        LocalDate fechaActual = LocalDate.now();
	        
	        // CONVERTIMOS LA FECHA DE NACIMIENTO DE DATA A LOCALDATE
	        LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant()
	                .atZone(ZoneId.systemDefault())
	                .toLocalDate();
	        
	        // CALCULAMOS LOS A�OS QUE HAY ENTRE LA FECHA DE NACIMEINTO Y LA FECHA ACTUAL
	        Period diferencia = Period.between(fechaNacimientoLocal, fechaActual);
	        
	        // VERIFICAMOS SI LA PERSONA ES MAYOR DE EDAD
	        return diferencia.getYears() >= 18;
	    }
	
	
	// METODO PARA VERIFICAR SI EL DNI ES VALIDO
		public static boolean validaDni(String dni) {
	        
			// ELIMINAMOS ESPACIOS EN BLANCO DEL DNI INGRESADO
	        dni = dni.trim();

	        // VERIFICAMOS QUE EL DNI TENGA LA LONGITUD CORRECTA (9 CARACTERES)
	        if (dni.length() != 9) {
	            return false;
	        }

	        // SEPARAMOS LA PARTE NUMERICA DE LA LETRA
	        int parteNumerica;
	        char letra;
	        try {
	            parteNumerica = Integer.parseInt(dni.substring(0, 8));
	            letra = Character.toUpperCase(dni.charAt(8));
	        } catch (NumberFormatException e) {
	            return false;
	        }

	        // DEFINIMOS LAS LETRAS VALIDAS 
	        char[] letrasValidas = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

	        // CALCULAMOS LA LETRA QUE DEBERIA PERTENECER A LA PARTE NUMERICA
	        int resto = parteNumerica % 23;
	        char letraCorrecta = letrasValidas[resto];

	        // VERIFICAMOS QUE LA LETRA INGRESADA ES IGUAL A LA LETRA CALCULADA
	        return letra == letraCorrecta;
	    }
	
}
