package comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.CommentDAO;
import comment.model.CommentDTO;
import controller.CommandHandler;

public class CommentReply implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardId = Integer.parseInt(req.getParameter("boardId_re"));
		String userId = req.getParameter("userId_re");
		String content = req.getParameter("content_re");
		int ref = Integer.parseInt(req.getParameter("ref_re"));
		int step = Integer.parseInt(req.getParameter("step_re"));
		int position = Integer.parseInt(req.getParameter("position_re"));
		
		CommentDTO dto = new CommentDTO(0, boardId, userId, null, content, 'N', ref, step, position);
		CommentDAO dao = new CommentDAO();
		
		dao.reply(dto);
		
		return "content.do?boardId=" + boardId;
	}
}
