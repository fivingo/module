package member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import member.model.MemberDAO;
import member.model.MemberDTO;

public class MemberJoin implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		
		MemberDTO dto = new MemberDTO(userId, pwd, name);
		MemberDAO dao = new MemberDAO();
		
		int result = dao.memberJoin(dto);
		String msg = null;
		if (result > 0)	{
			msg = "가입 완료";
		} else {
			msg = "가입 실패";
		}
		
		req.setAttribute("msg", msg);
		
		return "memberJoin.jsp";
	}
}
