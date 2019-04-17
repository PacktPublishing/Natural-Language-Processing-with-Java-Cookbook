package com.packt.javanlp.cookbook.chapter5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Sequence;

public class FindingPOSUsingTagging {

	public static void main(String[] args) {
		// Finding POS using tagging
		String sampleSentence = "When the mouse saw the cat it ran away.";
		String words[] = sampleSentence.split(" ");

		try (InputStream modelInputStream = new FileInputStream(new File("en-pos-maxent.bin"));) {

			POSModel posModel = new POSModel(modelInputStream);
			POSTaggerME posTaggerME = new POSTaggerME(posModel);

			String tags[] = posTaggerME.tag(words);
			for (int i = 0; i < words.length; i++) {
				System.out.print(words[i] + "/" + tags[i] + " ");
			}
			System.out.println();

			Sequence topSequences[] = posTaggerME.topKSequences(words);
			
			for (Sequence sequence : topSequences) {
			    List<String> outcomes = sequence.getOutcomes();
			    System.out.print("[");
			    for(int i=0; i<outcomes.size(); i++) {
			        System.out.print(words[i] + "/" + outcomes.get(i) + " ");
			    }
			    System.out.println("]");
			}
			
			for (Sequence sequence : topSequences) {
			    List<String> outcomes = sequence.getOutcomes();
			    double probabilities[] = sequence.getProbs();
			    for (int i = 0; i < outcomes.size(); i++) {
			        System.out.printf("%s/%s/%5.3f ",words[i], outcomes.get(i), probabilities[i]);
			    }
			    System.out.println();
			}
		} catch (IOException e) {
			// Handle exceptions
		}

	}

}
