package com.packt.javanlp.cookbook.chapter7;

import java.io.IOException;
import java.util.Map;

import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentence;
import com.google.cloud.language.v1.Token;
import com.google.protobuf.Descriptors.FieldDescriptor;

public class UsingTheGoogleNLPAPIToParseText {

	public static void main(String[] args) {
		String text = "He walked to the store.";
		text = "He walked to the store. Then, Jose bought a candy bar. " 
				+ "He ate all of it as he left the store.";

		try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create()) {
			Document document = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
			AnalyzeSyntaxRequest analyzeSyntaxRequest = AnalyzeSyntaxRequest.newBuilder().setDocument(document)
					.setEncodingType(EncodingType.UTF16).build();

			AnalyzeSyntaxResponse analyzeSyntaxResponse = languageServiceClient.analyzeSyntax(analyzeSyntaxRequest);

			int count = 0;
			for (Token token : analyzeSyntaxResponse.getTokensList()) {
				int headTokenIndex = token.getDependencyEdge().getHeadTokenIndex();
				System.out.printf("%2d - ", count++);
				if (!"ROOT".equalsIgnoreCase(token.getDependencyEdge().getLabel().toString())) {
					for (int i = 0; i < headTokenIndex; i++) {
						System.out.printf("  ");
					}
				} else {
					
				}
				System.out.printf("'%s'", token.getText().getContent());
				Map<FieldDescriptor, Object> fields = token.getPartOfSpeech().getAllFields();
				System.out.printf(" [");
				for (FieldDescriptor fd : fields.keySet()) {
					String name = fd.getName();
					name = name.toUpperCase().substring(0, 1) + 
							name.substring(1, name.length());
					System.out.printf("%s: %s, ", name, token.getPartOfSpeech().getField(fd));
				}
				System.out.printf("] %d%n",headTokenIndex);
			}
			

			System.out.println();
			for (Sentence sentence : analyzeSyntaxResponse.getSentencesList()) {
				System.out.println(sentence.getText().getContent());
				// Submit new request for each sentence
			}
		} catch (IOException e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}

//    System.out.printf("\tBeginOffset: %d\n", token.getText().getBeginOffset());
//    System.out.printf("Lemma: %s\n", token.getLemma());
//    System.out.printf(" - PartOfSpeechTag: %s", token.getPartOfSpeech().getTag());
//    System.out.printf("\tAspect: %s\n", token.getPartOfSpeech().getAspect());
//    System.out.printf("\tCase: %s\n", token.getPartOfSpeech().getCase());
//    System.out.printf("\tForm: %s\n", token.getPartOfSpeech().getForm());
//    System.out.printf("  Gender: %s", token.getPartOfSpeech().getGender());
//    System.out.printf("  Mood: %s", token.getPartOfSpeech().getMood());
//    System.out.printf(" Number: %s", token.getPartOfSpeech().getNumber());
//    System.out.printf("  Person: %s", token.getPartOfSpeech().getPerson());
//    System.out.printf("\tProper: %s\n", token.getPartOfSpeech().getProper());
//    System.out.printf("\tReciprocity: %s\n", token.getPartOfSpeech().getReciprocity());
//    System.out.printf("  Tense: %s", token.getPartOfSpeech().getTense());
//    System.out.printf("  Voice: %s", token.getPartOfSpeech().getVoice());
//		System.out.printf("\nDependencyEdge");
//		System.out.printf("\tHeadTokenIndex: %d\n", token.getDependencyEdge().getHeadTokenIndex());
//		System.out.printf("\tLabel: [%s]\n\n", token.getDependencyEdge().getLabel());

}
