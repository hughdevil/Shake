<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>商品详情</title>
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
	<!--//////////////////////////////////////////////   头  -->
	<%@ include file="../comm/header.jsp"%>
	<div class="container">

		<!--//////////////////////////////////////////////    左侧-->
		<%@ include file="./left.jsp"%>

		<!--//////////////////////////////////////////////    右侧  -->
		<div class="panel panel-default col-md-7">
			<ul class="list-group col-md-12 ">

				<!--商品名称  -->
				<li class="list-group-item"><h3 align="center">${item.iname }</h3></li>

				<!--商品操作  -->
				<li class="list-group-item">
					<ul>
						<li><strong><abbr title="attribute">${item.itemtype.tname}</abbr></strong> &nbsp;·&nbsp;<c:out value="${fn:substring(item.onshelfdate,0,19)}"></c:out>&nbsp;·&nbsp; <a href="<%=request.getContextPath() %>/item/${item.iid}/edit.do">编辑</a> &nbsp;·&nbsp; <a data-toggle="modal" data-target=".bs-example-modal-sm">下架<small>（删）</small>
						</a> <span style="float: right">&nbsp;&nbsp;&nbsp;</span> <c:choose>
								<c:when test="${isMyCol }">
									<span id="span_collect" style="float: right; color: red;" class="glyphicon glyphicon-heart" data-toggle="tooltip" data-placement="right" title="点击取消收藏"></span>
								</c:when>
								<c:otherwise>
									<span id="span_collect" style="float: right; color: red;" class="glyphicon glyphicon-heart-empty" data-toggle="tooltip" data-placement="right" title="点击收藏"></span>
								</c:otherwise>
							</c:choose></li>
					</ul>
				</li>

				<!--商品介绍  -->
				<li class="list-group-item   ">
					<div class="thumbnail ">
						<img class="img-rounded" alt="${img.iiname }" src=" <%=request.getContextPath()%>/${item.postImage}">
						<div class="caption ">
							<blockquote class="">
								<p>${item.idesc }</p>
							</blockquote>
							<footer style="text-align: right">
								<small> 发布人：</small> <cite title="发布人"><a href="<%=request.getContextPath()%>/user/${item.user.uid }/home.do"> ${item.user.name}</a></cite>&nbsp;&nbsp;&nbsp;&nbsp;
							</footer>
						</div>
					</div>
				</li>


				<!--照片墙  -->
				<li class="list-group-item">
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-primary  btn-lg" disabled="disabled">照片墙&nbsp;</button>
						<button type="button" class="btn btn-default  btn-lg" data-toggle="collapse" data-target="#collapsePics" aria-expanded="false" aria-controls="collapsePics">
							<span class="badge"><%=((Item) request.getAttribute("item")).getItemImages().size()%></span>&nbsp;
						</button>
					</div>
					<div class="collapse in" id="collapsePics">
						<div class="well">
							<div>
								<c:forEach items="${item.itemImages }" var="img">
									<div class="thumbnail ">
										<img alt="${img.iiname }" src=" <%=request.getContextPath()%>/${img.iiname}">
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</li>

				<!--评论区  -->
				<li class="list-group-item">
					<!--评论徽章  -->
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-primary  btn-lg" disabled="disabled">评论区&nbsp;</button>
						<button type="button" class="btn btn-default  btn-lg" data-toggle="collapse" data-target="#collapseComments" aria-expanded="false" aria-controls="collapseComments">
							<span class="badge"> <c:choose>
									<c:when test="${fn:length(item.comments) > 9}">
									9+
								</c:when>
									<c:otherwise>
									${fn:length(item.comments)}
									</c:otherwise>
								</c:choose>
							</span>&nbsp;
						</button>
					</div> <!--遍历评论  -->
					<div class="collapse" id="collapseComments">
						<%@ include file="../comment/comment.jsp"%>
					</div>
				</li>

				<li class="list-group-item">
					<div>
						<a href="<%=request.getContextPath()%>/comment/${item.iid}/all.do?page=1"> <span style="float: right" class="label label-warning">查看全部评论 >>> </span></a> <br>
					</div>
				</li>
				<!--发表评论  -->
				<%@ include file="../comment/remark.jsp"%>

				<li class="list-group-item"></li>
			</ul>
		</div>
	</div>
</body>
</html>