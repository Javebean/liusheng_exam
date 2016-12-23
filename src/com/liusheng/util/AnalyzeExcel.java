package com.liusheng.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

public class AnalyzeExcel {
	static Logger log = Logger.getLogger(AnalyzeExcel.class);
	@SuppressWarnings("resource")
	public static String analyzeExcel(int sheetAt, String filepath) throws InvalidFormatException {
		
		JSONObject excelResult = new JSONObject();
		List<List<String>> result = null;
		NPOIFSFileSystem fs = null;
		Iterator<Row> rowIterator = null;
		OPCPackage pkg = null;
		try {
			//FileInputStream fileInputStream = new FileInputStream(filepath);
			// Get the workbook instance for XLS file
			//HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

			//////////
			if (filepath.endsWith(".xls")) {
				fs = new NPOIFSFileSystem(new File(filepath));
				// Get the workbook instance for XLS file
				HSSFWorkbook workbook = new HSSFWorkbook(fs.getRoot(),true);
				// Get first sheet from the workbook
				HSSFSheet sheet = workbook.getSheetAt(sheetAt);
				/* HSSFSheet sheet = workbook.getSheet("people"); */
				rowIterator = sheet.iterator();
			} else if (filepath.endsWith(".xlsx")) {
				pkg = OPCPackage.open(new File(filepath));
				XSSFWorkbook workbookx = new XSSFWorkbook(pkg);
				// Get first sheet from the workbook
				XSSFSheet sheetAt2 = workbookx.getSheetAt(sheetAt);
				rowIterator = sheetAt2.iterator();
			} else {
				
				//上传的不是excel类型
				excelResult.put(Constant.RESPONSE_CODE_KEY, RESCODE.UPLOAD_FILE_TYPE_ERROR.getCode());
				excelResult.put(Constant.RESPONSE_MSG_KEY, RESCODE.UPLOAD_FILE_TYPE_ERROR.getMsg());
				return excelResult.toString();
			}

			
			//excel为空
			if(!rowIterator.hasNext()){
				excelResult.put(Constant.RESPONSE_CODE_KEY, RESCODE.EMPTY_EXCEL.getCode());
				excelResult.put(Constant.RESPONSE_MSG_KEY, RESCODE.EMPTY_EXCEL.getMsg());
				return excelResult.toString();
			}
			
			
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
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		excelResult.put(Constant.RESPONSE_CODE_KEY, RESCODE.SUCCESS.getCode());
		excelResult.put(Constant.RESPONSE_DATA_KEY, result);
		return excelResult.toString();
	}

	/*private static void processImages(HSSFWorkbook workbook) {
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
	}*/

	/*private static void iterateRecords(EscherRecord escherRecord, int level) {
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
	}*/

	/*private static void printAnchorDetails(EscherClientAnchorRecord anchorRecord) {
	        log.info("图片的左上角在 列： " +anchorRecord.getCol1() +"，行："+anchorRecord.getRow1()+"----"
	                +"坐标为： (" +anchorRecord.getDx1()+" , "+anchorRecord.getDy1()+")");
	        
	        
	        log.info("图片的右上角在 列： " +anchorRecord.getCol2() +"，行："+anchorRecord.getRow2()+"----"
	                +"坐标为： (" +anchorRecord.getDx2()+" , "+anchorRecord.getDy2()+")");
	    }*/
}
