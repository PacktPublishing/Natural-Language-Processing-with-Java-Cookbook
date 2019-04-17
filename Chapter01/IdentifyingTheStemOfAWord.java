package com.packt.javanlp.cookbook.chapter2;

import opennlp.tools.stemmer.PorterStemmer;

public class IdentifyingTheStemOfAWord {

	public static void main(String[] args) {
		// Identifying the stem of a word
		String wordList[] = { "draft", "drafted", "drafting", "drafts", "drafty", "draftsman" };
		PorterStemmer porterStemmer = new PorterStemmer();
		for (String word : wordList) {
			String stem = porterStemmer.stem(word);
			System.out.println("The stem of " + word + " is " + stem);
		}
	}

}
