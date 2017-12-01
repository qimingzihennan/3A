
$(document).ready(function() {
	$("#previous").click(function() {
		curPage = curPage - 1;
		if (curPage <= 0) {
			curPage = 1;
			alert("已经是第一页");
		} else {
			pageUtil();
		}
	});
	$("#next").click(function() {
		curPage = curPage + 1;
		if (curPage > totalPage) {
			curPage = totalPage;
			alert("已经是最后一页");
		} else {
			pageUtil();
		}
	});
	
	$("#firstpage").click(function() {
		if(curPage == 1){
			alert("已经是首页了");
		}else{
			curPage = 1;
			pageUtil();
		}
	});	
	
	$("#lastpage").click(function() {
		if(curPage == totalPage){
			alert("已经是尾页了");
		}else{
			curPage = totalPage;
			pageUtil();
		}
	});
});