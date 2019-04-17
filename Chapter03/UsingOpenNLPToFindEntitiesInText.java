package com.packt.javanlp.cookbook.chapter4;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class UsingOpenNLPToFindEntitiesInText {

	public static void main(String[] args) {
		// Using OpenNLP to find entities in text
		try (InputStream tokenStream = new FileInputStream(new File("en-token.bin"));
				InputStream entityModelInputStream = new FileInputStream(new File("en-ner-date.bin"));) {

			TokenizerModel tokenizerModel = new TokenizerModel(tokenStream);
			Tokenizer tokenizer = new TokenizerME(tokenizerModel);
			TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(entityModelInputStream);

			NameFinderME nameFinderME = new NameFinderME(tokenNameFinderModel);

			String text = "The city was founded in the 1850s and its first mayor was born March 3, 1832.";

			String tokens[] = tokenizer.tokenize(text);
			Span dateSpans[] = nameFinderME.find(tokens);

			for (int i = 0; i < dateSpans.length; i++) {
				System.out.print("Entity: [" + tokens[dateSpans[i].getStart()]);
				System.out.print(
						"] was a " + dateSpans[i].getType() + " entity found starting at " + dateSpans[i].getStart());
				System.out.println(" and ending at " + dateSpans[i].getEnd());

				String date = "";
				for (int j = dateSpans[i].getStart(); j < dateSpans[i].getEnd(); j++) {
					date += tokens[j] + " ";
				}
				//System.out.println("Date: " + date);
				double[] spanProbs = nameFinderME.probs(dateSpans);
				System.out.println("Date: " + date + " Probability: " + spanProbs[i]);

			}

		} catch (Exception ex) {
			// Handle exception
		}

	}

}
