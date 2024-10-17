package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	
	public static String excelTestData() throws IOException {
	String filePath = System.getProperty("user.dir") + "/TestDataURL.xlsx";
	FileInputStream fis = new FileInputStream(filePath);
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet("Sheet1");
	int rows = sheet.getPhysicalNumberOfRows();
	int cols = sheet.getRow(0).getPhysicalNumberOfCells();
	String url = sheet.getRow(0).getCell(1).getStringCellValue(); 
	
	workbook.close();
	
	return url;
	
	}
	

}
