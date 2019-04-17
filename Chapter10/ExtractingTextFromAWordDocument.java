package com.packt.javanlp.cookbook.chapter11;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

public class ExtractingTextFromAWordDocument {

	public static void main(String[] args) {
		// Extracting text from a Word document 
		try {
			String fileName = "PDFFile.docx";
			XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(fileName));

			XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xwpfDocument);
			System.out.println(xwpfWordExtractor.getText());
			System.out.println();

			List<XWPFParagraph> xwpfParagraphs = xwpfDocument.getParagraphs();
			for (XWPFParagraph paragraph : xwpfParagraphs) {
				System.out.println("[" + paragraph.getText() + "]");
			}
			System.out.println();

			List<XWPFPictureData> images = xwpfDocument.getAllPictures();
			for (int i = 0; i < images.size(); i++) {
				System.out.println("Saving: " + images.get(i));
				byte[] byteArray = images.get(i).getData();
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(byteArray));
				ImageIO.write(bufferedImage, "PNG", new File(images.get(i).getFileName()));
			}

			xwpfWordExtractor.close();			
		} catch (IOException e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}

}
