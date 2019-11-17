package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	// 회원 가입 관련 메서드
	public int memberJoin (MemberDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			
			String sql = "INSERT INTO member VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getName());
			
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
	
	// 로그인 관련 메서드
	public int login(String userId, String userPwd) {
		try {
			conn = dbcp.Dbcp.getConn();
			
			String sql = "SELECT pwd FROM member WHERE userId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String pwd = rs.getString("pwd");
				if (pwd.equals(userPwd)) {
					return 1;
				} else {
					return ERROR;
				}
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
}
