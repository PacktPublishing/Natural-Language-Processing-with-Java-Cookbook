package com.packt;

import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class FindingPOSFromTextese {

	public static void main(String[] args) {
		// Finding POS from textese
		String sampleSentence = "JK IMA eat by EOD!";
        MaxentTagger maxentTagger = new MaxentTagger("C:\\NLP Cookbook\\Code\\chapter5\\gate-EN-twitter.model");
        System.out.println(maxentTagger.tagString("JK IMA eat by EOD!"));
        List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new StringReader(sampleSentence));
        for (List<HasWord> sentence : sentences) {
            List<TaggedWord> taggedSentence = maxentTagger.tagSentence(sentence);
            // Display of words and tags
            for (TaggedWord taggedWord : taggedSentence) {
//                System.out.print(taggedWord.word() + "/" + taggedWord.tag() + " ");
                
                System.out.println(taggedWord.word() + "/" + taggedWord.tag() + 
                		" (" + taggedWord.beginPosition() + ", " + taggedWord.endPosition() + ")" );
                
            }
            System.out.println();      
        }
	}

}
