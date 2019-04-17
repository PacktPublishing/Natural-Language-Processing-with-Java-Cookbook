package com.packt.javanlp.cookbook.chapter2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class TokenizationUsingMaximumEntropy {

	public static void main(String[] args) {
		// Tokenization using maximum entropy
		String sampleText = "In addition, the rook was moved too far to be effective.";
		try (InputStream modelInputStream = new FileInputStream(
				new File("C:/Downloads/OpenNLP/Models", "en-token.bin"))) {
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
