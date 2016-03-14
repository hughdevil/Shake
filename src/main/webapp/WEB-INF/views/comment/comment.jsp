<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!--遍历评论  -->
<ul class="media-list">
	<c:forEach items="${item.comments }" var="comment">
		<li></li>
		<li class="media"><a class="pull-left" href="<%=request.getContextPath()%>/user/${comment.user.uid }/home.do"> <img class="media-object" height="64px" width="64px" src="<%=request.getContextPath()%>/${comment.user.headpic}" alt="">
		</a>
			<div class="media-body">
				<h4 class="media-heading">${comment.user.name }
					<small> <c:out value="${fn:substring(comment.markDate,0,19)}"></c:out></small>
					<c:if test="${comment.user.uid == user.uid || isADMIN == true }">
						<h6>
							<a data-toggle="modal" data-target="#modaldel${comment.cid}"> <span style="float: right" class="glyphicon glyphicon-remove"></span>
							</a> <span style="float: right">&nbsp;&nbsp;&nbsp;</span> <a data-toggle="modal" data-target="#modaledit${comment.cid}"> <span style="float: right" class="glyphicon glyphicon-pencil"></span></a>
						</h6>

						<!--评论删除拟态窗  -->
						<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="modaldel${comment.cid}" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										&nbsp; <span class="glyphicon glyphicon-exclamation-sign" style="color: red; text-align: left;"></span>
									</div>
									<div class="modal-body">
										<h5>您确认要执行该操作吗？该操作将删除该评论！</h5>
									</div>
									<div class="modal-footer">
										<a href="<%=request.getContextPath()%>/comment/${comment.cid}/del.do" class="btn btn-danger">确认</a>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
						<!--评论修改拟态窗  -->
						<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="modaledit${comment.cid}" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md">
								<div class="modal-content">
									<div class="modal-header">
										&nbsp; <span class="glyphicon glyphicon-exclamation-sign" style="color: red; text-align: left;"></span>
									</div>
									<form action="<c:url value="/comment/${comment.cid}/edit.do" />" method="post">
										<div class="modal-body" align="center">
											<textarea cols="70%" rows="4" name="content" placeholder="点击此处评论，至多250字" maxlength="250">${comment.content }</textarea>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-primary">确认编辑</button>
										</div>
									</form>
								</div>
							</div>
						</div>

					</c:if>
				</h4>
				${comment.content }
			</div></li>
	</c:forEach>
</ul>
