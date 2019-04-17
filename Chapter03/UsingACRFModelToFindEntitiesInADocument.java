package com.packt.javanlp.cookbook.chapter4;

import java.util.List;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

public class UsingACRFModelToFindEntitiesInADocument {

	public static void main(String[] args) {
		// Using a CRF model to find entities in a document
		CRFClassifier<CoreLabel> classifier = CRFClassifier
				.getClassifierNoExceptions("english.conll.4class.distsim.crf.ser.gz");

		List<List<CoreLabel>> entityList = classifier.classify("Sam and Mary left on Friday, November 12. ");

		for (CoreLabel coreLabel : entityList.get(0)) {
			String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
			System.out.println(coreLabel.word() + ":" + category);
		}

		
		for (CoreLabel coreLabel : entityList.get(0)) {
		    String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
		    if (!"O".equals(category)) {
		        System.out.println(coreLabel.word() + ":" + category);
		    }
		}
		
	}

}
