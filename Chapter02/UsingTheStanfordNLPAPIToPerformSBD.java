package com.packt.javanlp.cookbook.chapter3;

import java.io.StringReader;
import java.util.List;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordToSentenceProcessor;

public class UsingTheStanfordNLPAPIToPerformSBD {

	private static String text = "We will start with a simple sentence. However, is it possible for "
			+ "a sentence to end with a question mark? Obviously that is possible! "
			+ "Another complication is the use of a number such as 56.32 or "
			+ "ellipses such as ... Ellipses may be found ... with a sentence! "
			+ "Of course, we may also find the use of abbreviations such as " + "Mr. Smith or Dr. Jones.";

	public static void main(String[] args) {
		// Using the Stanford NLP API to perform SBD
		PTBTokenizer<CoreLabel> ptbTokenizer = new PTBTokenizer<CoreLabel>(new StringReader(text),
				new CoreLabelTokenFactory(), null);
		WordToSentenceProcessor<CoreLabel> wordToSentenceProcessor = new WordToSentenceProcessor<CoreLabel>();
		List<List<CoreLabel>> sentenceList = wordToSentenceProcessor.process(ptbTokenizer.tokenize());

		for (List<CoreLabel> sentence : sentenceList) {
			System.out.println(sentence);
		}
		
		for (List<CoreLabel> sentence : sentenceList) {
		    for (CoreLabel coreLabel : sentence) {
		        System.out.print(coreLabel + " ");
		    }
		    System.out.println();
		}
		
		for (List<CoreLabel> sentence : sentenceList) {
		    for (CoreLabel coreLabel : sentence) {
		        System.out.print(coreLabel.word() + " - " +  
		            coreLabel.beginPosition() + ":" +
		            coreLabel.endPosition() + " ");
		    }
		}
		System.out.println();
	}

}
