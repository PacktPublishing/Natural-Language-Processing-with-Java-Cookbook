package com.packt.javanlp.cookbook.chapter6;

import java.io.File;
import java.io.IOException;
import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;

public class TrainingAModelToClassifyTextUsingLingPipe {

	public static void main(String[] args) {
		// Training a model to classify text using LingPipe
		String[] categories = { "soc.religion.christian", "talk.religion.misc", "alt.atheism", "misc.forsale" };
		
		int nGramSize = 6;
		DynamicLMClassifier<NGramProcessLM> dynamicLMClassifier = 
		    DynamicLMClassifier.createNGramProcess(categories, nGramSize);
		final String rootDirectory = "C:/NLP Cookbook/Chapter 6/lingpipe-4.1.2-website/demos";
		final File trainingDirectory = new File(rootDirectory +  
		   "/data/fourNewsGroups/4news-train");
		
		for (int i = 0; i < categories.length; ++i) {
		    final File trainingFilesDirectory = new File(trainingDirectory, categories[i]);

		    String[] trainingFiles = trainingFilesDirectory.list();
		    for (int j = 0; j < trainingFiles.length; ++j) {
		        try {
		            File trainingFile = new File(trainingFilesDirectory, trainingFiles[j]);
		            String trainingText = Files.readFromFile(trainingFile, "ISO-8859-1");

		            Classification classification = new Classification(categories[i]);
		            Classified<CharSequence> classified = 
		                new Classified<>((CharSequence) trainingText, classification);
		            dynamicLMClassifier.handle(classified);
		            
		            
		        } catch (IOException ex) {
		            // Handle exceptions
		        }
		    }		    
		 }
		try {
		    AbstractExternalizable.compileTo((Compilable) dynamicLMClassifier, 
		        new File("classificationModel.model"));
		} catch (IOException ex) {
		    // Handle exceptions
		}
		
	}

}
