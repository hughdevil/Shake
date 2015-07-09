<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
<head>
<title>Help</title>
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

blockquote {
	word-wrap: break-word; /* ie */
	overflow: hidden;
}
</style>
</head>
<body>
	<!--//////////////////////////////////////////////   头  -->
	<%@ include file="../comm/header.jsp"%>
	<div class="container">

		<!--//////////////////////////////////////////////    左侧-->
		<div class="panel panel-default col-md-3 col-md-offset-1">
			<div class="panel  col-md-12"></div>
			<div class="panel  col-md-12"></div>

			<ul class="nav nav-pills  nav-stacked">
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/help/introduce.do">系统介绍</a></li>
				<li role="presentation" class="active"><a
					href="<%=request.getContextPath()%>/help/roleauth.do">角色权限</a></li>
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/help/demo.do">操作演示</a></li>
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/help/install.do">安装说明</a></li>
			</ul>

			<div class="panel  col-md-12"></div>
			<div class="panel  col-md-12"></div>
		</div>

		<!--//////////////////////////////////////////////    右侧  -->
		<div class="panel panel-default col-md-7">
			<ul class="list-group col-md-12 ">



				<!--2.角色权限  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li class="active">角色权限</li>
					</ol>
					<blockquote class="initialism">
						<p>本系统系统的主要使用人员为分为游客、注册会员、管理人员三类。</p>
					</blockquote>
				</li>

				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">角色权限</a></li>
						<li class="active">游客</li>
					</ol>
					<blockquote class="initialism">
						<p style="font-size: 15">
							<abbr title="attribute" style="font-weight: bold;">游客</abbr>：未登录用户。
						</p>
						<ul style="font-size: 5">
							<li>浏览一些已发布的二手商品信息（不包括发布人的联系方式） &nbsp;<span
								class="glyphicon glyphicon-ok"></span>
							</li>
						</ul>
					</blockquote>
				</li>

				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">角色权限</a></li>
						<li class="active">注册会员</li>
					</ol>
					<blockquote class="initialism">
						<p style="font-size: 15">
							<abbr title="attribute" style="font-weight: bold;">注册会员</abbr>：通过正确注册流程后的角色。
						</p>
						<ul style="font-size: 5">
							<li>发布自己的二手商品，并且提供自己发布商品的修改、删除操作 &nbsp;<span
								class="glyphicon glyphicon-ok"></span></li>
							<li>修改自己的个人信息、密码等 &nbsp;<span class="glyphicon glyphicon-ok"></span></li>
							<li>搜索已发布的二手商品 &nbsp;<span class="glyphicon glyphicon-ok"></span></li>
							<li>查询商品发布人的联系方式 &nbsp;<span class="glyphicon glyphicon-ok"></span></li>
							<li>访问注册会员的主页 &nbsp;<span class="glyphicon glyphicon-ok"></span></li>
						</ul>

					</blockquote>
				</li>


				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">角色权限</a></li>
						<li class="active">管理人员</li>
					</ol>
					<blockquote class="initialism">
						<p style="font-size: 15">
							<abbr title="attribute" style="font-weight: bold;">管理人员</abbr>：本站的管理群体，分为四种，权限由低到高。
						</p>
						<p align="center"
							style="font-style: italic; font-size: 15; color: blue;">
							版主 &nbsp;<span class="glyphicon glyphicon-chevron-left"></span>
							&nbsp; 站务 &nbsp;<span class="glyphicon glyphicon-chevron-left"></span>
							&nbsp; 管理员 &nbsp;<span class="glyphicon glyphicon-chevron-left"></span>
							&nbsp; 创始人员
						</p>

						<ul style="font-size: 5">
							<li>版主在普通注册会员的权限基础上，追加二手商品的删除、修改操作。（仅可以删除

								、修改发布人比自己角色权限低的二手商品）&nbsp;<span class="glyphicon glyphicon-ok"></span>
							</li>
							<li>站务在版主的权限基础上，追加对注册会员信息的修改、删除操作。（仅可以删除、 修改比自己角色权限低的注册会员）
								&nbsp;<span class="glyphicon glyphicon-ok"></span>
							</li>
							<li>管理员在站务的权限基础上、追加角色和商品种类的增加、修改、删除的操作。 &nbsp;<span
								class="glyphicon glyphicon-ok"></span></li>
							<li>创始人员与管理员权限相同。（因权限等级大于管理员，可对管理员进行增、删操 作） &nbsp;<span
								class="glyphicon glyphicon-ok"></span></li>
						</ul>

					</blockquote>
				</li>

			</ul>
		</div>
	</div>


</body>
</html>