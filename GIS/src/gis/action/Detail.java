package gis.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;

public class Detail implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String xCoord = req.getParameter("xCoord");
		String yCoord = req.getParameter("yCoord");
		String address = req.getParameter("address");
		String uName = req.getParameter("uName");
		String uGroup = req.getParameter("uGroup");
		
		req.setAttribute("xCoord", xCoord);
		req.setAttribute("yCoord", yCoord);
		req.setAttribute("address", address);
		req.setAttribute("uName", uName);
		req.setAttribute("uGroup", uGroup);
		return "detail.jsp";
	}
}
