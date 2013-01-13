package com.naveen.datagen.output;

import com.naveen.datagen.core.Data;

/**
 * interface for generalising the output formats
 * @author naveen
 *
 */
public abstract class OutputFormat {

	public abstract void generate(Data dat);
}
