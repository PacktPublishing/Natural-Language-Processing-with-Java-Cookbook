package com.packt.javanlp.cookbook.chapter9;

import edu.smu.tspell.wordnet.AdjectiveSynset;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.WordSense;

public class FindingHyponymsAndAntonyms {

	public static void main(String[] args) {
		// Finding hyponyms and antonyms
		System.setProperty("wordnet.database.dir", 
				"C:\\\\Downloads\\\\WordNet\\\\WordNet-3.0.tar\\\\WordNet-3.0\\\\WordNet-3.0\\\\dict");
		WordNetDatabase wordNetDatabase = WordNetDatabase.getFileInstance();

		String noun = "horse";
		Synset[] synsets = wordNetDatabase.getSynsets(noun, SynsetType.NOUN);
		for (int i = 0; i < synsets.length; i++) {
			NounSynset nounSynset = (NounSynset) (synsets[i]);
			NounSynset[] hyponyms = nounSynset.getHyponyms();

			System.out.println(nounSynset.getWordForms()[0] + " - " + nounSynset.getDefinition());
			if (hyponyms.length > 0) {
				System.out.println(" Hyponyms");
				for (NounSynset hyponym : hyponyms) {
					System.out.println("\t" + hyponym.getWordForms()[0]);
				}
			}

			WordSense[] antonyms = nounSynset.getAntonyms(noun);
			if (antonyms.length > 0) {
				System.out.println(" Antonyms");
				for (WordSense antonym : antonyms) {
					System.out.println("\t" + antonym.getWordForm());
				}
			}
			System.out.println();

//			String adjective = "big";
//			synsets = wordNetDatabase.getSynsets(adjective, SynsetType.ADJECTIVE);
//			for (int i = 0; i < synsets.length; i++) {
//			    AdjectiveSynset adjectiveSynset = (AdjectiveSynset) (synsets[i]);
//			    AdjectiveSynset[] similars = adjectiveSynset.getSimilar();
//
//			System.out.println(adjectiveSynset.getWordForms()[0] + " - " 
//			    + adjectiveSynset.getDefinition());
//			if (similars.length > 0) {
//			    System.out.println(" Similar");
//			    for (AdjectiveSynset similar : similars) {
//			        System.out.println("\t" + similar.getWordForms()[0]);
//			    }
//			}
//
//			WordSense[] antonyms = adjectiveSynset.getAntonyms(adjective);
//			if (antonyms.length > 0) {
//			    System.out.println(" Antonyms");
//			    for (WordSense antonym : antonyms) {
//			        System.out.println("\t" + antonym.getWordForm());
//			    }
//			}
//			System.out.println();
			
		}
	}

}
