<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style type="text/css">
body {
	position: relative;
	padding-top: 65px;
}
</style>


<nav class="navbar navbar-inverse navbar-static-top navbar-fixed-top"
	role="navigation">
	<div class="container container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand "
				href="<%=request.getContextPath()%>/item/post.do"
				style="color: window;"> <span class=" glyphicon glyphicon-grain"></span><font
				size="5">&nbsp;西大甩甩&nbsp;</font>
			</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<%=request.getContextPath()%>/item/publish.do"><span
						class=" glyphicon glyphicon-plus"></span>&nbsp;&nbsp;发布新商品</a></li>


				<!--角色下拉菜单  -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"><span
						class=" glyphicon glyphicon-tower"></span>&nbsp;&nbsp;角色 <span
						class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<%=request.getContextPath()%>/role/add.do">新增</a></li>
						<li><a href="<%=request.getContextPath()%>/role/list.do">列表</a></li>
					</ul></li>

				<!--商品类型下拉菜单  -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"><span
						class=" glyphicon glyphicon-th"></span>&nbsp;&nbsp;商品类型 <span
						class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<%=request.getContextPath()%>/item/type/add.do">新增</a></li>
						<li><a href="<%=request.getContextPath()%>/item/type/list.do">列表</a></li>
					</ul></li>
			</ul>


			<!--模糊搜索  -->
			<form class="navbar-form navbar-left" role="search"
				action="<%=request.getContextPath()%>/
				item/query.do"
				method="post">
				<div class="input-group input-group-sm ">
					<input type="text" name="iname" class="form-control"
						placeholder="请输入搜索标题名"> <span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</form>



			<c:choose>
				<c:when test="${empty user }">
					<div class="nav navbar-nav navbar-right">
						<a href="<%=request.getContextPath()%>/user/register.do"
							class="btn btn-default navbar-btn btn-sm">注册 </a> <a
							href="<%=request.getContextPath()%>/user/login.do"
							class="btn btn-default navbar-btn btn-success btn-sm"
							data-toggle="modal" data-target="#myModal">登录</a><a>&nbsp; </a> <a
							href="<%=request.getContextPath()%>/help.do"><span
							class="glyphicon glyphicon-question-sign" style="width: 40"></span></a>
					</div>

				</c:when>
				<c:otherwise>
					<div class="navbar-right">
						<ul class="nav navbar-nav">
							<li class="dropdown"><a
								class="navbar-brand dropdown-toggle " role="button"
								role="button" data-toggle="dropdown"> <span
									class="glyphicon glyphicon-user" style="color: green"></span></a>
								<ul class="dropdown-menu" style="text-align: left">
									<li><a><span class="glyphicon glyphicon-home"
											style="width: 40"></span>${user.name}</a></li>
									<li class="divider"></li>
									<li><a
										href="<%=request.getContextPath()%>/user/${user.uid }/home.do"><span
											class=" glyphicon glyphicon-cog" style="width: 40"></span>个人
									</a></li>
									<li><a href="<%=request.getContextPath()%>/user/repwd.do"><span
											class="glyphicon glyphicon-refresh glyphicon glyphicon-refresh"
											style="width: 40"></span>密码</a></li>
									<li class="divider"></li>
									<li><a href="<%=request.getContextPath()%>/help.do"><span
											class="glyphicon glyphicon-question-sign" style="width: 40"></span>帮助</a></li>
									<li class="divider"></li>
									<li><a
										href="<%=request.getContextPath()%>/user/logout.do "><span
											class="glyphicon glyphicon-log-out" style="width: 40"></span>退出</a></li>
								</ul></li>
						</ul>
					</div>

				</c:otherwise>
			</c:choose>

		</div>
	</div>
</nav>
<!-- 登录框 -->
<div class="modal fade " id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog " style="width: 400">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<span class=" glyphicon glyphicon-user"></span>&nbsp;&nbsp;登录
				</h4>
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