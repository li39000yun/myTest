<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String version = "20160118";%>
<script type="text/javascript">
var lyq = lyq || {};
lyq.contextPath = '<%=path%>';
lyq.basePath = '<%=basePath%>';
lyq.version = '<%=version%>';
</script>
<%-- 引入EasyUI --%>
<link rel="stylesheet" href="<%=basePath %>easyui/js/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath %>easyui/js/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath %>easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>easyui/js/locale/easyui-lang-zh_CN.js"></script>
<%-- 引入easyui扩展 --%>
<script type="text/javascript" src="<%=basePath %>js/lyqExtEasyUI.js"></script>
<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=basePath%>/style/lyqExtCss.css?version=<%=version%>" type="text/css">