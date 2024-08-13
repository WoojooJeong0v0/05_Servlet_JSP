<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/resources/css/main.css">
  <title>Membership 관리</title>
</head>
<body>
  <>
  <div class="topMenu">
  <div id="addbtn"><button>멤버 추가</button></div>
  <div id="search"><input type="text" value="검색할 내용 입력"><button>검색</button></div>
  </div>
  <div class="content">
  
   <table id="memberList" border="1">
    <thead>
      <tr>
        <th>이름</th>
        <th>전화번호</th>
        <th>누적 금액</th>
        <th>등급</th>
      </tr>
    </thead>
  
    <tbody>
      <c:forEach items="${memberList}" var="member" varStatus="vs">
        <tr>
          <th>${vs.count}</th>

          <td>
            <a href="/todo/detail?index=${vs.index}"> ${member.name}</a>
          </td>

          <td>${todo.regDate}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  </div>
  

 <script src="/resources/js/main.js"></script>
</body>
</html>