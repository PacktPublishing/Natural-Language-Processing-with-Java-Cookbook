package com.packt.javanlp.cookbook.chapter6;

import java.io.File;
import java.io.IOException;
import com.aliasi.classify.JointClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

public class UsingLingPipeToClassifyText {

	public static void main(String[] args) {
		// Using LingPipe to classify text
		String[] categories = { "soc.religion.christian", "talk.religion.misc", "alt.atheism", "misc.forsale" };

		String sampleText = "In the ancient and medieval world, the etymological Latin root religio was "
				+ "understood as an individual virtue of worship in mundane contexts; never as "
				+ "doctrine, practice, or actual source of knowledge. In general, religio referred "
				+ "to broad social obligations towards anything including family, neighbors, rulers, "
				+ "and even towards God.";

		try {
			LMClassifier lmClassifier = (LMClassifier) AbstractExternalizable
					.readObject(new File("classificationModel.model"));

			JointClassification jointClassification = lmClassifier.classify(sampleText);
			String bestCategory = jointClassification.bestCategory();
			System.out.println("Best Category: " + bestCategory);

			for (int i = 0; i < categories.length; i++) {
			    double score = jointClassification.score(i);
			    double probability = jointClassification.jointLog2Probability(i);
			    String category = jointClassification.category(i);
			    System.out.printf("Category: %-22s Score: %4.2f jointLog2Probability: %4.2f%n", 
			        category, score, probability);
			}
		} catch (IOException | ClassNotFoundException ex) {
			// Handle exceptions
		}

	}

}
