package com.naveen.datagen.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.naveen.datagen.core.Data;

/**
 * This class is for generating the XML output from the data
 * 
 * @author naveen
 * 
 */
public class XMLGenerator extends OutputFormat {

	private final String xmlFileLocation = "C:\\Users\\Naveen\\git\\DatabaseOutputGenerator\\" +
			"DatabaseOutputGenerator\\Suppliers and Parts.xml";

	@Override
	public void generate(Data dat) {
		try {
			File file = new File(xmlFileLocation);
			FileWriter fw = new FileWriter(file);
			JAXBContext context = JAXBContext.newInstance(Data.class);
			Marshaller jaxbMarsh = context.createMarshaller();
			jaxbMarsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarsh.marshal(dat, fw);
			jaxbMarsh.marshal(dat, System.out);
			fw.close();
			System.out.println("XML file has been created!!");

		} catch (JAXBException JAXBe) {
			JAXBe.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
