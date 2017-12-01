Date.prototype.format = function(format){
	 var o = {
		  "M+" : this.getMonth()+1,  //month
		  "d+" : this.getDate(),     //day	
		  "h+" : this.getHours(),    //hour
	      "m+" : this.getMinutes(),  //minute
	      "s+" : this.getSeconds(), //second
	      "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
	      "w+" : this.getDay(), //week
	      "S"  : this.getMilliseconds() //millisecond
	   };
	 
	   if(/(y+)/.test(format)) {
	    format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	   }

	   for(var k in o) {
	    if(new RegExp("("+ k +")").test(format)) {
	      format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	    }
	   }
	 return format;
};

if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['日','一','二','三','四','五','六'];
	$.fn.calendar.defaults.months = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '今天';
	$.fn.datebox.defaults.closeText = '关闭';
	$.fn.datebox.defaults.okText = '确定';
	$.fn.datebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	};
	$.fn.datebox.defaults.parser = function(s){
		if (!s) return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}

//Input check rule
$.extend($.fn.validatebox.defaults.rules, {
    money: {
        validator: function(value, param){
        	if (!value) {
        		return true;
        	}
        	var precision = 8;
        	var scale = 2;
        	if (param && param[0]) {
        		precision = param[0];
        	}
        	if (param && (param[1] || param[1] == 0)) {
        		scale = param[1];
        	}
        	
        	if (!/^\d+(\.\d+)?$/.test(value)) {
        		return false;
        	}
        	
        	var arr = value.split(".");
        	if (arr[0].length > precision) {
        		 return false;
        	}
        	if (arr[1] && arr[1].length > scale) {
        		return false;
        	}
            return true;
        },
        message: '请输入有效的金额。'
    },
    numeric: {
        validator: function(value, param){
        	if (!value) {
        		return true;
        	}
        	var precision = 8;
        	var scale = 2;
        	if (param && param[0]) {
        		precision = param[0];
        	}
        	if (param && (param[1] || param[1] == 0)) {
        		scale = param[1];
        	}
        	
        	if (!/^\d+(\.\d+)?$/.test(value)) {
        		return false;
        	}
        	
        	var arr = value.split(".");
        	if (arr[0].length > precision) {
        		 return false;
        	}
        	if (arr[1] && arr[1].length > scale) {
        		return false;
        	}
            return true;
        },
        message: '请输入有效的数值。'
    }
});

// Ajax default setting
$.ajaxSetup({
	error: function(request) {
		console.info("default error handler");
		if (request.responseJSON && request.responseJSON.reason) {
			$.messager.alert('提示', request.responseJSON.reason, 'error');
		}
	},
	statusCode: {
		403: function(request) {
			if ($(".messager-window:visible").length == 0) {
				if (request.responseJSON && request.responseJSON.reason) {
					$.messager.alert('提示', request.responseJSON.reason, 'error');
				} else {
					$.messager.alert('提示', "无权访问!", 'error');
				}
			}
		}
	}
});


/**
 * desc：自定义js类库，封装常用的js方法
 * author：程明
 */
//(function(){
//	window['fy']={};
//	
//	function getRootPath_web() {
//        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
//        var curWwwPath = window.document.location.href;
//        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
//        var pathName = window.document.location.pathname;
//        var pos = curWwwPath.indexOf(pathName);
//        //获取主机地址，如： http://localhost:8083
//        var localhostPaht = curWwwPath.substring(0, pos);
//        //获取带"/"的项目名，如：/uimcardprj
//        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//        return (localhostPaht + projectName);
//    }
//
//	window["fy"]["getRootPath_web"]=getRootPath_web;
//	
//	function test01(){
//		alert("test");
//	}
//	
//	window["fy"]["test01"]=test01;
//	
//	function test02( msg){
//		alert(msg);
//	}
//	
//	window["fy"]["test02"]=test02;
//
//})();





