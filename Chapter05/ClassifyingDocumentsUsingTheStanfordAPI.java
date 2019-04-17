package com.packt.javanlp.cookbook.chapter6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.objectbank.ObjectBank;

public class ClassifyingDocumentsUsingTheStanfordAPI {

	public static void main(String[] args) {
		// Classifying documents using the Stanford API
		ColumnDataClassifier columnDataClassifier = new ColumnDataClassifier("FlowersAndSpices.prop");
		Classifier<String, String> classifier = 
		 columnDataClassifier.makeClassifier(
		 columnDataClassifier.readTrainingExamples("FlowersAndSpices.train"));
		
		ObjectBank<String> objectBank = ObjectBank.getLineIterator("FlowersAndSpices.test", "utf-8");
		for (String line : objectBank) {
		    Datum<String, String> datum = columnDataClassifier.makeDatumFromLine(line);
		    System.out.println("Datum: [" + line + "]\tPredicted Category: " +  
		        classifier.classOf(datum));
		}
		
		String testItem[] = {"2","Dill Pollen"};
		Datum<String, String> datum = columnDataClassifier.makeDatumFromStrings(testItem);
		System.out.println("[" + testItem[0] + "\t" + testItem[1] + "] Predicted Category: " + 
		classifier.classOf(datum));
		
	}

}
