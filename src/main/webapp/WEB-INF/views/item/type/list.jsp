<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>商品类型列表</title>
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
	<%@ include file="../../comm/header.jsp"%>
	<div class="container ">

		<div class="panel panel-default col-md-8 col-md-offset-2">
			<ul class="list-group col-md-12 ">

				<li class="list-group-item"><h3 align="left">商品类型列表</h3></li>

				<li class="list-group-item">
					<div class="table-responsive">
						<blockquote>
							<table class="table table-hover">
								<tr class="active">
									<td width="60%"><span class="glyphicon glyphicon-list"></span>
										名称</td>
									<td width="20%"></td>
									<td width="20%"></td>
								</tr>
								<c:forEach var="itemtype" items="${itemtypes }">
									<tr>
										<td width="60%">${itemtype.tname }</td>
										<td width="20%"><a
											href="<%=request.getContextPath()%>/item/type/${itemtype.tid}/edit.do"><span
												class="glyphicon glyphicon-pencil"></span> </a></td>
										<td width="20%"><a
											href="<%=request.getContextPath()%>/item/type/${itemtype.tid}/del.do"><span
												class="glyphicon glyphicon-remove"></span> </a></td>
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
