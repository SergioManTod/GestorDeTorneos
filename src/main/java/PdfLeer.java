import java.text.DateFormat;
import java.time.LocalDate;
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
	
  
public  ArrayList<Object[]> leerPdf(int minimoJugadores) {
	   ArrayList<Object[]> listaequipos = new ArrayList<Object[]>();
		 
		// Cargar el documento PDF
        PdfDocument pdf = new PdfDocument();
       // pdf.loadFromFile("torneo_"+nombre+"/Inscripciones_recibidas/Formulario_Inscripcion_torneo_"+nombre+".pdf");
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
        	LocalDate fechaF = LocalDate.parse(fechaNacimiento);
        	List<LocalDate> listaFechas=new List<LocalDate>();
        	StringBuilder sb = new StringBuilder();
        	boolean inscribirEquipo=true;
        	
        String texto1="";
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
        for (int i = 2; i < formWidget.getFieldsWidget().getCount(); i++) {
        	boolean inscribirJugador=true;
        	PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
            for(int j=0;j<4;j++) {
            	switch (j) {
				case 0: {
					nombreJugador=leerCampo(pdf,formWidget,field);
					System.out.println("nombre jugador ="+nombreJugador);
					if(!comprobar.validarNombre(nombreJugador)) {
						inscribirJugador=false;
					}
					break;
					}
				case 1: {
					
					apellido1=leerCampo(pdf,formWidget,field);
					System.out.println("apellido1 ="+apellido1);
					if(!comprobar.validarApellido1(apellido1)) {
						inscribirJugador=false;
					}
					break;
					}
				case 2: {
					apellido2=leerCampo(pdf,formWidget,field);
					System.out.println("apellido2 = "+apellido2);
					if(!comprobar.validarApellido2(apellido2)) {
						inscribirJugador=false;
					}
					break;
					}
				case 3: {
					dni=leerCampo(pdf,formWidget,field);
					System.out.println("dni= "+dni);
					if(!comprobar.validaDni(dni)) {
						inscribirJugador=false;
					}
					break;
					}
				case 4: {
					fechaNacimiento=leerCampo(pdf,formWidget,field);
					System.out.println("fecha nacimiento ="+fechaNacimiento);
					if(!comprobar.validarEdad(fechaF)) {
						inscribirJugador=false;
					}
					
					break;
					}
				}
            	
            	if(inscribirJugador) {
            		listaNombresjugador.add(nombreJugador);
            		listaApellidos1.add(apellido1);
            		listaApellidos2.add(apellido2);
            		listaDni.add(dni);
            		listaFechas.add(fechaF);
            	}
            	
                 
            }
        
            
            
           
        }
        if(inscribirEquipo==true&& listaNombresjugador.size()>=minimoJugadores ) {
        	for(int i=0;i<listaNombresjugador.size();i++) {
        		  
        		Object[] objNomEquipo= {nombreEquipo}; 
        		Object[] objEmail= {email}; 
        		listaequipos.add(objNomEquipo);
        		listaequipos.add(objEmail);
        		
        		if(i==0) {
        			
        			
        			Delegado nuevodelegado= new Delegado(listaNombresjugador.get_Item(i),listaApellidos1.get_Item(i),listaApellidos2.get_Item(i),listaFechas.get_Item(i),listaEmail.get_Item(i));
        			Object[] objetoDelegado= {nuevodelegado};
        			listaequipos.add(objetoDelegado);
        		}else {
        			if(i==1) {
        				Arbitro nuevoArbitro= new Arbitro(listaNombresjugador.get_Item(i),listaApellidos1.get_Item(i),listaApellidos2.get_Item(i),listaFechas.get_Item(i),listaEmail.get_Item(i));
        				Object[] objetoArbitro= {nuevoArbitro};
            			listaequipos.add(objetoArbitro);
        						}else {
        				Jugador nuevoJugador= new Jugador(listaNombresjugador.get_Item(i),listaApellidos1.get_Item(i),listaApellidos2.get_Item(i),listaFechas.get_Item(i),listaEmail.get_Item(i));
        				Object[] objetojugador= {nuevoJugador};
            			listaequipos.add(objetojugador);
        			}
        			
        		}
        	}
        	pdf.close();
        	return listaequipos;
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
           System.out.println(campo + "\r\n");
           
       }
	   
	return campo;
	   
   }
   

}
