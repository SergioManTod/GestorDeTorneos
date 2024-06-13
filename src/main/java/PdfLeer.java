import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.mysql.cj.protocol.x.CompressionAlgorithm;
import com.spire.ms.System.Collections.Generic.List;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.fields.PdfField;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfTextBoxFieldWidget;



public class PdfLeer {
	
  
public  Equipo leerPdf(int minimoJugadores,String nombreTorneo, String nombreArchivo ) {
	   ArrayList<Object[]> listaequipos = new ArrayList<>();
	   
		// Cargar el documento PDF
        PdfDocument pdf = new PdfDocument();
        
        pdf.loadFromFile("src/main/torneos/torneo_"+nombreTorneo+"/Inscripciones_recibidas/"+nombreArchivo);
        	Metodos comprobar=new Metodos();
        // Obtener el widget del formulario
        PdfFormWidget formWidget = (PdfFormWidget) pdf.getForm();
        	List<String> listaEmail=new List<String>();
        	String email="";
        
        	List<String> listaNombresEquipo=new List<String>();
        	String nombreEquipo="";
        
        	List<String> listaNombresjugador=new List<String>();
        	String nombreJugador="";
        
        	List<String> listaApellidos1=new List<String>();
        	String apellido1="";
        	
        	List<String> listaApellidos2=new List<String>();
        	String apellido2="";
        	
        	List<String> listaDni=new List<String>();
        	String dni="";
        	
        	String fechaNacimiento=null;
        	LocalDate fechaF = null ;
        	List<LocalDate> listaFechas=new List<LocalDate>();
        	StringBuilder sb = new StringBuilder();
        	
        	boolean inscribirEquipo=true;
        	Delegado nuevoDelegado=new Delegado();
        	Arbitro nuevoArbitro=new Arbitro();
        	Jugador nuevoJugador=new Jugador();
        	
       
        // Recorrer la colección de widgets de campos y extraer los valores
        for (int i = 0; i < 2; i++) {
        	
        	PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
        	
        	if(i==0) {
        		nombreEquipo=leerCampo(pdf,formWidget,field);
        		if(!comprobar.validarNombreEquipo(listaNombresEquipo, nombreEquipo)) {
        			inscribirEquipo=false;
        		}
      
        		
        	}else {
        		email=leerCampo(pdf,formWidget,field);
        		if(!comprobar.validarEmail(listaEmail, email)) {
        			inscribirEquipo=false;
        		}
        	}
            

        }
//        
        for (int i = 2; i < formWidget.getFieldsWidget().getCount(); ) {
        	
            boolean inscribirJugador = true;
            
            for(int j=0;j<5;j++,i++) {
            	
            	PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
            	switch (j) {
				case 0: 
					nombreJugador = leerCampo(pdf, formWidget, field);
					
				break;
					case 1: 
						apellido1 = leerCampo(pdf, formWidget, field);
					
				break;
					case 2: 
						apellido2 = leerCampo(pdf, formWidget, field);
						break;
					
					case 3: 
						dni = leerCampo(pdf, formWidget, field);
						
					break;
					case 4: 
						fechaNacimiento = leerCampo(pdf, formWidget, field);
		             	
		             fechaF = stringToLocalDate(fechaNacimiento);
						
					break;
					default:
					throw new IllegalArgumentException("Unexpected value: " + j);
					}
            }
            	 if (!comprobar.validarNombre(nombreJugador) ||
                         !comprobar.validarApellido1(apellido1) ||
                         !comprobar.validarApellido2(apellido2) ||
                         !comprobar.validaDni(dni)) {
                         inscribirJugador = false;
                         
                     }

                     if (inscribirJugador) {
                         listaNombresjugador.add(nombreJugador);
                        
                         listaApellidos1.add(apellido1);
                        
                         listaApellidos2.add(apellido2);
                         
                         listaDni.add(dni);
                        
                         listaFechas.add(fechaF);
                         
                     }
            	
            
            
            
             
             
             

           
            
        }
        Jugador[] arrayJugadores=new Jugador[listaNombresjugador.size()-2];
       
       int contadorPosicion=0;	
        if(inscribirEquipo==true&& listaNombresjugador.size()>=minimoJugadores ) {
        	for(int i=0;i<listaNombresjugador.size();i++) {
        		  
        		
        		
        		
        		if(i==0) {
        			
        			
        			nuevoDelegado= new Delegado(listaNombresjugador.get(i).toString(),listaApellidos1.get(i).toString(),listaApellidos2.get(i).toString(),listaFechas.get(i),listaDni.get(i).toString());
        			
        			
        		}else {
        			if(i==1) {
        				nuevoArbitro= new Arbitro(listaNombresjugador.get(i).toString(),listaApellidos1.get(i).toString(),listaApellidos2.get(i).toString(),listaFechas.get(i),listaDni.get(i).toString());
        				
        			}else {
        						
        				 nuevoJugador=new Jugador(listaNombresjugador.get(i).toString(),listaApellidos1.get(i).toString(),listaApellidos2.get(i).toString(),listaFechas.get(i),listaDni.get(i).toString());			
        				 
        				 
        				 arrayJugadores[contadorPosicion]=nuevoJugador ;
        				
            			contadorPosicion++;
        			}
        			
        		}
        		
        	}
        	
        	Equipo nuevoEquipo=new Equipo( nombreEquipo,email,nuevoDelegado,nuevoArbitro,arrayJugadores);
        	
        	pdf.close();
        	return  nuevoEquipo;
        	
        }else {
            pdf.close();
        	return null;
        	}
        

        
    	
		
	
		
	}
   public String leerCampo( PdfDocument pdf,PdfFormWidget formWidget, PdfField field) {
	  String campo="";
	   if (field instanceof PdfTextBoxFieldWidget) {
           PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
           campo = textBoxField.getText();
           
           
       }
	   
	return campo ;
	   
   }
   
   public static LocalDate stringToLocalDate(String date) {
       final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate localDate;
       try {
           localDate = LocalDate.parse(date, dateTimeFormatter);
       } catch (DateTimeParseException e) {
           return null;
       }
       return localDate;
   }
   public void leerActa(String nombreArchivo,BaseDeDatos baseDeDatos, String NombreTorneo) throws SQLException {
	   System.out.println("inicia lectura");
	   String []nombresEquipos=dividirNombre(nombreArchivo);
	   System.out.println(nombresEquipos[0]);
	   System.out.println(nombresEquipos[1]);
	  
	   
		 Equipo equipoLocal =baseDeDatos.consultarEquipo(NombreTorneo, nombresEquipos[0]);
		Equipo equipoVisitante =baseDeDatos.consultarEquipo(NombreTorneo, nombresEquipos[1]); 
		if (equipoLocal != null) {
		    System.out.println(equipoLocal.getId());
		} else {
		    System.out.println("El equipo local es null");
		}

		if (equipoVisitante != null) {
		    System.out.println(equipoVisitante.getId());
		} else {
		    System.out.println("El equipo visitante es null");
		}
		 ArrayList<Jugador> listaJugadoresLocal=new ArrayList<Jugador>();
		   ArrayList<Jugador> listaJugadoresVisitate=new ArrayList<Jugador>();
	
		
	
	   Jugador[] jugadoresLocal=(Jugador[]) baseDeDatos.consultaJugadores(equipoLocal.getId());
	   Jugador[] jugadoresVisitante=(Jugador[]) baseDeDatos.consultaJugadores(equipoLocal.getId());
	   System.out.println(jugadoresLocal[0].getNombre());
	   
	  	equipoLocal.setJugadores(jugadoresLocal);
	  	equipoVisitante.setJugadores(jugadoresVisitante);
	   
	  	
	   
	  	
	   // Cargar el documento PDF
	   PdfDocument pdf = new PdfDocument();
       pdf.loadFromFile("src\\main\\torneos\\torneo_"+NombreTorneo+"\\Actas_rellenas\\"+nombreArchivo);
       PdfFormWidget formWidget = (PdfFormWidget) pdf.getForm();
       int contadorJugadoreslocal=0;
       int contadorJugadoresVisitante=0;
       int sumagLocal=0;
       int sumagVisitante=0;
       
       for (int i = 0; i < formWidget.getFieldsWidget().getCount(); i++) {
           PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
           if (i <formWidget.getFieldsWidget().getCount()/2) {
               equipoLocal.getJugadores()[contadorJugadoreslocal].setGoles(Integer.parseInt(leerCampo(pdf, formWidget, field)));
               System.out.println("local" +equipoLocal.getJugadores()[contadorJugadoreslocal].getGoles()+ equipoLocal.getJugadores()[contadorJugadoreslocal].getNombre());
               sumagLocal += equipoLocal.getJugadores()[contadorJugadoreslocal].getGoles();
               
               int idJugador=equipoLocal.getJugadores()[contadorJugadoreslocal].getId();
               int goleJugador=Integer.parseInt(leerCampo(pdf, formWidget, field));
               baseDeDatos.actualizaGoles(idJugador, goleJugador);
               contadorJugadoreslocal++;
           } else {
               equipoVisitante.getJugadores()[contadorJugadoresVisitante].setGoles(Integer.parseInt(leerCampo(pdf, formWidget, field)));
               sumagVisitante += equipoVisitante.getJugadores()[contadorJugadoresVisitante].getGoles();
               System.out.println("visitantte"+ equipoLocal.getJugadores()[contadorJugadoresVisitante].getGoles());
              
               int idJugador=equipoVisitante.getJugadores()[contadorJugadoresVisitante].getId();
               int goleJugador=Integer.parseInt(leerCampo(pdf, formWidget, field));
               baseDeDatos.actualizaGoles(idJugador, goleJugador);
               contadorJugadoresVisitante++;
           }
       }

       System.out.println("La suma de goles del equipo local es: " + sumagLocal);
       System.out.println("La suma de goles del equipo visitante es: " + sumagVisitante);
       int puntosPartidoLocal=0;
       int puntosPartidoVisitante=0;
       
       if(sumagLocal>sumagVisitante) {
    	   puntosPartidoLocal=3;
    	   System.out.println("El partido lo ganó en equipo local");
    	   baseDeDatos.actualizaPuntos(nombresEquipos[0]);
       }else if (sumagLocal<sumagVisitante) {
    	   puntosPartidoVisitante=3;
    	   System.out.println("El partido lo ganó en equipo Visitante");
    	   baseDeDatos.actualizaPuntos(nombresEquipos[1]);
       }else {
    	   puntosPartidoLocal=1;
           puntosPartidoVisitante=1;
           System.out.println("El partido terminó en empate");
       }
       
       
       pdf.close();
   }

   
		
   
   
   
   public String[] dividirNombre(String nombreArchivo) {
	    String[] nombreArray = new String[2];

	    try {
	        // Eliminar la parte "acta_" y la extensión ".pdf"
	        String nameWithoutPrefixAndExtension = nombreArchivo.replace("Acta_", "").replace(".pdf", "");

	        // Dividir el nombre en dos partes utilizando el guion bajo como separador
	        String[] nameParts = nameWithoutPrefixAndExtension.split("_");

	        // Verificar que el nombre tenga al menos dos partes
	        if (nameParts.length >= 2) {
	            nombreArray[0] = nameParts[0];
	            nombreArray[1] = nameParts[1];
	        } else {
	            throw new IllegalArgumentException("El nombre del archivo no tiene el formato esperado.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al dividir el nombre del archivo: " + e.getMessage());
	    }

	    return nombreArray;
	}
}

