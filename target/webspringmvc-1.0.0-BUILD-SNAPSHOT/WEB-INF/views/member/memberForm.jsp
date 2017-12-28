<%@page import="kr.or.nextit.member.service.impl.MemberServiceImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.nextit.member.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<%
// 	request.setCharacterEncoding("utf-8");	
// 	String mem_id = request.getParameter("mem_id");
	
// 	Member member = null;
// 	MemberServiceImpl memService = MemberServiceImpl.getInstance();
// 	member = memService.getMember(mem_id);
	
// 	request.setAttribute("member", member);
	
	
%>
<script type="text/javascript">
	
	$(function(){
		
		$("#btnIdCheck").click(function(){
			var mem_id = $('input[name=mem_id]').val()
			if(mem_id){
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/member/memberExists',
					data: "mem_id=" + mem_id,
					success: function(data, status){
						console.log(status);
						alert(JSON.stringify(data));
					},
					error: function(error){
						console.log(error);
					}
					
				});
			}else{
				alert("아이디를 입력 해주세요.")
			}	
		});
		
	})
	

	function validate(){
		var frm = document.memberForm;
		if(frm.mem_id.value == ""){
			alert("아이디를 입력하세요.");
			frm.mem_id.focus();
			return false;
		}
		if(frm.mem_name.value == ""){
			alert("이름을 입력하세요.");
			frm.mem_name.focus();
			return false;
		}
		if(frm.mem_pwd.value == "" || frm.mem_pwd_confirm == ""){
			alert("비밀번호를 입력하세요.");
			frm.mem_pwd.focus();
			return false;
		}else if(frm.mem_pwd.value != frm.mem_pwd_confirm.value){
			alert("비밀번호가 일치하지 않습니다.");
			frm.mem_pwd.value = "";
			frm.mem_pwd_confirm.value = "";
			frm.mem_pwd.focus();
			return false;			
		}
		if(frm.mem_birth.value == ""){
			alert("생년월일을 입력하세요.");
			frm.mem_birth.focus();
			return false;
		}
		if(frm.mem_phone.value == ""){
			alert("전화번호를 입력하세요.");
			frm.mem_phone.focus();
			return false;
		}
		if(frm.mem_email.value == ""){
			alert("이메일 주소를 입력하세요.");
			frm.mem_email.focus();
			return false;
		}
		if(frm.mem_zipcode.value == ""){
			alert("우편번호를 입력하세요.");
			frm.mem_zipcode.focus();
			return false;
		}
		if(frm.mem_addr_master.value == ""){
			alert("주소를 입력하세요.");
			frm.mem_addr_master.focus();
			return false;
		}
		if(frm.mem_addr_detail.value == ""){
			alert("상세주소를 입력하세요.");
			frm.mem_addr_detail.focus();
			return false;
		}
		
		return true;
	}
	
	function fn_list(){
		/* 
		* 회원 목록 이동 함수
		*/
		location.href="memberList";
	}
	
	function fn_save(){
		/*   
		* 회원등록함수
		*/
		var frm = document.memberForm;
		if(!validate()){
			return;
		}
		<c:if test="${empty member.mem_id}">
			frm.action="memberInsert";					
		</c:if>
		<c:if test="${not empty member.mem_id}">
			frm.action="memberUpdate";		
		</c:if>		
		
		frm.submit();

	}

	<c:if test="${isError}">
	alert(' ${message} ');
	// 주소 강제 변경 
	history.pushstate(null, "", "{pageContext.request.contextPath}/member/memberForm")
	</c:if>
</script>
</head>
<body>
<div class="container">
<c:if test="${empty member.mem_id or isInsert}">
	<h2>회원가입</h2>
	
</c:if>
<c:if test="${not empty member.mem_id and !isInsert}">
	<h2>회원 정보 수정</h2>
</c:if>

<h2></h2>
	<form name="memberForm" method="post">
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mem_id" size="20" value="${member.mem_id}" <c:if test="${not empty member.mem_id}">readonly</c:if>> 
				<button type="button" class="btn btn-primary" id="btnIdCheck">중복체크</button>8~20자 내의 영문, 숫자 조합 </td>
				 <!-- bean의 프로퍼티와 name을 같게하면 자동 맵핑 가능 -->
			</tr>		
			<tr>
				<td>이름</td>
				<td><input type="text" name="mem_name" size="20" value="${member.mem_name}"> 실명을 입력 하세요.</td> 
			</tr>		
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="mem_pwd" size="20" value="${member.mem_pwd}"> 8~20자 내의 영문, 숫자 조합</td> 
			</tr>		
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="mem_pwd_confirm" size="20" value="${member.mem_pwd}"> 8~20자 내의 영문, 숫자 조합</td> 
			</tr>		
			<tr>
				<td>생년월일</td>
				<td><input type="text" name="mem_birth" size="20" value="${member.mem_birth}"> 예) 19840415</td> 
			</tr>		
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="mem_phone" size="20" value="${member.mem_phone}"> '-'없이 입력</td> 
			</tr>		
			<tr>
				<td>이메일</td>
				<td><input type="text" name="mem_email" size="20" value="${member.mem_email}"></td> 
			</tr>		
			<tr>
				<td>주소</td>
				<td>
				<p><input type="text" name="mem_zipcode" size="6" value="${member.mem_zipcode}"> <button type="button" class="btn btn-primary">우편번호 검색</button></p>
				<p><input type="text" name="mem_addr_master" size="50" value="${member.mem_addr_master}"></p>
				<p><input type="text" name="mem_addr_detail" size="20" value="${member.mem_addr_detail}"></p>
				</td> 
			</tr>		


			<tr>
				<td colspan="2">
		
				<c:if test="${empty member.mem_id or isInsert}">
					<input id="btn_submit" type="button" value="가입하기" class="btn btn-default" onclick="fn_save()">
				</c:if>
				<c:if test="${not empty member.mem_id and !isInsert}">
					<input id="btn_submit" type="button" value="수정하기" class="btn btn-default" onclick="fn_save()">
				</c:if>
					<input type="reset" value="취소" class="btn btn-default">
					<input type="reset" value="목록" class="btn btn-default" onclick="fn_list()">
				</td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>