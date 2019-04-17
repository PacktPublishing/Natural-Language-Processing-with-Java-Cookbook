package com.packt.javanlp.cookbook.chapter11;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtractingTextAndMetadataFromAnHTMLPage {

	public static void main(String[] args) {
		// Extracting text and metadata from an HTML page 

		try {
			String htmlText = Jsoup.connect("https://www.w3.org/html/").get().html();
			System.out.println(htmlText.substring(0, 100));
			System.out.println();


			Document document = Jsoup.parse(new File("page.html"), "utf-8");

			String data = document.html();
			System.out.println(data);
			System.out.println();

			System.out.println(document.body());
			System.out.println();
			
			htmlText = document.body().text();
			System.out.println("Body Text: [" + htmlText + "]");

			String title = document.title();
			System.out.printf("Title: %s%n", title);

			String charsetName = document.charset().name();
			System.out.println("Charset: " + charsetName);

			String location = document.location();
			System.out.println("Location: " + location);

			System.out.println();
			Elements links = document.select("a[href]");
			for (Element link : links) {
				System.out.println("Link: " + link.attr("href") + "  Text : " + link.text());
			}

			System.out.println();
			Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			for (Element image : images) {
				System.out.println("Source File Name : " + image.attr("src"));
				System.out.println("Height : " + image.attr("height"));
				System.out.println("Width : " + image.attr("width"));
				System.out.println("Alternate Text : " + image.attr("alt"));
				System.out.println();
			}
			

//          System.out.println("Attributes");            
//          Attributes attributes = Jsoup.connect("https://en.wikipedia.org/wiki/Language").get().attributes();
//          for(Attribute attribute : attributes) {
//          	System.out.printf("Charset: %s%n", attribute);
//          }
			

//			List<TextNode> textNodes = document.textNodes();
//			for(TextNode textNode : textNodes) {
//				System.out.printf("TextNode: %s%n", textNode);
//			}
						
//			Elements elements = document.select("meta[name=description]");
//			Element element = elements.first();
//			if (element != null) {
//				String description = element.attr("content");
//				System.out.println("Description : " + description);
//			}

//          String keywords = document.select("meta[name=keywords]").first().attr("content");
//          System.out.println("Keywords : " + keywords);


		} catch (IOException e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}

}
