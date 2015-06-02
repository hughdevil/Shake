<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<nav class="navbar navbar-default " role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<%=request.getContextPath()%>/home.jsp">西大甩甩</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="<%=request.getContextPath()%>/item/post.do">最新商品</a></li>
			<li><a href="#">Link</a></li>
		</ul>
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>

		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${empty user }">
					<a href="<%=request.getContextPath()%>/user/login.do"
						class="btn btn-default navbar-btn btn-primary" data-toggle="modal"
						data-target="#myModal">登录</a>
					<a href="<%=request.getContextPath()%>/user/register.do"
						class="btn btn-default navbar-btn">注册 </a>
					<a>&nbsp; </a>
				</c:when>
				<c:otherwise>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">${user.name } <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">个人资料</a></li>
							<li class="divider"></li>
							<li><a href="#">商品</a></li>
							<li><a href="#">讨论</a></li>
							<li class="divider"></li>
							<li><a href="<%=request.getContextPath()%>/user/logout.do ">退出</a></li>
						</ul></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 登录框 -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog " style="width: 400" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">登录</h4>
				</div>
				<form method="post"
					action="<%=request.getContextPath()%>/user/login.do"
					class="form-horizontal" role="form">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">用户名：</label>
							<div class="col-sm-7">
								<input name="name" id="name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">密码：</label>
							<div class="col-sm-7">
								<input type="password" id="password" name="password"
									class="form-control" placeholder="Password" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Sign in</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</nav>
