package com.naveen.datagen;
public class GeneratorFactory {

	
    /*
     * This returns the required OutputFormat object
     * by making use of the ClassLoader
     * @param type 
     * @return OutputFormat
     */
	@SuppressWarnings("unchecked")
	public static OutputFormat getGenerator(final String output_type) {
		OutputFormat generator = null;
		final Class<OutputFormat> generatorClass;
		try {
			generatorClass = (Class<OutputFormat>) ClassLoader.getSystemClassLoader().loadClass(
					output_type + "Generator");
			generator = (generatorClass.newInstance());
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class not Found for Choice :"+output_type);
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return generator;
	}
}
