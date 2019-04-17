package com.packt.javanlp.cookbook.chapter;

import java.util.ArrayList;
import java.util.List;

//import com.google.api.core.ApiClock;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

public class DetectingTheNaturalLanguageInUseUsingGooglesAPI {

	public static void main(String[] args) {
		// Detecting the natural language in use using Google’s API

//		System.out.println(TranslateOptions.getDefaultInstance().getApplicationName());
//		ApiClock apiCLock = TranslateOptions.getDefaultInstance().getClock();
//		System.out.println(apiCLock);
//		System.out.println(TranslateOptions.getDefaultInstance().getLibraryVersion());
//		System.out.println(TranslateOptions.getDefaultInstance().getTargetLanguage());
//		System.out.println(TranslateOptions.getDefaultInstance().getLibraryName());

		Translate translate = TranslateOptions.getDefaultInstance().getService();

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

		textList.add("Language is");
		textList.add("Language ist");
		
		// From: https://ask.metafilter.com/126800/Same-sentence-different-language-still-makes-sense
		textList.add("The French for 'what's up' is 'Quoi de neuf?'");
		textList.add("menin aeide thea Peleiadeo Achileos\" becomes \"Men in "
				+ "Aida, they appeal, eh? A day, O Achilles!'");

		List<Detection> detectionList = translate.detect(textList);

		List<Language> supportedLanguages = translate.listSupportedLanguages();
		int count = 0;
		for (Detection detection : detectionList) {
			for (Language language : supportedLanguages) {
				if (language.getCode().equals(detection.getLanguage())) {
					System.out.printf("Text: \"%-16s\"   Code: %2s   Language: %-12s Confidence: %5.3f\n", 
							textList.get(count).substring(0, Math.min(16, 
									textList.get(count).length())), 
							language.getCode(), language.getName(), detection.getConfidence());
					count++;
				}
			}
		}
	}

}
