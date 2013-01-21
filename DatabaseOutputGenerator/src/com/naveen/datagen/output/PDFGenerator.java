package com.naveen.datagen.output;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.naveen.datagen.core.Data;
import com.naveen.datagen.core.Parts;
import com.naveen.datagen.core.Supplier;

public class PDFGenerator extends OutputFormat {

	@Override
	public void generate(Data dat) {

		Document doc = new Document(PageSize.A4);
		Path pdf_path = Paths
				.get("C:\\Users\\Naveen\\git\\DatabaseOutputGenerator\\DatabaseOutputGenerator\\"
						+ "Suppliers and Parts.pdf");
		try {
			OutputStream pdf_out = Files.newOutputStream(pdf_path);
			PdfWriter.getInstance(doc, pdf_out);
			doc.open();
			// Paragraph content = new Paragraph(dat.toString());
			Paragraph supplier_title = new Paragraph("Suppliers",
					FontFactory.getFont(FontFactory.HELVETICA, 16, Font.ITALIC));
			doc.add(supplier_title);
			Paragraph supplier_details = new Paragraph();
			for (Supplier sup : dat.getSuppliers_list()) {
				List supplier_list = new List(true, false, 75);
				supplier_list.setPostSymbol(" Name -");
				supplier_list.add(new ListItem(sup.getName()));
				supplier_list.setPostSymbol(" Code -");
				supplier_list.add(new ListItem(sup.getCode()));
				supplier_list.setPostSymbol(" E mail -");
				supplier_list.add(new ListItem(sup.getEmail()));
				supplier_list.setPostSymbol(" Telephone-");
				supplier_list.add(new ListItem(sup.getTelephone()));
				supplier_details.add(supplier_list);

				supplier_details.add(new Paragraph(" "));

				if (!sup.getParts_supplied().isEmpty()) {
					PdfPTable parts_list = new PdfPTable(3);
					PdfPCell name_cell = new PdfPCell(new Phrase("Part Name"));
					PdfPCell id_cell = new PdfPCell(new Phrase("Part ID"));
					PdfPCell cost_cell = new PdfPCell(new Phrase("Part Cost"));
					parts_list.addCell(name_cell);
					parts_list.addCell(id_cell);
					parts_list.addCell(cost_cell);

					for (Parts part : sup.getParts_supplied()) {
						parts_list.addCell(part.getName());
						parts_list.addCell(part.getId());
						parts_list.addCell(Integer.toString(part.getCost()));
					}
					supplier_details.add(parts_list);
				} else
					supplier_details.add(new Paragraph(
							"No Parts are being supplied by this Supplier"));

				supplier_details.add(new Paragraph(" "));
			}
			supplier_details
					.add("################# END OF REPORT #######################");

			doc.add(supplier_details);
			doc.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
