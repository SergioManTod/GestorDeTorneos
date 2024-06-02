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

        System.out.println("torneo_"+nombreTorneo+"\\Inscripciones_recibidas\\"+nombreArchivo);
        pdf.loadFromFile("torneo_"+nombreTorneo+"\\Inscripciones_recibidas\\"+nombreArchivo);
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
        	System.out.println("aqui no");
        	PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
        	System.out.println("aqui tampoco");
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
        for (int i = 2; i < formWidget.getFieldsWidget().getCount(); i++) {
        	System.out.println("aqui???");
            boolean inscribirJugador = true;
            PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);

            nombreJugador = leerCampo(pdf, formWidget, field);
            apellido1 = leerCampo(pdf, formWidget, field);
             apellido2 = leerCampo(pdf, formWidget, field);
             dni = leerCampo(pdf, formWidget, field);
             fechaNacimiento = leerCampo(pdf, formWidget, field);

             fechaF = stringToLocalDate(fechaNacimiento);

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
        Jugador[] arrayJugadores=new Jugador[listaNombresjugador.size()];
        if(inscribirEquipo==true&& listaNombresjugador.size()>=minimoJugadores ) {
        	for(int i=0;i<listaNombresjugador.size();i++) {
        		  
        		
        		
        		
        		if(i==0) {
        			
        			
        			nuevoDelegado= new Delegado(listaNombresjugador.get(i).toString(),listaApellidos1.get(i).toString(),listaApellidos2.get(i).toString(),listaFechas.get(i),listaDni.get(i).toString());
        			
        			
        		}else {
        			if(i==1) {
        				nuevoArbitro= new Arbitro(listaNombresjugador.get(i).toString(),listaApellidos1.get(i).toString(),listaApellidos2.get(i).toString(),listaFechas.get(i),listaDni.get(i).toString());
        				
        						}else {
        				nuevoJugador= new Jugador(listaNombresjugador.get(i).toString(),listaApellidos1.get(i).toString(),listaApellidos2.get(i).toString(),listaFechas.get(i),listaDni.get(i).toString());
        				
            			
        			}
        			
        		}
        		
        	}
        	Equipo nuevoEquipo=new Equipo(nombreEquipo,email,nuevoDelegado,nuevoArbitro,arrayJugadores);	
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
}

