package com.packt.javanlp.cookbook.chapter5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Span;

public class UsingAChunkerToFindPOS {

	public static void main(String[] args) {
		// Using a chunker to find POS
		try (InputStream posModelInputStream = new FileInputStream("en-pos-maxent.bin");
				InputStream chunkerInputStream = new FileInputStream("en-chunker.bin");) {

			POSModel posModel = new POSModel(posModelInputStream);
			POSTaggerME posTaggerME = new POSTaggerME(posModel);

			String sampleSentence = "When the mouse saw the cat it ran away.";
			String words[] = sampleSentence.split(" ");
			String tags[] = posTaggerME.tag(words);

			ChunkerModel chunkerModel = new ChunkerModel(chunkerInputStream);
			ChunkerME chunkerME = new ChunkerME(chunkerModel);
			String results[] = chunkerME.chunk(words, tags);

			for (int i = 0; i < results.length; i++) {
				System.out.println("[" + words[i] + "] " + results[i]);
			}

			Span[] spans = chunkerME.chunkAsSpans(words, tags);
			for (Span span : spans) {
			    String chunk = "";
			    for (int j = span.getStart(); j < span.getEnd(); j++) {
			        chunk += words[j] + " ";
			    }
			    System.out.printf("[%-10s]", chunk);
			    System.out.printf(" Type: %-4s - Begin: %d End: %d Length: %d\n", 
			    span.getType(), span.getStart(), span.getEnd(), span.length());
			}
			
		} catch (IOException ex) {
			// Handle exceptions
		}

	}

}
