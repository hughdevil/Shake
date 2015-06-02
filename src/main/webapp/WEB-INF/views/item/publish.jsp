<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src=" <%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item publish</title>
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
													.getElementById("newUpload").innerHTML += '<div id="div_'+j+'"><input  name="itemImages" type="file"  /><input type="button" value="删除"  onclick="del('
													+ j + ')"/></div>';
											j = j + 1;
										});
					});

	function del(o) {
		document.getElementById("newUpload").removeChild(
				document.getElementById("div_" + o));
	}
</script>
</head>
<body>
	<form  id="itemfrom" enctype="multipart/form-data" action="<c:url value="/item/publish.do" />"  method="post" >
		标题 ：<input type="text" name="title" /><br> 
		价格 ： <input type="text" name="price" /><br>
		数量：<input type="text" name="number" /><br>
		描述：<textarea rows="5" cols="50" name="desc"></textarea><br>
		有效：是<input type="radio" name="isvalid"  value="true"/>否<input type="radio" name="isvalid"  value="false"/><br>
		商品类型：
		<select name="itemtype">
		<c:forEach items="${itemtypes }" var="itemtype">
			<option value="${itemtype.tid}">${itemtype.tname}</option>
		</c:forEach>
		</select><br>
		上传图片：
		<div id="newUpload">
			<input type="file" name="itemImages">
		</div>
		<input type="button" id="btn_add" value="增加一行"> <br>
		<input type="submit"  id ="submit"  value="提交">
	</form>
</body>
</html>