package membership.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dto.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// service 객체 생성
			MemberService service = new MemberServiceImpl();
			
			// 전체 멤버 목록 Service 호출해서 얻어오기
			 List<Member> memberList = service.getMemberList();
			 
			 // get한 memberList를 req에 set해서 진행
			 req.setAttribute("memberList", memberList);
			 
			 String path = "/WEB-INF/views/main.jsp";
			 req.getRequestDispatcher(path).forward(req, resp);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
