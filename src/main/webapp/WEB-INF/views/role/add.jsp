<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>添加新角色</title>
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

		<form:form modelAttribute="role" method="post"
			class="form-horizontal col-md-10 col-md-offset-1 "
			onsubmit="return check();">
			<ul class="list-group">

				<li class="list-group-item">
					<h3 align="center">角色注册</h3>
				</li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">角色名：</label>
						</div>
						<div class="col-md-5">
							<form:input path="rname" type="text" class="form-control "
								required="required" maxlength="4" placeholder="注册角色的名称，至多4字" />
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">等级：</label>
						</div>
						<div class="col-md-2">
							<select class="form-control selectpicker  dropup"
								name="rlevelcode">
								<c:forEach items="${rlevels }" var="level">
									<option value="${level.key}">${level.value}</option>
								</c:forEach>
							</select>
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-3">
							<label for="" class="control-label " style="float: right">描述：</label>
						</div>
						<div class="col-md-5">
							<form:textarea path="rdesc" type="text" class="form-control "
								required="required" maxlength="100" rows="4" cols="100%"
								placeholder="注册角色的描述，至多100字" />
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
		</form:form>


		<form class="form-horizontal col-md-10 col-md-offset-1 " id="itemfrom"
			enctype="multipart/form-data"
			action="<c:url value="/user/register.do" />" method="post"></form>
	</div>
	<script>
		function check() {
			document.getElementById('submit').disabled = 'disabled';
		}
	</script>
</body>
</html>