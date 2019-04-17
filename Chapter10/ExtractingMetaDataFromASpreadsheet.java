package com.packt.javanlp.cookbook.chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExtractingMetaDataFromASpreadsheet {

	public static void main(String[] args) {
		// Extracting metadata from a spreadsheet
		try (FileInputStream fileInputStream = new FileInputStream("Temperatures.xlsx")) {
			Workbook workBook = new XSSFWorkbook(fileInputStream);

			XSSFWorkbook xssfWorkbook = (XSSFWorkbook) workBook;
			POIXMLProperties poiXMLProperties = xssfWorkbook.getProperties();
			CoreProperties coreProperties = poiXMLProperties.getCoreProperties();
			
			System.out.println("Title: " + coreProperties.getTitle());
			System.out.println("Key Words: " + coreProperties.getKeywords());
			System.out.println("Status: " + coreProperties.getContentStatus());
			System.out.println("Category: " + coreProperties.getCategory());
			System.out.println("Subject: " + coreProperties.getSubject());
			System.out.println("Creator: " + coreProperties.getCreator());
			System.out.println("Last Modified: " + coreProperties.getModified());

			workBook.close();
		} catch (FileNotFoundException e) {
			// Handle exceptions
			e.printStackTrace();
		} catch (IOException e) {
			// Handle exceptions
			e.printStackTrace();
		}
	}

}
