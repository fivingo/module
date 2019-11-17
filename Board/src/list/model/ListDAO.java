package list.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ListDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	// ref 마지막값 구하는 메서드
	public int getMaxRef() {
		try {
			String sql = "SELECT MAX(ref) FROM board";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			return max;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {}
		}
	}
	
	// 게시물 등록 관련 메서드
	public int write(ListDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			int ref = getMaxRef();
			String sql = 
				"INSERT INTO board VALUES (seq_board.nextval, ?, ?, SYSDATE, ?, 'N', 0, ?, 0, 0)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getUserId());
			ps.setString(3, dto.getContent());
			ps.setInt(4, ref + 1);
			
			int result = ps.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 게시물 목록 관련 메서드
	public ArrayList<ListDTO> list(int cPage, int listSize) {
		try {
			conn = dbcp.Dbcp.getConn();
			int startNumber = (cPage - 1) * listSize + 1;
			int endNumer = cPage * listSize;
//			String sql = "SELECT * FROM board ORDER BY boardId DESC";
			String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM "
				+ "(SELECT * FROM board WHERE deleteYN = 'N' ORDER BY ref DESC, position ASC) a) b "
				+ "WHERE rnum >= ? and rnum <= ?";
			ps = conn.prepareStatement(sql)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ;
			ps.setInt(1, startNumber);
			ps.setInt(2, endNumer);
			rs = ps.executeQuery();
			
			ArrayList<ListDTO> arr = new ArrayList<ListDTO>();
			
			while (rs.next()) {
				int boardId = rs.getInt("boardId");
				String title = rs.getString("title");
				String userId = rs.getString("userId");
				Date createDate = rs.getDate("createDate");
				String content = null;
				char deleteYN = rs.getString("deleteYN").charAt(0);
				int viewCount = rs.getInt("viewCount");
				int ref = rs.getInt("ref");
				int step = rs.getInt("step");
				int position = rs.getInt("position");
				
				ListDTO dto = 
						new ListDTO(boardId, title, userId, createDate, content, deleteYN, viewCount, ref, step, position);
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
			
			String sql = "SELECT COUNT(*) FROM board WHERE deleteYN = 'N'";
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
	
	// 게시물 상세보기 관련 메서드
	public ListDTO content(int boardId) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT * FROM board WHERE boardId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);
			rs = ps.executeQuery();
			
			ListDTO dto = null;
			while (rs.next()) {
				String title = rs.getString("title");
				String userId = rs.getString("userId");
				Date createDate = rs.getDate("createDate");
				String content = rs.getString("content");
				char deleteYN = rs.getString("deleteYN").charAt(0);
				int viewCount = rs.getInt("viewCount");
				int ref = rs.getInt("ref");
				int step = rs.getInt("step");
				int position = rs.getInt("position");
				
				dto = new ListDTO(boardId, title, userId, createDate, content, 
						deleteYN, viewCount, ref, step, position);
			}
			return dto;
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
	
	// 조회수 관련 메서드
	public void viewCount(int boardId, int viewCount) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "UPDATE board SET viewCount = ? WHERE boardId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewCount + 1);
			ps.setInt(2, boardId);
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
	
	// 게시물 수정 관련 메서드
	public int update(ListDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "UPDATE board SET title = ?, content = ? WHERE boardId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getBoardId());
			int result = ps.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 게시물 삭제 관련 메서드
	public int delete(int boardId) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "UPDATE board SET deleteYN = 'Y' WHERE boardId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);
			int result = ps.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 답글 위치정렬 관련 메서드
	public void setPosition(int ref, int position) {
		try {
			String sql = "UPDATE board SET position = position + 1 WHERE ref = ? and position >= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ref);
			ps.setInt(2, position);
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (Exception e2) {}
		}
	}
	
	// 게시물 답글 등록 관련 메서드
	public int reWrite(ListDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			setPosition(dto.getRef(), dto.getPosition() + 1);
			String sql =
				"INSERT INTO board VALUES(seq_board.nextval, ?, ?, SYSDATE, ?, 'N', 0, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getUserId());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getRef());
			ps.setInt(5, dto.getStep() + 1);
			ps.setInt(6, dto.getPosition() + 1);
			int count = ps.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 답글 유무 확인 관련 메서드
	public int checkReply(ListDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT COUNT(*) FROM board WHERE ref = ? and step = ? and position = ? and deleteYN = 'N'";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getRef());
			ps.setInt(2, dto.getStep() + 1);
			ps.setInt(3, dto.getPosition() + 1);
			rs = ps.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	
	// 검색된 총 게시물 수 관련 메서드
	public int getTotalCount_search(String search_s, String search_t) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT COUNT(*) FROM board WHERE " + search_s + " LIKE '%" + search_t + "%'";
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
	
	// 검색된 게시물 목록 관련 메서드
	public ArrayList<ListDTO> listSearch(int cPage, int listSize, String search_s, String search_t) {
		try {
			conn = dbcp.Dbcp.getConn();
			int startNumber = (cPage - 1) * listSize + 1;
			int endNumer = cPage * listSize;
			String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM "
				+ "(SELECT * FROM board WHERE deleteYN = 'N' AND " + search_s + " LIKE '%" + search_t + "%'"
				+ " ORDER BY ref DESC, position ASC) a) b WHERE rnum >= ? and rnum <= ?";
			ps = conn.prepareStatement(sql)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ;
			ps.setInt(1, startNumber);
			ps.setInt(2, endNumer);
			rs = ps.executeQuery();
			
			ArrayList<ListDTO> arr = new ArrayList<ListDTO>();
			
			while (rs.next()) {
				int boardId = rs.getInt("boardId");
				String title = rs.getString("title");
				String userId = rs.getString("userId");
				Date createDate = rs.getDate("createDate");
				String content = null;
				char deleteYN = rs.getString("deleteYN").charAt(0);
				int viewCount = rs.getInt("viewCount");
				int ref = rs.getInt("ref");
				int step = rs.getInt("step");
				int position = rs.getInt("position");
				
				ListDTO dto = 
						new ListDTO(boardId, title, userId, createDate, content, deleteYN, viewCount, ref, step, position);
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
}







































