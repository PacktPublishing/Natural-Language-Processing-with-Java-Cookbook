package com.packt.javanlp.cookbook.chapter9;

import org.apache.commons.text.similarity.HammingDistance;
import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.text.similarity.LevenshteinResults;

public class FindingTheDistanceBetweenText {

	public static void main(String[] args) {
		// Finding the distance between text
		HammingDistance hammingDistance = new HammingDistance();
		System.out.println("Hamming Distance: " + hammingDistance.apply("bat", "bat"));
		System.out.println("Hamming Distance: " + hammingDistance.apply("bat", "cat"));
		System.out.println("Hamming Distance: " + hammingDistance.apply("bat", "rut"));

		LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
		System.out.println("Levenshtein Distance: " + levenshteinDistance.apply("bat", "bat"));
		System.out.println("Levenshtein Distance: " + levenshteinDistance.apply("bat", "rat"));
		System.out.println("Levenshtein Distance: " + levenshteinDistance.apply("bat", "rut"));
		System.out.println("Levenshtein Distance: " + levenshteinDistance.apply("bat", "battle"));

		LevenshteinDetailedDistance levenshteinDetailedDistance = new LevenshteinDetailedDistance();
		LevenshteinResults levenshteinResults = levenshteinDetailedDistance.apply("similar", "simulator");
		System.out.println("Number of deletions: " + levenshteinResults.getDeleteCount());
		System.out.println("Number of insertions: " + levenshteinResults.getInsertCount());
		System.out.println("Number of substitutions: " + levenshteinResults.getSubstituteCount());
	}

}
