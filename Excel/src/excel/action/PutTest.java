package excel.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PutTest {
	public static void main(String[] args) throws IOException {
		// 임시 DTO?
		Map<String, Object> map = null;
		// 가상 DB조회 후 목록을 담을 List객체
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ArrayList<String> columnList = new ArrayList<String>();
		
		// DB 조회 후 데이터를 담았다는 가상의 데이터
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("seq", i + 1);
			map.put("title", "제목" + i);
			map.put("content", "내용" + i);
			list.add(map);
		}
		
		// Map의 Key값을 담기위함
		if (list != null && list.size() > 0) {
			// List의 첫번재 데이터의 Key값만 알면 되므로
			Map<String, Object> m = list.get(0);
			// Map의 Key값을 columnList 객체에 추가
			for (String k : m.keySet()) {
				columnList.add(k);
			}
		}
		
		// 1. workbook 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 2. sheet 생성
		XSSFSheet sheet = workbook.createSheet("시트명");
		// 엑셀의 행
		XSSFRow row = null;
		// 엑셀의 셀
		XSSFCell cell = null;
		// 임의의 DB 데이터 조회
		if (list != null && list.size() > 0) {
			int i = 0;
			for (Map<String, Object> mapObject : list) {
				// 시트에 하나의 행을 생성
				row = sheet.createRow((short)i);
				i++;
				if (columnList != null && columnList.size() > 0) {
					for (int j = 0; j < columnList.size(); j++) {
						// row에 컬럼을 생성
						cell = row.createCell((short)j);
						// map에 담긴 데이터를 가져와 cell에 추가
						cell.setCellValue(String.valueOf(mapObject.get(columnList.get(j))));
					}
				}
			}
		}
		FileOutputStream fos = new FileOutputStream("C:/Users/fivin/Desktop/엑셀파일 다운.xlsx");
		// 파일 쓰기
		workbook.write(fos);
		fos.close();
		System.out.println("성공성공");
	}
}















