package list.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import list.model.ListDAO;

public class Delete implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		ListDAO dao = new ListDAO();
		
		int result = dao.delete(boardId);
		req.setAttribute("result", result);
		return "delete.jsp";
	}
}
