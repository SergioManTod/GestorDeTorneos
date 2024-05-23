import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


public class Persona {
	private String nombre;
	private String apellidoUno;
	private String apellidoDos;
	private Date fechaNacimiento;
	private String dni;
	private boolean esMayor=true;
	
	//-------CONSTRUCTORES
	public Persona(String nombre, String apellidoUno, String apellidoDos, Date fechaNacimiento, String dni, boolean esMayor) {
		super();
		this.nombre = nombre;
		this.apellidoUno = apellidoUno;
		this.apellidoDos = apellidoDos;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.esMayor= esMayor;
	}

	public Persona() {
		super();
		// TODO Esbozo de constructor generado automáticamente
	}
	//---- GETTERS Y SETTERS

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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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
	//---- toString

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellidoUno=" + apellidoUno + ", apellidoDos=" + apellidoDos
				+ ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + ", esMayor=" + esMayor + "]";
	}
	
	//-----METODOS
	//--- METODO PARA VERIFICAR LA EDAD
	public static boolean validaEdad(Date fechaNacimiento) {
        // -----OBTENEMOS AL FECHA ACTUAL
        LocalDate fechaActual = LocalDate.now();
        // -----CONVERTIMOS LA FECHA DE NACIMIENTO DE DATA A LOCALDATE
        LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        // ----- CALCULAMOS LOS AÑOS QUE HAY ENTRE LA FECHA DE NACIMEINTO Y LA FECHA ACTUAL
        Period diferencia = Period.between(fechaNacimientoLocal, fechaActual);
        // Verificar si la persona es mayor de edad (18 años o más)
        return diferencia.getYears() >= 18;
    }
//--- METODO PARA VERIFICAR SI EL DNI ES VALIDO
	public static boolean validaDni(String dni) {
        // Eliminar espacios en blanco del DNI
        dni = dni.trim();

        // Verificar que el DNI tenga la longitud correcta (9 caracteres)
        if (dni.length() != 9) {
            return false;
        }

        // Extraer la parte numérica y la letra del DNI
        int parteNumerica;
        char letra;
        try {
            parteNumerica = Integer.parseInt(dni.substring(0, 8));
            letra = Character.toUpperCase(dni.charAt(8));
        } catch (NumberFormatException e) {
            return false;
        }

        // Definir las letras válidas para el DNI
        char[] letrasValidas = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        // Calcular la letra que debería corresponder a la parte numérica
        int resto = parteNumerica % 23;
        char letraCorrecta = letrasValidas[resto];

        // Verificar que la letra del DNI coincida con la letra calculada
        return letra == letraCorrecta;
    }
	
}
