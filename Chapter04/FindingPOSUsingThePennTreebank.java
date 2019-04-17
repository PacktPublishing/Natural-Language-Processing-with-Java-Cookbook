package com.packt;

import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class FindingPOSUsingThePennTreebank {

	public static void main(String[] args) {
		String sampleSentence = "When the mouse saw the cat it ran away.";
        MaxentTagger maxentTagger = new MaxentTagger( "wsj-0-18-bidirectional-distsim.tagger");
        List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new StringReader(sampleSentence));
        for (List<HasWord> sentence : sentences) {
            List<TaggedWord> taggedSentence = maxentTagger.tagSentence(sentence);
            // Display of words and tags
            for (TaggedWord taggedWord : taggedSentence) {
                System.out.print(taggedWord.word() + "/" + taggedWord.tag() + " ");
            }
            System.out.println();
            
            // List of specifc tags
            System.out.println("Noun List");
            for (TaggedWord taggedWord : taggedSentence) {
                if (taggedWord.tag().startsWith("NN")) {
                    System.out.println(taggedWord.word() + " ");
                }
            }
            System.out.println();
        }

	}

}
