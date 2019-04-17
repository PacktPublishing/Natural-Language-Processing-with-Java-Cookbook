package com.packt.javanlp.cookbook.chapter7;

// Displaying parse trees graphically imports
import edu.stanford.nlp.parser.ui.Parser;

public class DisplayingParseTreesGraphically {

	public static void main(String[] args) {
		// Displaying parse trees graphically
		String parameters[] = new String[2];
		parameters[0] = "englishPCFG.ser.gz";
		parameters[1] = "StanfordDataFile.txt";
		Parser.main(parameters);
	}

}
