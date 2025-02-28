package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

public class Controller extends HttpServlet {
	/* properties 파일을 통한 기능추가를 위한 세팅
	 * - Command 패턴: 하나의 명령어를 하나의 클래스에서 처리 */
	private Map<String, CommandHandler> cmdMap;
	
	public Controller() {
		cmdMap = new HashMap<String, CommandHandler>();	// Map 객체 생성
	}
	
	@Override
	public void init() throws ServletException {	// 서블릿을 생성하고 초기화
		String fullpath = this.getServletContext().getRealPath("/WEB-INF/Command.properties");
		//System.out.println(fullpath);
		
		Properties pr = new Properties();	// Properties 객체 생성
		try {
			FileInputStream fis = new FileInputStream(fullpath);	// 경로 저장
			pr.load(fis);	// key와 value 추출 (Properties collection의 기능)
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator<Object> keys = pr.keySet().iterator();	// 열거형 클래스
		while (keys.hasNext()) {
			String key = (String) keys.next();	// key값 취득
			String value = pr.getProperty(key);	// value값 취득 (클래스 경로)
			try {
				Class<?> refClass = Class.forName(value);	// 클래스 경로 저장
				try {
					Object obj = refClass.newInstance();	// 저장된 클래스 객체 생성
					cmdMap.put(key, (CommandHandler) obj);	// Map에 저장
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	/*****************************************************************************************/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		userProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		userProcess(req, resp);
	}
	
	protected void userProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/** MVC 패턴의 Controller */
		/* 1. 요청받기 */
		//String type = req.getParameter("type");
		// URI 패턴으로 변경
		String type = req.getRequestURI();
		if (type.indexOf(req.getContextPath()) == 0) {
			type = type.substring(req.getContextPath().length());
		}
		
		/* 
		2. 요구분석 - command 패턴으로 처리
		조건문에서 모든 기능 처리
		-> 조건문 내 기능 처리를 객체 생성으로 대체
		-> 조건문 대신 properties 파일로 지정 (형식: type(혹은 명령어) = 클래스 경로)
		 */
//		//String result = "";
//		//String gopage = "";
//		commandHandler cmd = null;
//		if (type.equals("write")) {
//			// 3. 기능처리
//			//result = "글쓰기 수행";
//			//gopage = "/write.jsp";
//			cmd = new WriteAction();	// action class로 기능처리
//		} else if (type.equals("content")) {
//			//result = "본문보기 수행";
//			//gopage = "/content.jsp";
//			cmd = new ContentAction();
//		} else if (type.equals("list")) {
//			//result = "목록보기 수행";
//			//gopage = "/list.jsp";
//			cmd = new ListAction();
//		}
		CommandHandler cmd = cmdMap.get(type);
		String gopage = cmd.process(req, resp);
		
		/* 4. 데이터 저장 - action class에서 저장 */
		//req.setAttribute("result", result);
		
		/* 5. JSP에게 전달 - forward 방식 */
		RequestDispatcher dis = req.getRequestDispatcher(gopage);
		dis.forward(req, resp);
	}
}
