package com.packt.javanlp.cookbook.chapter10;

import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

public class SpellCheckingUsingTheLanguageToolAPI {

	public static void main(String[] args) {
		JLanguageTool jLanguageTool = new JLanguageTool(new AmericanEnglish());
		try {
			for (Rule rule : jLanguageTool.getAllRules()) {
				if (!rule.isDictionaryBasedSpellingRule()) {
					jLanguageTool.disableRule(rule.getId());
				}
			}
			String text;
			text = "He dissapeared with nott even a trace. It was rong.";
			List<RuleMatch> ruleMatchList = jLanguageTool.check(text);

			for (RuleMatch ruleMatch : ruleMatchList) {
				System.out.println("Spelling problem in the sentence: [" 
						+ ruleMatch.getSentence().getText() + "]");
				System.out.println( 
						"'" + text.substring(ruleMatch.getFromPos(), ruleMatch.getToPos()) + "'"
						+" At Position: [" + ruleMatch.getFromPos() + "-"
						+ ruleMatch.getToPos() + "]");

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
