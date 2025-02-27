import java.io.File;

	public class CreaDirectorios {

	
	    public static void arbolDeCarpetas(String nombTorneo) {
	        // Define las rutas de los directorios
	        String mainDirPath = "src/main/torneos/torneo_" + nombTorneo;
	        String subDirPath1 = mainDirPath + File.separator + "Inscripciones_recibidas";
	        String subDirPath2 = mainDirPath + File.separator + "Equipos_aceptados";
	        String subDirPath3 = mainDirPath + File.separator + "Actas_de_partidos";
	        String subDirPath4 = mainDirPath + File.separator + "Calendario";
	        String subDirPath5 = mainDirPath + File.separator + "Formulario_de_inscripcion";
	        String subDirPath6 = mainDirPath + File.separator + "Actas_rellenas";
	        String subDirPath7 = mainDirPath + File.separator + "Actas_Procesadas";

	        // Crea los directorios
	        createDirectory(mainDirPath);
	        createDirectory(subDirPath1);
	        createDirectory(subDirPath2);
	        createDirectory(subDirPath3);
	        createDirectory(subDirPath4);
	        createDirectory(subDirPath5);
	        createDirectory(subDirPath6);
	        createDirectory(subDirPath7);
	    }

	    // Método para crear un directorio
	    public static void createDirectory(String path) {
	        File directory = new File(path);

	        // Intenta crear los directorios
	        if (directory.mkdirs()) {
	            System.out.println("Directorio(s) creado(s): " + directory.getPath());
	        } else {
	            if (directory.exists()) {
	                System.out.println("El directorio ya existe: " + directory.getPath());
	            } else {
	                System.out.println("Fallo al crear el directorio: " + directory.getPath());
	            }
	        }
	    }
	}
