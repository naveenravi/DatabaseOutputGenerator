package com.naveen.datagen.output;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.naveen.datagen.core.Data;

/**
 * This class is for generating JSON output from the data
 * @author naveen
 *
 */
public class JSONGenerator extends OutputFormat {

	private final String jsonFileLocation = "C:\\Users\\Naveen\\git\\DatabaseOutputGenerator\\" +
			"DatabaseOutputGenerator\\Suppliers and Parts.json";

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
