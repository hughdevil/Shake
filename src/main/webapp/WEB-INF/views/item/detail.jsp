<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>商品详情</title>
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

<style type="text/css">
textarea {
	border: 0;
	background-color: transparent;
	color: #666464;
	height: auto;
}
</style>
</head>
<body>
	<div class="container">
		<!--//////////////////////////////////////////////   头  -->
		<%@ include file="../comm/header.jsp"%>

		<!--//////////////////////////////////////////////    左侧-->
		<div class="panel panel-default col-md-3 col-md-offset-1">
			<ul class="list-group">
				<li class="list-group-item"><h4 align="center">商品信息</h4></li>
			</ul>
			<div class="panel panel-default col-md-12"></div>


			<div class="panel panel-default col-md-12">
				<ul class="list-group">
					<li class="list-group-item" style="text-align: center; color: red"><h3>
							<strong>￥ ${item.iprice }</strong>
						</h3></li>
					<!--成色  -->
					<li class="list-group-item">
						<div class="progress">
							<div class="progress-bar progress-bar-info"
								style="width:${item.newly }%">
								${item.newly }%
								<c:choose>
									<c:when test="${item.newly==60 }">
										新及其以下
									</c:when>
									<c:otherwise>新</c:otherwise>
								</c:choose>
							</div>
						</div>
					</li>

					<li class="list-group-item">入手时间：${item.hasdate }</li>
					<li class="list-group-item">数量：${item.iNumber }</li>
					<li class="list-group-item">有效：${item.valid }</li>
					<li class="list-group-item">所在园区：${item.user.addr }</li>

				</ul>
			</div>

			<div class="panel panel-default col-md-12">
				<h4 align="center">
					<a href="#">联系方式</a>
				</h4>
			</div>

		</div>

		<!--//////////////////////////////////////////////    右侧  -->
		<div class="panel panel-default col-md-7">
			<ul class="list-group col-md-12 ">

				<li class="list-group-item"><h3 align="center">${item.iname }</h3>
				</li>

				<li class="list-group-item">
					<ul>
						<li><strong><abbr title="attribute">${item.itemtype.tname}</abbr></strong>
							&nbsp;·&nbsp;${item.onshelfdate }&nbsp;·&nbsp; <a
							href="<%=request.getContextPath() %>/item/${item.iid}/edit.do">编辑</a>
							&nbsp;·&nbsp; <a
							href="<%=request.getContextPath() %>/item/${item.iid}/unload.do">下架<small>（删）</small>
						</a></li>
					</ul>
				</li>

				<li class="list-group-item">
					<div class="thumbnail ">
						<img class="img-rounded" alt="${img.iiname }"
							src=" <%=request.getContextPath()%>/${item.postImage}">
						<div class="caption">
							<blockquote>${item.idesc }</blockquote>
							<footer style="text-align: right">
								———— from： <cite title="发布人"><a href="#">
										${item.user.name}</a></cite>&nbsp;&nbsp;&nbsp;&nbsp;
							</footer>
						</div>
					</div>
				</li>


				<!--照片墙  -->
				<li class="list-group-item">
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-primary  btn-lg"
							disabled="disabled">照片墙&nbsp;</button>
						<button type="button" class="btn btn-default  btn-lg">
							<span class="badge"><%=((Item) request.getAttribute("item")).getItemImages()
					.size()%></span>&nbsp;
						</button>
					</div>
					<div class="well">
						<div>
							<c:forEach items="${item.itemImages }" var="img">
								<div class="thumbnail ">
									<img alt="${img.iiname }"
										src=" <%=request.getContextPath()%>/${img.iiname}">
								</div>
							</c:forEach>
						</div>
					</div>
				</li>

				<!--评论区  -->
				<li class="list-group-item">
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-primary  btn-lg"
							disabled="disabled">评论区&nbsp;</button>
						<button type="button" class="btn btn-default  btn-lg">
							<span class="badge"><%=((Item) request.getAttribute("item")).getComments()
					.size()%></span>&nbsp;
						</button>
					</div>
					<ul class="media-list">
						<c:forEach items="${item.comments }" var="comment">
							<li></li>
							<li class="media"><a class="pull-left" href="#"> <img
									class="media-object"
									src="<%=request.getContextPath()%>/img/user.jpg" alt="...">
							</a>
								<div class="media-body">
									<h4 class="media-heading">${comment.user.name }
										<small> ${comment.markDate }</small>
									</h4>
									<p>${comment.content }</p>
								</div></li>
						</c:forEach>
					</ul>
				</li>

				<!--发表评论  -->
				<li class="list-group-item">
					<form action="<c:url value="/comment/remark.do" />" method="post">
						<input type="hidden" name="itemid" value="${item.iid }">
						<div align="center">
							<textarea cols="75%" rows="4" name="content" placeholder="点击此处评论"></textarea>
							<br> <input type="submit" id="submit" value="提交"
								class="btn btn-info btn-md">
						</div>
					</form>
				</li>
				<li class="list-group-item"></li>
			</ul>
		</div>
	</div>

</body>
</html>