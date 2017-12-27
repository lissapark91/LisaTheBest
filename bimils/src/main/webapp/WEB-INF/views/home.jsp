<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>bimils.com에 오신 것을 환영합니다.</title>
</head>
<body>

<div class="homeBoard">
<h3>인 기 글</h3>
	<table class="table table-boardered table-hover">
		<thead>
		<tr><th>순  위</th><th>제  목</th><th>추천수</th></tr>
		</thead>
		<tbody>
		<!-- not empty boardList -->
		<c:if test="${not empty boardList}">
		<c:forEach var="board" items="${boardList}" varStatus="status">
		<c:url var="viewURL" value="boardView/${board.bo_seq_no}">
		</c:url>
		<tr>
			<td>${status.index}</td>
			<td><a href="${viewURL}">${board.bo_title }</a></td>
			<td>${board.bo_up_cnt }</td>
		</tr>		
		</c:forEach>
		</c:if>
	</tbody>
	</table>
</div>


<div class="homeBoard">
<h3>최 신 글</h3>
	<table class="table table-boardered table-hover">
		<thead>
		<tr><th>순  위</th><th>제  목</th><th>추천수</th></tr>
		</thead>
		<tbody>
		<!-- not empty boardList -->
		<c:if test="${not empty boardList}">
		<c:forEach var="board" items="${boardList}" varStatus="status">
		<c:url var="viewURL" value="boardView/${board.bo_seq_no}">
		</c:url>
		<tr>
			<td>${status.index}</td>
			<td><a href="${viewURL}">${board.bo_title }</a></td>
			<td>${board.bo_up_cnt }</td>
		</tr>		
		</c:forEach>
		</c:if>
	</tbody>
	</table>
</div>

</body>
</html>
