package file.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.net.aso.e;

public class FileDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	// 파일 업로드시 boardId 가져오기 관련 메서드
	public int getBoardId(String title, String userId, String content) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT boardId FROM board WHERE title = ? and userId = ? and content = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, userId);
			ps.setString(3, content);
			rs = ps.executeQuery();
			int boardId = 0;
			if (rs.next()) {	// 못가져옴 - 부적합 열 인덱스
				boardId = rs.getInt(1);
			}
			return boardId;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e2) {}
		}
	}
	
	// 파일명 DB저장 관련 메서드
	public void fileUpload(String fileName, String title, String userId, String content) {
		try {
			conn = dbcp.Dbcp.getConn();
			int boardId = getBoardId(title, userId, content);
			String sql = "INSERT INTO files VALUES (seq_files.nextval, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fileName);
			ps.setString(2, Integer.toString(boardId) + fileName.substring(fileName.indexOf("."), fileName.length()));
			ps.setInt(3, boardId);
			
			int result = ps.executeUpdate();
			//System.out.println("파일명저장 " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 파일명 DB 업데이트 관련 메서드
	public void fileUpload_update(String fileName, String title, String userId, String content, int boardId) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "INSERT INTO files VALUES (seq_files.nextval, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fileName);
			ps.setString(2, Integer.toString(boardId) + fileName.substring(fileName.indexOf("."), fileName.length()));
			ps.setInt(3, boardId);
			
			int result = ps.executeUpdate();
			//System.out.println("파일명저장 " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 파일 리스트 관련 메서드
	public ArrayList<FileDTO> fileList(int boardId) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT * FROM files WHERE boardId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);
			rs = ps.executeQuery();
			
			ArrayList<FileDTO> arr = new ArrayList<FileDTO>();
			
			while (rs.next()) {
				int fileId = rs.getInt("fileId");
				String maskName = rs.getString("maskName");
				String originalName = rs.getString("originalName");
				
				FileDTO dto = new FileDTO(fileId, maskName, originalName, boardId);
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
	
	// 게시글 수정시 파일 삭제 관련 메서드
	public void fileDelete(int fileId) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "DELETE FROM files WHERE fileId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fileId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
}



















