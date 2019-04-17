package com.packt.javanlp.cookbook.chapter9;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.text.similarity.CosineSimilarity;

public class FindingCosineSimilarityOfText {

	public static void main(String[] args) {
		// Finding Cosine similarity of text
		CosineSimilarity cosineSimilarity = new CosineSimilarity();
		String firstSample = "A simple sentence";
		String secondSample = "One simple sentence";

		Map<CharSequence, Integer> vectorA = Arrays.stream(firstSample.split(""))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));
		Map<CharSequence, Integer> vectorB = Arrays.stream(secondSample.split(""))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

		System.out.printf("%5.4f\n", cosineSimilarity.cosineSimilarity(vectorA, vectorB));

		HashMap<CharSequence, Integer> vectorC = new HashMap<>();
		for (char character : secondSample.toCharArray()) {
			int count = (vectorC.get(character + "") == null) ? 0 : vectorC.get(character + "");
			vectorC.put(character + "", count + 1);
		}

		System.out.printf("%5.4f\n", cosineSimilarity.cosineSimilarity(vectorA, vectorC));

		vectorA = Arrays.stream(firstSample.split(" "))
				.collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
		vectorB = Arrays.stream(secondSample.split(" "))
				.collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

		System.out.printf("%5.4f\n", cosineSimilarity.cosineSimilarity(vectorA, vectorB));

	}

}
