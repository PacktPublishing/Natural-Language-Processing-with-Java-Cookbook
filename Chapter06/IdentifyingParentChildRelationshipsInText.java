package com.packt.javanlp.cookbook.chapter7;

import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class IdentifyingParentChildRelationshipsInText {

	public static void main(String[] args) {
		// Identifying parent-child relationships in text

//      String parserModel = "C:/Current Books in Progress/NLP and Java/Models/edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
		LexicalizedParser lexicalizedParser = LexicalizedParser.loadModel("englishPCFG.ser.gz");

		String sentence = "The old man sat down beside the tree.";
		sentence = "The old man sat down beside the tree and read a book.";
		
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tokenizer = tokenizerFactory.getTokenizer(new StringReader(sentence));
		List<CoreLabel> coreLabelList = tokenizer.tokenize();
		Tree parseTree = lexicalizedParser.apply(coreLabelList);

		TreebankLanguagePack treebankLanguagePack = lexicalizedParser.treebankLanguagePack();
		GrammaticalStructureFactory grammaticalStructureFactory = treebankLanguagePack.grammaticalStructureFactory();
		GrammaticalStructure grammaticalStructure = grammaticalStructureFactory.newGrammaticalStructure(parseTree);
		List<TypedDependency> typedDependencyList = grammaticalStructure.typedDependenciesCCprocessed();
		System.out.println(typedDependencyList);
		System.out.println();
		
		for (TypedDependency dependency : typedDependencyList) {
//			System.out.println("Governor Word: [" + dependency.gov() 
//					+ "] Relation: [" + dependency.reln().getLongName()
//					+ "] Dependent Word: [" + dependency.dep() + "]");
			System.out.println("Governor Word: [" + dependency.gov().originalText() 
					+ " - " + dependency.gov().tag()
					+ "] Relation: [" + dependency.reln().getLongName() 
					+ "] Dependent Word: [" + dependency.dep()
					+ "]");
		}
		System.out.println();

		for (TypedDependency subjectDependency : typedDependencyList) {
//			System.out.println("Governor Word: [" + dependency.gov().originalText() + " - " + dependency.gov().tag()
//					+ "] Relation: [" + dependency.reln().getLongName() + "] Dependent Word: [" + dependency.dep()
//					+ "]");
			if (subjectDependency.gov().tag() != null && subjectDependency.gov().tag().equals("NN")) {
//				System.out.println("Governor Word: [" + dependency.gov().originalText() + " - " + dependency.gov().tag()
//						+ "] Relation: [" + dependency.reln().getLongName() + "] Dependent Word: [" + dependency.dep()
//						+ "]");
				for (TypedDependency verbDependency : typedDependencyList) {
					if (verbDependency.gov().tag() != null 
							&& verbDependency.gov().tag().equals("VBD")
							&& verbDependency.reln().getLongName().equals("nominal subject")
							&& verbDependency.dep().originalText().equals(subjectDependency.gov().originalText())) {
//						System.out.println("\tGovernor Word: [" + dependency2.gov().originalText() + " - "
//								+ dependency2.gov().tag() + "] Relation: [" + dependency2.reln().getLongName()
//								+ "] Dependent Word: [" + dependency2.dep().originalText() + "]");
						for (TypedDependency objectDependency : typedDependencyList) {
							if (objectDependency.gov().tag() != null 
									&& objectDependency.gov().tag().equals("VBD")
									&& (objectDependency.reln().getLongName().equals("nmod_preposition") ||
											objectDependency.reln().getLongName().equals("direct object"))) {
//								System.out.println("\tGovernor Word: [" + dependency3.gov().originalText() + " - "
//										+ dependency3.gov().tag() + "] Relation: [" + dependency3.reln().getLongName()
//										+ "] Dependent Word: [" + dependency3.dep().originalText() + "]");

								System.out.println(subjectDependency.gov().originalText() 
										+ " " + verbDependency.gov().originalText()
										+ " " + objectDependency.dep().originalText());
							}
							
						}
					// Governor Word: [sat - VBD] Relation: [nmod_preposition] Dependent Word: [tree/NN]
					}
				}
			}
		}

		System.out.println();
	}

}
