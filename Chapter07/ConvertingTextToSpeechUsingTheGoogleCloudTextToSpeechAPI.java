package com.packt.javanlp.cookbook.chapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;

public class ConvertingTextToSpeechUsingTheGoogleCloudTextToSpeechAPI {

	public static void main(String[] args) {
		// Converting Text to Speech Using the Google Cloud Text-to- Speech API
		String text = "Now is the time for all good men to come to the aid of their country.";
		String fileName = "audio.linear16";
		SsmlVoiceGender ssmlVoiceGender = SsmlVoiceGender.NEUTRAL;
		AudioEncoding audioEncoding = AudioEncoding.LINEAR16;
		String languageCode = "en-US";
		

		try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
			SynthesisInput synthesisInput = SynthesisInput
					.newBuilder()
					.setText(text)
					.build();

			// Build the voice request
			VoiceSelectionParams voiceSelectionParams = VoiceSelectionParams
					.newBuilder()
					.setLanguageCode(languageCode)
					.setSsmlGender(ssmlVoiceGender)
					.build();

			// Select the type of audio file you want returned
			AudioConfig audioConfig = AudioConfig
					.newBuilder()
					.setAudioEncoding(audioEncoding)
					.build();
			
			// Perform the text-to-speech request
			SynthesizeSpeechResponse synthesizeSpeechResponse = 
					textToSpeechClient.synthesizeSpeech(
							synthesisInput,	voiceSelectionParams, audioConfig);

			// Get the audio contents from the response
			ByteString audioContents = synthesizeSpeechResponse.getAudioContent();

			// Write the response to the output file.
			try (OutputStream outputStream = new FileOutputStream(fileName)) {
				outputStream.write(audioContents.toByteArray());
				System.out.println("Audio content written to file \"audio.linear16\"");
			} catch (FileNotFoundException ex) {
				// Handle exceptions
				ex.printStackTrace();
			} catch (IOException ex) {
				// Handle exceptions
				ex.printStackTrace();
			}
		} catch (IOException ex) {
			// Handle exceptions
			ex.printStackTrace();
		}
	}

}
