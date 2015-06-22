<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>新用户注册</title>
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
			enctype="multipart/form-data"
			action="<c:url value="/user/register.do" />" method="post">

			<ul class="list-group">

				<li class="list-group-item">
					<h3 align="center">用户注册</h3>
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">用户名：</label>
						</div>
						<div class="col-md-5">
							<input name="uname" type="text" class="form-control "
								placeholder="注册用户的名称，日后可以修改">
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">密码：</label>
						</div>
						<div class="col-md-5">
							<input name="psw" type="password" class="form-control "
								placeholder="请输入密码">
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">确认密码：</label>
						</div>
						<div class="col-md-5">
							<input name="repsw" type="password" class="form-control "
								placeholder="请与上述密码保持一致">
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">性别：</label>
						</div>
						<div class="col-md-5">
							<div class="radio">
								<label> <input type="radio" name="sex" value="male">
									男
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex" value="female">
									女
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex" value="secret"
									checked> 保密
								</label>
							</div>
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">手机：</label>
						</div>
						<div class="col-md-5">
							<input name="phone" type="text" class="form-control "
								onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="11"
								placeholder="请仔细填写，发布商品时的默认联系电话">
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">QQ：</label>
						</div>
						<div class="col-md-5">
							<input name="qq" type="text" class="form-control "
								onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="15"
								placeholder="请仔细填写，发布商品时的默认联系QQ">
						</div>
					</div></li>
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">
								邮箱：</label>
						</div>
						<div class="col-md-5">

							<div class="input-group">
								<input name="mail" type="text" class="form-control "
									placeholder="默认联系邮箱"> <span class="input-group-addon">@</span><input
									name="mailtype" type="text" class="form-control "
									placeholder="邮箱类型">
							</div>

						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">地址：</label>
						</div>
						<div class="col-md-5">
							<input name="addr" type="text" class="form-control "
								placeholder="请仔细填写，发布商品时的默认联系地址">
						</div>
					</div></li>

				<!--  照片-->
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" style="float: right" class="control-label ">头像：</label>
						</div>
						<div class="col-md-3">
							<div class="input-group">
								<input type="file" class="btn btn-default" name="hearpic" />
							</div>
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