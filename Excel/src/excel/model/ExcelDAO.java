package excel.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	// 엑셀 파일 업로드 관련 메서드
	public int insertExcel(String filePath) {
		try {
			conn = dbcp.Dbcp.getConn();
			
			File filename = new File(filePath);
			FileInputStream fis = new FileInputStream(filename);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			int rowIndex = 0;
			
			// 시트 수 (첫번재만 존재하므로 0을 준다)
			// 만약 각 시트를 읽기위해서는 for문 사용
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();	// 행의 수
			
			for (rowIndex = 1; rowIndex < rows; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);	// 행을 읽는다
				
				if (row != null) {
					int studentNum = (int) row.getCell((short)1).getNumericCellValue();
					
					String name = row.getCell((short)2).getStringCellValue();
					
					// poi 날짜 가져오기 - 생년월일
					XSSFCell cell_birth = row.getCell((short)3);
					cell_birth.setCellType(XSSFCell.CELL_TYPE_STRING);
					String birth = cell_birth.getStringCellValue();
					
					// major FK
					String major = row.getCell((short)4).getStringCellValue();
					int majorCode = getMajorCode(major);
					
					// poi 날짜 가져오기 - 입학일
					XSSFCell cell_admission = row.getCell((short)5);
					cell_admission.setCellType(XSSFCell.CELL_TYPE_STRING);
					String admission = cell_admission.getStringCellValue();
					
					// term FK
					String term = row.getCell((short)6).getStringCellValue();
					int termCode = getTermCode(term);
					
					String addr = row.getCell((short)7).getStringCellValue();
					
					// professor FK
					String professor = row.getCell((short)8).getStringCellValue();
					int proCode = getProfessorCode(professor);
					
					String beIn = row.getCell((short)9).getStringCellValue();
					
					String sql = "INSERT INTO student (studentNum, name, birth, major, admission,"
							+ " term, addr, professor, beIn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1, studentNum);
					ps.setString(2, name);
					ps.setString(3, birth);
					ps.setInt(4, majorCode);
					ps.setString(5, admission);
					ps.setInt(6, termCode);
					ps.setString(7, addr);
					ps.setInt(8, proCode);
					ps.setString(9, beIn);
					
					ps.executeUpdate();
					ps.clearParameters();
					
					// 학사 경고
					int probation = (int) row.getCell((short)10).getNumericCellValue();
					if (probation > 0) {
						sql = "INSERT INTO probation (proNum, stuNum, term) VALUES (seq_probation.nextval, ?, ?)";
						ps = conn.prepareStatement(sql);
						ps.setInt(1, studentNum);
						ps.setInt(2, probation);
						
						ps.executeUpdate();
						ps.clearParameters();
					}
				}
			}
			System.out.println("업로드 완료");
			return 1;
		} catch (SQLIntegrityConstraintViolationException e1) {
			System.out.println("무결성");
			return -2;
		} catch (IllegalStateException e2) {
			System.out.println("다른형식");
			return -3;
		} catch (Exception e3) {
			return ERROR;
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 학과 -> 학과코드
	public int getMajorCode(String major) {
		try {
			String sql = "SELECT code FROM major WHERE major = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, major);
			rs = ps.executeQuery();
			int code = 0;
			if (rs.next()) {
				code = rs.getInt(1);
			}
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	// 학기 -> 학기코드
	public int getTermCode(String term) {
		try {
			String sql = "SELECT code FROM term WHERE term = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, term);
			rs = ps.executeQuery();
			int code = 0;
			if (rs.next()) {
				code = rs.getInt(1);
			}
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}	
	
	// 교수 -> 교수코드
	public int getProfessorCode(String professor) {
		try {
			String sql = "SELECT code FROM professor WHERE name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, professor);
			rs = ps.executeQuery();
			int code = 0;
			if (rs.next()) {
				code = rs.getInt(1);
			}
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	// 엑셀파일 다운로드 관련 메서드
	public void putExcel() throws IOException {
		ArrayList<ExcelDTO> arr = getData();	// DB 데이더 가져오기
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow row = null;		// 엑셀의 행
		XSSFCell cell = null;	// 엑셀의 셀
		
		if (arr != null && arr.size() > 0) {
			// 행 하나 생성
			row = sheet.createRow((short)0);
			
			// 셀 생성
			cell = row.createCell((short)0);
			cell.setCellValue("학번");
			cell = row.createCell((short)1);
			cell.setCellValue("이름");
			cell = row.createCell((short)2);
			cell.setCellValue("학과");
			cell = row.createCell((short)3);
			cell.setCellValue("지도교수");
			cell = row.createCell((short)4);
			cell.setCellValue("학기");
			cell = row.createCell((short)5);
			cell.setCellValue("주소");
			
			for (int i = 0; i < arr.size(); i++) {
				// 행 개수만큼 생성
				row = sheet.createRow((short) (i + 1));
				
				// row에 컬럼을 생성하고 DTO에 담긴 데이터를 가져와 cell에 추가
				cell = row.createCell((short)0);
				cell.setCellValue(arr.get(i).getStudentNum());
				cell = row.createCell((short)1);
				cell.setCellValue(arr.get(i).getName());
				cell = row.createCell((short)2);
				cell.setCellValue(getMajor(arr.get(i).getMajorCode()));
				cell = row.createCell((short)3);
				cell.setCellValue(getProfessor(arr.get(i).getProCode()));
				cell = row.createCell((short)4);
				cell.setCellValue(getTerm(arr.get(i).getTermCode()));
				cell = row.createCell((short)5);
				cell.setCellValue(arr.get(i).getAddr());
			}
		}
		// 엑셀파일명에 현재 시간 추가
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh시 mm분 ss초");
		
		FileOutputStream fos = new FileOutputStream("C:/Users/fivin/Desktop/엑셀파일 다운_" + sdf.format(date) + ".xlsx");
		// 파일 쓰기
		workbook.write(fos);
		fos.close();
		System.out.println("파일 생성");
	}
	
	// DB 데이터 가져오기 관련 메서드
	public ArrayList<ExcelDTO> getData() {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT * FROM student ORDER BY studentNum ASC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<ExcelDTO> arr = new ArrayList<ExcelDTO>();
			
			while (rs.next()) {
				int studentNum = rs.getInt("studentNum");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				int majorCode = rs.getInt("major");
				String admission = rs.getString("admission");
				int termCode = rs.getInt("term");
				String addr = rs.getString("addr");
				int proCode = rs.getInt("professor");
				String beIn = rs.getString("beIn");
				//ps.clearParameters();
				
				ExcelDTO dto = new ExcelDTO(studentNum, name, birth, majorCode, admission, 
						termCode, addr, proCode, beIn, 0);
				arr.add(dto);
			}
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 엑셀파일 다운로드(학사경고) 관련 메서드
	public void putExcel_probation() throws IOException {
		ArrayList<ExcelDTO> arr = getData_probation();	// DB 데이더 가져오기
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow row = null;		// 엑셀의 행
		XSSFCell cell = null;	// 엑셀의 셀
		
		if (arr != null && arr.size() > 0) {
			// 행 하나 생성
			row = sheet.createRow((short)0);
			
			// 셀 생성
			cell = row.createCell((short)0);
			cell.setCellValue("학번");
			cell = row.createCell((short)1);
			cell.setCellValue("이름");
			cell = row.createCell((short)2);
			cell.setCellValue("학과");
			cell = row.createCell((short)3);
			cell.setCellValue("학기");
			cell = row.createCell((short)4);
			cell.setCellValue("경고학기");
			
			for (int i = 0; i < arr.size(); i++) {
				// 행 개수만큼 생성
				row = sheet.createRow((short) (i + 1));
				
				// row에 컬럼을 생성하고 DTO에 담긴 데이터를 가져와 cell에 추가
				cell = row.createCell((short)0);
				cell.setCellValue(arr.get(i).getStudentNum());
				cell = row.createCell((short)1);
				cell.setCellValue(arr.get(i).getName());
				cell = row.createCell((short)2);
				cell.setCellValue(getMajor(arr.get(i).getMajorCode()));
				cell = row.createCell((short)3);
				cell.setCellValue(getTerm(arr.get(i).getTermCode()));
				cell = row.createCell((short)4);
				cell.setCellValue(arr.get(i).getProbation());
			}
		}
		// 엑셀파일명에 현재 시간 추가
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh시 mm분 ss초");
		
		FileOutputStream fos = new FileOutputStream(
				"C:/Users/fivin/Desktop/엑셀파일 다운(학사경고)_" + sdf.format(date) + ".xlsx");
		// 파일 쓰기
		workbook.write(fos);
		fos.close();
		System.out.println("파일 생성(학고)");
	}
	
	// DB 데이터(학사경고) 가져오기 관련 메서드
	public ArrayList<ExcelDTO> getData_probation() {
		try {
			ArrayList<ExcelDTO> arr = new ArrayList<ExcelDTO>();
			
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT * FROM probation ORDER BY stuNum ASC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int studentNum = rs.getInt("stuNum");
				int term = rs.getInt("term");
				
				ExcelDTO dto = new ExcelDTO();
				dto.setStudentNum(studentNum);
				dto.setProbation(term);
				
				arr.add(dto);
			}
			
			// 학사경고 있는 사람만 관련 정보만 추출
			if (arr != null && arr.size() > 0) {
				for (ExcelDTO dto : arr) {
					sql = "SELECT name, major, term FROM student WHERE studentNum = ?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, dto.getStudentNum());
					rs = ps.executeQuery();
					
					while (rs.next()) {
						String name = rs.getString("name");
						int majorCode = rs.getInt("major");
						int termCode = rs.getInt("term");
						
						dto.setName(name);
						dto.setMajorCode(majorCode);
						dto.setTermCode(termCode);
					}
				}
			}
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 학과코드 -> 학과명
	public String getMajor(int majorCode) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT major FROM major WHERE code = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, majorCode);
			rs = ps.executeQuery();
			String major = null;
			if (rs.next()) {
				major = rs.getString(1);
			}
			return major;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 학기코드 -> 학기 
	public String getTerm(int termCode) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT term FROM term WHERE code = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, termCode);
			rs = ps.executeQuery();
			String term = null;
			if (rs.next()) {
				term = rs.getString(1);
			}
			return term;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}	
	
	// 교수코드 -> 교수명
	public String getProfessor(int proCode) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT name FROM professor WHERE code = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, proCode);
			rs = ps.executeQuery();
			String name = null;
			if (rs.next()) {
				name = rs.getString(1);
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 게시물 목록 관련 메서드
	public ArrayList<ExcelDTO> list(int cPage, int listSize) {
		try {
			conn = dbcp.Dbcp.getConn();
			int startNumber = (cPage - 1) * listSize + 1;
			int endNumer = cPage * listSize;
			String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM "
				+ "(SELECT * FROM student ORDER BY studentNum ASC) a) b "
				+ "WHERE rnum >= ? and rnum <= ?";
			ps = conn.prepareStatement(sql)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ;
			ps.setInt(1, startNumber);
			ps.setInt(2, endNumer);
			rs = ps.executeQuery();
			
			ArrayList<ExcelDTO> arr = new ArrayList<ExcelDTO>();
			
			while (rs.next()) {
				int studentNum = rs.getInt("studentNum");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				int majorCode = rs.getInt("major");
				String admission = rs.getString("admission");
				int termCode = rs.getInt("term");
				String addr = rs.getString("addr");
				int proCode = rs.getInt("professor");
				String beIn = rs.getString("beIn");
				
				ExcelDTO dto = new ExcelDTO(studentNum, name, birth, majorCode, admission, termCode, addr, proCode, beIn, 0);
				arr.add(dto);
			}
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
		
	// 총 게시물 수 관련 메서드
	public int getTotalCount() {
		try {
			conn = dbcp.Dbcp.getConn();
			
			String sql = "SELECT COUNT(*) FROM student";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);		// 첫번째 인덱스의 값 반환
			return count == 0 ? 1 : count;	// ArithmeticException 발생대비 0대신 1반환
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
}
