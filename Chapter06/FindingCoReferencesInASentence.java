package com.packt.javanlp.cookbook.chapter7;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.coref.data.CorefChain.CorefMention;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class FindingCoReferencesInASentence {

	public static void main(String[] args) {
		// Finding co-references in a sentence

		Annotation sampleText = new Annotation(
				"He walked to the store. Then, Jose bought a candy bar. " 
						+ "He eat all of it as he left the store.");
		Properties properties = new Properties();
		properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,coref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
		pipeline.annotate(sampleText);

		for (CorefChain corefChain : sampleText.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
			System.out.println("CorefChain: " + corefChain);
			System.out.println("\tMention: " + corefChain.getRepresentativeMention());
			System.out.println("\tMention Map: " + corefChain.getMentionMap());
			System.out.println();
		}

		System.out.println("Second List");
		for (CorefChain corefChain : sampleText.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
			System.out.println("CorefChain: " + corefChain);
//			System.out.println("\tClusterId: " + corefChain.getChainID());
			CorefMention corefMention = corefChain.getRepresentativeMention();
			System.out.println("\tMention: " + corefMention + " Span: [" + corefMention.mentionSpan + "]");

			List<CorefMention> mentionList = corefChain.getMentionsInTextualOrder();
			Iterator<CorefMention> mentionIterator = mentionList.iterator();
			while (mentionIterator.hasNext()) {
				CorefMention nextCorefMention = mentionIterator.next();
				System.out.println("\t\tMention: " + nextCorefMention + " Span: [" + corefMention.mentionSpan + "]");
				System.out.print("\t\t\tType: " + nextCorefMention.mentionType + " Gender: " + nextCorefMention.gender);
				System.out.println(" Start: " + nextCorefMention.startIndex + " End: " + nextCorefMention.endIndex);
			}
			System.out.println();
		}
	}

}
