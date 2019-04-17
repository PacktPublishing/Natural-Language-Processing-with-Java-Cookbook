package com.packt.javanlp.cookbook.chapter11;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

public class ExtractingTextFromAPDFDocument {

	public static void main(String[] args) {
		// Extracting text from a PDF document
		try {
			File file = new File("PDFFile.pdf");
			PDDocument pdDocument = PDDocument.load(file);
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			String documentText = pdfTextStripper.getText(pdDocument);
			System.out.println(documentText);

//			System.out.println("Start Article: " + pdfStripper.getArticleStart());
//			System.out.println("End Article:" + pdfStripper.getArticleEnd());
//			System.out.println("Start Page:" + pdfStripper.getPageStart());
//			System.out.println("End Page:" + pdfStripper.getEndPage());
//			BufferedInputStream br = new BufferedInputStream(pdfStripper.getCurrentPage().getContents());
//			String str = br.readAllBytes().toString();
//			System.out.println("Current Page:" + str);
//			System.out.println();

			PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);

			// Document images
			System.out.println("Images");
			List<RenderedImage> renderedImages = getImagesFromPDF(pdDocument);
			for (int i = 0; i < renderedImages.size(); i++) {
				RenderedImage renderedImage = renderedImages.get(i);
				String imageFileName = "image" + i + ".png";
				System.out.println(imageFileName);
				ImageIO.write(renderedImage, "PNG", new File(imageFileName));
			}
			System.out.println();

			// Renders the entire page as an image
			BufferedImage bufferedImage = pdfRenderer.renderImage(0);
			System.out.println("Page Image.png");
			ImageIO.write(bufferedImage, "PNG", new File("Page Image.png"));
			System.out.println();

//			System.out.println("Pages");
//			List<PDPage> list = new ArrayList<>();
//			for (PDPage page : document.getPages()) {
//				System.out.println(page);
////				System.out.println(page.hasContents());
////				COSDictionary cosDictionary= page.getCOSObject();
////				System.out.println(cosDictionary.keySet());
////				PDResources resources = page.getResources();
////
////				System.out.println();
////				System.out.println();
//			}
//			System.out.println();

//			System.out.println("Document Outline");
//			PDDocumentCatalog catalog = document.getDocumentCatalog();
//			PDDocumentOutline pdDocumentOutline = catalog.getDocumentOutline();
//			System.out.println(pdDocumentOutline);

			// https://www.tutorialspoint.com/pdfbox/pdfbox_document_properties.htm
			// https://www.tutorialspoint.com/pdfbox/pdfbox_creating_a_pdf_document.htm

			pdDocument.close();
		} catch (IOException ex) {
			// Handle exceptions
			ex.printStackTrace();
		}
	}

	// Methods adapted from
	// https://stackoverflow.com/questions/8705163/extract-images-from-pdf-using-pdfbox

	public static List<RenderedImage> getImagesFromPDF(PDDocument pdDocument) {
		List<RenderedImage> renderedImages = new ArrayList<>();
		try {
			for (PDPage pdPage : pdDocument.getPages()) {
				renderedImages.addAll(getImagesFromResources(pdPage.getResources()));
			}
		} catch (IOException ex) {
			// Handle exceptions
			ex.printStackTrace();
		}

		return renderedImages;
	}

	private static List<RenderedImage> getImagesFromResources(PDResources pdResources) throws IOException {
		List<RenderedImage> renderedImages = new ArrayList<>();

		for (COSName cosNames : pdResources.getXObjectNames()) {
			PDXObject pdxObject = pdResources.getXObject(cosNames);

			if (pdxObject instanceof PDFormXObject) {
				renderedImages.addAll(getImagesFromResources(((PDFormXObject) pdxObject).getResources()));
			} else if (pdxObject instanceof PDImageXObject) {
				renderedImages.add(((PDImageXObject) pdxObject).getImage());
			}
		}

		return renderedImages;
	}

}
