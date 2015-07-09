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
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/help/roleauth.do">角色权限</a></li>
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/help/demo.do">操作演示</a></li>
				<li role="presentation" class="active"><a
					href="<%=request.getContextPath()%>/help/install.do">安装说明</a></li>
			</ul>

			<div class="panel  col-md-12"></div>
			<div class="panel  col-md-12"></div>
		</div>

		<!--//////////////////////////////////////////////    右侧  -->
		<div class="panel panel-default col-md-7">
			<ul class="list-group col-md-12 ">

				<!--4.安装说明  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">安装说明</a></li>
						<li class="active">安装要求</li>
					</ol>
					<blockquote class="initialism">
						<p style="font-size: 15">
							<abbr title="attribute" style="font-weight: bold;">请确保安装该网站的机器上，存在下列软件，并且软件能够正常运行</abbr>
						</p>
						<ul style="font-size: 5">
							<li>Tomcat &nbsp;<span class="glyphicon glyphicon-ok"></span>
							</li>
							<li>Mysql &nbsp;<span class="glyphicon glyphicon-ok"></span>
							</li>
						</ul>
					</blockquote>
				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">安装说明</a></li>
						<li class="active">安装步骤</li>
					</ol>
					<blockquote class="">
						<p style="font-size: 15">
							<abbr title="attribute" style="font-weight: bold;">安装步骤如下：</abbr>
						</p>
						<ul style="font-size: 5">
							<li>启动Mysql后，执行系统自带的shake_init.sql</li>
							<li>将shake.war包部署到Tomcat路径下的<abbr title="attribute"
								style="font-weight: bold;">“webapps”</abbr>路径下，并且启动Tomcat
							</li>
							<li>Tomcat启动后，打开浏览器，输入tomcat路径，确保Tomcat运行成功后，在后面的地址加上<abbr
								title="attribute" style="font-weight: bold;">“/shake”</abbr>，进入本站
							</li>
							<li>初次登录，账号密码”admin”，创始人权限，请及时修改密码</li>
							<li>根据需求，添加角色若干</li>
							<li>根据需求，添加商品类型若干</li>
							<li>根据帮助文档，做各种演示，如果出现问题，请及时与我们<a
								href="mailto:454143003@qq.com"> 联系 </a></li>
							<li>简单测试无问题后，开放系统。</li>
						</ul>
					</blockquote>
				</li>


			</ul>
		</div>
	</div>


</body>
</html>