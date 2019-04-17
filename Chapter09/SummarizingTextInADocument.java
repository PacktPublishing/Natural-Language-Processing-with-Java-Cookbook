package com.packt.javanlp.cookbook.chapter10Summarizer;

import java.util.ArrayList;

public class SummarizingTextInADocument {
	public static void main(String[] args) {
		String filePath = "files/file-medium_en.txt"; // the file you want to summarize
		String languageCode = "EN"; // "NO"=norwegian or "EN"=english (decides which stop-word dict to use)
		int lengthOfSummary = 5; // Summary length

		// read file and create Sentence objects
		SentenceBuilder sentenceBuilder = new SentenceBuilder(languageCode, filePath);

		// split to Word objects, remove stop words and count frequency of each unique
		// word
		WordBuilder wordBuilder = new WordBuilder();
		wordBuilder.getWords(languageCode, filePath);
		wordBuilder.removeStopWords(languageCode);
		wordBuilder.doCount(WordBuilder.getCleanWordObjects());

		// find top N words from N different sentences
		wordBuilder.findTopNWords(lengthOfSummary);

		ArrayList<Sentence> sentenceList = sentenceBuilder.getSentenceObjects(); // Why not static??

		// print final summary
		System.out.println("\n------------------Summary-------------------");
		ArrayList<Word> wordList = WordBuilder.getMaxWordList();
		StringBuilder textSummary = new StringBuilder();

		for (int i = 0; i < wordList.size(); i++) {
			int index = wordList.get(i).getBelongingSentenceNo();
			textSummary.append(sentenceList.get(index).getText() + " ");
		}
		System.out.println(textSummary);

		// sort top N words with regards to belonging sentence no
		Summarizer summarizer = new Summarizer();
		summarizer.createSummary();

		// debugging
		DebugClass.printInfo();
		DebugClass.printFreqMap();
		DebugClass.printStats();
		summarizer.sortTopNWordList();
		DebugClass.printTopNWords();

	}
}
