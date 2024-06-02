import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Metodos {

	public Metodos() {
		super();
		// TODO Auto-generated constructor stub
	}
	//-----METODOS
	//verificar nombre
	public boolean validarNombre(String nombre) {
		if(nombre.equalsIgnoreCase("")||nombre.contentEquals("Aqui Nombre de equipo")) {
			return false;	
		}else {
			return true;
		}
		
		
	}
	//verificar apellido1
	public  boolean validarApellido1(String apellido1) {
		if(apellido1.equalsIgnoreCase("")||apellido1.contentEquals("aqui el apellido")) {
			return false;	
		}else {
			return true;
		}
		
		
	}
	//verificar appellido2
	public boolean validarApellido2(String apellido2) {
		if(apellido2.equalsIgnoreCase("")||apellido2.contentEquals("aqui el 2º apellido")) {
			return false;	
		}else {
			return true;
		}
		
		
	}
	
		//--- METODO PARA VERIFICAR LA EDAD
			public  boolean validarEdad(LocalDate fechaNacimiento) {
		        // OBTENEMOS AL FECHA ACTUAL
		        LocalDate fechaActual = LocalDate.now();
		        
		        // CONVERTIMOS LA FECHA DE NACIMIENTO DE DATA A LOCALDATE
		      
		        
		        // CALCULAMOS LOS A�OS QUE HAY ENTRE LA FECHA DE NACIMEINTO Y LA FECHA ACTUAL
		        Period diferencia = Period.between(fechaNacimiento, fechaActual);
		        
		        // VERIFICAMOS SI LA PERSONA ES MAYOR DE EDAD
		        return diferencia.getYears() >= 18;
		    }
		
		
		// METODO PARA VERIFICAR SI EL DNI ES VALIDO
			public  boolean validaDni(String dni) {
		        
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
			
			//validar nombre de equipo
			public boolean validarNombreEquipo(List<String> lista,String nombreEquipo) {
				for (int i = 0; i < lista.size(); i++) {
                	if(lista.get(i).equalsIgnoreCase(nombreEquipo)||nombreEquipo.equalsIgnoreCase(""))
                		return false;
                   }
				
				return true;
				
			}
			public boolean validarEmail(List<String> lista,String email) {
				String regexPattern = "^(.+)@(\\S+)$";
				if (Pattern.compile(regexPattern).matcher(email).matches()) {
					for (int i = 0; i < lista.size(); i++) {
						
						
	                	if(lista.get(i).equalsIgnoreCase(email))
	                		return false;
	                   }
				}
				
				
				return true;
				
			}
	
}
