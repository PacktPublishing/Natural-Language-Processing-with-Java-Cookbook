package com.packt.javanlp.cookbook.chapter2a;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.lemmatizer.LemmatizerModel;

public class DetermineTheLexicalMeaningOfAWordUsingOpenNLP {

	public static void main(String[] args) {
		// Determine the lexical meaning of a word using OpenNLP
		LemmatizerModel lemmatizerModel = null;
		try (InputStream modelInputStream = new FileInputStream("C:\\Downloads\\OpenNLP\\en-lemmatizer.bin")) {
			lemmatizerModel = new LemmatizerModel(modelInputStream);
			LemmatizerME lemmatizer = new LemmatizerME(lemmatizerModel);

			String[] tokens = new String[] { "The", "girls", "were", "leaving", "the", "clubhouse", "for", "another",
					"adventurous", "afternoon", "." };
			String[] posTags = new String[] { "DT", "NNS", "VBD", "VBG", "DT", "NN", "IN", "DT", "JJ", "NN", "." };
			String[] lemmas = lemmatizer.lemmatize(tokens, posTags);
			for (int i = 0; i < tokens.length; i++) {
				System.out.println(tokens[i] + " - " + lemmas[i]);
			}

		} catch (FileNotFoundException e) {
			// Handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// Handle exception
			e.printStackTrace();
		}
	}

}
