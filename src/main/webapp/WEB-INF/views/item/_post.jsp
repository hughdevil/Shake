<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src=" <%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item post</title>
<script type="text/javascript">
</script>
</head>
<body>
	<table>
		<c:forEach items="${itemList}" var="item">
			<tr onclick="">
					<td>${item.iname }<img alt="${item.iname }" src="<%=request.getContextPath() %>/${item.postImage}"  width="50" height="50"></td>
					<td>${item.user.name}</td>
					<td>${item.onshelfdate }</td>
					<td> <a href="<%=request.getContextPath() %>/item/${item.iid}/detail.do">详情</a></td>
					<td> <a href="<%=request.getContextPath() %>/item/${item.iid}/edit.do">编辑</a></td>						
			</tr>
		</c:forEach>
		<tr>
			<td><a href="<%=request.getContextPath() %>/item/post.do?page=1" >1</a>
			<a href="<%=request.getContextPath() %>/item/post.do?page=${pager.prePage}" >上一页</a>
			当前页：${pager.currentPage }
			<a href="<%=request.getContextPath() %>/item/post.do?page=${pager.nextPage}" >下一页</a>
			总页数：<a href="<%=request.getContextPath() %>/item/post.do?page=${pager.totalPages}" >${pager.totalPages }</a></td>
		</tr>
	</table>
</body>
</html>