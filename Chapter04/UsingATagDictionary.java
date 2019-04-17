package com.packt.javanlp.cookbook.chapter5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import opennlp.tools.postag.MutableTagDictionary;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerFactory;
import opennlp.tools.postag.TagDictionary;

public class UsingATagDictionary {

	public static void main(String[] args) {
		// Using a tag dictionary
		try (InputStream modelInputStream = new FileInputStream("en-pos-maxent.bin");) {

			POSModel posModel = new POSModel(modelInputStream);
			POSTaggerFactory posTaggerFactory = posModel.getFactory();
			MutableTagDictionary mutableTagDictionary = (MutableTagDictionary) posTaggerFactory.getTagDictionary();

			String currentTags[] = mutableTagDictionary.getTags("process");
			for (String tag : currentTags) {
				System.out.print("/" + tag);
			}
			System.out.println();

			ArrayList<String> newTags = new ArrayList<String>();
			for (int i = 0; i < currentTags.length; i++) {
			 newTags.add(currentTags[i]);
			}
			newTags.add("MYTAG");
			
			mutableTagDictionary.put("process", newTags.toArray(new String[newTags.size()]));
			
			currentTags = mutableTagDictionary.getTags("process");
			for (String tag : currentTags) {
			    System.out.print("/" + tag);
			}
			System.out.println();
			
		} catch (IOException e) {
			// Handle exceptions
		}
	}

}
