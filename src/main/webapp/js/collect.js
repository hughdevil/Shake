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
