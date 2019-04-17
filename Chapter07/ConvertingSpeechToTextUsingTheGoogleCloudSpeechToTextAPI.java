package com.packt.javanlp.cookbook.chapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

public class ConvertingSpeechToTextUsingTheGoogleCloudSpeechToTextAPI {

	public static void main(String[] args) {
		// Instantiates a client
		try (SpeechClient speechClient = SpeechClient.create()) {
			String fileName = "C:/NLP Cookbook/Code/Chapter8/audio.linear16";
			int sampleRateHertz = 24000;
			AudioEncoding audioEncoding = AudioEncoding.LINEAR16;
			String languageCode = "en-US";

			File file = new File(fileName);
			byte[] dataByteArray = Files.readAllBytes(file.toPath());
			ByteString audioByteString = ByteString.copyFrom(dataByteArray);

			RecognitionConfig recognitionConfig = RecognitionConfig
					.newBuilder()
					.setEncoding(audioEncoding)
					.setSampleRateHertz(sampleRateHertz)
					.setLanguageCode(languageCode)
					.build();
			RecognitionAudio recognitionAudio = RecognitionAudio
					.newBuilder()
					.setContent(audioByteString)
					.build();

			RecognizeResponse recognizeResponse = speechClient.recognize(
					recognitionConfig, recognitionAudio);
			SpeechRecognitionResult result = recognizeResponse.getResults(0);
			SpeechRecognitionAlternative transcription = result.getAlternatives(0);
			System.out.printf("Transcription: %s%nConfidence: %5.3f%n", 
					transcription.getTranscript(), 
					transcription.getConfidence());

			System.out.println("----------------------");
			List<SpeechRecognitionResult> resultsList = recognizeResponse.getResultsList();
			for (SpeechRecognitionResult speechRecognitionResult : resultsList) {
				List<SpeechRecognitionAlternative> alternativeList = 
						speechRecognitionResult.getAlternativesList();
				for (SpeechRecognitionAlternative alternative : alternativeList) {
					System.out.printf("Transcription: %s%nConfidence: %5.3f%n",
							alternative.getTranscript(), 
							alternative.getConfidence());
				}
			}
		} catch (IOException ex) {
			// Handle exceptions
			ex.printStackTrace();
		}
	}

}
