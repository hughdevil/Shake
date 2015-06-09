<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>修改密码</title>
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

</head>
<body>
	<!--头  -->
	<%@ include file="../comm/header.jsp"%>
	<div class="container">
		<form class="form-horizontal col-md-10 col-md-offset-1 "
			action="<c:url value="/user/repwd.do" />" method="post">
			<input name="uid" type="hidden" value="${user.uid }">
			<ul class="list-group">

				<li class="list-group-item">
					<h3 align="center">修改个人密码</h3>
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right" >用户名：</label>
						</div>
						<div class="col-md-5">
							<input name="uname" type="text" class="form-control "
								placeholder="注册用户的名称，日后可以修改" disabled="disabled"
								value="${user.name }">
						</div>
					</div></li>


				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">旧密码：</label>
						</div>
						<div class="col-md-5">
							<input name="oldpsw" type="password" class="form-control "
								placeholder="请输入密码">
						</div>
					</div></li>
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">新密码：</label>
						</div>
						<div class="col-md-5">
							<input name="psw" type="password" class="form-control "
								placeholder="如果请输入密码">
						</div>
					</div></li>
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">确认新密码：</label>
						</div>
						<div class="col-md-5">
							<input name="repsw" type="password" class="form-control "
								placeholder="请与上述密码保持一致">
						</div>
					</div></li>

				<li class="list-group-item"></li>
				<li class="list-group-item">
					<div align="center">
						<div class="btn-group">
							<input type="reset" class="btn btn-danger btn-lg" value="重置">
							<input type="submit" class="btn btn-warning btn-lg" id="submit"
								value="确认无误，注册">
						</div>
					</div>
				</li>

			</ul>
		</form>
	</div>
</body>
</html>