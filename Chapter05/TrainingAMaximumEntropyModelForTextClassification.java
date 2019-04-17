package com.packt.javanlp.cookbook.chapter6;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class TrainingAMaximumEntropyModelForTextClassification {

	public static void main(String[] args) {
		// Training a maximum entropy model for text classification
		try (InputStream dataInputStream = new FileInputStream("en-frograt.train")) {

			ObjectStream<String> objectStream =
				    new PlainTextByLineStream(dataInputStream, StandardCharsets.UTF_8);
				ObjectStream<DocumentSample> documentSampleStream = new DocumentSampleStream(objectStream);
				
				DoccatModel documentCategorizationModel = 
						DocumentCategorizerME.train("en", documentSampleStream);
				
				OutputStream modelOutputStream = new BufferedOutputStream(
						new FileOutputStream(new File("en-frograt.bin")));
						OutputStream modelBufferedOutputStream = new BufferedOutputStream(modelOutputStream);
						 documentCategorizationModel.serialize(modelBufferedOutputStream);
						 
		} catch (FileNotFoundException e) {
		    // Handle exceptions
		} catch (IOException e) {
		    // Handle exceptions
		}
	}

}
