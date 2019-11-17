package creation.action;

import java.io.File;
import java.io.FileOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeTest {

	public static void main(String[] args) {
		QRCodeWriter q = new QRCodeWriter();

		try {
			String url = "http://192.168.0.217:9090/content.do?boardId=224";

			url = new String(url.getBytes("UTF-8"), "ISO-8859-1");

			BitMatrix bitMatrix = q.encode(url, BarcodeFormat.QR_CODE, 100, 100);

			MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File("C:/Users/fivin/Desktop/qrcode.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
