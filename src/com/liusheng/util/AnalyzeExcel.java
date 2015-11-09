package com.liusheng.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class AnalyzeExcel {
	public static List<List<String>> analyzeExcel(int sheetAt) {
		List<List<String>> result = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(
					"E:\\netexam.xls");
			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(sheetAt);
			/* HSSFSheet sheet = workbook.getSheet("people"); */

			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			/* HSSFRow row1 = sheet.getRow(2); */

			result = new ArrayList<List<String>>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				List<String> rowData = new ArrayList<String>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t");
						rowData.add(cell.getStringCellValue());
						break;
					}
				}
				System.out.println();
				result.add(rowData);
			}
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
