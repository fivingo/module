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

public class Update implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String savePath = "C:/Users/fivin/Desktop/Develop/Study/BoardStudy/WebContent/FileUpload";
		MultipartRequest multiReq = new MultipartRequest(req, savePath, 1024 * 1024 * 100, "UTF-8");
		
		int boardId = Integer.parseInt(multiReq.getParameter("boardId"));
		String title = multiReq.getParameter("title");
		String userId = multiReq.getParameter("userId");
		String content = multiReq.getParameter("content");
		
		ListDAO listDao = new ListDAO();
		ListDTO listDto = new ListDTO(boardId, title, userId, null, content, 'N', 0, 0, 0, 0);
		
		// 게시물 DB 수정
		int result = listDao.update(listDto);
		req.setAttribute("result", result);
		
		// 저장된 파일 삭제
		String savedFile_s = multiReq.getParameter("savedFile");
		if (!(savedFile_s == null || savedFile_s.equals(""))) {
			int savedFile = Integer.parseInt(savedFile_s);
			
			FileDAO fileDao = new FileDAO();
			for (int i = 1; i <= savedFile; i++) {
				String delFile = multiReq.getParameter("checkDel" + i);
				if (delFile != null) {
					fileDao.fileDelete(Integer.parseInt(delFile));	// DB 삭제
					String maskFile = multiReq.getParameter("maskFile");
					System.out.println(maskFile);
					File file = new File(savePath + "/" + maskFile);
					file.delete();
				}
			}
		}
		
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
			
			/* DBCP 문제?? */
			for (int j = 0; j < fileArr.size(); j++) {
				// 파일명 DB 저장
				fileDao.fileUpload_update(fileArr.get(j), title, userId, content, boardId);
				
				// 파일명 변경
				File oriFile = new File(savePath + "/" + fileArr.get(j));
				File rename = new File(savePath + "/" + fileDao.getBoardId(title, userId, content) + fileArr.get(j).substring(fileArr.get(j).indexOf("."), fileArr.get(j).length()));
				oriFile.renameTo(rename);
			}
		}
		return "update.jsp";
	}
}
