package gis.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import gis.model.GISDAO;
import gis.model.GISDTO;

public class InfoWindow implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String xCoord = req.getParameter("xCoord");
		String yCoord = req.getParameter("yCoord");
		String address = req.getParameter("address");
		String uName = req.getParameter("uName");
		String uGroup = req.getParameter("uGroup");
		
		GISDTO dto = new GISDTO(0, xCoord, yCoord, address, uName, uGroup);
		GISDAO dao = new GISDAO();
		dao.setMarkerInfo(dto);
		
		return "redirect.jsp";
	}
}
