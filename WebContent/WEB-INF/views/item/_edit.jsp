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
	<form  id="itemfrom" enctype="multipart/form-data" action="<c:url value="/item/edit.do" />"  method="post" >
		<input type="hidden" name="iid"  value="${item.iid }"/>
		标题 ：<input type="text" name="title"  value="${item.iname }"/><br> 
		价格 ： <input type="text" name="price" value="${item.iprice }"/><br>
		数量：<input type="text" name="number" value="${item.iNumber }"/><br>
		描述：<textarea rows="5" cols="50" name="desc" >${item.idesc }</textarea><br>
		有效（重新选定）：是<input type="radio" name="isvalid"  value="true"  />否<input type="radio" name="isvalid"  value="false"/><br>
		商品类型：${item.itemtype.tid }
		<select name="itemtype">
			<c:forEach items="${itemtypes }" var="it">
				<c:choose>
					<c:when test='${it.tid==item.itemtype.tid}' >
						<option value="${it.tid}" selected="selected" >${it.tname}</option>
					</c:when>
					<c:otherwise>
						<option value="${it.tid}" >${it.tname}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select><br>
		
		<h2>图片：</h2><br>
		<c:forEach items="${itemimages }"  var="img">
			<input type="checkbox" name="selectedimages" value="${img.iiid }">  
			<img alt="${img.iiname }" src=" <%=request.getContextPath()%>/${img.iiname}"><br>
		</c:forEach>
		
		上传图片：
		<div id="newUpload">
			<input type="file" name="newimages">
		</div>
		<input type="button" id="btn_add" value="增加一行"> <br>
		<input type="submit"  id ="submit"  value="提交">
	</form>
</body>
</html>