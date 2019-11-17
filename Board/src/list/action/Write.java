package list.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandHandler;
import file.model.FileDAO;
import list.model.ListDAO;
import list.model.ListDTO;

public class Write implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String savePath = "C:/Users/fivin/Desktop/Develop/Study/BoardStudy/WebContent/FileUpload";
		MultipartRequest multiReq = new MultipartRequest(req, savePath, 1024 * 1024 * 100, "UTF-8");
		
		String title = multiReq.getParameter("title");
		String userId = multiReq.getParameter("userId");
		String content = multiReq.getParameter("content");
		
		ListDAO listDao = new ListDAO();
		ListDTO listDto = new ListDTO(0, title, userId, null, content, 'N', 0, 0, 0, 0);
		
		// 게시물 DB 저장
		int result = listDao.write(listDto);
		req.setAttribute("result", result);
		
		// 파일 업로드
		String fileCount_s = multiReq.getParameter("fileCount");
		if (!(fileCount_s == null || fileCount_s.equals(""))) {
			
			int fileCount = Integer.parseInt(fileCount_s);
			ArrayList<String> fileArr = new ArrayList<String>();
			
			for (int i = 1; i <= fileCount; i++) {
				String oriFileName = multiReq.getOriginalFileName("file" + i);
				if (!(oriFileName == null || oriFileName.equals("")))
				fileArr.add(oriFileName);
			}
		
			FileDAO fileDao = new FileDAO();
			
			for (int j = 0; j < fileArr.size(); j++) {
				System.out.println(fileArr.get(j));
				// 파일명 DB 저장
				fileDao.fileUpload(fileArr.get(j), title, userId, content);
				
				// 파일명 변경
				File oriFile = new File(savePath + "/" + fileArr.get(j));
				File rename = new File(savePath + "/" + fileDao.getBoardId(title, userId, content) + fileArr.get(j).substring(fileArr.get(j).indexOf("."), fileArr.get(j).length()));
				oriFile.renameTo(rename);
			}
		}
		return "write.jsp";
	}
}
