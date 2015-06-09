<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>个人资料</title>
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
<script type="text/javascript">
	$(document).ready(function() {
		$(".onshelfdate").each(function(index) {
			var str = $("#onshelfdate" + index).text();
			$("#onshelfdate" + index).html(str.substr(0, 11));
		});

	});
</script>

</head>
<body>
	<!--头  -->
	<%@ include file="../comm/header.jsp"%>
	<div class="container">

		<!--//////////////////////////////////////////////    左侧 个人资料-->
		<div class="panel panel-default col-md-4 col-md-offset-1">
			<ul class="list-group">
				<li class="list-group-item"><h3 align="center">
						<strong>资料卡片 </strong>
					</h3></li>
			</ul>
			<div class="panel panel-default col-md-12"></div>
			<h4></h4>
			<div class="thumbnail ">
				<img alt="${showuser.headpic }"
					src=" <%=request.getContextPath()%>/${showuser.headpic}">
			</div>

			<div class="panel panel-default col-md-12">
				<ul class="list-group" style="text-align: center">

					<li class="list-group-item">
						<h4></h4>
						<div class="container row">
							<span style="text-align: left;"
								class="col-md-2 glyphicon glyphicon-barcode">：<strong>${showuser.name }</strong>
							</span><label class="
								col-md-1"
								style="width: 40; text-align: right"> <c:choose>
									<c:when test="${showuser.sex==1 }">
										<img alt="male" height="17"
											src="<%=request.getContextPath()%>/img/male.jpg">
									</c:when>
									<c:when test="${showuser.sex==-1 }">
										<img alt="female" height="17"
											src="<%=request.getContextPath()%>/img/female.jpg">
									</c:when>
									<c:otherwise>
										<span class="glyphicon glyphicon-eye-close"
											style="color: #4D4D4D; font-weight: bold;"></span>
									</c:otherwise>
								</c:choose>
							</label>
						</div>
					</li>

					<li class="list-group-item">
						<div class="container row">
							<span class="col-md-2   glyphicon glyphicon-king"
								style="text-align: left;">：<abbr title="角色">${showuser.role.rname }</abbr></span>
						</div>
					</li>


					<li class="list-group-item">
						<div class="container row">
							<span class="col-md-2  glyphicon glyphicon-phone"
								style="text-align: left;">：<abbr title="Phone">${showuser.phone }</abbr></span>
						</div>
					</li>

					<li class="list-group-item"><div class="container row">
							<span class="col-md-2  glyphicon glyphicon-envelope"
								style="text-align: left;">：<a href="mailto:#">${showuser.email }</a></span>
						</div></li>

					<li class="list-group-item"><div class="container row">
							<span class="col-md-2 glyphicon glyphicon-home  "
								style="text-align: left;">：${showuser.addr }</span>
						</div></li>

					<li class="list-group-item"><div class="container row">
							<span class="col-md-2  " style="text-align: left;"><strong>QQ</strong>：${showuser.QQ }</span>
						</div>
						<h4></h4></li>

				</ul>
			</div>

			<div class="panel panel-default col-md-12">
				<ul class="list-group">
					<li class="list-group-item"><div class="container row">
							<span class="col-md-3 glyphicon glyphicon-registration-mark  "
								style="text-align: left;">：${showuser.regDate }</span>
						</div></li>

					<li class="list-group-item"><div class="container row">
							<span class="col-md-3 glyphicon  glyphicon-log-in  "
								style="text-align: left;">：${showuser.IP }</span>
						</div></li>
				</ul>
			</div>

			<div class="panel panel-default col-md-12">
				<ul class="list-group">
					<li class="list-group-item"><div class="container row">
							<span class="col-md-3  glyphicon glyphicon-cog  "
								style="text-align: left;">： <a
								href="<%=request.getContextPath() %>/user/${showuser.uid}/edit.do"><span
									class="glyphicon glyphicon-pencil"></span> </a> <c:if
									test="${candel }">
									 · <a
										href="<%=request.getContextPath() %>/user/${showuser.uid}/del.do"><span
										class="glyphicon glyphicon-remove"></span> </a>
								</c:if></span>
						</div></li>
				</ul>
			</div>
		</div>

		<!--//////////////////////////////////////////////    右侧发表的商品-->
		<div class="panel panel-default col-md-7">
			<ul class="list-group col-md-12 ">

				<li class="list-group-item"><h3 align="left">发布记录</h3></li>

				<li class="list-group-item">
					<div class="table-responsive">
						<blockquote>
							<table class="table table-hover">
								<tr class="active">
									<td width="70%"><span class="glyphicon glyphicon-list"></span>
										标题</td>
									<td width="40%"></td>
								</tr>
								<c:forEach var="item" items="${items }" varStatus="status">
									<tr>
										<td width="60%"><a
											href="<%=request.getContextPath()%>/item/${item.iid}/detail.do">
												${item.iname }</a></td>
										<td width="40%"><small class="onshelfdate"
											id="onshelfdate${status.count-1 }">
												${item.onshelfdate }</small></td>
									</tr>
								</c:forEach>
							</table>
						</blockquote>
					</div>
				</li>




			</ul>
		</div>


	</div>
</body>
</html>