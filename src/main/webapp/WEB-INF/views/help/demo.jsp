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
				<li role="presentation" class="active"><a
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



				<!--3.操作演示  -->
				<!--3.1用户  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">用户</a></li>
						<li class="active">注册</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/userregister.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击右上方的用户注册按钮。在注册时，用户名、密码都是必须输入的信息，在填写完个人信息之后，点击注册即可。</p>
							</blockquote>
						</div>

					</div>

				</li>

				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">用户</a></li>
						<li class="active">登录</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/userlogin.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击右上角的登录按钮，则出现以下界面，填写相应用户名和密码，点击Sign in即可登陆。</p>
							</blockquote>
						</div>

						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/userlogined.png">

					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">用户</a></li>
						<li class="active">修改个人密码</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/repwd.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击修改密码按钮之后，根据文本框前的提示，输入相应的密码信息之后，点击提交的按钮就可以修改密码。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">用户</a></li>
						<li class="active">查看个人信息</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemhome.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击“个人”按钮、发布人联系地址或发布人名称，即可查看个人详细信息。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">用户</a></li>
						<li class="active">修改个人信息</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/useredit.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击个人信息下方的修改按钮（小笔形状），即进入修改界面，填写完对应的信息，点击确认按钮即可。</p>
							</blockquote>
						</div>


					</div>

				</li>


				<!--3.2商品  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品</a></li>
						<li class="active">首页展示</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itempost.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>登陆成功之后看到就是系统首页。</p>
							</blockquote>
						</div>

					</div>
				</li>

				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品</a></li>
						<li class="active">查看商品详细信息</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemdetail.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击自己感兴趣的商品，就可以查看商品的详细信息。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品</a></li>
						<li class="active">商品评论</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemcomment.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>在评论区输入自己需要向卖家了解的信息，点击提交即可发送给卖家。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品</a></li>
						<li class="active">发布新商品</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itempublish.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击“发布新商品”出现以下页面。填写需要卖出的商品的详细信息，确认信息之后点击“确认无误，发布商品”即可。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品</a></li>
						<li class="active">删除商品</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemdel.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击下架链接，用户可以删除自己发布的商品</p>
							</blockquote>
						</div>


					</div>

				</li>

				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品</a></li>
						<li class="active">商品查找</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemquery.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>在“商品类型”右边的搜索框中，可以进行“模糊查询”和“精确查询”。</p>
							</blockquote>
						</div>


					</div>

				</li>



				<!--3.3角色  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">角色</a></li>
						<li class="active">新增角色</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/roleadd.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击角色按钮，再点击“新增”即可列出新增角色。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">角色</a></li>
						<li class="active">角色列表</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/rolepost.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击角色按钮，再点击“列表”即可列出所有角色。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">角色</a></li>
						<li class="active">角色授权</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/rolegive.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>管理人员可编辑用户资料，在角色一栏中，通过下拉菜单选择角色。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">角色</a></li>
						<li class="active">角色修改</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/roleedit.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击角色按钮，再点击“列表”即可列出所有角色，点击修改按钮管理员就可以将角色给修改。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">角色</a></li>
						<li class="active">删除角色</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/roledel.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击角色按钮，再点击“列表”即可列出所有角色，点击删除按钮管理员就可以将角色给删除。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<!--3.4商品类型  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品类型</a></li>
						<li class="active">新增商品类型</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemtypeadd.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击“商品类型”按钮，然后再点击“新增”管理员就可以增加商品类型。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品类型</a></li>
						<li class="active">显示所有商品类型</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemtypepost.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击“列表”即可查询所有的商品类型。</p>
							</blockquote>
						</div>

					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品类型</a></li>
						<li class="active">删除商品类型</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemtypedel.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击“列表”即可查询所有的商品类型，点击删除按钮管理员就可以将角色给删除。</p>
							</blockquote>
						</div>

					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">商品类型</a></li>
						<li class="active">修改商品类型</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/itemtypeedit.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>点击“列表”即可查询所有的商品类型，点击修改按钮管理员就可以将角色给修改。</p>
							</blockquote>
						</div>

					</div>

				</li>


				<!--3.5其他  -->
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">其他</a></li>
						<li class="active">成功提示</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/success.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>在进行某些操作后，成功的提示。</p>
							</blockquote>
						</div>


					</div>

				</li>
				<li class="list-group-item">
					<ol class="breadcrumb">
						<li><a href="#">帮助</a></li>
						<li><a href="#">操作演示</a></li>
						<li><a href="#">其他</a></li>
						<li class="active">错误提示</li>
					</ol>
					<div class="thumbnail ">
						<img class="img-rounded"
							src=" <%=request.getContextPath()%>/img/demo/error.png">
						<div class="caption ">
							<blockquote class="initialism">
								<p>在进行某些操作后，错误的提示。</p>
							</blockquote>
						</div>


					</div>

				</li>

			</ul>
		</div>
	</div>


</body>
</html>