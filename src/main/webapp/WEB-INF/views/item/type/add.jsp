<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>添加新类型</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" media="screen">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
 <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
 <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
 <![endif]-->
<script type="text/javascript" src=" <%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src=" <%=request.getContextPath()%>/js/bootstrap.min.js"></script>


</head>
<body>
	<!--头  -->
	<%@ include file="../../comm/header.jsp"%>
	<div class="container">

		<!--左侧  -->
		<%@ include file="./left.jsp"%>

		<!--右侧  -->
		<form:form class="panel panel-default col-md-5" modelAttribute="itemtype" method="post" onsubmit="return check();">
			<ul class="list-group">

				<li class="list-group-item">
					<h3 align="center">
						<span class="glyphicon glyphicon-plus"></span>
					</h3>
				</li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-4">
							<label for="" class="control-label " style="float: right">类型名：</label>
						</div>
						<div class="col-md-6">
							<form:input path="tname" type="text" class="form-control " required="required" maxlength="4" placeholder="新类型的名称，限4字" />
						</div>
					</div></li>

				<li class="list-group-item"><div class="row">
						<div class="col-md-4">
							<label for="" class="control-label " style="float: right">描述：</label>
						</div>
						<div class="col-md-6">
							<form:textarea path="tdesc" type="text" class="form-control " required="required" maxlength="100" rows="4" cols="100%" placeholder="新类型的描述，限100字" />
						</div>
					</div></li>

				<li class="list-group-item">
					<div align="center">
						<div class="btn-group">
							<input type="reset" class="btn btn-danger btn-sm" value="重置"> <input type="submit" class="btn btn-warning btn-sm" id="submit" value="确认无误，注册">
						</div>
					</div>
				</li>
			</ul>
		</form:form>


		<form class="form-horizontal col-md-10 col-md-offset-1 " id="itemfrom" enctype="multipart/form-data" action="<c:url value="/user/register.do" />" method="post"></form>
	</div>
	<script>
		function check() {
			document.getElementById('submit').disabled = 'disabled';
		}
	</script>
</body>
</html>