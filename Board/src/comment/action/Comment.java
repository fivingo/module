package comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;

import comment.model.CommentDAO;
import comment.model.CommentDTO;
import controller.CommandHandler;

public class Comment implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int boardId = Integer.parseInt(req.getParameter("boardId_c"));
		String userId = req.getParameter("userId_c");
		String content = req.getParameter("content_c");
		
		CommentDTO dto = new CommentDTO(0, boardId, userId, null, content, 'N', 0, 0, 0);
		CommentDAO dao = new CommentDAO();
		
		dao.comment(dto);
		
		return "content.do?boardId=" + boardId;
	}
}
