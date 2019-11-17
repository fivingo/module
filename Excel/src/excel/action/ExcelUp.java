package excel.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import controller.CommandHandler;
import excel.model.ExcelDAO;

public class ExcelUp implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savePath = "C:/Users/fivin/Desktop/Develop/Study/Excel/WebContent/excelFile/";
		MultipartRequest multiReq = new MultipartRequest(req, savePath, 1024 * 1024 * 100, "UTF-8");
		String fileName = multiReq.getOriginalFileName("file");
		
		ExcelDAO dao = new ExcelDAO();
		int result = dao.insertExcel(savePath + fileName);
		req.setAttribute("result", result);
		return "close.jsp";
	}
}
