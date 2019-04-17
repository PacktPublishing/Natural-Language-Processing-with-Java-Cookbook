package com.packt.javanlp.cookbook.chapter6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetectingSpam {

	public static void main(String[] args) {
		// Detecting spam
		ArrayList<String> testList = new ArrayList();
		try (BufferedWriter spamBufferedWriter = new BufferedWriter(new FileWriter(new File("spamtraining.train")))) {

			String rootDirectoryName = "C:/NLP Cookbook/Chapter 6/lingspam_public/stop";
			File rootDirectory = new File(rootDirectoryName);
			for (String directoryName : rootDirectory.list()) {
				File file = new File(rootDirectoryName + "/" + directoryName);

				String fileNames[] = file.list();
				if (fileNames != null) {
					for (String fileName : fileNames) {
						String filePath = rootDirectoryName + "/" + directoryName + "/" + fileName;
						StringBuilder lineStringBuilder = new StringBuilder();

						BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
						String line = null;
						if (fileName.contains("spms")) {
							lineStringBuilder.append("spam\t");
						} else {
							lineStringBuilder.append("ham\t");
						}
						while ((line = br.readLine()) != null) {
							lineStringBuilder.append(line);
						}
						if (directoryName.equals("part10")) {
							testList.add(lineStringBuilder.toString());
						} else {
							spamBufferedWriter.write(lineStringBuilder.toString() + "\n");
						}
						lineStringBuilder.setLength(0);
					}
				}

			}

		} catch (IOException ex) {
			// Handle exceptions
		}

		try (InputStream dataInputStream = new FileInputStream("spamtraining.train")) {
			ObjectStream<String> objectStream = new PlainTextByLineStream(dataInputStream, StandardCharsets.UTF_8);
			ObjectStream<DocumentSample> documentSampleStream = new DocumentSampleStream(objectStream);
			DoccatModel documentCategorizationModel = DocumentCategorizerME.train("en", documentSampleStream);
			DocumentCategorizerME documentCategorizer = new DocumentCategorizerME(documentCategorizationModel);
			for (String testItem : testList) {
				double[] probabilities = documentCategorizer.categorize(testItem);
				String bestCategory = documentCategorizer.getBestCategory(probabilities);
				System.out.println("The best fit for: [" + testItem.subSequence(0, 32) + "...] is: " + bestCategory);
			}
		} catch (FileNotFoundException ex) {
			// Handle exceptions
		} catch (IOException ex) {
			// Handle exceptions
		}
		
		String text = "Congratualtions! You have won! Click here...";
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File("spam.txt")))) {

			String line = null;
			while ((line = br.readLine()) != null) {
			    Pattern pattern = Pattern.compile(line);
			    Matcher matcher = pattern.matcher(text);
			    if (matcher.find() == true) {
			        System.out.println("Spam detected");
			        break;
			    }
			}
			
			System.out.println(text.contains("Click here"));
			} catch (FileNotFoundException e) {
			 // Handle exceptions
			} catch (IOException e) {
			 // Handle exceptions
			}
		
	}

}
