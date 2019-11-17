package comment.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	public CommentDAO() {
	}
	
	// ref 마지막값 구하는 메서드
	public int getMaxRef(int boardId) {
		try {
			String sql = "SELECT MAX(ref) FROM board_reply WHERE boardId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);
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
	
	// 댓글 등록 관련 메서드
	public void comment(CommentDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			int ref = getMaxRef(dto.getBoardId());
			String sql = 
				"INSERT INTO board_reply VALUES (seq_board_reply.nextval, ?, ?, SYSDATE, ?, 'N', ?, 0, 0)";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, dto.getBoardId());
			ps.setString(2, dto.getUserId());
			ps.setString(3, dto.getContent());
			ps.setInt(4, ref + 1);
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
	
	// 댓글 목록 관련 메서드
	public ArrayList<CommentDTO> commentList(int boardId_c) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT * FROM (SELECT * FROM board_reply "
					+ "WHERE boardId = ? and deleteYN = 'N' ORDER BY ref DESC, position ASC)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId_c);
			rs = ps.executeQuery();
			
			ArrayList<CommentDTO> arr = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				int replyId = 0;
				int boardId = rs.getInt("boardId");
				String userId = rs.getString("userId");
				Date createDate = rs.getDate("createDate");
				String content = rs.getString("content");
				char deleteYN = rs.getString("deleteYN").charAt(0);
				int ref = rs.getInt("ref");
				int step = rs.getInt("step");
				int position = rs.getInt("position");
				
				CommentDTO dto = 
					new CommentDTO(replyId, boardId, userId, createDate, content, deleteYN, ref, step, position);
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
	
	// 대댓글 위치정렬 관련 메서드
	public void setPosition(int boardId, int ref, int position) {
		try {
			String sql = "UPDATE board_reply SET position = position + 1 WHERE boardId = ? and ref = ? and position > ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);
			ps.setInt(2, ref);
			ps.setInt(3, position);
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (Exception e2) {}
		}
	}
	
	// 대댓글 등록 관련 메서드
	public int reply(CommentDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			setPosition(dto.getBoardId(), dto.getRef(), dto.getPosition());
			String sql = 
				"INSERT INTO board_reply VALUES (seq_board_reply.nextval, ?, ?, SYSDATE, ?, 'N', ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getBoardId());
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
}




















