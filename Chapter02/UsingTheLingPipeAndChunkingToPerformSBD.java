package com.packt.javanlp.cookbook.chapter3;

import java.util.ArrayList;
import java.util.List;
import com.aliasi.sentences.IndoEuropeanSentenceModel;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;

public class UsingTheLingPipeAndChunkingToPerformSBD {

	public static void main(String[] args) {
		// Using the LingPipe and chunking to perform SBD
		String text =
			    "We will start with a simple sentence. However, is it possible for "
			    + "a sentence to end with a question mark? Obviously that is possible! "
			    + "Another complication is the use of a number such as 56.32 or "
			    + "ellipses such as ... Ellipses may be found ... with a sentence! "
			    + "Of course, we may also find the use of abbreviations such as " + "Mr. Smith or Dr. Jones.";
			TokenizerFactory tokenizerFactory = 
			    IndoEuropeanTokenizerFactory.INSTANCE;
			SentenceModel sentenceModel = new IndoEuropeanSentenceModel();
			
			List<String> tokenList = new ArrayList<>();
			List<String> whiteList = new ArrayList<>();
			
			Tokenizer tokenizer = tokenizerFactory.tokenizer(text.toCharArray(), 0, text.length());
			tokenizer.tokenize(tokenList, whiteList);

			int[] sentenceBoundaries = sentenceModel.boundaryIndices(
			tokenList.toArray(new String[tokenList.size()]),
			whiteList.toArray(new String[whiteList.size()]));
			
			int start = 0;
			for (int boundary : sentenceBoundaries) {
			    System.out.print("[");
			    while (start <= boundary) {
			        System.out.print(tokenList.get(start) + whiteList.get(start + 1));
			        start++;
			    }
			    System.out.println("]");
			}
			
			int begin = 0;
			for (int boundary : sentenceBoundaries) {
			    System.out.println(begin + ":" + boundary);
			    begin = boundary;
			}
	}

}
