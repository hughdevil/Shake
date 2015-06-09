
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>基本CSS样式 · Bootstrap</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Bootstrap是Twitter推出的一个用于前端开发的开源工具包。它由Twitter的设计师Mark Otto和Jacob Thornton合作开发，是一个CSS/HTML框架。Bootstrap中文网致力于为广大国内开发者提供详尽的中文文档、代码实例等，助力开发者掌握并使用这一框架。">
<meta name="keywords"
	content="Bootstrap,CSS,CSS框架,CSS framework,javascript,bootcss,bootstrap开发,bootstrap代码,bootstrap入门">
<meta name="author" content="Bootstrap中文网">

<!-- Le styles -->
<link
	href="http://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/docs.css" rel="stylesheet">
<style>
body {
	font-family: "ff-tisa-web-pro-1", "ff-tisa-web-pro-2", "Lucida Grande",
		"Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB",
		"Hiragino Sans GB W3", "Microsoft YaHei UI", "Microsoft YaHei",
		"WenQuanYi Micro Hei", sans-serif;
}
</style>
<link href="assets/js/google-code-prettify/prettify.css"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="assets/ico/favicon.png">

</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar">

	<!-- Navbar
    ================================================== -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="./index.html">Bootstrap</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class=""><a href="./index.html">首页</a></li>
						<li class=""><a href="./getting-started.html">起步</a></li>
						<li class=""><a href="./scaffolding.html">脚手架</a></li>
						<li class="active"><a href="./base-css.html">基本CSS样式</a></li>
						<li class=""><a href="./components.html">组件</a></li>
						<li class=""><a href="./javascript.html">JavaScript插件</a></li>
						<li class=""><a href="./customize.html">定制</a></li>
						<li><a href="http://expo.bootcss.com" target="_blank">网站实例</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>


	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>

	<script src="http://cdn.bootcss.com/holder/2.0/holder.min.js"></script>
	<script src="assets/js/google-code-prettify/prettify.js"></script>

	<script src="assets/js/application.js"></script>
	<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3Ff8075d186077efcfbc077b500516e94a' type='text/javascript'%3E%3C/script%3E"));
	</script>

	<!-- 全局统计代码 -->
	<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F079fac161efc4b2a6f31e80064f14e82' type='text/javascript'%3E%3C/script%3E"));
	</script>



</body>
</html>
