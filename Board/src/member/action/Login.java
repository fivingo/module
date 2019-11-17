package member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandHandler;
import member.model.MemberDAO;

public class Login implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String pwd = req.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.login(userId, pwd);
		if (result > 0) {
			HttpSession session = req.getSession();
			session.setAttribute("userId", userId);
			return "list.jsp";
		} else {
			return "login.jsp";
		}
	}
}