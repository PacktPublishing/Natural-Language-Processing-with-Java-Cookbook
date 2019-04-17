package com.packt.javanlp.cookbook.chapter4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
import opennlp.tools.util.TrainingParameters;

public class TrainingASpecializedNERModel {

	public static void main(String[] args) {
		// Training a specialized NER model
		InputStreamFactory inputStreamFactory = new InputStreamFactory() {
			public InputStream createInputStream() throws IOException {
				return new FileInputStream("training-data.train");
			}
		};

		try (OutputStream modelOutputStream = new BufferedOutputStream(
				new FileOutputStream(new File("location-model.bin")));
				ObjectStream<String> stringStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
				ObjectStream<NameSample> nameSampleStream = new NameSampleDataStream(stringStream);) {

			TokenNameFinderModel locationModel = NameFinderME.train("en", "LOCATION", nameSampleStream,
					TrainingParameters.defaultParams(), new TokenNameFinderFactory());

			locationModel.serialize(modelOutputStream);

		} catch (IOException ex) {
			// Handle exceptions
		}

		try (InputStream tokenInputStream = new FileInputStream(new File("en-token.bin"));
				InputStream entityModelInputStream = new FileInputStream(new File("location-model.bin"));) {
			TokenizerModel tokenizerModel = new TokenizerModel(tokenInputStream);
			Tokenizer tokenizer = new TokenizerME(tokenizerModel);
			TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(entityModelInputStream);
			NameFinderME nameFinderME = new NameFinderME(tokenNameFinderModel);
			String text = "The city of Cairo is quite large. However, Quebec is not quite as big.";

			String tokens[] = tokenizer.tokenize(text);
			Span locationSpans[] = nameFinderME.find(tokens);
			for (int i = 0; i < locationSpans.length; i++) {
				System.out.println("Entity: [" + tokens[locationSpans[i].getStart()] + "]");
			}
		} catch (Exception ex) {
			// Handle exceptions
		}

	}

}
