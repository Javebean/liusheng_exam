package com.liusheng.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class AnalyzeExcel {
	static Logger log = Logger.getLogger(AnalyzeExcel.class);
	public static List<List<String>> analyzeExcel(int sheetAt, String filepath) {
		List<List<String>> result = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
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
						break;
					case Cell.CELL_TYPE_NUMERIC:
						break;
					case Cell.CELL_TYPE_STRING:
						rowData.add(cell.getStringCellValue());
						break;
					}
				}
				result.add(rowData);
			}

			/* get picture in excel */
			/*List<HSSFPictureData> pictures = workbook.getAllPictures();

			for (HSSFPictureData p : pictures) {
				String ext = p.suggestFileExtension();
				byte[] data = p.getData();
				log.info("excel中的图片的格式为：" + ext);
				FileOutputStream out = new FileOutputStream("e:\\excel.jpg");
				out.write(data);
				out.close();
			}*/
			processImages(workbook);

			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static void processImages(HSSFWorkbook workbook) {
		EscherAggregate drawingAggregate = null;
		HSSFSheet sheet = null;
		List<EscherRecord> recordList = null;
		Iterator<EscherRecord> recordIter = null;
		int numSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numSheets; i++) {
			log.info("处理sheet: " + (i + 1));
			sheet = workbook.getSheetAt(i);
			drawingAggregate = sheet.getDrawingEscherAggregate();
			if (drawingAggregate != null) {
				recordList = drawingAggregate.getEscherRecords();
				recordIter = recordList.iterator();
				while (recordIter.hasNext()) {
					iterateRecords(recordIter.next(), 1);
				}
			}
		}
	}

	private static void iterateRecords(EscherRecord escherRecord, int level) {
		List<EscherRecord> recordList = null;
		Iterator<EscherRecord> recordIter = null;
		EscherRecord childRecord = null;
		recordList = escherRecord.getChildRecords();
		recordIter = recordList.iterator();
		while (recordIter.hasNext()) {
			childRecord = recordIter.next();
			if (childRecord instanceof EscherClientAnchorRecord) {

				printAnchorDetails((EscherClientAnchorRecord) childRecord);
			}
			if (childRecord.getChildRecords().size() > 0) {
				iterateRecords(childRecord, ++level);
			}
		}
	}

	private static void printAnchorDetails(EscherClientAnchorRecord anchorRecord) {
	        log.info("图片的左上角在 列： " +anchorRecord.getCol1() +"，行："+anchorRecord.getRow1()+"----"
	                +"坐标为： (" +anchorRecord.getDx1()+" , "+anchorRecord.getDy1()+")");
	        
	        
	        log.info("图片的右上角在 列： " +anchorRecord.getCol2() +"，行："+anchorRecord.getRow2()+"----"
	                +"坐标为： (" +anchorRecord.getDx2()+" , "+anchorRecord.getDy2()+")");
	    }
}
