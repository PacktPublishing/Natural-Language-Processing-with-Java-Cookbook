package com.packt.javanlp.cookbook.chapter3;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.sentences.MedlineSentenceModel;
import com.aliasi.sentences.SentenceChunker;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;

public class PerformingSBDOnSpecializedText {

	public static void main(String[] args) {
		// Performing SBD on specialized text
		String text = "In total, 33 patients with AIS and 39 healthy controls were "
				+ "enrolled in this study. The major findings were as follows: (1) "
				+ "The stroke group had a significantly lower level of serum Hg "
				+ "(6.4?±?4.3 µg/L vs. 9.8?±?7.0 µg/L, P =?0.032, OR?=?0.90, 95% "
				+ "CI?=?0.81–0.99) and a lower level of urine Hg (0.7?±?0.7 µg/L "
				+ "vs. 1.2?±?0.6 µg/L, P =?0.006, OR?=?0.27, 95% CI?=?0.11–0.68) "
				+ "than the control group. (2) No significant difference in serum "
				+ "Pb (S-Pb), As (S-As), and Cd (S-Cd) levels and urine Pb (U-Pb), "
				+ "As (U-As) and Cd (U-Cd) levels was observed in either group.";

		TokenizerFactory tokenizerfactory = IndoEuropeanTokenizerFactory.INSTANCE;
		MedlineSentenceModel medlineSentenceModel = new MedlineSentenceModel();

		SentenceChunker sentenceChunker = new SentenceChunker(tokenizerfactory, medlineSentenceModel);
		Chunking chunking = sentenceChunker.chunk(text.toCharArray(), 0, text.length());
		String slice = chunking.charSequence().toString();

		for (Chunk chunk : chunking.chunkSet()) {
			System.out.println("[" + slice.substring(chunk.start(), chunk.end()) + "]");
		}

	}

}
