<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" >
<title>* ** * 게 시 판 * ** *</title>

<style>
	.header{
		width: 940px;
		margin: auto;
	}
	.footer{
		width: 940px;
		margin: auto;
	}

</style>
<script type="text/javascript">

		
	function fn_search(currentPage){
		var frm = document.searchForm
		frm.currentPage.value = currentPage;
		if(frm.searchType.value != "" && frm.searchWord.value == ""){
			alert('검색어를 입력하세요.')
			return false;
		}
		frm.action = "${pageContext.request.contextPath}/board/boardList"
		frm.submit();
	}
	
	function fn_new(){
		location.href="${pageContext.request.contextPath}/board/boardForm"
	}
	

</script>
</head>
<body>
<div class="header">
<h3>게시판 목록</h3>
<c:if test="${empty LOGIN_USER }">
<span  style="float: right;"><a href="${pageContext.request.contextPath}/login/loginForm">로그인</a></span>
<span  style="float: right;"> &nbsp;/&nbsp; </span>
</c:if>
<c:if test="${not empty LOGIN_USER }">
<span  style="float: right;"><a href="${pageContext.request.contextPath}/login/logout">로그아웃</a></span>
<span  style="float: right;"> &nbsp;/&nbsp; </span>
</c:if>
<span style="float: right;"><a href="${pageContext.request.contextPath}/">Home </a></span>
<!-- 검색 -->
<form class="form-inline" name="searchForm" method="get">
	<div class="form-group">
	<input type="hidden" name="currentPage" value="${param.currentPage }"/>
	<select name="searchType" class="form-control">
		<option value="">전체</option>
		<option value="01" ${param.searchType == "01"? 'selected' : '' } >제목</option>
		<option value="02" ${param.searchType == '02' ? 'selected' : '' } >내용</option>
		<option value="03" ${param.searchType == '03' ? 'selected' : '' } >제목+내용</option>
		<option value="04" ${param.searchType == '04' ? 'selected' : '' } >작성자</option>
	</select>
	</div>
	<div class="form-group">
	<input class="form-control" type="text" size="25" name="searchWord" placeholder="검색어를 입력해 주세요." value="${param.searchType == '' ? '' : param.searchWord}"/>
	</div>
	<div class="form-group">
	<input class="form-control" type="button" value="검색" onclick="fn_search(1)"/>
	</div>
	<p>
	<div class="form-group">
		<label>총 게시글 : ${pagingUtil.totalCount }</label> 
	</div>
	<div class="form-group" style="float: right;">
		<label>페이지 사이즈</label>
		<select name="pageSize" class="form-contorl">
			<option value="10" ${param.pageSize == "10"? 'selected' : '' }>10</option>
			<option value="15" ${param.pageSize == "15"? 'selected' : '' }>15</option>
			<option value="20" ${param.pageSize == "20"? 'selected' : '' }>20</option>
		</select>
	</div>
	</p>
</form>
</div>
<div class="container">
	<table class="table table-boardered table-hover">
		<thead>
		<tr><th>번호</th><th>제  목</th><th>작성자</th><th>작성일자</th><th>조회수</th></tr>
		</thead>
		<tbody>
		<!-- not empty boardList -->
		<c:if test="${not empty boardList}">
		<c:forEach var="board" items="${boardList}" varStatus="status">
		<c:url var="viewURL" value="boardView/${board.bo_seq_no}">
		</c:url>
		<tr>
			<td>${status.index + pagingUtil.startRow}</td>
			<td><a href="${viewURL}">${board.bo_title }</a></td>
			<td>${board.bo_writer_name}(${board.bo_writer_sec })</td>
			<td>${board.reg_date }</td>
			<td>${board.bo_hit_cnt }</td>
		</tr>		
		</c:forEach>
		</c:if>
		<!-- //not empty boardList -->
		
		<!-- empty boardList -->
		<c:if test="${empty boardList}">
		<tr><td colspan="5" align="center">게시글이 존재하지 않습니다.</td></tr>
		</c:if>
		<!-- //empty boardList -->
		</tbody>
	</table>
	<!-- 페이지 네비게이션 -->
	<div class="text-center">
	<ul class="pagination" >
		${pagingUtil.pageHtml}
	
<!-- 	  <li><a href="#">&lt;</a></li> -->
<%-- 	<c:forEach var="i" begin="1" step="1" end="5"> --%>
<%-- 	<c:url var="pageURL" value="boardList.do"> --%>
<%-- 		<c:param name="page" value="${i}"></c:param> --%>
<%-- 	</c:url> --%>
<%-- 	<c:if test="${param.page == i}"> --%>
<%-- 	  <li><a href="${pageURL}"><b>${i}</b></a></li>	 --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${param.page != i}"> --%>
<%-- 	  <li><a href="${pageURL}">${i}</a></li>	 --%>
<%-- 	</c:if> --%>
<%-- 	 </c:forEach> --%>
<!-- 	  <li><li><a href="#">&gt;</a></li> -->
	</ul>
	
	<!-- 페이지네비게이션 끝 -->
	</div>
<!-- container끝 -->
</div>
<div class="footer" align="right">

<input class="btn-default" type="button" value="글쓰기" onclick="fn_new()"  style="WIDTH: 100px"/>
</div>
</body>
</html>