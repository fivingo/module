package file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;

public class FileDownload implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardId = req.getParameter("boardId");
		// 파일 업로드된 경로
		String savePath = "C:/Users/fivin/Desktop/Develop/Study/BoardStudy/WebContent/FileUpload";

		// 서버에 실제 저장된 파일명
		String maskFile = req.getParameter("maskFile");
		// 실제 내보낼 파일명
		String oriFile = new String(req.getParameter("oriFile").getBytes("8859_1"), "UTF-8");

		System.out.println(boardId);
		System.out.println(maskFile);
		System.out.println(oriFile);
		
		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";

		try {
			// 파일을 읽어 스트림에 담기
			try {
				file = new File(savePath, maskFile);
				in = new FileInputStream(file);
			} catch (Exception fe) {
				skip = true;
			}

			client = req.getHeader("User-Agent");

			// 파일 다운로드 헤더 지정
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Description", "JSP Generated Data");

			if (!skip) {
				// IE
				if (client.indexOf("MSIE") != -1) {
					resp.setHeader("Content-Disposition", "attachment; filename=" 
							+ new String(oriFile.getBytes("KSC5601"), "ISO8859_1"));
				} else {
					// 한글 파일명 처리
					oriFile = new String(oriFile.getBytes("utf-8"), "iso-8859-1");

					resp.setHeader("Content-Disposition", "attachment; filename=\"" + oriFile + "\"");
					resp.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				}

				resp.setHeader("Content-Length", "" + file.length());

				os = resp.getOutputStream();
				byte b[] = new byte[(int) file.length()];
				int leng = 0;

				while ((leng = in.read(b)) > 0) {
					os.write(b, 0, leng);
				}
			} else {
				resp.setContentType("text/html;charset=UTF-8");
			}
			in.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "content.jsp?boardId=" + boardId;
	}
}
