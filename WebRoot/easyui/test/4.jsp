<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>4.jsp</title>
</head>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript">
$(function(){   
    $('#btn').bind('click', function(){   
    	show();
    });   
    $('#cc2').combo({   
        required:true,   
        multiple:true  
    });
    $('#cc').combobox({
    	onChange : function(newValue, oldValue) {
    		console.info(newValue);
    		console.info(oldValue);
    		$('#t').val(newValue);
    	},
    	editable : false,
    	hasDownArrow : false
    });
});
show = function() {
	console.info($("#cc").combobox("getValues"));
	console.info($("#cc").combobox("getText"));
	console.info($("#cc").combobox("getValue"));
	$("#cc").combobox("hidePanel");
	console.info($("#cc").combobox("options"));
	console.info($("#cc").combobox("options").height);
}
</script>
<style>
#cc {
	width: 80px
}
</style>
<body>
	<input id="cc" class="easyui-combobox" name="dept" data-options="valueField:'id',textField:'text',url:'combobox_data.json'" />
	<input id="cc2" value="001">
	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">showValue</a>  
	<input id="t">
</body>
</html>
