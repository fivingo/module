package creation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class UrlDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	// 단축 url 생성 전 확인 관련 메서드
	public int checkUrl(int boardId) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT COUNT(*) FROM t_shortUrl WHERE bod_idx = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);
			rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			return count;
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
	
	// 원본 url로 단축 url 키코드 가져오기 관련 메서드
	public String getShortUrl(String url) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT sh_URL FROM t_shortUrl WHERE or_URL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, url);
			rs = ps.executeQuery();
			rs.next();
			String sh_URL = rs.getString(1);
			return sh_URL;
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
	// 단축 url로 원본 url 가져오기 관련 메서드
	public String getOriginalUrl(String url) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT or_URL FROM t_shortUrl WHERE sh_URL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, url);
			rs = ps.executeQuery();
			rs.next();
			String or_URL = rs.getString(1);
			return or_URL;
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
	
	// 단축 url 키코드 중복 확인 및 생성 관련 메서드
	public String setShortUrl(UrlDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT COUNT(*) FROM t_shortUrl WHERE sh_URL = ?";
			ps = conn.prepareStatement(sql);
			                                                                                                                                                                                    
			String shortUrl = randomStr();
			ps.setString(1, shortUrl);
			rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if (count < 1) {
				sql = "INSERT INTO t_shortUrl (bod_idx, or_URL, sh_URL, cr_ID) VALUES (?, ?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, dto.getBod_idx());
				ps.setString(2, dto.getOr_URL());
				ps.setString(3, shortUrl);
				ps.setString(4, dto.getCr_ID());
				ps.executeUpdate();
			} else {
				/* 새로운 키코드가 이미 있는 경우 처리가 없음 */
				
			}
			return shortUrl;
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
	
	// 무작위 문자열 생성 관련 메서드
	public String randomStr() {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			int rIndex = random.nextInt(3);
			if (rIndex == 0) {
				// a-z
				str = str + (char) (random.nextInt(26) + 97);
			} else if (rIndex == 1) {
				// A-Z
				str = str + (char) (random.nextInt(26) + 65);
			} else if (rIndex == 2) {
				// 0-9
				str = str + (random.nextInt(10));
			}
		}
		return str;
	}
}
