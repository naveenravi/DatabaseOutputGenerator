package com.naveen.datagen;
public class GeneratorFactory {

	/*
	 * public Output generate(Output out){ if(out instanceof XMLGenerator) {
	 * return new XMLGenerator(); }else if (out instanceof GenerateJSON){ return
	 * new GenerateJSON(); } return null; }
	 */

	public static OutputFormat getGenerator(final String type) {
		OutputFormat generator = null;
		final Class generatorClass;
		try {
			generatorClass = ClassLoader.getSystemClassLoader().loadClass(
					type + "Generator");
			generator = (OutputFormat) (generatorClass.newInstance());
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class not Found for Choice :"+type);
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return generator;
	}
}
