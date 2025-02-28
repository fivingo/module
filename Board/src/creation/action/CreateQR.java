package creation.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import creation.model.QrDAO;

public class CreateQR implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getParameter("url");
		String boardId_s = req.getParameter("boardId");
		int boardId = Integer.parseInt(boardId_s);
		String userId = req.getParameter("userId");
		
		QrDAO dao = new QrDAO();
		dao.createQR(url, boardId);
		dao.setQRInfo(boardId, userId);
		
		req.setAttribute("QRcode", "QRcode_" + boardId);
		try {
			Thread.sleep(5000);	// 5초 뒤에
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "creationPopup.jsp";
	}
}
