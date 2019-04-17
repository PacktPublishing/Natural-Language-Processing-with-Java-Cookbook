package com.packt.javanlp.cookbook.chapter4;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

public class UsingAChunkerToFindEntities {

	public static void main(String[] args) {
		// Using a chunker to find entities
		String sentences[] = { "Sam and Mary left on Friday, November 12. ",
				"They stopped in Boston at an ATM to get $300 for expenses. ",
				"While they were there Sam bumped into an old friend, Mr. Smith, "
						+ "who was on his way to work at ATT. ",
				"They decided to leave together and departed for Maine" };

		try {

			File modelFile = new File("ne-en-news-muc6.AbstractCharLmRescoringChunker");
			Chunker chunker = (Chunker) AbstractExternalizable.readObject(modelFile);

			for (int index = 0; index < sentences.length; index++) {
				System.out.println("Sentence " + (index + 1));

				Chunking chunking = chunker.chunk(sentences[index]);
				Set<Chunk> set = chunking.chunkSet();

				for (Chunk chunk : set) {
					System.out.println("\tEntity: " + sentences[index].substring(chunk.start(), chunk.end())
							+ "\tType: " + chunk.type());
				}

			}

		} catch (IOException | ClassNotFoundException ex) {
			// Handle exception
		}

	}

}
