package com.packt.javanlp.cookbook.chapter11;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.apache.poi.ooxml.POIXMLProperties.ExtendedProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ExtractingMetaDataFromAWordDocument {

	public static void main(String[] args) {
		// Extracting metadata from a Word document
		try {
			String fileName = "PDFFile.docx";
			XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(fileName));

			XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xwpfDocument);

			CoreProperties coreProperties = xwpfWordExtractor.getCoreProperties();
			System.out.println("Creator: " + coreProperties.getCreator());
			System.out.println("Keywords: " + coreProperties.getKeywords());
			System.out.println("Subject: " + coreProperties.getSubject());
			System.out.println("Title: " + coreProperties.getTitle());
			System.out.println("Creation Date: " + coreProperties.getCreated());
			System.out.println("Last Modified Date: " + coreProperties.getModified());
//			System.out.println("Author: " + pdDocumentInformation.getAuthor());
			System.out.println();
			

			ExtendedProperties extendedProperties = 
					xwpfWordExtractor.getMetadataTextExtractor().getExtendedProperties();
			System.out.println("Number of Pages: " + extendedProperties.getPages());
			System.out.println("Number of Words: " + extendedProperties.getWords());
			System.out.println("Number of Characters: " + extendedProperties.getCharacters());
			System.out.println("Application Version: " + extendedProperties.getAppVersion());
			System.out.println("Company: " + extendedProperties.getCompany());

			xwpfWordExtractor.close();
		} catch (IOException e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}

}
