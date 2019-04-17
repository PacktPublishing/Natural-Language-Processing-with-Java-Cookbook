package com.packt.javanlp.cookbook.chapter7;

//Using OpenNLP to generate a parse tree imports
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

public class UsingOpenNLPToGenerateAParseTree {

	public static void main(String[] args) {
		// Using OpenNLP to generate a parse tree
        try (InputStream modelInputStream = new FileInputStream("en-parser-chunking.bin");) {
            ParserModel parserModel = new ParserModel(modelInputStream);
            Parser parser = ParserFactory.create(parserModel);
            String sentence = "The old man sat down beside the tree.";

            Parse parseTrees[] = ParserTool.parseLine(sentence, parser, 3);
            int count = 1;
            for (Parse parseTree : parseTrees) {
                System.out.println("Parse Tree " + count++);
                parseTree.show();

                // There's more ...
                System.out.println();
                Parse children[] = parseTree.getChildren();
                for (Parse child : children) {
                    System.out.println(child.getText());
                    Parse tagNodes[] = child.getTagNodes();
                    System.out.println("Tags");
                    for (Parse tagNode : tagNodes) {
                        System.out.println("[" + tagNode + "]" + " Type: " + tagNode.getType()
                                + "  Probability: " + tagNode.getProb());
                    }
                }
                System.out.println();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
