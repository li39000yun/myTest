<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>1.jsp</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" href="<%=basePath %>easyui/js/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath %>easyui/js/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath %>easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>easyui/js/jquery.easyui.min.js"></script>
<style type="text/css">
#i {
	width: 120px;
}
</style>
<script type="text/javascript">
	compare = function() {
		$('#i').val();
		console.info($('#i').val());
		console.info($('#i').attr("value"));
		console.info($("#a").css("cursor"));
		if ($("#a").css("cursor")=="default"){
			$('#a').linkbutton('enable');	
		} else {
			$('#a').linkbutton('disable');	
		}
	}
	$(function(){
		$('#a').linkbutton();
	});
</script>
</head>
<body>
	<input id="i" name="inp" value="aaa" />
	<button name="but" onclick="compare()">对比</button>
	<a id="a" href="javascript:void(0);" onclick="compare()">对比</a>
</body>
</html>
