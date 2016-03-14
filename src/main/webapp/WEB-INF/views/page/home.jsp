<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="right">
	<ul class="pagination ">
		<li><a href=""><strong>1</strong></a></li>
		<li><a href="<%=request.getContextPath() %>/user/${uid}/home.do?page=${pager.prePage}"><strong>&laquo;</strong></a></li>
		<li><a class="active disabled"><strong>${pager.currentPage }</strong></a></li>
		<li><a href="<%=request.getContextPath() %>/user/${uid}/home.do?page=${pager.nextPage}"><strong>&raquo;</strong></a></li>
		<li><a href="<%=request.getContextPath() %>/user/${uid}/home.do?page=${pager.totalPages}"><strong>${pager.totalPages }</strong></a></li>
	</ul>
</div>
