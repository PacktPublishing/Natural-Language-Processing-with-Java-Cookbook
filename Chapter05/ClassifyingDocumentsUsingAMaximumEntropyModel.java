package com.packt.javanlp.cookbook.chapter6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

public class ClassifyingDocumentsUsingAMaximumEntropyModel {

	public static void main(String[] args) {
		// Classifying documents using a maximum entropy model
		try (InputStream modelInputStream = new FileInputStream(new File("en-frograt.bin"));) {

			String testString = "Amphibians are animals that dwell in wet environments";
			
			DoccatModel documentCategorizationModel = new DoccatModel(modelInputStream);
			DocumentCategorizerME documentCategorizer = new 
			DocumentCategorizerME(documentCategorizationModel);
			
			double[] probabilities = documentCategorizer.categorize(testString);
			String bestCategory = documentCategorizer.getBestCategory(probabilities);
			System.out.println("The best fit is: " + bestCategory);
			
			for (int i = 0; i < documentCategorizer.getNumberOfCategories(); i++) {
			    System.out.printf("Category: %-4s - %4.2f\n", 
			        documentCategorizer.getCategory(i), probabilities[i]);
			}
			
			System.out.println(documentCategorizer.getAllResults(probabilities));
		} catch (FileNotFoundException e) {
		    // Handle exceptions
		} catch (IOException e) {
		    // Handle exceptions
		}
	}

}
