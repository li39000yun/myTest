var XWinAddress = {
	G : function(id) {
		return document.getElementById(id);
	},
	setAddressByMap : function(addressId) {
		this.addressId = addressId;
		var win = this.win_map;
		if (!win) {
			win = new XWin( {
				title : '选择位置',
				width : 700,
				content : '<div id="frm"></div>'
			});
			this.win_map = win;
		}
		var address = encodeURIComponent(XWinAddress.G(addressId).value);
		XWinAddress.G('frm').innerHTML = '<iframe class="frm" src="'+XWinAddress.getRootPath_web()+'/js/plugins/xwin/address.jsp?callback=XWinAddress.setAddress&address=' + address + '" frameborder="no" style="width:100%;height:400px;"></iframe>';
		win.show();
	},
	setAddress : function(r) {
		XWinAddress.G(XWinAddress.addressId.replace('address','lng')).value = r.lng || '';
		XWinAddress.G(XWinAddress.addressId.replace('address','lat')).value = r.lat || '';
		XWinAddress.win_map.hide();
	},
	getRootPath_web : function() {
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
		var curWwwPath = window.document.location.href;
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8083
		var localhostPaht = curWwwPath.substring(0, pos);
		//获取带"/"的项目名，如：/uimcardprj
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return (localhostPaht + projectName);
	}
};