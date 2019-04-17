package com.packt.javanlp.cookbook.chapter2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Tokenization using the Java SDK
		String sampleText = "In addition, the rook was moved too far to be effective.";
		Scanner scanner = new Scanner(sampleText);
		ArrayList<String> list = new ArrayList<>();
		
		while (scanner.hasNext()) {
		    String token = scanner.next();
		    list.add(token);
		 }

		for (String token : list) {
		    System.out.println(token);
		}


	}

}
