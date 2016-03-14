<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!--发表评论  -->
<li class="list-group-item">
	<form action="<c:url value="/comment/remark.do" />" method="post">
		<input type="hidden" name="itemid" value="${item.iid }">
		<div align="center">
			<textarea cols="75%" rows="4" name="content" placeholder="点击此处评论，至多250字" maxlength="250"></textarea>
			<br> <input type="submit" id="submit" value="提交" class="btn btn-info btn-md">
		</div>
	</form>
</li>
