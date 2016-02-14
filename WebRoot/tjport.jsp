<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TJ码头查询</title>
</head>
<body>
<form id="form1" name="form1" action="http://www.tjgportnet.com/sc/portinfo.aspx" method="post">
	<table cellSpacing="3" cellPadding="3" width="100%" align="center" border="0">
		<tr valign="top">
			<td class="DESC" noWrap width="100"><font color="red">*</font>柜号：</td>
			<td width="100%">
				<input type='text' name='blno' value="tseq51399600">
				<input type="hidden" name="captcha" value="a443" />
				<input type="hidden" name="formguid" value="6ecbac12-faa3-474e-900d-95596fef6feb" />
				<input type="hidden" name="formname" value="wssearchform" />
				<input type="hidden" name="ctnno" value="" />
				<input type="hidden" name="dpboxchange" value="0" />
				<input type="hidden" name="formproc" value="1" />
				<input type="hidden" name="sid_1" value="1" />
				<input type="hidden" name="sid_2" value="1" />
				<input type="hidden" name="sid_3" value="1" />
				<input type="hidden" name="sid_4" value="1" />
				<input type="hidden" name="sid_5" value="1" />
				<input type="hidden" name="sid_6" value="1" />
				<input type="hidden" name="sid_7" value="1" />
				<input type="hidden" name="checkall" value="1" />
			</td>
		</tr>
		<tr>
			<td align="center" colSpan="4"><input class="flat" id="btnSearch" style="MARGIN: 6px" type="submit" value="查  询">
			</td>
		</tr>
	</table>
</form>
<script>
form1.submit();
</script>
</body>
</html>