package com.packt.javanlp.cookbook.chapter3;

import java.text.BreakIterator;

public class PerformingSBDUsingTheBreakIteratorClass {
	private static String text = "We will start with a simple sentence. However, is it possible for "
			+ "a sentence to end with a question mark? Obviously that is possible! "
			+ "Another complication is the use of a number such as 56.32 or "
			+ "ellipses such as ... Ellipses may be found ... with a sentence! "
			+ "Of course, we may also find the use of abbreviations such as " + "Mr. Smith or Dr. Jones.";

	public static void main(String[] args) {
		// Performing SBD using the BreakIterator class

		BreakIterator breakIterator = BreakIterator.getSentenceInstance();
		breakIterator.setText(text);

		int startPosition = breakIterator.first();
		int endingPosition = breakIterator.first();
		while (true) {
			endingPosition = breakIterator.next();
			if (endingPosition == BreakIterator.DONE) {
				break;
			} else {
				System.out.println(startPosition + "-" + endingPosition + " ["
						+ text.substring(startPosition, endingPosition) + "]");
				startPosition = endingPosition;
			}
		}
		
		breakIterator.setText(text); 
		startPosition = breakIterator.first(); 
		endingPosition = breakIterator.next(); 
		System.out.println("First sentence: [" + 
		    text.substring(startPosition, endingPosition) + "]");
		
		breakIterator.setText(text); 
		endingPosition = breakIterator.last(); 
		startPosition = breakIterator.previous(); 
		System.out.println("Last sentence: [" + 
		    text.substring(startPosition, endingPosition) + "]");
	}

}
