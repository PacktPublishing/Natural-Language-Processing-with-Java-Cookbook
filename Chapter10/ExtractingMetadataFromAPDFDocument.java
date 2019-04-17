package com.packt.javanlp.cookbook.chapter11;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class ExtractingMetadataFromAPDFDocument {

	public static void main(String[] args) {
		try {
			File file = new File("PDFFile.pdf");
			PDDocument pdDocument = PDDocument.load(file);
			
			// Document properties
			PDDocumentInformation pdDocumentInformation = pdDocument.getDocumentInformation();
			Set<String> metaDataKeys = pdDocumentInformation.getMetadataKeys();
			for (String key : metaDataKeys) {
				System.out.println(key + ": " + pdDocumentInformation.getPropertyStringValue(key));
			}
			System.out.println();

			System.out.println("Author: " + pdDocumentInformation.getAuthor());
			System.out.println("Creator: " + pdDocumentInformation.getCreator());
			System.out.println("Keywords: " + pdDocumentInformation.getKeywords());
			System.out.println("Subject: " + pdDocumentInformation.getSubject());
			System.out.println("Title: " + pdDocumentInformation.getTitle());
			System.out.println("Creation Date: " + pdDocumentInformation.getCreationDate().getTime());
			System.out.println("Last Modified Date: " + pdDocumentInformation.getModificationDate().getTime());
			System.out.println();
			pdDocument.close();
		} catch (IOException ex) {
			// Handle exceptions
			ex.printStackTrace();
		}
	}

}
