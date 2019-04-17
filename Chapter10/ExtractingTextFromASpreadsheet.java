package com.packt.javanlp.cookbook.chapter11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExtractingTextFromASpreadsheet {

	public static void main(String[] args) {
		// Extracting text from a spreadsheet
		try (FileInputStream fileInputStream = new FileInputStream("Temperatures.xlsx")) {
			Workbook workBook = new XSSFWorkbook(fileInputStream);

			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				Sheet sheet = workBook.getSheetAt(i);
				for (Row row : sheet) {
					System.out.printf("Row: - %2d", row.getRowNum());
					for (Cell cell : row) {
						System.out.printf("\t%3s", cell);
					}
					System.out.println();
				}
				System.out.println();
			}
			System.out.println();
			
			Sheet sheet = workBook.getSheetAt(0);
			Cell cell = sheet.getRow(2).getCell(2);
			System.out.println("Cell: " + cell);
			
			cell = sheet.getRow(0).getCell(0);
			System.out.println("Cell: " + cell);
			System.out.println("Cell: " + cell.getDateCellValue());
			System.out.println("Cell: " + cell.getNumericCellValue());
			System.out.println("Cell: " + cell.getCellType());

			cell = sheet.getRow(11).getCell(0);
			System.out.println("Hyperlink: " + cell);
			System.out.println("Hyperlink: " + cell.getHyperlink().getAddress());
			System.out.println();			

			System.out.println("Number of Sheets: " + workBook.getNumberOfSheets());
			System.out.println("Spreadsheet Version: " + workBook.getSpreadsheetVersion());	
			System.out.println();

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
