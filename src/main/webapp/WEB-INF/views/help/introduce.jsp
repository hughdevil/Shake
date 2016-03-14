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
			<div class="panel  col-md-12">
				<ul class="nav nav-pills  nav-stacked">
					<li role="presentation" class="active"><a
						href="<%=request.getContextPath()%>/help/introduce.do">系统介绍</a></li>
					<li role="presentation"><a
						href="<%=request.getContextPath()%>/help/roleauth.do">角色权限</a></li>
					<li role="presentation"><a
						href="<%=request.getContextPath()%>/help/demo.do">操作演示</a></li>
					<li role="presentation"><a
						href="<%=request.getContextPath()%>/help/install.do">安装说明</a></li>
				</ul>
			</div>
			<div class="panel  col-md-12"></div>
		</div>

		<!--//////////////////////////////////////////////    右侧  -->
		<div class="panel panel-default col-md-7">
			<ul class="list-group col-md-12 ">


				<!--1.系统介绍  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li class="active">系统介绍</li>
					</ol>
					<blockquote class="initialism">
						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							随着互联网上本地化电子商务的发展，信息和实物之间、线上和线下之间的联系变得愈加紧密，对于收入有限的校园内的学生群体来说，有些物品用过之后就会被闲置，而同时得到一件可以满足自己需求的商品二手商品往往更加经济实惠。比如手机、电脑、数码产品及配件，校园代步工具，服装伞帽，电器，运动健身器材，图书教材，租赁等都可以通过O2O模式迅速达成交易，满足卖家买家双方的需求。本校现有的跳蚤市场仅仅是校园论坛下的一个子模块，商品交易信息发布全靠发帖，容易造成信息混乱，买家不易寻找自己想要的东西；没有规范的展示模式，卖家无法把自己的商品全面展示。

						</p>
					</blockquote>
					<blockquote class="initialism">
						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							西大甩甩项目旨在为广大师生提供一个便捷的二手交易平台，为用户提供全面有效的信息，使用户可以快速买到合适的二手商品或是快速把自己闲置的东西卖出去。同时，有效防止虚假信息或过期信息对用户造成干扰。为广大师生的生活提供一定的便利。
						</p>
					</blockquote>
				</li>
			</ul>
		</div>
	</div>


</body>
</html>