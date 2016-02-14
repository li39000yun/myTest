<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testAddress.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="xwin/xwin.css" rel="stylesheet" />
	<script src="xwin/xwin.js"></script>
	<script type="text/javascript" src="xwin/xwinAddress.js"></script>
  </head>
  <body>
	<div style="padding:20px">
		装货地址：<input id="address" value="中关村soho" style="width:300px;"> <a href="javascript:XWinAddress.showMap('address')">选择</a>
	</div>
	<div style="padding:20px">
		装货地址：<input id="address2" value="盐田北山路口" style="width:300px;"> <a href="javascript:XWinAddress.showMap('address2')">选择</a>
	</div>
  </body>
</html>
