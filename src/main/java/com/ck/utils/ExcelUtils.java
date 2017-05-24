package com.ck.utils;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

        		private static XSSFSheet ExcelWSheet;

        		private static XSSFWorkbook ExcelWBook;

        		private static XSSFCell Cell;

        		private static XSSFRow Row;

        		//this value is setted after setExcelFile
        		private static int rowLength=0;

        		private static int colLength=0;

        		private static HashMap<String, String>[][] map = null;

        		private static HashMap<String,Integer> header = null;

    		//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
				public static HashMap<String, String>[][] getMap(){
					return map;
				}

				public static HashMap<String, Integer> getHeader(){
					return header;
				}

    		public static void setExcelFile(String Path,String SheetName) throws Exception {

       			try {

           			// Open the Excel file

					FileInputStream ExcelFile = new FileInputStream(Path);

					// Access the required test data sheet

					ExcelWBook = new XSSFWorkbook(ExcelFile);

					ExcelWSheet = ExcelWBook.getSheet(SheetName);

					rowLength = ExcelWSheet.getLastRowNum()+1;

					colLength = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
					} catch (Exception e){

						throw (e);

					}

			}

			public static Object[][] testData(String Path,String SheetName)throws Exception{
				ArrayList<String> arrkey = new ArrayList<String>();
				header = new HashMap<>();
				setExcelFile(Path,SheetName);
				map = new HashMap[rowLength - 1][1];
				// init HashMap Array
				if (rowLength > 1) {
					for (int i = 0; i < rowLength - 1; i++) {
						map[i][0] = new HashMap();
					}
				}
				//get every row data and save headr the data structure is map
				for (int c = 0; c < colLength; c++) {
					String cellvalue = getCellData(0, c);
					arrkey.add(cellvalue);
					header.put(cellvalue,c);
				}
				// loop and get all cell data
				for (int r = 1; r < rowLength; r++) {
					for (int c = 0; c < colLength; c++) {
						String cellvalue =getCellData(r,c);
						map[r - 1][0].put(arrkey.get(c), cellvalue);
					}
				}
				return map;
			}

    		//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    	    private static String getCellData(int RowNum, int ColNum) throws Exception{
       			try{

          			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

          			String CellData = CellUtil.getValue(Cell);

          			return CellData;

          			}catch (Exception e){

						return"";
          			}
		    }

    		//This method is to write in the Excel cell, Row num and Col num are the parameters

    		public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

       			try{

          			Row  = ExcelWSheet.getRow(RowNum);

					Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

					if (Cell == null) {

						Cell = Row.createCell(ColNum);

						Cell.setCellValue(Result);

						} else {

							Cell.setCellValue(Result);

						}

          // Constant variables Test Data path and Test Data file name

						}catch(Exception e){

							throw (e);

					}

    		}
    		public static void writeToExcel() throws Exception {
				FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();
			}
    }