package com.naveen.datagen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.APPEND;

public class PDFGenerator extends OutputFormat {

	@Override
	public void generate(Data dat) {

       Document doc = new Document(PageSize.A4);
       Path pdf_path = Paths.get("C:");
       try {
		OutputStream pdf_out = Files.newOutputStream(pdf_path, CREATE, APPEND);
		PdfWriter.getInstance(doc, pdf_out);
		doc.open();
		Paragraph content = new Paragraph(dat.toString());
		doc.add(content);
		doc.close();
		
	} catch (IOException e) {		
		e.printStackTrace();
	} catch (DocumentException e) {		
		e.printStackTrace();
	}		
	}

}
