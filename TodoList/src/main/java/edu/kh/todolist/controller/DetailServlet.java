package edu.kh.todolist.controller;

import java.io.IOException;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/todo/detail")
public class DetailServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	try {
	 int index = Integer.parseInt(req.getParameter("index"));
	 
	 //상세조회 서비스 호출 후 결과 반환 받기
	 TodoListService service = new TodoListServiceImpl();
	 
	 Todo todo = service.todoDetailView(index);
	 // index번째 todo가 없으면 null 반환 (redirect하겠다)
	 // index번째 todo가 존재하지 않을 경우 -> -> 메인페이지 (/) 로 redirec 후
	 // "해당 index에 할 일이 존재하지 않습니다" alert 출력
		 if (todo == null) {
			 
			 // session객체에 message 세팅
			 HttpSession session = req.getSession();
			 session.setAttribute("message", "해당 index에 할 일이 존재하지 않습니다");
			 
			 resp.sendRedirect("/");
			 return;
		 }
		 
		 // index번째 todo가 존재하는 경우
		 // detail.jsp로 forward 해서 응답
		 req.setAttribute("todo", todo); // request scope에 세팅
		 // JSP 파일 경로 (webapp폴더 기준)
		 String path = "/WEB-INF/views/detail.jsp";
		 
		 // 요청 발송자를 이용해서 요청 위임
		 req.getRequestDispatcher(path).forward(req, resp);
		 // 지정된 경로에 req랑 resp를 위임해줘 
		 
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 	}
	
	
}
}
