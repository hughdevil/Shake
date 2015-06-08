<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>商品列表</title>
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
	<div class="container">
		<!--头  -->
		<%@ include file="../comm/header.jsp"%>

		<!-- 公告 -->
		<div class="alert alert-warning alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			<strong>公告：!</strong>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		</div>

		<!-- 分类 -->
		<ul class="nav nav-tabs">
			<li class="<c:if test="${empty tid}">active</c:if>"><a
				href="<%=request.getContextPath()%>/item/post.do">全部</a></li>
			<c:forEach items="${itemtypes }" var="it">
				<li class='<c:if test="${tid==it.tid }">active</c:if>'><a
					href="<%=request.getContextPath() %>/item/post.do?type=${it.tid}">${it.tname }</a></li>
			</c:forEach>
		</ul>

		<!--单个商品  -->
		<div class="row">
			<c:forEach items="${itemList}" var="item">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail ">
						<img class="img-responsive img-thumbnail"
							src="<%=request.getContextPath() %>/${item.postImage}"
							alt="${item.iname }">
						<div class="caption">
							<h2 style="color: red">￥${item.iprice }</h2>
							<h4>
								<a
									href="<%=request.getContextPath() %>/item/${item.iid}/detail.do">
									${item.iname }</a>
							</h4>
							<p style="text-align: right">${item.user.name}&nbsp;&nbsp;
								${item.onshelfdate }</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>


		<!--分页  -->
		<%@ include file="../comm/postpager.jsp"%>
	</div>
</body>
</html>
