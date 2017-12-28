<%@page import="kr.or.nextit.member.service.impl.MemberServiceImpl"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	request.setCharacterEncoding("utf-8");	
	String mem_id = request.getParameter("mem_id");

	boolean isError = false;
	
	
// 	MemberServiceImpl memService = MemberServiceImpl.getInstance();
// 	int updCnt = memService.deleteMember(mem_id);
// 	if(updCnt == 0){
// 		isError = true;
// 	}
	
	request.setAttribute("isError", isError);
%>
	
	<c:if test="${isError}">
	<script type="text/javascript">
		alert("회원 삭제를 실패하였습니다.");
		history.go(-1);
	</script>
	</c:if>
	<c:if test="${not isError}">
	<script type="text/javascript">
		alert("회원을 삭제하였습니다.");
		location.href="memberList.do";
	</script>
	</c:if>


</body>
</html>