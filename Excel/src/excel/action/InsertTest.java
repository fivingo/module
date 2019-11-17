package excel.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class InsertTest {
	public static void main(String[] args) throws IOException {

		File filename = new File("C:/Users/fivin/Desktop/엑셀파일.xlsx");
		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int rowIndex = 0;
		int columnIndex = 0;
		// 시트 수 (첫번재만 존재하므로 0을 준다)
		// 만약 각 시트를 읽기위해서는 for문 사용
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();	// 행의 수
		
		for (rowIndex = 1; rowIndex < rows; rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);	// 행을 읽는다
			
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells();	// 셀의 수
				
				for (columnIndex = 1; columnIndex <= cells; columnIndex++) {
					XSSFCell cell = sheet.getRow(rowIndex).getCell((short)columnIndex);	// 셀값 읽기
					
					String value = null;
					if (cell == null) {
						continue;
					} else {
						// 타입별로 내용 읽기
						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_FORMULA:
							value = "FORMULA value=" + cell.getCellFormula();
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							value = "NUMERIC value=" + cell.getNumericCellValue();
							break;
						case XSSFCell.CELL_TYPE_STRING:
							value = "STRING value=" + cell.getStringCellValue();
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							value = " ";
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							value = "BOOLEAN value-" + cell.getBooleanCellValue();
							break;
						case XSSFCell.CELL_TYPE_ERROR:
							value = "ERROR value=" + cell.getErrorCellValue();
							break;
						default:
							value = "UNKNOWN value of type " + cell.getCellType();
						}
						System.out.println("VALUE=" + value);
					}
				}
				System.out.println();
			}
		}
	}
}
