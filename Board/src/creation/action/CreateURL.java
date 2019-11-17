package creation.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import creation.model.UrlDAO;
import creation.model.UrlDTO;

public class CreateURL implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		String url = req.getParameter("url");
		String userId = req.getParameter("userId");
		
		UrlDAO dao = new UrlDAO();
		
		int count = dao.checkUrl(boardId);
		if (count > 0) {
			String shortUrl = url.substring(0, url.lastIndexOf("/")) + "/u/" + dao.getShortUrl(url);
			req.setAttribute("shortUrl", shortUrl);
		} else {
			UrlDTO dto = new UrlDTO(boardId, url, null, userId);
			String shortUrl = url.substring(0, url.lastIndexOf("/")) + "/u/" + dao.setShortUrl(dto);
			req.setAttribute("shortUrl", shortUrl);
		}
		
		/* 굳이 이렇게 할필요없이 servlet에서 주소 잘라오는 메서드 활용 */
		
		return "creationPopup.jsp";
	}
}
