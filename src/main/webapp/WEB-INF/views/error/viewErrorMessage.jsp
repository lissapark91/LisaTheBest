<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러 페이지</title>
</head>
<body>
<!-- exception 기본 객체는 페이지에 isErrorPage=true 페이지 디렉토리가 설정되어 있어야 사용가능. -->
에러타입 : <%= exception.getClass().getName() %> <br />
에러 메세지 : <%= exception.getMessage() %> <br />

<p>
요청 처리 과정에서 예외가 발생했습니다. <br />
빠른 시간 내에 문제를 해결하겠습니다. <br />
</p>
</body>
</html>