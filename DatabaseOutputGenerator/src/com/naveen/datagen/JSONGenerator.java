package com.naveen.datagen;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

/**
 * This class is for generating JSON output from the data
 * @author naveen
 *
 */
public class JSONGenerator extends OutputFormat {

	private final String jsonFileLocation = "C:\\Supplier and Parts.json";

	@Override
	public void generate(Data dat) {

		Gson gson = new Gson();
		String json = gson.toJson(dat);
		File file = new File(jsonFileLocation);
		try {
			FileWriter fw2 = new FileWriter(file);
			fw2.write(json);
			fw2.close();
			System.out.println(json);
			System.out.println("JSON file has been created!!");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
