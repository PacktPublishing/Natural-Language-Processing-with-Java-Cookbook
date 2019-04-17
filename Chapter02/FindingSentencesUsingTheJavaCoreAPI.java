package com.packt.javanlp.cookbook.chapter3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindingSentencesUsingTheJavaCoreAPI {

	public static void main(String[] args) {
		// Finding sentences using the Java core API
		String text =
			    "We will start with a simple sentence. However, is it possible for "
			    + "a sentence to end with a question mark? Obviously that is possible! "
			    + "Another complication is the use of a number such as 56.32 or "
			    + "ellipses such as ... Ellipses may be found ... within a sentence! "
			    + "Of course, we may also find the use of abbreviations such as " 
			    + "Mr. Smith or Dr. Jones.";	
		
		String sentenceDelimiters = "[.?!]";
		 String[] sentences = (text.split(sentenceDelimiters));
		 for (String sentence : sentences) {
		     System.out.println(sentence);
		 }
		 
		 Pattern sentencePattern = Pattern.compile("\\s+[^.!?]*[.!?]");
		 Matcher matcher = sentencePattern.matcher(text);
		 while (matcher.find()) {
		     System.out.println(matcher.group());
		 }
		 
		}

}
