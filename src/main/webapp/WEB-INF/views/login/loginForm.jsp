<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>* ** 로그인 * ** * **</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script>
	function fn_login(){

		var frm = document.loginForm
		
		if(frm.mem_id.value==""){
			alert("아이디를 입력해 주세요.")
			//frm.mem_id.focus()
			return false;
		}
		if(frm.mem_pwd.value==""){
			alert("비밀번호를 입력해 주세요.")
			//frm.mem_pwd.focus()
			return false;
		}
		
		frm.action = "${pageContext.request.contextPath}/logincheck";
		frm.submit();
	}
	
</script>

</head>
<body>
	<div class="container">
	<h3 align="center">비밀스 닷컴</h3>
	<div class="panel panel-default" style="width:400px; margin:auto;">
		
		<div class="panel-heading">
		<div class="panel-title">
		<div>
			로그인 <span style="float:right;"><a href="${pageContext.request.contextPath }/">Home</a></span> <!-- root로 보냄 -->
		</div>
		</div>
		</div>		
		
		<div class="panel-body">
			<form class="form-horizontal" name="loginForm" method="post">
				<div class="form-group">
					<div class="col-xs-12">
					<input type="text" name="mem_id" value="${empty cookie.REMEBER_ME ? '' : cookie.USER_ID.value}" class="form-control" placeholder="ID">
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
					<input type="password" name="mem_pwd" value="" class="form-control" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
					<label for="remember_me"><input type="checkbox" name="remember_me" id="remember_me" value="Y" ${empty cookie.REMEBER_ME ? '' : 'checked'}> 아이디 저장</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
					<input type="button" value="Login" class="btn btn-warning btn-block" onclick="fn_login()">
					</div>
				</div>
			</form>
			
			<hr>
			
			<div align="center">
				<a href="#">아이디 찾기</a> /
				<a href="#">비밀번호 찾기</a> /
				<a href="${pageContext.request.contextPath }/member/form">회원가입</a> 
			</div>
			
		</div>
		
	</div>
	</div>
</body>
</html>