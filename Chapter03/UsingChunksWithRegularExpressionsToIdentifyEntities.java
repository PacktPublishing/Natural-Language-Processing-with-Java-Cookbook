package com.packt.javanlp.cookbook.chapter4;

import java.util.Set;
import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.RegExChunker;

public class UsingChunksWithRegularExpressionsToIdentifyEntities {

	public static void main(String[] args) {
		// Using chunks with regular expressions to identify entities
		String sampleText = "His email address is hisemail@somecompany.com.";
		String emailRegularExpression = "[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@("
				+ "[A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})";

		//Chunker chunker = new RegExChunker(emailRegularExpression, "EMAIL", 1.0);
		Chunker chunker = new EmailRegExChunker();

		Chunking chunking = chunker.chunk(sampleText);
		Set<Chunk> chunkSet = chunking.chunkSet();

		for (Chunk chunk : chunkSet) {
			System.out
					.println("Entity: " + sampleText.substring(chunk.start(), chunk.end()) + "\tType: " + chunk.type());
		}

	}

}
