<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.swu.shake.model.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!--商品删除提示拟态框  -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				&nbsp; <span class="glyphicon glyphicon-exclamation-sign" style="color: red; text-align: left;"></span>
			</div>
			<div class="modal-body">
				<h4>&nbsp; &nbsp; &nbsp; 您确认要删除该商品吗？</h4>
			</div>
			<div class="modal-footer">
				<a href="<%=request.getContextPath() %>/item/${item.iid}/unload.do" class="btn btn-danger">确认</a>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!--收藏提示拟态框  -->
<div class="modal fade" id="modal_collect">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					&nbsp; <span class="glyphicon glyphicon-exclamation-sign" style="color: red; text-align: left;"></span>
				</h4>
			</div>
			<div class="modal-body">
				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="modal_collect_body"></span>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!--收藏触发JS  -->
<script type="text/javascript">
$(function() {
	$('[data-toggle="tooltip"]').tooltip()
})

$(document).ready(function(){
	$("#span_collect").on('click',function(){
		var classValue = $("#span_collect").attr("class");
		
		if(classValue == "glyphicon glyphicon-heart-empty"){
			/*收藏  */
			  $.get("<%=request.getContextPath() %>/collect/${item.iid}/collect.do",function(data,status){
				  if(status){
					  $("#span_collect").attr("class","glyphicon glyphicon-heart");
					  $("#span_collect").attr("title","点击取消收藏");
					  $("#span_collect").attr("data-original-title","点击取消收藏");
					  $("#modal_collect_body").text(data.desc);
					  $("#modal_collect").modal();
			    	}else{
			    	  $("#modal_collect_body").text("数据：" + data.status + "\n状态：" + status);
			    	  $("#modal_collect").modal();
			    		
			    	}
			      
			    });
		}else{
			/*取消收藏  */
			  $.get("<%=request.getContextPath() %>/collect/${item.iid}/uncollect.do",function(data,status){
				  if(status){
					  $("#span_collect").attr("class","glyphicon glyphicon-heart-empty");
					  $("#span_collect").attr("title","点击收藏");
					  $("#span_collect").attr("data-original-title","点击收藏");
					  $("#modal_collect_body").text(data.desc);
					  $("#modal_collect").modal();
				   }else{
				      $("#modal_collect_body").text("数据：" + data.status + "\n状态：" + status);
				      $("#modal_collect").modal();
				    		
				   }
			    });
		  }
	   
	  });
	});

</script>
