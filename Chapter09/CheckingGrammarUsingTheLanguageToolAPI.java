package com.packt.javanlp.cookbook.chapter10;

import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

public class CheckingGrammarUsingTheLanguageToolAPI {

	public static void main(String[] args) {
		JLanguageTool jLanguageTool = new JLanguageTool(new BritishEnglish());

		try {
			String test = "This is is not good. " 
					+ "The boy hit ball. " 
					+ "He go to the school and I taught him. "
					+ "Sue and Albert are married and she has been working for a long time. " 
					+ "Hit ball. "
					+ "He don't want no excuse.";
			List<RuleMatch> ruleMatchList = jLanguageTool.check(test);
			for (RuleMatch ruleMatch : ruleMatchList) {
				System.out.println("Grammar problem in the sentence: [" 
						+ ruleMatch.getSentence().getText() + "]");
				System.out.println(ruleMatch.getMessage());
				System.out.println("At Position: [" + ruleMatch.getFromPos() + "-"
						+ ruleMatch.getToPos() + "]");
				System.out.println("Problem description: " + ruleMatch.getRule().getDescription());

				int count = 1;
				System.out.println("--- Possible Corrections ---");
				for (String replacement : ruleMatch.getSuggestedReplacements()) {
					System.out.println("Correction " + count++ + ": " + replacement);
				}
				System.out.println();
			}
		} catch (IOException ex) {
			// Handle exceptions
			ex.printStackTrace();
		}
	}

}
