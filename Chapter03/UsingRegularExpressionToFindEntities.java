package com.packt.javanlp.cookbook.chapter4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsingRegularExpressionToFindEntities {

	public static void main(String[] args) {
		// Using regular expression to find entities
		String sampleText = "I can normally be reached at nlp@nlpworks.com. "
				+ "If not you can email me at  mrnlp@nlpworks.org";
		String emailRegularExpression = "[a-zA-Z0-9'._%+-]+@" + "(?:[a-zA-Z0-9-]+\\.)" + "+[a-zA-Z]{2,4}";

		Pattern pattern = Pattern.compile(emailRegularExpression);
		Matcher matcher = pattern.matcher(sampleText);

		while (matcher.find()) {
			System.out.println(matcher.group());
		}

		matcher = pattern.matcher(sampleText);
		while (matcher.find()) {
			System.out.println(matcher.group() + " [" + matcher.start() + ":" + matcher.end() + "]");
		}

		String phoneNumberRegularExpression = "\\d{3}-\\d{3}-\\d{4}";
		String zipCodeRegularExpression = "[0-9]{5}(\\-?[0-9]{4})?";

		pattern = Pattern
				.compile(phoneNumberRegularExpression + "|" + zipCodeRegularExpression + "|" + emailRegularExpression);
		sampleText = "Her phone number is 888-555-1111. You may also need her ZIP code: 55555-4444";

		matcher = pattern.matcher(sampleText);
		while (matcher.find()) {
			System.out.println(matcher.group() + " [" + matcher.start() + ":" + matcher.end() + "]");
		}
	}

}
