package com.packt.javanlp.cookbook.chapter;

import java.util.List;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

public class DiscoveringSupportedLanguagesUsingGoogleAPI {

	public static void main(String[] args) {
		Translate translate = TranslateOptions.getDefaultInstance().getService();

		List<Language> supportedLanguages = translate.listSupportedLanguages();

		for (Language language : supportedLanguages) {
			System.out.printf("%-20s Code: %3s\n", language.getName(), language.getCode());
		}
	}

}
