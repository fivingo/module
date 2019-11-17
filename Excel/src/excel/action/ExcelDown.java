package excel.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import excel.model.ExcelDAO;

public class ExcelDown implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ExcelDAO dao = new ExcelDAO();
		dao.putExcel();
		
		return "list.jsp";
	}
}
