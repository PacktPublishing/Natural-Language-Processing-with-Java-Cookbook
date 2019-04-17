package com.packt.javanlp.cookbook.chapter2a;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class TrainingANeuralNetworkTokenizerForSpecializedText {

	public static void main(String[] args) {
		// Training a neural network tokenizer for specialized text

		InputStreamFactory inputStreamFactory = new InputStreamFactory() {
			public InputStream createInputStream() throws FileNotFoundException {
				return new FileInputStream("C:/NLP Cookbook/Code/chapter2a/training-data.train");
			}
		};
		
		try (
			ObjectStream<String> stringObjectStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
			ObjectStream<TokenSample> tokenSampleStream = new TokenSampleStream(stringObjectStream);) {
			
			TokenizerModel tokenizerModel = TokenizerME.train(tokenSampleStream,
					new TokenizerFactory("en", null, true, null), TrainingParameters.defaultParams());
			BufferedOutputStream modelOutputStream = new BufferedOutputStream(
					new FileOutputStream(new File("C:/NLP Cookbook/Code/chapter2a/mymodel.bin")));
			tokenizerModel.serialize(modelOutputStream);

		} catch (IOException ex) {
			// Handle exception
			ex.printStackTrace();
		}

		String sampleText = "In addition, the rook was moved too far to be effective.";
		try (InputStream modelInputStream = new FileInputStream(
				new File("C:/Downloads/OpenNLP/Models", "mymodel.bin"));) {
			TokenizerModel tokenizerModel = new TokenizerModel(modelInputStream);
			Tokenizer tokenizer = new TokenizerME(tokenizerModel);
			String tokenList[] = tokenizer.tokenize(sampleText);
			for (String token : tokenList) {
				System.out.println(token);
			}
		} catch (FileNotFoundException e) {
			// Handle exception
		} catch (IOException e) {
			// Handle exception
		}

	}

}
