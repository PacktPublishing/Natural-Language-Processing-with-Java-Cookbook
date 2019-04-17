package com.packt.javanlp.cookbook.chapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.aliasi.classify.Classification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

public class DetectingTheNaturalLanguageInUseUsingLingPipe {

	public static void main(String[] args) {
		// Detecting the natural language in use using LingPipe

		ArrayList<String> textList = new ArrayList<>();
		textList.add("Language is a system that consists of the development, "
				+ "acquisition, maintenance and use of complex systems of "
				+ "communication, particularly the human ability to do so; "
				+ "and a language is any specific example of such a system.");
		textList.add("Language ist ein Lied des US-amerikanischen DJs und "
				+ "Musikproduzenten Porter Robinson, das von Heather Bright "
				+ "gesungen und am 10. April 2012 auf Beatport veröffentlicht"
				+ " wurde. Language kann dem Genre Electro House zugeordnet "
				+ "werden und hat 128 bpm. Die Vollversion war bereits ab "
				+ "dem 26. März 2012 bei YouTube anhörbar. Der Track ist "
				+ "unter anderem auch auf dem Soundtrack von Forza Horizon enthalten.");

		HashMap<String,String> languageEncodings = new HashMap();
		languageEncodings.put("cat","Catalan");
		languageEncodings.put("de","German");
		languageEncodings.put("dk","Danish");
		languageEncodings.put("ee","Estonian");
		languageEncodings.put("en","English");
		languageEncodings.put("fi","Finnish");
		languageEncodings.put("fr","French");
		languageEncodings.put("it","Italian");
		languageEncodings.put("jp","Japanese");
		languageEncodings.put("kr","Korean");
		languageEncodings.put("nl","Dutch");
		languageEncodings.put("no","Norwegian");
		languageEncodings.put("se","Swedish");
		languageEncodings.put("sorb","Sorbian");
		languageEncodings.put("tr","Turkish");
		
		try {
			LMClassifier lmClassifier = (LMClassifier) AbstractExternalizable
					.readObject(new File("langid-leipzig.classifier"));
			for (String text : textList) {
				Classification classification = lmClassifier.classify(text);
				System.out.println("Best Language: " + classification.bestCategory());
				System.out.println("Best Language: " + 
						languageEncodings.get(classification.bestCategory()));
			}
			for (String category : lmClassifier.categories()) {
				System.out.println(languageEncodings.get(category));
			}
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

}
