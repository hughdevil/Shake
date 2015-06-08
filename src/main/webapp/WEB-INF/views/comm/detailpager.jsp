<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="right">
	<ul class="pagination">
		<li><a href="">1</a></li>
		<li><a
			href="<%=request.getContextPath() %>/item/post.do?page=${pager.prePage}">&laquo;</a></li>
		<li><a class="active disabled">${pager.currentPage }</a></li>
		<li><a
			href="<%=request.getContextPath() %>/item/post.do?page=${pager.nextPage}">&raquo;</a></li>
		<li><a
			href="<%=request.getContextPath() %>/item/post.do?page=${pager.totalPages}">${pager.totalPages }</a></li>
	</ul>
</div>
