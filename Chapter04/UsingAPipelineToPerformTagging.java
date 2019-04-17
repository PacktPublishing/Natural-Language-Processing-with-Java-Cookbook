package com.packt;

import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class UsingAPipelineToPerformTagging {

	public static void main(String[] args) {
		// Using a pipeline to perform tagging
		String sampleSentence = "When the mouse saw the cat it ran away.";
		Properties properties = new Properties();
		properties.put("annotators", "tokenize, ssplit, pos");
		properties.put("pos.model", "wsj-0-18-bidirectional-distsim.tagger");
		properties.put("pos.maxlen", 10);
		StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
		Annotation annotation = new Annotation(sampleSentence);
		pipeline.annotate(annotation);
		
		List<CoreMap> sentenceList = annotation.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentenceList) {
		    for (CoreLabel coreLabel : sentence.get(TokensAnnotation.class)) {
		        String word = coreLabel.get(TextAnnotation.class);
		        String partOfSpeech = coreLabel.get(PartOfSpeechAnnotation.class);
		        System.out.print(word + "/" + partOfSpeech + " ");
		    }
		    System.out.println();
		}
	}

}
