package com.packt.javanlp.cookbook.chapter5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerFactory;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.WordTagSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Sequence;
import opennlp.tools.util.TrainingParameters;

public class TrainingASpecializedPOSModel {

	public static void main(String[] args) {
		// Training a specialized POS model
		POSModel posTaggerMEModel = null;
		InputStreamFactory inputStreamFactory = new InputStreamFactory() {
			public InputStream createInputStream() throws IOException {
				return new FileInputStream("sample.train");
			}
		};

		try (ObjectStream<String> stringObjectStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
				ObjectStream<POSSample> posSampleObjectStream = new WordTagSampleStream(stringObjectStream);) {

			posTaggerMEModel = POSTaggerME.train("en", posSampleObjectStream, TrainingParameters.defaultParams(),
					new POSTaggerFactory());

		} catch (IOException e) {
			// Handle exceptions
		}

		try (OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(new File("sample.bin")));) {
			posTaggerMEModel.serialize(modelOut);
		} catch (IOException e) {
			// Handle exceptions
		}

		String sampleSentence = "When the mouse saw the cat it ran away.";
		String words[] = sampleSentence.split(" ");
		try (InputStream modelInputStream = new FileInputStream(new File("sample.bin"));) {
			POSModel posModel = new POSModel(modelInputStream);
			POSTaggerME posTaggerME = new POSTaggerME(posModel);
			String tags[] = posTaggerME.tag(words);
			for (int i = 0; i < words.length; i++) {
				System.out.print(words[i] + "/" + tags[i] + " ");
			}
			System.out.println();
			Sequence topSequences[] = posTaggerME.topKSequences(words);
			for (Sequence sequence : topSequences) {
				List<String> outcomes = sequence.getOutcomes();
				System.out.print("[");
				for (int i = 0; i < outcomes.size(); i++) {
					System.out.print(words[i] + "/" + outcomes.get(i) + " ");
				}
				System.out.println("]");
			}
			System.out.println();
		} catch (IOException e) {
			// Handle exceptions
		}

	}

}
