<%@page import="kr.or.nextit.member.service.impl.MemberServiceImpl"%>
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
<title>회원 정보 수정</title>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="kr.or.nextit.member.model.Member"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>    
<%

// 	boolean isError = false;

// 	MemberServiceImpl memService = MemberServiceImpl.getInstance();
// 	int updCnt = memService.updateMember(member);
// 	if(updCnt == 0){
// 		isError = true;
// 	}
// 	request.setAttribute("isError", isError);

%>
</head>
<body>
<c:if test="${isError == true}">
	<script type="text/javascript">
		alert("회원 정보 수정에 실패하였습니다.");
		history.go(-1);
	</script>
</c:if>
<c:if test="${isError == false }">
	<script type="text/javascript">
		alert("회원 정보 수정을 완료하였습니다.");
		location.href="memberView.do?mem_id=<%= member.getMem_id() %>";
	</script>
</c:if>

	
</body>
</html>