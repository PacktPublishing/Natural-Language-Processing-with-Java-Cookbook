package com.packt.javanlp.cookbook.chapter3;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import opennlp.tools.sentdetect.SentenceDetectorFactory;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class TrainingANeuralNetworkToPerformSBDWithSpecializedText {

	public static void main(String[] args) {
		// Training a neural network to perform SBD with specialized text
		String terminators[] = { ".", "!", "?", "..." };
		String sampleSentences[] = { "A simple sentence", "Another sentence a bit longer", "Last sentence" };

		StringBuilder stringBuilder = new StringBuilder();
		for (String sentenceTerminator : terminators) {
			for (String sentence : sampleSentences) {
				stringBuilder.append(sentence).append(sentenceTerminator);
				stringBuilder.append(System.lineSeparator());
			}
		}

		String trainingSentences = stringBuilder.toString();

		try (ObjectStream<String> lineStream = new PlainTextByLineStream(
				() -> new ByteArrayInputStream(trainingSentences.getBytes()), Charset.forName("UTF-8"));
				ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream)) {

			SentenceDetectorFactory sentenceDetectorFactory = new SentenceDetectorFactory("en", true, null, null);
			SentenceModel sentenceModel = SentenceDetectorME.train("en", sampleStream, sentenceDetectorFactory,
					TrainingParameters.defaultParams());

			OutputStream modelOutputStream = new BufferedOutputStream(new FileOutputStream("modelFile"));
			sentenceModel.serialize(modelOutputStream);

			String text = "We will start with a simple sentence. However, is it possible for "
					+ "a sentence to end with a question mark? Obviously that is possible! "
					+ "Another complication is the use of a number such as 56.32 or "
					+ "ellipses such as ... Ellipses may be found ... with a sentence! "
					+ "Of course, we may also find the use of abbreviations such as " + "Mr. Smith or Dr. Jones.";

			SentenceDetectorME sentenceDetector = null;
			InputStream inputStrean = new FileInputStream("modelFile");
			sentenceModel = new SentenceModel(inputStrean);
			sentenceDetector = new SentenceDetectorME(sentenceModel);
			String sentences[] = sentenceDetector.sentDetect(text);
			for (String sentence : sentences) {
				System.out.println("[" + sentence + "]");
			}
		} catch (FileNotFoundException ex) {
			// Handle exceptions
		} catch (IOException ex) {
			// Handle exceptions
		}

	}

}
