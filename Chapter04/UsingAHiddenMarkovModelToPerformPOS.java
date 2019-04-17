package com.packt.javanlp.cookbook.chapter5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.aliasi.hmm.HiddenMarkovModel;
import com.aliasi.hmm.HmmDecoder;
import com.aliasi.tag.ScoredTagging;
import com.aliasi.tag.Tagging;

public class UsingAHiddenMarkovModelToPerformPOS {

	public static void main(String[] args) {
		// Using a Hidden Markov Model to perform POS
		String sampleSentence = "When the mouse saw the cat it ran away.";
		try (FileInputStream fileInputStream = new FileInputStream("pos-en-general-brown.HiddenMarkovModel");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {

			HiddenMarkovModel hiddenMarkovModel = (HiddenMarkovModel) objectInputStream.readObject();
			HmmDecoder hmmDecoder = new HmmDecoder(hiddenMarkovModel);

			List<String> tokenList = Arrays.asList(sampleSentence.split(" "));
			Tagging<String> taggingString = hmmDecoder.tag(tokenList);
			for (int i = 0; i < taggingString.size(); ++i) {
				System.out.print(taggingString.token(i) + "/" + taggingString.tag(i) + " ");
			}
			System.out.println();
			
			Iterator<ScoredTagging<String>> scoredTaggingIterator = 
				    hmmDecoder.tagNBest(tokenList, 3);
			
			while (scoredTaggingIterator.hasNext()) {
			    ScoredTagging<String> scoredTagging = scoredTaggingIterator.next();
			    System.out.printf("Score: %7.3f Sequence: ", scoredTagging.score());
			    for (int i = 0; i < tokenList.size(); ++i) {
			        System.out.print(scoredTagging.token(i) + "/" + 
			            scoredTagging.tag(i) + " ");
			    }
			    System.out.println();
			}
		} catch (IOException ex) {
			// Handle exceptions
		} catch (ClassNotFoundException ex) {
			// Handle exceptions
		}

	}

}
