package com.packt.javanlp.cookbook.chapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

public class LanguageDetectionAndTranslationUsingAmazonAWS {

	public static void main(String[] args) {
		// Language detection and translation using Amazon AWS
		// Create credentials using a provider chain. For more information, see
		// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
		
		HashMap<String,String> languageEncodings = new HashMap();
		languageEncodings.put("ar","Arabic");
		languageEncodings.put("zh","Chinese (Simplified)");
		languageEncodings.put("zh-TW","Chinese (Traditional)");
		languageEncodings.put("cs","Czech");
		languageEncodings.put("da","Danish");
		languageEncodings.put("nl","Dutch");
		languageEncodings.put("en","English");
		languageEncodings.put("fi","Finnish");
		languageEncodings.put("de","German");
		languageEncodings.put("he","Hebrew");
		languageEncodings.put("id","Indonesian");
		languageEncodings.put("it","Italian");
		languageEncodings.put("ja","Japanese");
		languageEncodings.put("ko","Korean");
		languageEncodings.put("pl","Polish");
		languageEncodings.put("pt","Portuguese");
		languageEncodings.put("ru","Russian");
		languageEncodings.put("es","Spanish");
		languageEncodings.put("sv","Swedish");
		languageEncodings.put("tr","Turkish");
		

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

		AWSCredentialsProvider awsCredentialsProvider = 
				DefaultAWSCredentialsProviderChain.getInstance();

		AmazonTranslate amazonTranslate = AmazonTranslateClient.builder()
				.withCredentials(awsCredentialsProvider)
				.withRegion("us-east-2").build();

		TranslateTextRequest translateTextRequest = new TranslateTextRequest()
				.withText(textList.get(0))
				.withSourceLanguageCode("en")
				.withTargetLanguageCode("es");
		TranslateTextResult translateTextResult = 
				amazonTranslate.translateText(translateTextRequest);
		System.out.println(translateTextResult.getTranslatedText());
		System.out.println();

		// Automatic detection of language
		for (String text : textList) {
			translateTextRequest = new TranslateTextRequest()
					.withText(text)
					.withSourceLanguageCode("auto")
					.withTargetLanguageCode("es");
			translateTextResult = amazonTranslate.translateText(translateTextRequest);
			System.out.println(
					"Source Language: " + translateTextResult.getSourceLanguageCode() + 
					"\nTarget language: " + translateTextResult.getTargetLanguageCode() + 
					"\nTranslation: " + translateTextResult.getTranslatedText());
			System.out.println();
			

			System.out.println(
					"Source Language: " + languageEncodings.get(translateTextResult.getSourceLanguageCode()) + 
					"\nTarget language: " + languageEncodings.get(translateTextResult.getTargetLanguageCode()) + 
					"\nTranslation: " + translateTextResult.getTranslatedText());
			System.out.println();
		}
	}

}
