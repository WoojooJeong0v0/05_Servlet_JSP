package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * jakarta.servlet.http.HttpServlet
 * - http프로토콜 서비스를 지원하는 추상 클래스 (상속 받아서 구현해야 함)
 *  -> 상속 받아서 사용
 *  
 * - 해당 클래스를 상속 받았다고 해서 
 * 	클라이언트의 요청을 받아서 처리할 수 있는
 * 	진짜 Servlet이 된 것은 아니다!!!!
 * 	(doGet이라는 녀석을 쓸 수 있게 모습만 가져다 줌)
 * 
 * 	[Servlet 등록 절차]
 * 	1. web.xml에 직접 작성하는 방법
 * 		-> src/main/webapp/WEB-INF 폴더 내에 존재함
 * 	2. @WebServlet() 어노테이션 이용하는 방법
 */

public class ExampleServlet1 extends HttpServlet {

	// 제출된 GET방식 요청(html에서 작성함)을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// protected라서 상속된 것만 쓸 수 있음
		// 자동완성되는 super.doGet 지우기
		
		System.out.println("/ex1 GET방식 요청 처리를 위한 메서드 수행");
		
		/**
		 * [HttpServletRequest]
		 * - 클라이언트 요청 시 생성되는 객체
		 * 
		 * - 요청 시 전달된 데이터, 요청한 클라이언트 정보.
		 * 유연한 요청 처리를 위한 객체를 제공함
		 * 
		 * 
		 * [HttpServletReponse]
		 * - 클라이언트 요청 시 생성되는 객체
		 * 
		 * - 서버가 클라이언트에게 응답할 수 있는 방법을 제공함
		 * 
		 * - (응답 방법) 서버 -> 클라이언트로 연결된 출력용 스트림 제공
		 * 		PrintWriter
		 */
		
		// 매개변수 (Parameter)
	
		// String req.getParameter("name속성값")
		// -> 요청 시 전달된 데이터를 얻어오겠다는 구문
		// -> 얻어오고 싶은 input의 name속성값을 작성
		// -> HTML에서 읽어온 데이터는 기본적으로 모두 String
		String inputName = req.getParameter("inputName");
		String inputAge = req.getParameter("inputAge");
		System.out.printf("%s의 나이는 %s세 입니다\n", inputName, inputAge);
		
		// ---------------------------------
		
		/* 서버 -> 클라이언트 응답 처리
		 * 
		 * 1) 서버에서 HTML 코드를 만들어 클라이언트에게 전달 == 응답 response
		 * 2) 클라이언트의 브라우저가 응답 받은 HTML 코드를 해석해 화면을 보여줌
		 *  */
		
		// 응답처리 1. 응답하는 문서의 형식과 문자 인코딩 지정
		resp.setContentType("text/html; charset=UTF-8");
		
		// text/html : 글자로 된 HTML형식의 문서
		// -> MIME Type : 인터넷으로 전달되는 데이터/파일의 형식을 구분하는 것
		
		// charset : 문자 인코딩 지정 속성
		// UTF-8 : 유니코드를 위한 가변 길이 문자 인코딩 방식 중 하나
		//    - 영어, 숫자, 기본 특수문자 : 1byte 로 인식
		//    - 나머지(한글,한문 등) 	  : 3byte로 인식
		
		
		// 응답처리 2. 출력용 스트림 얻어오기
		PrintWriter out = resp.getWriter();
		
		// 응답처리 3. 출력할 HTML 코드 만들기
		StringBuilder sb = new StringBuilder(); // 객체 생성
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		
		sb.append("<head>");
		sb.append("<title> /ex1 응답 페이지 </title>");
		sb.append("</head>");
		
		sb.append("<body>");
		sb.append("<h1>응답 페이지 입니다</h1>");
			sb.append("<ul>");
			sb.append("<li>입력 받은 이름 : " + inputName + "</li>");
			sb.append("<li>입력 받은 나이 : " + inputAge + "</li>");
			sb.append("</ul>");
		sb.append("</body>");
		
		sb.append("</html>");
		
		
		// 응답처리 4. 출력스트림을 이용해 클라이언트에게 HTML코드 출력(응답작업)
		out.write(sb.toString());
		
	}
}
