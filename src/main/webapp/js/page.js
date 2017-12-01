$(document).ready(function() {
	$("#previous").click(function() {
		var pageNum = parseFloat($("#page").val());
		var pageNumNew = pageNum - 1;
		if (pageNumNew == 0) {
			alert("已经是第一页");
		} else {
			$("#pageNums").val(pageNumNew);
			$("#forSelect").submit();
		}
	});
	$("#next").click(function() {
		var pageNum = parseFloat($("#page").val());
		var totalPage = $("#totalPage").val();
		var pageNumNew = pageNum + 1;
		if (pageNumNew > totalPage) {
			alert("已经是最后一页");
		} else {
			$("#pageNums").val(pageNumNew);
			$("#forSelect").submit();
		}
	});
	
	
	$("#firstpage").click(function() {
		var pageNum = parseFloat($("#page").val());
		if(pageNum == 1){
			alert("已经是首页了");
		}else{
			$("#pageNums").val(1);
			$("#forSelect").submit();			
		}
	});	
	
	$("#lastpage").click(function() {
		var pageNum = parseFloat($("#page").val());
		var totalPage = $("#totalPage").val();
		if(pageNum == totalPage){
			alert("已经是尾页了");
		}else{
			$("#pageNums").val(totalPage);
			$("#forSelect").submit();	
		}
	});		
});