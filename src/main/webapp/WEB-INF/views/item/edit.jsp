<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>编辑商品</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/css/bootstrap-select.min.css"
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-select.min.js"
	charset="UTF-8"></script>

</head>

<body>
	<script type="text/javascript">
		$('.selectpicker').selectpicker({
			style : 'btn-info',
			size : 4
		});
	</script>
	<!--头  -->
	<%@ include file="../comm/header.jsp"%>
	<div class="container">

		<form class="form-horizontal col-md-10 col-md-offset-1 " id="itemfrom"
			enctype="multipart/form-data" onsubmit="return check();"
			action="<c:url value="/item/edit.do" />" method="post">
			<input type="hidden" name="iid" value="${item.iid }" />
			<ul class="list-group">

				<!--标题、分类  -->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<select name="itemtype" class="form-control selectpicker">
								<c:forEach items="${itemtypes }" var="it">
									<c:choose>
										<c:when test='${it.tid==item.itemtype.tid}'>
											<option value="${it.tid}" selected="selected">${it.tname}</option>
										</c:when>
										<c:otherwise>
											<option value="${it.tid}">${it.tname}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-10">
							<input name="title" type="text" class="form-control " required
								maxlength="30" placeholder="标题  /  产品的品名、概要等"
								value="${item.iname }">
						</div>
					</div></li>

				<!-- 价格 -->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" class="control-label " style="float: right"><font
								color="red">*</font> 价格：</label>
						</div>
						<div class="col-md-6">
							<div class="input-group">
								<span class="input-group-addon">￥</span> <input name="price"
									onkeyup="this.value=this.value.replace(/\D/g,'')" type="text"
									class="form-control" required="required" pattern="^[0-9]{1,7}"
									title="正确格式：最大值 9999999  最小值 0" maxlength="9"
									placeholder="最大值 9999999  最小值 0  当前产品的出售价格"
									value="<%=(int) (((Item) request.getAttribute("item")).getIprice())%>">
								<span class="input-group-addon">.00</span>
							</div>
						</div>
					</div></li>

				<!-- 数量 -->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" class="control-label " style="float: right">
								<font color="red">*</font> 数量：
							</label>
						</div>
						<div class="col-md-6">
							<div class="input-group">
								<span class="input-group-addon">共</span> <input type="text"
									onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="3"
									name="number" class="form-control" required
									pattern="^[0-9]{1,3}" title="正确格式：最大值 999  最小值 0"
									class="form-control" placeholder="最大值 999  最小值 0  产品现有数量"
									value="${item.iNumber }"> <span
									class="input-group-addon">台（个、件）</span>
							</div>
						</div>
					</div></li>

				<!-- 是否有效 -->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" class="control-label " style="float: right">
								<font color="red">*</font> 有效（重选）：
							</label>
						</div>
						<div class="col-md-6">
							<div class="radio">
								<label> <input type="radio" name="isvalid" value="ture"
									checked> Yes
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="isvalid" value="false">
									No
								</label>
							</div>
						</div>
					</div></li>


				<!--成色  -->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" class="control-label " style="float: right"><font
								color="red">*</font> 成色：</label>
						</div>
						<div class="row">
							<div class="col-md-6 ">
								<div class="input-group col-md-6">
									<span class="input-group-addon"> <input name="newly"
										value="100" type="radio"
										<c:if test="${item.newly==100 }">checked</c:if>>
									</span> <input type="text" disabled="disabled" class="form-control"
										value="全新">
								</div>
								<div class="input-group col-md-6">
									<span class="input-group-addon"> <input name="newly"
										value="99" type="radio"
										<c:if test="${item.newly==99 }">checked</c:if>>
									</span> <input type="text" disabled="disabled" class="form-control"
										value="99新">
								</div>
								<div class="input-group col-md-6">
									<span class="input-group-addon"> <input name="newly"
										value="90" type="radio"
										<c:if test="${item.newly==90 }">checked</c:if>>
									</span> <input type="text" disabled="disabled" class="form-control"
										value="9成新">
								</div>
								<div class="input-group col-md-6">
									<span class="input-group-addon"> <input name="newly"
										value="80" type="radio"
										<c:if test="${item.newly==80 }">checked</c:if>>
									</span> <input type="text" disabled="disabled" class="form-control"
										value="8成新">
								</div>
								<div class="input-group col-md-6">
									<span class="input-group-addon"> <input name="newly"
										value="70" type="radio"
										<c:if test="${item.newly==70 }">checked</c:if>>
									</span> <input type="text" disabled="disabled" class="form-control"
										value="7成新">
								</div>
								<div class="input-group col-md-6">
									<span class="input-group-addon"> <input name="newly"
										value="60" type="radio"
										<c:if test="${item.newly==60 }">checked</c:if>>
									</span> <input type="text" disabled="disabled" class="form-control"
										value="6成或更低成色">
								</div>
							</div>
						</div>
					</div></li>

				<!--入手时间  -->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" style="float: right" class="control-label "><font
								color="red">*</font> 入手时间：</label>
						</div>
						<div class="col-md-5">
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy MM dd " data-link-field="dtp_input2"
								data-link-format="yyyy-mm-dd">
								<input class="form-control" size="16" type="text" name="hasdate"
									readonly value="${item.hasdate }"> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="hidden" id="" value="" />
						</div>
					</div></li>


				<!-- 描述 -->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" style="float: right" class="control-label "><font
								color="red">*</font> 描述：</label>
						</div>
						<div class="col-md-6">
							<div class="input-group">
								<textarea name="desc" class="form-control" rows="8" cols="100%"
									maxlength="250" required="required">${item.idesc }</textarea>
							</div>
						</div>
					</div></li>

				<!--  已上传照片-->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" style="float: right" class="control-label ">旧照片：</label>
						</div>
						<div class="col-md-6">
							<c:forEach items="${itemimages }" var="img">
								<div class="thumbnail ">
									<img alt="${img.iiname }"
										src=" <%=request.getContextPath()%>/${img.iiname}">
									<div class="caption">
										默认下次继续沿用： <input type="checkbox" name="selectedimages"
											value="${img.iiid }" checked="checked">
									</div>
								</div>
							</c:forEach>
						</div>
					</div></li>



				<!--  上传新照片-->
				<li class="list-group-item"><div class="row">
						<div class="col-md-2">
							<label for="" style="float: right" class="control-label ">
								新照片：</label>
						</div>
						<div class="col-md-6">
							<div class="thumbnail ">
								<img alt="${item.postImage }"
									src=" <%=request.getContextPath()%>/${item.postImage}">
								<div class="caption">原封面（如果要替换封面请直接上传新封面即可）</div>
							</div>

							<div class="input-group">
								<span class="input-group-addon">新封面：</span> <input type="file"
									class="btn btn-default" name="postImage" />
							</div>

							<div class="input-group">
								<div id="newUpload">
									<br>
								</div>
								<br> <input type="button" class="btn btn-success btn-sm"
									id="btn_add" value="新增一张图片" />
							</div>
						</div>
					</div></li>

				<!-- 重置，提交 -->
				<li class="list-group-item"></li>
				<li class="list-group-item">
					<div align="center">
						<div class="btn-group">
							<input type="reset" class="btn btn-danger btn-lg" value="重置">
							<input type="submit" class="btn btn-warning btn-lg" id="submit"
								value="确认无误，发布商品">
						</div>
					</div>
				</li>


			</ul>
		</form>
	</div>


	<!--  //////////////////////////////////////////////////////////////////// -->
	<!--时间选择  -->
	<script type="text/javascript">
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	</script>

	<!--新增图片  -->
	<script type="text/javascript">
		i = 1;
		j = 1;
		$(document)
				.ready(
						function() {
							$("#btn_add")
									.click(
											function() {
												document
														.getElementById("newUpload").innerHTML += '<div id="div_'+j+'" class="btn-group"><input  name="newimages" class="btn control" type="file"  /><input type="button" class="btn btn-default" value="删除"  onclick="del('
														+ j + ')"/><br></div>';
												j = j + 1;
											});
						});

		function del(o) {
			document.getElementById("newUpload").removeChild(
					document.getElementById("div_" + o));
		}
	</script>

	<script>
		function check() {
			document.getElementById('submit').disabled = 'disabled';
		}
	</script>
	<!--  //////////////////////////////////////////////////////////////////// -->
</body>
</html>