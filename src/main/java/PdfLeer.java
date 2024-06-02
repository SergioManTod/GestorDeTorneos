import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import com.spire.ms.System.Collections.Generic.List;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.fields.PdfField;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfTextBoxFieldWidget;



public class PdfLeer {
	
  
public  PdfLeer leerPdf() {
	
		
		// Cargar el documento PDF
        PdfDocument pdf = new PdfDocument();
       // pdf.loadFromFile("torneo_"+nombre+"/Inscripciones_recibidas/Formulario_Inscripcion_torneo_"+nombre+".pdf");

        // Obtener el widget del formulario
        PdfFormWidget formWidget = (PdfFormWidget) pdf.getForm();
        String email="";	
        String nombreEquipo="";
        String nombreJugador="";
        	String apellido1="";
        	String apellido2="";
        	String dni="";
        	String fechaNacimiento=null;
        	
        StringBuilder sb = new StringBuilder();
        String texto1="";
        // Recorrer la colección de widgets de campos y extraer los valores
        for (int i = 0; i < 2; i++) {
        	PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);

        	if(i==0) {
        		nombreEquipo=leerCampo(pdf,formWidget,field);
        	}else {
        		email=leerCampo(pdf,formWidget,field);
        	}
            
//            if (field instanceof PdfTextBoxFieldWidget) {
//                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
//                Strin= textBoxField.getText();
//                System.out.println(texto1 + texto + "\r\n");
//                sb.append("El texto en el campo de texto es: " + texto + "\r\n");
//            }
            // También puedes manejar otros tipos de campos aquí (listas desplegables, etc.).

            // ...
        }
        for (int i = 2; i < formWidget.getFieldsWidget().getCount(); i++) {
            
        	PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
            for(int j=0;j<5;j++) {
            	switch (j) {
				case 0: {
					nombreJugador=leerCampo(pdf,formWidget,field);
					break;
					}
				case 1: {
					apellido1=leerCampo(pdf,formWidget,field);
					break;
					}
				case 2: {
					apellido2=leerCampo(pdf,formWidget,field);
					break;
					}
				case 3: {
					dni=leerCampo(pdf,formWidget,field);
					break;
					}
				case 4: {
					fechaNacimiento=leerCampo(pdf,formWidget,field);
					break;
					}
				}
//            	
//            	 if (field instanceof PdfTextBoxFieldWidget) {
//                     PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
//                     String texto = textBoxField.getText();
//                     System.out.println(texto1 + texto + "\r\n");
//                     sb.append("El texto en el campo de texto es: " + texto + "\r\n");
//                 }
            	 if(j!=4) {
            		 i++; 
            	 }
            	 if(i==2) {
            		 LocalDate fechaF = LocalDate.parse(fechaNacimiento);
                     
                	 Delegado nuevodelegado= new Delegado(nombreJugador,apellido1,apellido2,fechaF,dni);
                	 
            	 }else {
            		 if(i==3) {
            			 LocalDate fechaF = LocalDate.parse(fechaNacimiento);
                         
                    	 Arbitro nuevoArbitro= new Arbitro(nombreJugador,apellido1,apellido2,fechaF,dni);
                    	 
            		 }else {
            			 LocalDate fechaF = LocalDate.parse(fechaNacimiento);
                         
                    	 Jugador nuevo= new Jugador(nombreJugador,apellido1,apellido2,fechaF,dni);
            		 }
            	 }
            	
                 
            }

         
         
           
        }

        

        pdf.close();
		
		return null;
		
	}
   public String leerCampo( PdfDocument pdf,PdfFormWidget formWidget, PdfField field) {
	  String campo="";
	   if (field instanceof PdfTextBoxFieldWidget) {
           PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
           campo = textBoxField.getText();
           System.out.println(campo + "\r\n");
           
       }
	   
	return campo;
	   
   }
   

}
