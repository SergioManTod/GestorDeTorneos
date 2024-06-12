import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.fields.PdfTextBoxField;
import com.spire.pdf.graphics.PdfBrush;
import com.spire.pdf.graphics.PdfBrushes;
import com.spire.pdf.graphics.PdfFont;
import com.spire.pdf.graphics.PdfFontFamily;
import com.spire.pdf.graphics.PdfPen;
import com.spire.pdf.graphics.PdfRGBColor;
import com.spire.pdf.graphics.PdfSolidBrush;



public class PdfCrear {
	
	
	
	
	public  PdfCrear crearpdfFormulario(String nombreTorneo,int cantidadJugadores) {
		
		PdfDocument doc = new PdfDocument();

	    PdfPageBase page = doc.getPages().add();
	    
	    // Crear fuentes y pinceles para el texto
	    PdfFont font = new PdfFont(PdfFontFamily.Helvetica, 8f);
	    PdfFont fontT = new PdfFont(PdfFontFamily.Times_Roman, 16f);
	    PdfBrush brush = new PdfSolidBrush(new PdfRGBColor(Color.black));
		
		
	    
	    // Definir la posición inicial para los campos del formulario
	    
	    float y = 50;
	    float width = 100;
	    float height = 15;
	 
	    
	    page.getCanvas().drawString("Inscripción Equipo", fontT, brush, new Point2D.Float(150,25));
	    page.getCanvas().drawString(nombreTorneo, fontT, brush, new Point2D.Float(150,50));
	    
	    // Añadir campo para el nombre
	    	
	    y+=25;	
	    try {
	    	page.getCanvas().drawString("Nombre del equipo:", font, brush, new Point2D.Float(100, y));
	    	
	    	Rectangle2D.Float tbxBounds = new Rectangle2D.Float(250, y,200, height);
	        
			PdfTextBoxField equipoNombre = new PdfTextBoxField(page, "nombreEquipo");
			equipoNombre.setBounds(tbxBounds);
			equipoNombre.setText("Aqui Nombre de equipo");
			equipoNombre.setFont(font);
			doc.getForm().getFields().add(equipoNombre);
			y += 25;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	
	    try {
	    	page.getCanvas().drawString("Correo electronico:", font, brush, new Point2D.Float(100, y));
	    	
	    	Rectangle2D.Float tbxBounds = new Rectangle2D.Float(250, y,200, height);
	        
			PdfTextBoxField equipoNombre = new PdfTextBoxField(page, "correo Electronico");
			equipoNombre.setBounds(tbxBounds);
			equipoNombre.setText("correo electronico");
			equipoNombre.setFont(font);
			doc.getForm().getFields().add(equipoNombre);
			y += 25;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 
	    String titulo="";
	    //crear bucle para repetir estructura en base a numero de jugadores
	    
	    for(int i=0;i<=cantidadJugadores;i++) {
	    	if(i==7) {
	    		y=50;
	    	page =	 doc.getPages().add();
	    	}
	     if(i==0) {
	    	 titulo="Jugador/Responsable";
	     }else {
	    	 if(i==1) {
	    		 titulo="Jugador/Arbitro";
	    	 }else {
	    		 titulo="Jugador";
	    	 }
	     }
	     
	    //titulo para Entrenador
	    page.getCanvas().drawString(titulo, fontT, brush, new Point2D.Float(50,y));
	    y+=20;
	    // etiquetas campos
	    page.getCanvas().drawString("Nombre", font, brush, new Point2D.Float(50,y));
	    page.getCanvas().drawString("Primer Apellido", font, brush, new Point2D.Float(200,y));
	    page.getCanvas().drawString("Segundo Apellido", font, brush, new Point2D.Float(400,y));
	    y+=10;
		try {
			PdfTextBoxField nombreCampo;
			nombreCampo = new PdfTextBoxField(page, "Nombre");
			 nombreCampo.setBounds(new Rectangle2D.Float(50, y, width, height));
			 	nombreCampo.setText("aqui el nombre");
		        nombreCampo.setFont(font);
		        doc.getForm().getFields().add(nombreCampo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    
	    // Añadir campo para los apellidos
	   
	    // Aumentar la posición en Y para el siguiente campo
	    
		try {
			PdfTextBoxField apellido1Campo;
			apellido1Campo = new PdfTextBoxField(page, "Apellido1");
			apellido1Campo.setBounds(new Rectangle2D.Float(200, y, width, height));
			apellido1Campo.setText("aqui el apellido");
			apellido1Campo.setFont(font);
	        doc.getForm().getFields().add(apellido1Campo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
			// Aumentar la posición en Y para el siguiente campo
	    
		try {
			PdfTextBoxField apellido2Campo;
			apellido2Campo = new PdfTextBoxField(page, "Apellido2");
			apellido2Campo.setBounds(new Rectangle2D.Float(400, y, width, height));
			apellido2Campo.setText("aqui el 2º apellido");
			apellido2Campo.setFont(font);
	        doc.getForm().getFields().add(apellido2Campo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		
		
	    // Añadir campo para el DNI
	    y+=15; 
	    
	 
		try {
			page.getCanvas().drawString("DNI", font, brush, new Point2D.Float(50,y));
			y +=10;
			PdfTextBoxField dniCampo;
			dniCampo = new PdfTextBoxField(page, "Dni");
			
			dniCampo.setBounds(new Rectangle2D.Float(50, y, width, height));
			dniCampo.setText("aqui DNI");
	        dniCampo.setFont(font);
	        doc.getForm().getFields().add(dniCampo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			y-=10;
			page.getCanvas().drawString("Fecha de nacimiento dd/MM/YY", font, brush, new Point2D.Float(200,y));
			y +=10;
			PdfTextBoxField fechaCampo;
			fechaCampo = new PdfTextBoxField(page, "fechanNacimiento");
			
			fechaCampo.setBounds(new Rectangle2D.Float(200, y, width, height));
			fechaCampo.setText("aqui fecha");
	        fechaCampo.setFont(font);
	        doc.getForm().getFields().add(fechaCampo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	y+=25;
	    }
	    
	    // Guardar el documento PDF
	    doc.saveToFile("src/main/torneos/torneo_"+nombreTorneo+"/Formulario_de_inscripcion/Formulario_Inscripcion_torneo_"+nombreTorneo+".pdf");
	    doc.close();
		return null;
	}
	
	
	public void crearCalendario(Partido partidos [],String nombreTorneo ) {
		PdfDocument doc = new PdfDocument();

	    PdfPageBase page = doc.getPages().add();
	    
	    // Crear fuentes y pinceles para el texto
	    PdfFont font = new PdfFont(PdfFontFamily.Helvetica, 10f);
	    PdfFont fontT = new PdfFont(PdfFontFamily.Times_Roman, 16f);
	    PdfBrush brush = new PdfSolidBrush(new PdfRGBColor(Color.black));
		
		
	    
	    // Definir la posición inicial para los campos del formulario
	    
	    float y = 50;
	    float width = 100;
	    float height = 15;
	 
	    
	   
	    page.getCanvas().drawString("TORNEO:  "+nombreTorneo, fontT, brush, new Point2D.Float(150,50));
	    y+=20;
	    page.getCanvas().drawString("Equipo Visitante", fontT, brush, new Point2D.Float(50,y));
	    page.getCanvas().drawString("Equipo Local", fontT, brush, new Point2D.Float(200,y));
	    page.getCanvas().drawString("Arbitro", fontT, brush, new Point2D.Float(300,y));
	    y+=30;
	    for (int i=0;i<partidos.length;i++){
	    	y += 20;
	        
	        PdfBrush brushBlack = PdfBrushes.getBlack(); // Color del texto y la línea
	        page.getCanvas().drawString(partidos[i].getEquipoLocal().getNombre(), font, brushBlack, new Point2D.Float(50, y));
	        page.getCanvas().drawString(partidos[i].getEquipoVisitante().getNombre(), font, brushBlack, new Point2D.Float(200, y));
	        
	        if (!partidos[i].getEquipoLocal().getNombre().equalsIgnoreCase("descanso") && 
	            !partidos[i].getEquipoVisitante().getNombre().equalsIgnoreCase("descanso")) {
	            page.getCanvas().drawString(partidos[i].getArbitroPartido().getNombre(), font, brushBlack, new Point2D.Float(300, y));
	        }
	        
	        // Dibujar una línea negra en la base del texto
	        page.getCanvas().drawLine(new PdfPen(brushBlack, 1), new Point2D.Float(0, y + 11), new Point2D.Float(400, y + 11));

	    }
	    
	   
	    
	    // Guardar el documento PDF
	    doc.saveToFile("src/main/torneos/torneo_"+nombreTorneo+"/Calendario/Calendario_torneo_"+nombreTorneo+".pdf");
	    
	    doc.close();
	}
	public void  crearActa( Partido partido,String nombreTorneo) {
		PdfDocument doc = new PdfDocument();

	    PdfPageBase page = doc.getPages().add();
	    
	    // Crear fuentes y pinceles para el texto
	    PdfFont font = new PdfFont(PdfFontFamily.Helvetica, 10f);
	    PdfFont fontT = new PdfFont(PdfFontFamily.Times_Roman, 16f);
	    PdfBrush brush = new PdfSolidBrush(new PdfRGBColor(Color.black));
		
		
	    
	    // Definir la posición inicial para los campos del formulario
	    
	    float y = 50;
	    float width = 20;
	    float height = 15;
	 
	    
	   
	    page.getCanvas().drawString("TORNEO: "+nombreTorneo, fontT, brush, new Point2D.Float(150,50));
	    y+=25;
	    page.getCanvas().drawString("Arbitro "+partido.getArbitroPartido().getNombre(), fontT, brush, new Point2D.Float(150,y));
	    y+=30;
	    page.getCanvas().drawString(partido.getEquipoLocal().getNombre(), fontT, brush, new Point2D.Float(50,y));
	   
	    page.getCanvas().drawString(partido.getEquipoVisitante().getNombre(), fontT, brush, new Point2D.Float(350,y));
	    page.getCanvas().drawString("Goles", fontT, brush, new Point2D.Float(150,y));
	    page.getCanvas().drawString("Goles", fontT, brush, new Point2D.Float(300,y));
	    
	    for(int i=0;i<partido.getEquipoLocal().jugadores.length+2;i++) {
	    	y+=20;
	    	if(i==partido.getEquipoLocal().jugadores.length) {
	    		
	    		page.getCanvas().drawString(partido.getEquipoLocal().getResponsable().getNombre(), fontT, brush, new Point2D.Float(50,y));
		    	
	    	}else {
	    		if (i==partido.getEquipoVisitante().jugadores.length+1) {
	    			page.getCanvas().drawString(partido.getEquipoLocal().getArbitro().getNombre(), fontT, brush, new Point2D.Float(50,y));
	    		}else {
	    			page.getCanvas().drawString(partido.getEquipoLocal().getJugadores()[i].getNombre(), fontT, brush, new Point2D.Float(50,y));
	    		}
	    	}
	    	
	    	
	    	Rectangle2D.Float tbxBounds = new Rectangle2D.Float(150, y,width, height);
	        
			PdfTextBoxField goles;
			try {
				goles = new PdfTextBoxField(page, "goles");
				goles.setBounds(tbxBounds);
				goles.setText("0");
				goles.setFont(font);
				doc.getForm().getFields().add(goles);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	    
	}
	    y=105;
for(int i=0;i<partido.getEquipoVisitante().jugadores.length+2;i++) {
	    	y+=20;
	    	if(i==partido.getEquipoVisitante().jugadores.length) {
	    		
	    		page.getCanvas().drawString(partido.getEquipoVisitante().getResponsable().getNombre(), fontT, brush, new Point2D.Float(350,y));
		    	
	    	}else {
	    		if (i==partido.getEquipoVisitante().jugadores.length+1) {
	    			page.getCanvas().drawString(partido.getEquipoVisitante().getArbitro().getNombre(), fontT, brush, new Point2D.Float(350,y));
	    		}else {
	    			page.getCanvas().drawString(partido.getEquipoVisitante().getJugadores()[i].getNombre(), fontT, brush, new Point2D.Float(350,y));
	    		}
	    	}
	    	
	    	
	    	
	    	Rectangle2D.Float tbxBounds = new Rectangle2D.Float(300, y,width, height);
	        
			PdfTextBoxField goles;
			try {
				goles = new PdfTextBoxField(page, "goles");
				goles.setBounds(tbxBounds);
				goles.setText("0");
				goles.setFont(font);
				doc.getForm().getFields().add(goles);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	    
	}

		// Guardar el documento PDF
 	
	    doc.saveToFile("src/main/torneos/torneo_"+nombreTorneo+"/Actas_de_partidos/Acta_"+partido.getEquipoLocal().getNombre()+"_"+partido.getEquipoVisitante().getNombre()+".pdf");
	    
	    doc.close();
	    }
	   
	    	
	    }
	    
	

