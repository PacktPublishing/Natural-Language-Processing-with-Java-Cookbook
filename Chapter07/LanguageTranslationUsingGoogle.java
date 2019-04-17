package com.packt.javanlp.cookbook.chapter;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class LanguageTranslationUsingGoogle {

	public static void main(String[] args) {
		Translate translate = TranslateOptions.getDefaultInstance().getService();

		// Greek for highway - Language code - el
		String text = "Αυτοκινητόδρομος";
		Translation translation = translate.translate(text);
		System.out.printf("%s Translation: %s\n", text, translation.getTranslatedText());
		
		// With translation options
		translation = translate.translate(text, Translate.TranslateOption.sourceLanguage("el"),
				Translate.TranslateOption.targetLanguage("de"), Translate.TranslateOption.model("nmt"));
		System.out.printf("%s Translation: %s\n", text, translation.getTranslatedText());

		// With translation options and model of 'base'
		translation = translate.translate(text, Translate.TranslateOption.sourceLanguage("el"),
				Translate.TranslateOption.targetLanguage("de"), Translate.TranslateOption.model("base"));
		System.out.printf("%s Translation: %s\n", text, translation.getTranslatedText());

		// Using a list
		List<String> translationList = new ArrayList<>();
		// German https://de.wikipedia.org/wiki/Urknall
		translationList.add("Als Urknall wird in der Kosmologie der Beginn des Universums, also "
				+ "der Anfangspunkt der Entstehung von Materie, Raum und Zeit bezeichnet.");
		// Korean https://ko.wikipedia.org/wiki/%EB%8C%80%ED%8F%AD%EB%B0%9C
		translationList.add("대폭발(大爆發, 영어: Big Bang 빅뱅[*])[1] 은 천문학 또는 물리학에서, 우주의 "
				+ "처음을 설명하는 우주론 모형으로, 매우 높은 에너지를 가진 작은 물질과 공간이 약 137억 년 전의 거대한 "
				+ "폭발을 통해 우주가 되었다고 보는 이론이다.");
		// Portuguese https://pt.wikipedia.org/wiki/Big_Bang
		translationList.add("Big Bang ou Grande Expansão[1] é a teoria cosmológica dominante "
				+ "sobre o desenvolvimento inicial do universo.");
		List<Translation> translations = translate.translate(translationList, 
				Translate.TranslateOption.targetLanguage("en"));

		for(int i=0; i< translationList.size(); i++) {
			System.out.printf("\n%-32s \nSource Languge Code: %-32s\n\tTranslation: %s\n", 
					translationList.get(i).substring(0, Math.min(32, 
							translationList.get(i).length())), 
					translations.get(i).getSourceLanguage(),
					translations.get(i).getTranslatedText());
		}
		
		System.out.println();
		text = "<p><b>Language</b> is a system that consists of the development, "
				+ "acquisition, maintenance and use of complex systems of "
				+ "<a href=\"/wiki/Communication\" title=\"Communication\">"
				+ "communication</a>, particularly the <a href=\"/wiki/Human\" "
				+ "title=\"Human\">human</a> ability to do so; and <b>a language</b> "
				+ "is any specific example of such a system.\r\n</p";
		translation = translate.translate(text, Translate.TranslateOption.targetLanguage("es"));
		System.out.printf("%-32s \nTranslation: %s\n", text.substring(0, 32), translation.getTranslatedText());
		
	}

}
