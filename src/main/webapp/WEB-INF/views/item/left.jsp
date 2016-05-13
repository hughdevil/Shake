<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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


<!--左侧  -->
<div class="panel panel-default col-md-3 col-md-offset-1">
	<ul class="list-group">
		<li class="list-group-item">
			<div class="page-header">
				<h3 align="center">
					<strong> 商品信息</strong>
				</h3>
			</div>
		</li>
	</ul>
	<div class="panel panel-default col-md-12"></div>


	<div class="panel panel-default col-md-12">
		<ul class="list-group">
			<li class="list-group-item" style="text-align: center; color: orange;"><h3>
					<strong> &yen; ${item.iprice }</strong>
				</h3></li>
			<!--成色  -->
			<li class="list-group-item">
				<div class="progress">
					<div class="progress-bar progress-bar-info" style="width:${item.newly }%">
						${item.newly }%
						<c:choose>
							<c:when test="${item.newly==60 }">
										新及其以下
									</c:when>
							<c:otherwise>新</c:otherwise>
						</c:choose>
					</div>
				</div>
			</li>

			<li class="list-group-item">入手时间：${item.hasdate }</li>
			<li class="list-group-item">数量：${item.iNumber }</li>
			<li class="list-group-item">有效： <c:choose>
					<c:when test="${item.valid }">
						<span class="glyphicon glyphicon-ok " style="color: green"></span>
					</c:when>
					<c:otherwise>
						<span class=" glyphicon glyphicon-remove" style="color: red"></span>
					</c:otherwise>
				</c:choose>
			</li>
			<li class="list-group-item">所在园区：${item.user.addr }</li>

		</ul>
	</div>

	<div class="panel panel-default col-md-12">
		<h4 align="center">
			<a href="<%=request.getContextPath()%>/user/${item.user.uid }/home.do">联系方式</a>
		</h4>
	</div>
</div>
<!--引入Modal 和 收藏的js -->
<%@ include file="./modal.jsp"%>
