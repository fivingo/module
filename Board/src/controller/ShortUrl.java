package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import creation.model.UrlDAO;

//@WebServlet("/u/*")	// web.xml에서 매핑하던지 둘중하나만
public class ShortUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ShortUrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    
		String uri = request.getRequestURI();
		String shortUrl = uri.substring(uri.lastIndexOf("/") + 1);
		
		UrlDAO dao = new UrlDAO();
		String originalUrl = dao.getOriginalUrl(shortUrl);
		originalUrl = originalUrl.replace("192.168.0.217", "localhost");
		//System.out.println(originalUrl);
		
		response.sendRedirect(originalUrl);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
