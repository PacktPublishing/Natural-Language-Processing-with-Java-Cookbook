package com.packt.javanlp.cookbook.chapter2;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.tokenizer.EnglishStopTokenizerFactory;

public class RemovingStopwordsUsingLingPipe {

	public static void main(String[] args) {
		// Removing stopwords using LingPipe
		String sentence = "The blue goose and a quiet lamb stopped to smell the roses.";
		TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
		tokenizerFactory = new EnglishStopTokenizerFactory(tokenizerFactory);
		Tokenizer tokenizer = tokenizerFactory.tokenizer(sentence.toCharArray(), 0, sentence.length());
		for (String token : tokenizer) {
			System.out.println(token);
		}
	}

}
