package com.packt.javanlp.cookbook.chapter2;

import opennlp.tools.tokenize.SimpleTokenizer;

public class TokenizationUsingOpenNLP {

	public static void main(String[] args) {
		// Tokenization using OpenNLP
		String sampleText = "In addition, the rook was moved too far to be effective.";
		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
		String tokenList[] = simpleTokenizer.tokenize(sampleText);
		for (String token : tokenList) {
			System.out.println(token);
		}

	}

}
