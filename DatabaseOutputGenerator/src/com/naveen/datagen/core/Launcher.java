package com.naveen.datagen.core;

import java.sql.SQLException;
import java.util.Scanner;

import com.naveen.datagen.output.OutputFormat;

/**
 * Contains the main class for launching the application with a design allowing
 * the addition of other types of output in future;
 * 
 * @author naveen
 * 
 */
public class Launcher {

	private static enum output_format {
		JSON, XML, PDF
	};

	public static void main(String args[]) {

		final DatabaseOperations Dobj = new DatabaseOperations();
		boolean input_check = true;

		try {
			Dobj.connect();
			final Data dat = Dobj.execeuteQueries();
			Scanner input_scan = new Scanner(System.in);;
			System.out.println("What type of output do you want?");
			while (input_check) {
				System.out.println("--------------------");
				System.out.println(output_format.JSON);
				System.out.println(output_format.XML);
				System.out.println(output_format.PDF);
				System.out.println("or Say 'BYE' to exit!!");				 
				String choice = input_scan.nextLine().toUpperCase();
				if (choice.equalsIgnoreCase("bye")) {
					System.out.println("T-H-A-N-K-S for using and GOODBYE");
					input_check = false;
					
					break;
				} else {				
					OutputFormat OutputObj = GeneratorFactory
							.getGenerator(choice);
					if (OutputObj != null) {
						OutputObj.generate(dat);
					}
				}
			}
			input_scan.close();
			System.out.println("---------FIN--V2--------------");
		} catch (ClassNotFoundException  | SQLException exception) {
			exception.printStackTrace();			
		}					
	}
}
