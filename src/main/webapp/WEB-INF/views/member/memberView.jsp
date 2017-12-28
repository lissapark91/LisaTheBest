<%@page import="kr.or.nextit.member.service.impl.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.model.Member"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

// 	request.setCharacterEncoding("utf-8");
// 	String mem_id = request.getParameter("mem_id");
	
// 	Member member = null;
	
// 	MemberServiceImpl memService = MemberServiceImpl.getInstance();
// 	member = memService.getMember(mem_id);
	
// 	request.setAttribute("member", member);

	
%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
<title>Member View</title>
<script>
	//회원 정보 수정
	function fn_update(){
		location.href="memberForm?mem_id=${member.mem_id}";
	}
	//삭제
	function fn_del(mem_id){
		location.href="memberDelete?mem_id="+mem_id;
		//자바스크립트가 jsp와 분리되어 있는 상황일 때.. 문제가 된다.
	}
	//목록으로
	function fn_list(){
		location.href="memberList";
	}
</script>

</head>
<body>
	
	<div class="container">
		<h2>회원 상세 정보</h2>
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td>${member.mem_id }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${member.mem_name }</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${member.mem_birth }</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${member.mem_phone }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.mem_email }</td>
			</tr>
		
			<tr>
				<td>주소</td>
				<td>
				<p>
				${member.mem_zipcode }
				</p>
				<p>
				${member.mem_addr_master }
				</p>
				<p>
				${member.mem_addr_detail }
				</p>
				</td>
			</tr>
			<tr>
				<td>등록일자</td>
				<td>${member.reg_date }</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="button" class="btn btn-default" value="수정" onclick="fn_update()">
					<input type="button" class="btn btn-default" value="삭제" onclick="fn_del('${member.mem_id}')">
					<input type="button" class="btn btn-default" value="목록" onclick="fn_list()">
				</td>
			</tr>
			
			
		</table>
	</div>
	
</body>
</html>