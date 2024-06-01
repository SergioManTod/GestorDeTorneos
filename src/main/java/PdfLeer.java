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

        StringBuilder sb = new StringBuilder();
        String texto1="";
        // Recorrer la colección de widgets de campos y extraer los valores
        for (int i = 0; i < 2; i++) {
        	
        	if(i==0) {
        		texto1="el nombre de equipo es ";
        	}else {
        		texto1="el correo de contacto es ";
        	}
            PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);

            if (field instanceof PdfTextBoxFieldWidget) {
                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
                String texto = textBoxField.getText();
                System.out.println(texto1 + texto + "\r\n");
                sb.append("El texto en el campo de texto es: " + texto + "\r\n");
            }
            // También puedes manejar otros tipos de campos aquí (listas desplegables, etc.).

            // ...
        }
        for (int i = 2; i < formWidget.getFieldsWidget().getCount(); i++) {
            
        		
            for(int j=0;j<5;j++) {
            	switch (j) {
				case 0: {
					texto1="nombre de jugador= ";
					break;
					}
				case 1: {
					texto1="apellido 1 de jugador= ";
					break;
					}
				case 2: {
					texto1="apellido 2 de jugador= ";
					break;
					}
				case 3: {
					texto1="dni de jugador= ";
					break;
					}
				case 4: {
					texto1="fecha de nacimiento de jugador= ";
					break;
					}
				}
            	PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
            	 if (field instanceof PdfTextBoxFieldWidget) {
                     PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
                     String texto = textBoxField.getText();
                     System.out.println(texto1 + texto + "\r\n");
                     sb.append("El texto en el campo de texto es: " + texto + "\r\n");
                 }
            	 if(j!=4) {
            		 i++; 
            	 }
            	
            }

           
            // También puedes manejar otros tipos de campos aquí (listas desplegables, etc.).

            // ...
        }

       // try {
            // Escribir los valores en un archivo .txt
            //FileWriter writer = new FileWriter("ValoresDeTodosLosCampos.txt");
        //writer.write(sb.toString());
        // writer.flush();
        // writer.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        //}

        pdf.close();
		
		return null;
		
	}

}
