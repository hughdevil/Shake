<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>商品列表</title>
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
	<%@ include file="../comm/header.jsp"%>
	<div class="container ">

		<!-- 公告 -->
		<!-- <div class="alert alert-warning alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			<strong>公告：!</strong>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		</div> -->

		<!-- 分类 -->
		<ul class="nav nav-pills ">
			<li class="<c:if test="${empty tid}">active</c:if>"><a href="<%=request.getContextPath()%>/item/post.do"><span class="glyphicon glyphicon-list"></span> &nbsp;全部</a></li>
			<c:if test="${empty sessionScope.iname }">
				<c:forEach items="${itemtypes }" var="it">
					<li class='<c:if test="${tid==it.tid }">active  </c:if>'><a href="<%=request.getContextPath() %>/item/post.do?type=${it.tid}">${it.tname }</a></li>
				</c:forEach>
			</c:if>
		</ul>
		<h3></h3>

		<!--单个商品  -->
		<div class="row">
			<c:forEach items="${itemList}" var="item" varStatus="status">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail ">
						<a href="<%=request.getContextPath() %>/item/${item.iid}/detail.do" style="overflow-y: hidden; display: block; height: 250px;"> <img class="img-responsive" src="<%=request.getContextPath() %>/${item.postImage}" alt="${item.iname }">
						</a>
						<div class="caption">
							<h3 style="color: orange;">
								<strong> &yen; ${item.iprice }</strong>
							</h3>
							<h4>
								<small> <c:choose>
										<c:when test="${fn:length(item.iname ) > 10}">
											<c:out value="${fn:substring(item.iname,0,10)}"></c:out>
										</c:when>
										<c:otherwise>${item.iname}</c:otherwise>
									</c:choose></small>
							</h4>
							<p>
								<span style="float: left">${item.user.name}</span> <span style="float: right"><c:out value="${fn:substring(item.onshelfdate,0,16)}"></c:out></span> <br>
							</p>
						</div>

					</div>
				</div>
				<c:if test="${status.count %4 == 0}">
		</div>
		<div class="row">
			</c:if>
			</c:forEach>
		</div>


		<!--分页  -->
		<%@ include file="../page/post.jsp"%>
	</div>
</body>
</html>
