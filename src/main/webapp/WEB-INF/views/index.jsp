<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bimils.com</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">비 밀 스 닷 컴</a>
    </div>
    
    
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <c:if test="${empty LOGIN_USER}">
      <li><a href="${pageContext.request.contextPath}/login/loginForm">로그인</a></li>
      </c:if>
      <li><a href="${pageContext.request.contextPath}/member/memberList">회원관리</a></li>
      <li><a href="${pageContext.request.contextPath}/board/boardList">게시판</a></li>
    </ul>
    <c:if test="${not empty LOGIN_USER}">
    <div style="display: inline; float:right; color:white; padding-top: 15px; padding-bottom: 15px;">
    	${LOGIN_USER.mem_name}님으로 로그인 하였습니다.
    	<a href="${pageContext.request.contextPath}/login/logout">로그아웃</a>
    </div>
    </c:if>
  </div>
</nav>
  
<div class="container">
  <h3>안녕하세요?</h3>
  <p>반갑습니다~~ ^^</p>
</div>
</body>
</html>