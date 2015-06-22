<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>修改个人资料</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/css/bootstrap-select.min.css"
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-select.min.js"
	charset="UTF-8"></script>

<script type="text/javascript">
	$('.selectpicker').selectpicker({
		style : 'btn-info',
		size : 4
	});
</script>
</head>
<body>
	<!--头  -->
	<%@ include file="../comm/header.jsp"%>
	<div class="container">
		<form class="form-horizontal col-md-10 col-md-offset-1 "
			enctype="multipart/form-data"
			action="<c:url value="/user/edit.do" />" method="post">
			<input name="uid" type="hidden" value="${upuser.uid }">
			<ul class="list-group">

				<li class="list-group-item">
					<h3 align="center">修改个人资料</h3>
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">用户名：</label>
						</div>
						<div class="col-md-5">
							<input name="uname" type="text" class="form-control "
								placeholder="注册用户的名称，日后可以修改" value="${upuser.name }">
						</div>
					</div></li>

				<c:if test="${!empty roles }">
					<!-- 如果是高等级角色是可以选择本身以下的权限的，但自己不可以选择自己 -->
					<li class="list-group-item"><div class="row">
							<div class="col-md-3">
								<label for="" class="control-label " style="float: right">角色：</label>
							</div>
							<div class="col-md-5">
								<select class="selectpicker form-control" name="rid">
									<c:forEach items="${roles }" var="role">
										<option value="${role.rid}" style="text-align: center;"
											<c:if test="${!empty upuser.role  && upuser.role.rid==role.rid}">selected="selected"
				</c:if>>${role.rname}
										</option>

									</c:forEach>
								</select>
							</div>
						</div></li>
				</c:if>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">性别：</label>
						</div>
						<div class="col-md-5">
							<div class="radio">
								<label> <input type="radio" name="sex" value="male"
									<c:if test="${upuser.sex==1 }"> checked="checked"</c:if>>
									男
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex" value="female"
									<c:if test="${upuser.sex==-1 }"> checked="checked"</c:if>>
									女
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex" value="secret"
									<c:if test="${upuser.sex==0 }"> checked="checked"</c:if>>
									保密
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
								placeholder="请仔细填写，发布商品时的默认联系电话" value="${ upuser.phone}">
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">QQ：</label>
						</div>
						<div class="col-md-5">
							<input name="qq" type="text" class="form-control "
								onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="15"
								placeholder="请仔细填写，发布商品时的默认联系QQ" value="${upuser.QQ }">
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
									placeholder="默认联系邮箱" value="${mail }"> <span
									class="input-group-addon">@</span><input name="mailtype"
									type="text" class="form-control " placeholder="邮箱类型"
									value="${mailtype }">
							</div>

						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">地址：</label>
						</div>
						<div class="col-md-5">
							<input name="addr" type="text" class="form-control "
								placeholder="请仔细填写，发布商品时的默认联系地址" value="${upuser.addr }">
						</div>
					</div></li>

				<!--  照片-->
				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" style="float: right" class="control-label ">头像：</label>
						</div>
						<div class="col-md-3">
							<div class="thumbnail ">
								<img alt="${upuser.headpic }"
									src=" <%=request.getContextPath()%>/${upuser.headpic}">
								<div class="caption">原头像（如果要替换头像请直接上传新封面即可）</div>
							</div>
							<div class="input-group">
								<input type="file" class="btn btn-default" name="hearpic" />
							</div>
						</div>
					</div></li>

				<c:if test="${iscuruser }">
					<!-- 如果不是本用户，密码就不用出现 -->
					<li class="list-group-item"><div class="row">
							<div class="col-md-3">
								<label for="" class="control-label " style="float: right">确认密码：</label>
							</div>
							<div class="col-md-5">
								<input name="psw" type="password" class="form-control "
									placeholder="请输入密码，来确认修改">
							</div>
						</div></li>
				</c:if>

				<li class="list-group-item"></li>
				<li class="list-group-item">
					<div align="center">
						<div class="btn-group">
							<input type="reset" class="btn btn-danger btn-lg" value="重置">
							<input type="submit" class="btn btn-warning btn-lg" id="submit"
								value="确认无误，修改">
						</div>
					</div>
				</li>

			</ul>
		</form>
	</div>
</body>
</html>