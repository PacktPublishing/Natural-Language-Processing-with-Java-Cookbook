package com.packt.javanlp.cookbook.chapter3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class UsingOpenNLPToPerformSBD {

	private static String text = "We will start with a simple sentence. However, is it possible for "
			+ "a sentence to end with a question mark? Obviously that is possible! "
			+ "Another complication is the use of a number such as 56.32 or "
			+ "ellipses such as ... Ellipses may be found ... with a sentence! "
			+ "Of course, we may also find the use of abbreviations such as " + "Mr. Smith or Dr. Jones.";

	public static void main(String[] args) {
		// Using OpenNLP to perform SBD
		try (InputStream inputStream = new FileInputStream(new File("en-sent.bin"))) {

			SentenceModel sentenceModel = new SentenceModel(inputStream);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);
			String sentences[] = sentenceDetector.sentDetect(text);
			for (String sentence : sentences) {
				System.out.println("[" + sentence + "]");
			}
			
			Span spans[] = sentenceDetector.sentPosDetect(text);
			for (Span span : spans) {
			    System.out.println(span);
			}
			
			double probablities[] = sentenceDetector.getSentenceProbabilities();
			for(int i=0; i<sentences.length; i++) {
			    System.out.printf("Sentence %d: %6.4f\n",i, probablities[i]);
			}

		} catch (FileNotFoundException ex) {
			// Handle exceptions
		} catch (IOException ex) {
			// Handle exceptions
		}
	}

}
