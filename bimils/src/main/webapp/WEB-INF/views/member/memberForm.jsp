<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

<script type="text/javascript">
	
//중복체크 플래그
var duplicateCheck = false;

$(function(){
	//id
	$('input[name=mem_id]').on('keyup', function(){
		duplicateCheck = false;
		$("#resultMsg").text("")
	})
	
	$("#btnIdCheck").click(function(){
		var $mem_id = $('input[name=mem_id]')
		
		if($mem_id.val()){
			duplicateCheck = false;
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/member/exists',
				data: "type=id&mem_id=" + $mem_id.val(),
				success: function(data, status){
					console.log(status);
					console.log(JSON.stringify(data));
//						alert(JSON.stringify(data));
					if(data.result == "true"){
						$("#resultMsg").text("사용중인 아이디 입니다.")
						$mem_id.val('')
					}else{
						$("#resultMsg").text("사용가능한 아이디 입니다.")							
//							$mem_id.attr('readOnly',true)
						$('input[name=mem_name]').focus()
						duplicateCheck = true;
					}

				},
				error: function(error){
					console.log(error);
				}
				
			});
		}else{
			alert("아이디를 입력 해주세요.")
		}	
	});
	
	//name
	$('input[name=mem_id]').on('keyup', function(){
		duplicateCheck = false;
		$("#resultMsg").text("")
	})
	
	$("#btnIdCheck").click(function(){
		var $mem_id = $('input[name=mem_id]')
		
		if($mem_id.val()){
			duplicateCheck = false;
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/member/exists',
				data: "type=id&mem_id=" + $mem_id.val(),
				success: function(data, status){
					console.log(status);
					console.log(JSON.stringify(data));
//						alert(JSON.stringify(data));
					if(data.result == "true"){
						$("#resultMsg").text("사용중인 아이디 입니다.")
						$mem_id.val('')
					}else{
						$("#resultMsg").text("사용가능한 아이디 입니다.")							
//							$mem_id.attr('readOnly',true)
						$('input[name=mem_name]').focus()
						duplicateCheck = true;
					}

				},
				error: function(error){
					console.log(error);
				}
				
			});
		}else{
			alert("아이디를 입력 해주세요.")
		}	
	});
	
	//email
	$('input[name=mem_id]').on('keyup', function(){
		duplicateCheck = false;
		$("#resultMsg").text("")
	})
	
	$("#btnIdCheck").click(function(){
		var $mem_id = $('input[name=mem_id]')
		
		if($mem_id.val()){
			duplicateCheck = false;
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/member/exists',
				data: "type=id&mem_id=" + $mem_id.val(),
				success: function(data, status){
					console.log(status);
					console.log(JSON.stringify(data));
//						alert(JSON.stringify(data));
					if(data.result == "true"){
						$("#resultMsg").text("사용중인 아이디 입니다.")
						$mem_id.val('')
					}else{
						$("#resultMsg").text("사용가능한 아이디 입니다.")							
//							$mem_id.attr('readOnly',true)
						$('input[name=mem_name]').focus()
						duplicateCheck = true;
					}

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
		

		if(!duplicateCheck){
			alert("아이디 중복 체크를 해주세요.")
			return false;
		}
		
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
	
	function fn_save(){
		/*   
		* 회원등록함수
		*/
		var frm = document.memberForm;
		if(!validate()){
			return;
		}

		frm.action="memberInsert";
		
		frm.submit();

	}

</script>
</head>
<body>
<div>

<h2>회원가입</h2>

	<form name="memberForm" method="post">
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mem_id" size="20" > 
				<button type="button" class="btn btn-primary" id="btnIdCheck">중복체크</button> 8~20자 내의 영문, 숫자 조합 
				<label id="resultMsgId"></label>
				</td>
			</tr>		
			<tr>
				<td>닉네임</td>
				<td><input type="text" name="mem_name" size="20" > 
				<button type="button" class="btn btn-primary" id="btnNameCheck">중복체크</button> 닉네임을 입력 하세요.
				<label id="resultMsgName"></label>
				</td>
			</tr>		
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="mem_pwd" size="20" > 8~20자 내의 영문, 숫자 조합</td> 
			</tr>		
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="mem_pwd_confirm" size="20" ><label id="resultMsgPwd"></label></td> 
			</tr>			

			<tr>
				<td>이메일</td>
				<td><input type="text" name="mem_email" size="20" > <button type="button" class="btn btn-primary" id="btnEmailCheck">중복체크</button>
				<label id="resultMsgEmail"></label>
				</td> 
			</tr>		
			
			<tr>
				<td colspan="2">
					<input id="btn_submit" type="button" value="가입하기" class="btn btn-default" onclick="fn_save()">

					<input type="reset" value="취소" class="btn btn-default">
				</td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>