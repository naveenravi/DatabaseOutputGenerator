package com.naveen.datagen;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Contains the main class for launching the application with a design allowing
 * the addition of other types of output in future;
 * 
 * @author naveen
 * 
 */
public class Launcher {

	/*private enum output_format {
		XML(XMLGenerator.class.toString()), 
		JSON(JSONGenerator.class.toString());
		
		private  Class generatorClass;
		
        private OutputFormat out;	
        
		/*public OutputFormat getOut() {
			try {
				out = (OutputFormat) generatorClass.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return out;
		}
		public void setOut(OutputFormat out) {
			this.out = out;
		}
		private output_format(String output){
			try {
				generatorClass = ClassLoader.getSystemClassLoader().loadClass(output);
				this.setOut((OutputFormat) generatorClass.newInstance());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public OutputFormat getOut() {
			return out;
		}

		public void setOut(OutputFormat out) {
			this.out = out;
		}
		
	};*/
	
	private static enum output_format { JSON,XML};

	public static void main(String args[]) {

		final DatabaseOperations Dobj = new DatabaseOperations();
		boolean input_check = true;

		try {
			Dobj.connect();
			final Data dat = Dobj.execeuteQueries();			
			System.out.println("What type of output do you want?");
			while (input_check) {
				System.out.println("--------------------");
				System.out.println(output_format.JSON);
				System.out.println(output_format.XML);
				System.out.println("or Say 'BYE' to exit!!");
				Scanner input_scan = new Scanner(System.in);
				String choice = input_scan.nextLine().toUpperCase();
                
				if(choice.equalsIgnoreCase("bye")){
                	System.out.println("T-H-A-N-K-S for using and GOODBYE");
   				     input_check = false; 
   				     break;
                }else{
                	//OutputFormat OutputObj = output_format.XML.getOut();   
                	OutputFormat OutputObj = GeneratorFactory.getGenerator(choice);
    				OutputObj.generate(dat);	
                }
			}
			System.out.println("---------FIN--V2--------------");

		} catch (ClassNotFoundException CFE) {
			CFE.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
