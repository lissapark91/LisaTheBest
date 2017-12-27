<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<style>

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
	width: 100%;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 450px
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
/* footer {
	
} */

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
div.homeBoard {
	width: 360px;
	height: 400px;
	float: left;
	margin-left: 30px;
	padding: 5px;
	text-align: center;
}
div.homeBoard table{
	text-align: center;
}
div.logo {
	height: 100px;
	padding: 20px;
}
</style>

<title>bimils.com</title>

</head>
<body>

<div class="container" style="padding: 0; margin: auto;">
	
	<header>
		<tiles:insertAttribute name="header"/>
	</header>
	
	<nav class="navbar navbar-default">
		<tiles:insertAttribute name="nav"/>
	</nav>
	
	<!-- <article> -->
	<div class="container-fluid" style="min-height: 400px;">
		<tiles:insertAttribute name="body"/>
	</div>
	<!-- </article> -->
		
	<footer class="container-fluid">
		<tiles:insertAttribute name="footer"/>
	</footer>

</div>


</body>
</html>
