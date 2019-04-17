package com.packt.javanlp.cookbook.chapter6;

import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class PerformingSentimentAnalysisOnReviews {

	public static void main(String[] args) {
		// Performing sentiment analysis on reviews
		String goodReview = "With its impressive action sequences, taut economic "
				+ "direction, and relentlessly fast pace, it's clear why The "
				+ "Terminator continues to be an influence on sci-fi and action flicks.";
		String badReview = "\r\n" + "There are a few jumps and bumps, but there's no real sense of "
				+ "dread, unease or questioning. We simply watch the events "
				+ "unfold with a full understanding of what's going on.";

		Properties properties = new Properties();
		properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

		int predicatedTotal = 0;

		String[] lines = goodReview.split("\\.");
//		String[] lines = badReview.split("\\.");
		for (int i = 0; i < lines.length; i++) {
			Annotation annotation = pipeline.process(lines[i]);
			CoreMap coreMapSentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
			Tree tree = coreMapSentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
			String sentiment = coreMapSentence.get(SentimentCoreAnnotations.SentimentClass.class);
			int predictedScore = RNNCoreAnnotations.getPredictedClass(tree);
			predicatedTotal = predicatedTotal + (predictedScore);
			System.out.println(lines[i].substring(0, 32) + " ... Score: " + predictedScore + " " + sentiment);
		}
		System.out.println();
		System.out.printf("Average: %3.2f", predicatedTotal / (float) lines.length);
	}

}
