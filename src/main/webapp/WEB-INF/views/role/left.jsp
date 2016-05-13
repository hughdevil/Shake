<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--顶部  -->
<div class="page-header" align="center">
	<h2>
		<strong>角色</strong> <a tabindex="0" class="btn btn-sm" role="button" data-toggle="popover" data-trigger="focus" title="商品类型说明" data-content="加号：添加新类型。叉号：删除。笔头：修改"> <small>说明</small></a>
	</h2>
</div>

<!--左侧  -->
<div class="panel panel-default col-md-5 col-md-offset-1">
	<ul class="list-group col-md-12 ">

		<li class="list-group-item">
			<div class="table-responsive">
				<blockquote>
					<table class="table table-hover">
						<tr class="active">
							<td width="60%"><span class="glyphicon glyphicon-list"></span> 列表</td>
							<td width="20%"></td>
							<td width="20%"><a href="<%=request.getContextPath()%>/role/add.do"><span class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="right" title="点击新增"></span></a></td>
						</tr>

						<c:forEach var="role" items="${roles }">
							<tr>
								<td width="90%">${role.rname }</td>
								<td width="5%"><a href="<%=request.getContextPath()%>/role/${role.rid}/edit.do"><span class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="right" title="点击修改"></span> </a></td>
								<td width="5%"><a data-toggle="modal" data-target="#modal${role.rid}"><span class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="right" title="点击删除"></span> </a></td>
							</tr>

							<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="modal${role.rid}" aria-labelledby="mySmallModalLabel">
								<div class="modal-dialog modal-sm">
									<div class="modal-content">
										<div class="modal-header">
											&nbsp; <span class="glyphicon glyphicon-exclamation-sign" style="color: red; text-align: left;"></span>
										</div>
										<div class="modal-body">
											<h5>您确认要执行该操作吗？该操作将删除该角色！该角色的用户将为空！</h5>
										</div>
										<div class="modal-footer">
											<a href="<%=request.getContextPath()%>/role/${role.rid}/del.do" class="btn btn-danger">确认</a>
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>

						</c:forEach>
					</table>
				</blockquote>
			</div>
		</li>

	</ul>
</div>

<script>
	function check() {
		document.getElementById('submit').disabled = 'disabled';
	}

	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})

	$(function() {
		$('[data-toggle="popover"]').popover()
	})

	$('#example').popover(options)
</script>