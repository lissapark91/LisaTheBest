<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" >
<script type="text/javascript">

	function fn_list(){
		location.href="${pageContext.request.contextPath}/board/boardList"
	}
	function fn_del(seqNo){
		location.href="${pageContext.request.contextPath}/board/boardDelete?bo_seq_no=" + seqNo		
	}
	
	function fn_form(seqNo){
		location.href="${pageContext.request.contextPath}/board/boardForm?bo_seq_no=" + seqNo		
	}
	
</script>
</head>
<body>
<div class="container">
	<h3>글 쓰기</h3>

	<input type="hidden" name="bo_type" value="BBS"/>
		<table class="table">
	
			<tr>
				<td  width="15%">제  목</td>
				<td>
					${board.bo_title}
				</td>
			</tr>
		
			<tr>
				<td>작성자</td>
				<td>
					${board.bo_writer_name}(${board.bo_writer_sec})
				</td>
			</tr>

			<tr>
				<td>공개여부</td>
				<td>
					${board.bo_open_yn == 'Y' ? '공개' : '비공개' }
				</td>
			</tr>
			
			<tr>
				<td>첨부파일</td>

				<td>
				<!-- 다운로드는 컨트롤에게 넘겨준다. -->
				<c:forEach var="fileItem" items="${board.fileItemList }">
					<div>
						<a href="${pageContext.request.contextPath}/common/download?file_seq_no=${fileItem.file_seq_no}">${fileItem.file_name}</a> (${fileItem.file_fancy_size })
					</div>
				</c:forEach>
				</td>

			</tr>
		
			<tr>
				<td colspan="2" style="white-space: pre-wrap;">${board.bo_content}</td>
			</tr>
		
		</table>
		
		<p align="center">
		<c:if test="${not empty LOGIN_USER and board.bo_writer == LOGIN_USER.mem_id}">
			<input type="button" value="수정" class="btn btn-primary" onclick="fn_form('${board.bo_seq_no}')"/>
			<input type="button" value="삭제" class="btn btn-primary" onclick="fn_del('${board.bo_seq_no}')"/>
		</c:if>
			<input type="button" value="목록" class="btn btn-primary" onclick="fn_list()"/>
		</p>
	</div>

</body>
</html>