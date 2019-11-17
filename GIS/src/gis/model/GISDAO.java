package gis.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import gis.model.GISDTO;

public class GISDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	// 마커 정보 목록 관련 메서드
	public ArrayList<GISDTO> list(int cPage, int listSize) {
		try {
			conn = dbcp.Dbcp.getConn();
			int startNumber = (cPage - 1) * listSize + 1;
			int endNumer = cPage * listSize;
			String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM "
				+ "(SELECT * FROM gis ORDER BY idx DESC) a) b "
				+ "WHERE rnum >= ? and rnum <= ?";
			ps = conn.prepareStatement(sql)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ;
			ps.setInt(1, startNumber);
			ps.setInt(2, endNumer);
			rs = ps.executeQuery();
			
			ArrayList<GISDTO> arr = new ArrayList<GISDTO>();
			
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String xCoord = rs.getString("xCoord");
				String yCoord = rs.getString("yCoord");
				String address = rs.getString("address");
				String uName = rs.getString("uName");
				String uGroup = rs.getString("uGroup");
				
				GISDTO dto = new GISDTO(idx, xCoord, yCoord, address, uName, uGroup);
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
	
	// 총 마커 수 관련 메서드
	public int getTotalCount() {
		try {
			conn = dbcp.Dbcp.getConn();
			
			String sql = "SELECT COUNT(*) FROM gis";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
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
	
	// 검색된 마커 정보 목록 관련 메서드
	public ArrayList<GISDTO> listSearch(int cPage, int listSize, String search_t) {
		try {
			conn = dbcp.Dbcp.getConn();
			int startNumber = (cPage - 1) * listSize + 1;
			int endNumer = cPage * listSize;
			String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM "
				+ "(SELECT * FROM gis WHERE uName LIKE '%" + search_t + "%'"	// LIKE 테스트 필요
				+ " ORDER BY idx DESC) a) b WHERE rnum >= ? and rnum <= ?";
			ps = conn.prepareStatement(sql)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ;
			ps.setInt(1, startNumber);
			ps.setInt(2, endNumer);
			rs = ps.executeQuery();
			
			ArrayList<GISDTO> arr = new ArrayList<GISDTO>();
			
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String xCoord = rs.getString("xCoord");
				String yCoord = rs.getString("yCoord");
				String address = rs.getString("address");
				String uName = rs.getString("uName");
				String uGroup = rs.getString("uGroup");
				
				GISDTO dto = new GISDTO(idx, xCoord, yCoord, address, uName, uGroup);
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
	
	// 검색된 총 마커 정보 수 관련 메서드
	public int getTotalCount_search(String search_t) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT COUNT(*) FROM gis WHERE uName LIKE '%" + search_t + "%'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
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
	
	// 마커 정보 등록 관련 메서드
	public int setMarkerInfo(GISDTO dto) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = 
				"INSERT INTO gis (idx, xCoord, yCoord, address, uName, uGroup) VALUES (seq_gis.nextval, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getxCoord());
			ps.setString(2, dto.getyCoord());
			ps.setString(3, dto.getAddress());
			ps.setString(4, dto.getuName());
			ps.setString(5, dto.getuGroup());
			
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
	
	// 마커 정보 가져오기 관련 메서드
	public ArrayList<GISDTO> getMarkerInfo() {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT * FROM gis";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<GISDTO> arr = new ArrayList<GISDTO>();
			
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String xCoord = rs.getString("xCoord");
				String yCoord = rs.getString("yCoord");
				String address = rs.getString("address");
				String uName = rs.getString("uName");
				String uGroup = rs.getString("uGroup");
				
				GISDTO dto = new GISDTO(idx, xCoord, yCoord, address, uName, uGroup);
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
