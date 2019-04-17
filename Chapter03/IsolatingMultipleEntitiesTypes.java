package com.packt.javanlp.cookbook.chapter4;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class IsolatingMultipleEntitiesTypes {

	public static void main(String[] args) {
		// Isolating multiple entities types
		String sentences[] = { "Sam and Mary left on Friday, November 12. ",
				"They stopped in Boston at an ATM to get $300 for expenses. ",
				"While they were there Sam bumped into an old friend who was on his way to work at ATT. ",
				"They decided to leave together and departed for Maine" };

		try (InputStream tokenStream = new FileInputStream(new File("en-token.bin"))) {
			TokenizerModel tokenModel = new TokenizerModel(tokenStream);

			Tokenizer tokenizer = new TokenizerME(tokenModel);

			String modelNames[] = { "en-ner-person.bin", "en-ner-location.bin", "en-ner-organization.bin",
					"en-ner-money.bin", "en-ner-time.bin" };

			for (int i = 0; i < sentences.length; i++) {
				System.out.println("Sentence " + (i + 1));
				for (String name : modelNames) {
					TokenNameFinderModel entityModel = new TokenNameFinderModel(new FileInputStream(new File(name)));
					NameFinderME nameFinderME = new NameFinderME(entityModel);

					String tokens[] = tokenizer.tokenize(sentences[i]);

					Span spans[] = nameFinderME.find(tokens);
					for (Span span : spans) {
						System.out.print("\tEntity: ");
						for (int j = span.getStart(); j < span.getEnd(); j++) {
							System.out.print(tokens[j]);
						}
						System.out.println(" - Entity Type: " + span.getType());
					}

				}
			}

		} catch (Exception ex) {
			// Handle exceptions
		}

	}

}
