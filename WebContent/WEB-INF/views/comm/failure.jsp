<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>ERROR</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" media="screen">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
 <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
 <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
 <![endif]-->
<script type="text/javascript"
	src=" <%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src=" <%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<style type="text/css">
</style>
</head>
<body>
	<!--//////////////////////////////////////////////   头  -->
	<%@ include file="./header.jsp"%>
	<div class="container">
		<h1>&nbsp;</h1>
		<h1>&nbsp;</h1>
		<div class="col-md-2 col-md-offset-5">
			<img class="img-responsive"
				src="<%=request.getContextPath()%>/img/failure.gif" alt="failure">
			<h4 style="text-align: center">ERROR: ${message}</h4>
		</div>

	</div>
</body>
</html>