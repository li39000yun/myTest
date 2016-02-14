var XWinAddress = {
	G : function(id) {
		return document.getElementById(id);
	},
	showMap : function(addressId) {
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
		XWinAddress.G('frm').innerHTML = '<iframe class="frm" src="xwin/address.jsp?callback=XWinAddress.setAddress&address=' + address + '" frameborder="no" style="width:100%;height:400px;"></iframe>';
		win.show();
	},
	setAddress : function(r) {
		XWinAddress.G(XWinAddress.addressId).value = r || '';
		XWinAddress.win_map.hide();
	}
};