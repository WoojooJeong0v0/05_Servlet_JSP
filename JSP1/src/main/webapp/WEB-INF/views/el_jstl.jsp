<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>EL / JSTL 사용법</title>
</head>
<body>
  <h1>EL (Expression language) : 표현 언어 </h1>
  <pre>
  <%-- <% =%> 이 친구가 표현식 친구 --%>
  - JSP에서 표현식을 효율적이고 간단하게 작성할 수 있도록 고안된 언어
  - JSP 내부에 기본적 내장
  - Java 값을 HTML 형태로 쉽게 출력할 수 있다!

  [기본 작성법]
  \${key} (백슬래시는 제외하고 사용)
  </pre>

  <h3>전달 받은 파라미터를 출력하는 방법</h3>

  <p style="color:#387F39;">
  주소 뒤에 쿼리스트링(?name=홍길동&age=20) 직접 작성해보면서 테스트
  </p>

  <h4>1. JSP 표현식</h4>
  <div>
  name : <%= request.getParameter("name") %>
  <br>
  age : <%= request.getParameter("age") %>
  </div>

   <h4>2. EL 표현식</h4>
  <div>
  <%-- EL에서 파라미터를 얻어오는 방법 : ${param.key값}  --%>
  name : ${param.name}
  <br>
  age : ${param.age}
  </div>

  <hr>

  <h1>JSTL(JSP Standard Tag Library)</h1>
  <pre>
  <%-- <% 자바코드 %> --%> 
  JSP에서 자주 사용하는 Java 코드를(if, for, 변수 선언, String 관련 메서드 등...)
  스크립틀릿 이 아닌 HTML 태그 모양으로 작성할 수 있도록
  태그를 제공하는 라이브러리 (라이브러리 : 코드 모음)

  [라이브러리 추가 방법] (매우 중요!!)
   1. 필요한 라이브러리(.jar) 파일을 다운 받기
   2. 프로젝트 webapp/WEB-INF/lib 폴더에 다운 받은 라이브러리 추가 (복사 붙여넣기)
   3. JSTL을 사용할 JSP 파일 제일 위에 taglib 추가 구문 작성 (태그 라이브러리)
  </pre>

  <h3>JSTL c:if문 사용해 보기</h3>
  <c:if test="${param.age > 20}">
  <h3> 성인 입니다 </h3>
  </c:if>
  <c:if test="${param.age <= 20}">
  <h3> 성인이 아닙니다 </h3>
  </c:if>


</body>
</html>