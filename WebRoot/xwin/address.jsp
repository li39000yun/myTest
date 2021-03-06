<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>获取位置</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script src="http://api.map.baidu.com/api?v=2.0&ak=61f8bd72d68aef3a7b66537761d29d82"></script>
<script src="xcomplete.js"></script>
<script>
	function G(id) {
		return document.getElementById(id)
	}
	Page = {
		init : function() {
			var p = JSON.parse(localStorage['map_address'] || 0) || {
				lat : 37,
				lng : 112,
				zoom : 5
			};

			map = new BMap.Map('mapbox');
			geoc = new BMap.Geocoder();
			local = new BMap.LocalSearch(map, {
				onSearchComplete : function(r) {
					Page.onSearch(r);
				}
			});

			this.initComplete();

			var point = new BMap.Point(p.lng, p.lat);
			map.centerAndZoom(point, p.zoom);

			if (/address=([^&]+)/.test(location.search)) {
				G('address').value = decodeURIComponent(RegExp.$1);
				this.setAddress();
			}

			map.addEventListener("click", function(e) {
				var pt = e.point;
				geoc.getLocation(pt, function(rs) {
					if (rs && rs.address) {
						Page.setMarker(rs.point, true);

						G('address').value = rs.address;
						G('submit').style.display = '';
					} else {
						alert('找不到对应地址');
					}
				});
			});

			map.addEventListener("moveend", Page.savePosition);
			map.addEventListener("zoomend", Page.savePosition);
		},
		initComplete : function(r) {
			complete = new XComplete({
				id : 'address',
				color : '#6b6b6b',
				overcolor : '#666',
				bordercolor : '#ddd',
				overground : '#eee',
				onchange : function(o) {
					if (o && (o = o.childNodes[0])) {
						G('address').value = o.innerHTML.replace(/&amp;/g, '&');
						if (o = o.getAttribute('data')) {
							o = o.split(',');
							Page.setMarker(new BMap.Point(Number(o[0]), Number(o[1])));
						}
					}
				},
				callback : function(kw) {
					if (!kw) {
						this.hide();
						return;
					}
					local.search(kw);
				}
			});
		},
		onSearch : function(r) {
			var c, s = '', a = r.tr || [];
			for ( var i = 0; i < a.length; i++) {
				c = a[i];
				d = c.point;
				s += '<div style="padding-left:10px;"><div style="padding:3px 0;color:#000;" data="' + [d.lng,d.lat] + '">' + c.address + '<\/div><div style="color:#999;">' + c.title + '<\/div><\/div>';
			}
			complete.show(s);
		},
		setAddress : function() {
			var address = G('address').value;
			geoc.getPoint(address, function(point) {
				if (point) {
					Page.setMarker(point);
				} else {
					alert('未找到地址：' + address);
				}
			});
		},
		setMarker : function(point, fixed) {
			if (!window.marker) {
				marker = new BMap.Marker(point);
				map.addOverlay(marker);
			}
			marker.setPosition(point);
			if (!fixed)
				map.setCenter(point);
			var zoom = map.getZoom();
			if (zoom < 15)
				map.setZoom(15);
			G('submit').style.display = '';
		},
		savePosition : function() {
			var p = map.getCenter();
			p.zoom = map.getZoom();
			localStorage['map_address'] = JSON.stringify(p);
		},
		submit : function() {
			if (/callback=([^&]+)/.test(location.search)) {
				var c, a = (RegExp.$1).split('.'), f = parent;
				while (c = a.shift())
					f = f[c];
				f(G('address').value);
			} else {
				alert('请将当前页面用iframe的形式嵌入，并传入callback参数，确认后会将地址回传给指定方法');
			}
		}
	}

	window.onload = function() {
		Page.init();
	}
</script>
<style type="text/css">
body,html,#mapbox {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	padding: 0;
	font-size: 12px;
	font-family: "微软雅黑";
}

#top {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 40px;
	background: #fff;
	border-bottom: 1px solid #ccc;
}

#address {
	position: absolute;
	top: 0;
	left: 0;
	width: 300px;
	height: 40px;
	padding: 0 10px;
	font-size: 14px;
	color: red;
	border: 0;
	outline: none;
}

#submit {
	position: absolute;
	top: 0;
	right: 0;
	line-height: 40px;
	padding: 0 20px;
	background: #f60;
	color: #fff;
	cursor: pointer;
}
</style>
</head>
<body>
	<div id="mapbox"></div>
	<div id="top">
		<input id="address" placeholder="请搜索或者点击地图上的点" autofocus>
		<div id="submit" style="display:none" onclick="Page.submit()">确认选择</div>
	</div>
</body>
</html>