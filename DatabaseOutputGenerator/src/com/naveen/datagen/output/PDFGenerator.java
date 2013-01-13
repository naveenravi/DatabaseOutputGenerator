package com.naveen.datagen.output;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.naveen.datagen.core.Data;
import com.naveen.datagen.core.Parts;
import com.naveen.datagen.core.Supplier;

public class PDFGenerator extends OutputFormat {

	@Override
	public void generate(Data dat) {

       Document doc = new Document(PageSize.A4);
       Path pdf_path = Paths.get("C:\\Users\\Naveen\\Desktop\\Testing\\Suppliers and Parts.pdf");
       try {
		OutputStream pdf_out = Files.newOutputStream(pdf_path);
		PdfWriter.getInstance(doc, pdf_out);
		doc.open();
		//Paragraph content = new Paragraph(dat.toString());
		Paragraph content = new Paragraph();
		content.add("SUPPLIERS");
		for(Supplier sup : dat.getSuppliers_list()){
			content.add(sup.toString());			
			content.add("--------------------------------------------");
		}
		content.add("################################################");
		content.add("################################################");
		content.add("PARTS");
		for(Parts part : dat.getParts_list()){
			content.add(part.toString());
			content.add("--------------------------------------------");
		}
		content.add("################################################");
		doc.add(content);
		doc.close();
		
	} catch (IOException e) {		
		e.printStackTrace();
	} catch (DocumentException e) {		
		e.printStackTrace();
	}		
	}

}
