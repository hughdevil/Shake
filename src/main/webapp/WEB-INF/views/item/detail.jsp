<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src=" <%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Detail</title>

</head>
<body>
		标题 ：${item.iname }<br> 
		发布人：${item.user.name}<br>
		价格 ：${item.iprice }<br>
		数量：${item.iNumber }<br>
		描述：${item.idesc }<br>
		有效：${item.valid }<br>
		上架日期：${item.onshelfdate }<br>
		商品类型：${item.itemtype.tname}<br>
		<h2>图片：</h2><br>
		<c:forEach items="${item.itemImages }"  var="img">
			<img alt="${img.iiname }" src=" <%=request.getContextPath()%>/${img.iiname}"><br>
		</c:forEach>
		
		<h2>评论：</h2><br>
		<c:forEach items="${item.comments }"  var="comment">
			${comment.markDate }:${comment.user.name } :${comment.content }<br> 
		</c:forEach>
		
		<h2>发表评论：</h2><br>
		<form action="<c:url value="/comment/remark.do" />" method="post">
			<input type="hidden"  name ="itemid" value="${item.iid }">
			<textarea rows="5" cols="50" name="content"></textarea><br>
			<input type="submit"  id ="submit"  value="提交">
		</form>
		
</body>
</html>