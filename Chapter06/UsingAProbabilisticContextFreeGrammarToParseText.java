package com.packt.javanlp.cookbook.chapter7;

import java.util.Arrays;
import java.util.List;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreePrint;

public class UsingAProbabilisticContextFreeGrammarToParseText {

	public static void main(String[] args) {
		// Using a probabilistic context free grammar to parse text

		LexicalizedParser lexicalizedParser = LexicalizedParser.loadModel("englishPCFG.ser.gz");

		List<String> wordList = Arrays.asList("The", "old man", "sat", "down", "beside", "the", "tree", ".");
		Tree tree = lexicalizedParser.parseStrings(wordList);
		tree.pennPrint();
		
		System.out.println("----------------------------------------");

		TreePrint treePrint = new TreePrint("penn,typedDependenciesCollapsed");
		treePrint.printTree(tree);


		System.out.println("----------------------------------------");
		System.out.println("TreePrint Formats");
		for (String treeFormat : TreePrint.outputTreeFormats) {
			System.out.println(treeFormat);
		}
		System.out.println();

		
		System.out.println("----------------------------------------");

		treePrint = new TreePrint("penn,xmlTree");
		treePrint.printTree(tree);


	}

}
