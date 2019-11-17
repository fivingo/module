package creation.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public static final int ERROR = -1;
	
	// QR코드 생성 관련 메서드
	public void createQR(String url, int boardId) {
		try {
			QRCodeWriter qrw = new QRCodeWriter();
			
			url = new String(url.getBytes("UTF-8"), "ISO-8859-1");
			
			BitMatrix bitMatrix = qrw.encode(url, BarcodeFormat.QR_CODE, 200, 200);

			MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(
					"C:/Users/fivin/Desktop/Develop/Study/BoardStudy/WebContent/QRcode/QRcode_" + boardId + ".png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// QR코드 정보 DB 입력 관련 메서드
	public void setQRInfo(int boardId, String userId) {
		try {
			conn = dbcp.Dbcp.getConn();
			String sql = "SELECT COUNT(*) FROM t_QR WHERE bod_idx = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);
			rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if (count < 1) {
				sql = "INSERT INTO t_QR (bod_idx, cr_ID, fileName) VALUES (?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, boardId);
				ps.setString(2, userId);
				
				String fileName = "QRcode_" + boardId + ".png";
				ps.setString(3, fileName);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs  != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
}
