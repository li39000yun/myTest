var shipperOrderJS = {
	rvalue : "",
	dialect : "zh_cn",
	resource : {
		zh_cn : {
			CUSTOMER : "\u8bf7\u5148\u9009\u62e9\u5ba2\u6237!",
			FACTORY : "\u8bf7\u5148\u9009\u62e9\u5de5\u5382!",
			TRUCKNO : "\u60a8\u786e\u5b9a\u8981\u66f4\u6539\u5df2\u5f55\u5165\u7684\u8f66\u8f86\u76f8\u5173\u4fe1\u606f\u5417\uff1f",
			EDIT : "\u6b64\u5355\u5df2\u9501\u5b9a\u6216\u5904\u4e8e\u4e0d\u53ef\u4fee\u6539\u72b6\u6001\uff01",
			SELECTED : "\u8bf7\u9009\u62e9\u4e00\u6761\u8bb0\u5f55.",
			ISCONFIRM : "\u60a8\u786e\u5b9a\u8981\u6267\u884c\u6b64\u64cd\u4f5c\u5417\uff1f",
			CONTAINER : "\u67dc\u53f7\u91cd\u590d\u662f\u5426\u5f3a\u5236\u901a\u8fc7\uff1f",
			LINENAME : "\u8bf7\u5148\u9009\u62e9\u8239\u516c\u53f8\uff01"
		},
		zh_tw : {
			CUSTOMER : "\u8acb\u5148\u9078\u64c7\u5ba2\u6236!",
			FACTORY : "\u8acb\u5148\u9078\u64c7\u5de5\u5ee0!",
			TRUCKNO : "\u60a8\u78ba\u5b9a\u8981\u66f4\u6539\u5df2\u9304\u5165\u7684\u8eca\u8f1b\u76f8\u95dc\u8cc7\u8a0a\u55ce\uff1f",
			EDIT : "\u6b64\u55ae\u5df2\u9396\u5b9a\u6216\u8655\u65bc\u4e0d\u53ef\u4fee\u6539\u72c0\u614b\uff01",
			SELECTED : "\u8acb\u9078\u64c7\u4e00\u689d\u8a18\u9304.",
			ISCONFIRM : "\u60a8\u78ba\u5b9a\u8981\u57f7\u884c\u6b64\u64cd\u4f5c\u55ce\uff1f",
			CONTAINER : "\u6ac3\u865f\u91cd\u8907\u662f\u5426\u5f37\u5236\u901a\u904e\uff1f",
			LINENAME : "\u8acb\u5148\u9078\u64c7\u8239\u516c\u53f8\uff01"
		},
		en_us : {
			CUSTOMER : "Please select customers first!",
			FACTORY : "Please select the factory first!",
			TRUCKNO : "Are you sure you want to change the vehicle information you input?",
			EDIT : "This record is locked or  the state can not be modified!",
			SELECTED : "Please select a record.",
			ISCONFIRM : "Are you sure to execute this operations\uff1f",
			CONTAINER : "The container number already exists, is forced through!",
			LINENAME : "Please select line first\uff01"
		},
		get : function(e) {
			try {
				var f = xkernel.get("locale");
				if (f) {
					shipperOrderJS.dialect = f.innerHTML
				}
				var h = shipperOrderJS.resource[shipperOrderJS.dialect][e];
				return (h == null) ? e : h
			} catch (g) {
				return e
			}
		}
	},
	changeQuantity : function() {
		var g = xkernel.get("num1").value * 1;
		var h = xkernel.get("num2").value * 1;
		var e = xkernel.get("num3").value * 1;
		var f = xkernel.get("num4").value * 1;
		xkernel.get("conQuantity").value = g + h + e + f
	},
	init : function() {
		xkernel.ajax.callCommbox("customer.commonBox", commbox.dataInit, "1", 0);
		xkernel.ajax.callCommbox("customs.commonBox", commbox.dataInit, "7");
		xkernel.ajax.callCommbox("customsStart.commonBox", commbox.dataInit, "8");
		xkernel.ajax.callCommbox("customsEnd.commonBox", commbox.dataInit, "9");
		xkernel.ajax.callCommbox("line.commonBox", commbox.dataInit, "10");
		xkernel.ajax.callCommbox("pile.commonBox", commbox.dataInit, "12-13", 0);
		xkernel.ajax.callCommbox("truck.commonBox", commbox.dataInit, "14");
		xkernel.ajax.callCommbox("transportTeam.commonBox", commbox.dataInit, "15");
		xkernel.ajax.callCommbox("driver.commonBox", commbox.dataInit, "16");
		xkernel.ajax.callCommbox("truckShelf.commonBox", commbox.dataInit, "17");
		xkernel.ajax.callCommbox("department.commonBox", commbox.dataInit, "18");
		xkernel.ajax.callCommbox("user.selectByUserType", commbox.dataInit, "19", 2);
		xkernel.ajax.callCommbox("customerContact.commonBox", commbox.dataInit, "2", parseInt(xkernel.get("customerId").value));
		xkernel.ajax.callCommbox("factory.commonBox", commbox.dataInit, "3-5", parseInt(xkernel.get("customerId").value));
		xkernel.ajax.callCommbox("factoryContact.commonBox", commbox.dataInit, "4", parseInt(xkernel.get("factoryId").value));
		xkernel.ajax.callCommbox("ship.commonBox", commbox.dataInit, "11", parseInt(xkernel.get("lineId").value));
		xkernel.ajax.callCommbox("warehouse.queryForAjax", commbox.dataInit, "20", "");
		xkernel.ajax.callCommbox("tallyClerk.commonBox", commbox.dataInit, "21");
		xkernel.ajax.callCommbox("printCompany.commonBox", commbox.dataInit, "22");
		xkernel.ajax.callCommbox("place.commonBox", commbox.dataInit, "23", 0, 0);
		var d = xkernel.get("customerId").value;
		if (d > 0) {
			var c = (xkernel.get("factoryTab").rows.length - 1) / 3;
			for ( var b = 0; b < c; b++) {
				xkernel.ajax.callCommbox("factory.commonBox", commbox.dataInit, "S_factory" + (b + 2), parseInt(d));
				var a = xkernel.get("factoryId" + (b + 2)).value;
				xkernel.ajax.callCommbox("factoryContact.commonBox", commbox.dataInit, "S_contact" + (b + 2), parseInt(a));
				xkernel.ajax.callCommbox("place.commonBox", commbox.dataInit, "S_loadPlace" + (b + 2), 0, 0)
			}
		}
	},
	addConInit : function() {
		var h = xkernel.get("customerId").value;
		var d = xkernel.get("factoryId").value;
		xkernel.ajax.callCommbox("factory.commonBox", commbox.dataInit, "3-5", parseInt(h));
		xkernel.ajax.callCommbox("factoryContact.commonBox", commbox.dataInit, "4", parseInt(d));
		xkernel.ajax.callCommbox("pile.commonBox", commbox.dataInit, "12-13", 0);
		xkernel.ajax.callCommbox("truck.commonBox", commbox.dataInit, "14");
		xkernel.ajax.callCommbox("transportTeam.commonBox", commbox.dataInit, "15");
		xkernel.ajax.callCommbox("driver.commonBox", commbox.dataInit, "16");
		xkernel.ajax.callCommbox("truckShelf.commonBox", commbox.dataInit, "17");
		xkernel.ajax.callCommbox("place.commonBox", commbox.dataInit, "S_loadPlace2", 0, 0);
		xkernel.ajax.callCommbox("warehouse.queryForAjax", commbox.dataInit, "20", "");
		xkernel.ajax.callCommbox("tallyClerk.commonBox", commbox.dataInit, "21");
		xkernel.ajax.callCommbox("printCompany.commonBox", commbox.dataInit, "22");
		var g = xkernel.get("customerId").value;
		if (g > 0) {
			var f = (xkernel.get("factoryTab").rows.length - 1) / 3;
			for ( var e = 0; e < f; e++) {
				xkernel.ajax.callCommbox("factory.commonBox", commbox.dataInit, "S_factory" + (e + 2), parseInt(g));
				var a = xkernel.get("factoryId" + (e + 2)).value;
				xkernel.ajax.callCommbox("factoryContact.commonBox", commbox.dataInit, "S_contact" + (e + 2), parseInt(a));
				xkernel.ajax.callCommbox("place.commonBox", commbox.dataInit, "S_loadPlace" + (e + 2), 0, 0)
			}
		}
	},
	selectCustomer : function(c, d) {
		xkernel.get("customerId").value = c.id;
		rvalue = xkernel.ajax.call("customer.getByUnion", null, parseInt(c.id));
		if (rvalue.state) {
			if (rvalue.result.employeeName) {
				xkernel.get("operationMan").value = rvalue.result.employeeId;
				xkernel.get("operationManName").value = rvalue.result.employeeName
			} else {
				xkernel.get("operationMan").value = 0;
				xkernel.get("operationManName").value = ""
			}
			if (rvalue.result.contact) {
				xkernel.get("customerContact").value = rvalue.result.contact.name;
				xkernel.get("contactTelephone").value = rvalue.result.contact.telephone;
				xkernel.get("contactMobilePhone").value = rvalue.result.contact.mobilePhone;
				xkernel.get("customerContactId").value = rvalue.result.contact.id
			} else {
				xkernel.get("customerContact").value = "";
				xkernel.get("contactTelephone").value = "";
				xkernel.get("contactMobilePhone").value = "";
				xkernel.get("customerContactId").value = 0
			}
		}
		xkernel.ajax.callCommbox("customerContact.commonBox", commbox.dataInit, "2", parseInt(c.id));
		xkernel.ajax.callCommbox("factory.commonBox", commbox.dataInit, "3-5", parseInt(c.id));
		var f = (xkernel.get("factoryTab").rows.length - 1) / 3;
		for ( var e = 0; e < f; e++) {
			xkernel.ajax.callCommbox("factory.commonBox", commbox.dataInit, "S_factory" + (e + 2), parseInt(c.id))
		}
		rvalue = xkernel.ajax.call("factory.queryDefaultByCustomerId", null, parseInt(c.id), null);
		if (rvalue.state) {
			if (rvalue.result.length > 0) {
				shipperOrderJS.selectFactory(rvalue.result[0], d)
			} else {
				xkernel.get("factoryId").value = 0;
				xkernel.get("factoryShortName").value = "";
				xkernel.get("address").value = "";
				xkernel.get("loadPlace").value = "";
				xkernel.get("contact").value = "";
				xkernel.get("telephone").value = "";
				xkernel.get("fax").value = "";
				xkernel.get("mobilePhone").value = "";
				xkernel.get("customsId").value = 0;
				xkernel.get("customs").value = "";
				xkernel.get("startCustoms").value = ""
			}
		}
	},
	selectCustomerContact : function(b, a) {
		rvalue = xkernel.ajax.call("customerContact.getByUnion", null, parseInt(b.id));
		xkernel.get("contactTelephone").value = rvalue.result.telephone;
		xkernel.get("contactMobilePhone").value = rvalue.result.mobilePhone;
		xkernel.get("customerContactId").value = rvalue.result.id
	},
	selectFactory : function(c, d) {
		xkernel.ajax.callCommbox("factoryContact.commonBox", commbox.dataInit, "4", parseInt(c.id));
		rvalue = xkernel.ajax.call("factory.getByUnion", null, parseInt(c.id));
		xkernel.get("factoryId").value = rvalue.result.id;
		xkernel.get("factoryShortName").value = rvalue.result.shortName;
		xkernel.get("address").value = rvalue.result.address;
		xkernel.get("loadPlace").value = rvalue.result.placeName;
		if (rvalue.result.shipperRemark != "") {
			xkernel.get("shipperRemark").value = rvalue.result.shipperRemark
		}
		xkernel.get("contact").value = rvalue.result.contactMan;
		xkernel.get("telephone").value = rvalue.result.telephone;
		xkernel.get("fax").value = rvalue.result.fax;
		xkernel.get("mobilePhone").value = rvalue.result.mobilePhone;
		xkernel.get("customsId").value = rvalue.result.customsId;
		xkernel.get("customs").value = rvalue.result.customs;
		xkernel.get("startCustoms").value = rvalue.result.startCustoms;
		xkernel.get("lng").value = rvalue.result.lon;
		xkernel.get("lat").value = rvalue.result.lat;
		shipperOrderJS.queryRoutes();
		if (rvalue.result.address != "" && rvalue.result.lon == 0 && rvalue.result.lat == 0) {
			XWinAddress.setAddressByMap("address")
		}
	},
	selectContact : function(c, d) {
		rvalue = xkernel.ajax.call("factoryContact.get", null, parseInt(c.id));
		xkernel.get("telephone").value = rvalue.result.telephone;
		xkernel.get("mobilePhone").value = rvalue.result.mobilePhone;
		xkernel.get("fax").value = rvalue.result.fax
	},
	selectFactory2 : function(b, a) {
		var c = a.id.replace("factoryShortName", "");
		rvalue = xkernel.ajax.call("factory.getByUnion", null, parseInt(b.id));
		xkernel.get("factoryId" + c).value = rvalue.result.id;
		xkernel.get("factoryShortName" + c).value = rvalue.result.shortName;
		xkernel.get("loadPlace" + c).value = rvalue.result.placeName;
		xkernel.get("contact" + c).value = rvalue.result.contactMan;
		xkernel.get("telephone" + c).value = rvalue.result.telephone;
		xkernel.get("address" + c).value = rvalue.result.address;
		xkernel.get("lng" + c).value = rvalue.result.lon;
		xkernel.get("lat" + c).value = rvalue.result.lat;
		xkernel.ajax.callCommbox("factoryContact.commonBox", commbox.dataInit, "S_contact" + c, parseInt(b.id));
		if (rvalue.result.address != "" && rvalue.result.lon == 0 && rvalue.result.lat == 0) {
			XWinAddress.setAddressByMap("address" + c)
		}
	},
	selectContact2 : function(b, a) {
		rvalue = xkernel.ajax.call("factoryContact.get", null, parseInt(b.id));
		var c = a.id.replace("contact", "");
		xkernel.get("contact" + c).value = rvalue.result.name;
		xkernel.get("telephone" + c).value = rvalue.result.telephone
	},
	selectLine : function(c, d) {
		xkernel.get("lineId").value = c.id;
		xkernel.ajax.callCommbox("ship.commonBox", commbox.dataInit, "11", parseInt(c.id))
	},
	selectGetConPile : function(c, d) {
		rvalue = xkernel.ajax.call("pile.getUnion", null, parseInt(c.id));
		xkernel.get("getConPlace").value = rvalue.result.placeName;
		shipperOrderJS.queryRoutes()
	},
	selectReturnConPile : function(c, d) {
		rvalue = xkernel.ajax.call("pile.getUnion", null, parseInt(c.id));
		xkernel.get("returnConPlace").value = rvalue.result.placeName;
		shipperOrderJS.queryRoutes()
	},
	selectTruck : function(h, f) {
		xkernel.get("truckId").value = h.id;
		var e = xkernel.get("transportTeamId").value;
		var g = xkernel.get("driverId").value;
		if (e > 0 || g > 0) {
			if (!confirm(shipperOrderJS.resource.get("TRUCKNO"))) {
				return null
			}
		}
		rvalue = xkernel.ajax.call("truck.getUnion", null, parseInt(h.id));
		xkernel.get("transportTeamId").value = rvalue.result.transportTeamId;
		xkernel.get("transportTeam").value = rvalue.result.transportTeamName;
		xkernel.get("driverId").value = rvalue.result.driverId;
		xkernel.get("driver").value = rvalue.result.driverName;
		xkernel.get("truckShelfId").value = rvalue.result.shelfId;
		xkernel.get("truckShelf").value = rvalue.result.shelfNo;
		xkernel.get("manageMode").value = rvalue.result.manageMode;
		xkernel.get("driverMobilePhone").value = rvalue.result.mobilePhone;
		xkernel.get("wastageOilKm").value = rvalue.result.wastageOilKm;
		xkernel.get("customsNo").value = rvalue.result.customsNo;
		shipperOrderJS.queryRoutes()
	},
	selectTransportTeam : function(c, d) {
		xkernel.get("transportTeamId").value = c.id
	},
	selectDriver : function(c, d) {
		xkernel.get("driverId").value = c.id;
		rvalue = xkernel.ajax.call("driver.get", null, parseInt(c.id));
		if (rvalue.state) {
			xkernel.get("driverMobilePhone").value = rvalue.result.mobilePhone
		}
	},
	selectTruckShelf : function(c, d) {
		xkernel.get("truckShelfId").value = c.id
	},
	selectOperationMan : function(c, d) {
		xkernel.get("operationMan").value = c.id
	},
	queryUser : function(b) {
		xkernel.get("followManId").value = "";
		rvalue = xkernel.ajax.call("user.queryForAjax", null, b);
		if (rvalue.state) {
			return rvalue.result
		}
		return null
	},
	queryWarehouse : function(b) {
		rvalue = xkernel.ajax.call("warehouse.queryForAjax", null, b);
		if (rvalue.state) {
			return rvalue.result
		}
		return null
	},
	selectUser : function(c, d) {
		xkernel.get("followManId").value = c.code
	},
	selectCustoms : function(c, d) {
		xkernel.get("customsId").value = c.id
	},
	selectWarehouse : function(c, d) {
		xkernel.get("warehouseId").value = c.id;
		rvalue = xkernel.ajax.call("warehouse.queryWarehouse", null, parseInt(c.id));
		if (rvalue.state) {
			xkernel.get("place").value = rvalue.result.place
		}
		return null
	},
	queryTallyClerk : function(b) {
		rvalue = xkernel.ajax.call("tallyClerk.queryForAjax", null, b);
		if (rvalue.state) {
			return rvalue.result
		}
		return null
	},
	selectTallyClerk : function(c, d) {
		xkernel.get("tallyClerkId").value = c.id
	},
	checkDifferent : function(b) {
		if (b) {
			if (b.checked) {
				xkernel.get("isDifferentGet").value = "1"
			} else {
				xkernel.get("isDifferentGet").value = "0"
			}
		}
	},
	checkIsDoubleCon : function(b) {
		if (b) {
			if (b.checked) {
				xkernel.get("isDoubleCon").value = "2";
				xkernel.get("otherBookingNo").readOnly = false;
				xkernel.get("otherContainerNo").readOnly = false;
				xkernel.get("otherSealNo").readOnly = false;
				xkernel.get("otherContainerWeight").readOnly = false;
				xkernel.get("otherBookingNo").style.backgroundColor = "#FFFFFF";
				xkernel.get("otherContainerNo").style.backgroundColor = "#FFFFFF";
				xkernel.get("otherSealNo").style.backgroundColor = "#FFFFFF";
				xkernel.get("otherContainerWeight").style.backgroundColor = "#FFFFFF";
				xkernel.get("isDoubleDiv").style.display = "block"
			} else {
				xkernel.get("isDoubleCon").value = "1";
				xkernel.get("otherBookingNo").readonly = true;
				xkernel.get("otherContainerNo").readonly = true;
				xkernel.get("otherSealNo").readonly = true;
				xkernel.get("otherContainerWeight").readonly = true;
				xkernel.get("otherBookingNo").style.backgroundColor = "#EEEEEE";
				xkernel.get("otherContainerNo").style.backgroundColor = "#EEEEEE";
				xkernel.get("otherSealNo").style.backgroundColor = "#EEEEEE";
				xkernel.get("otherContainerWeight").style.backgroundColor = "#EEEEEE";
				xkernel.get("otherBookingNo").value = "";
				xkernel.get("otherContainerNo").value = "";
				xkernel.get("otherSealNo").value = "";
				xkernel.get("otherContainerWeight").value = "0";
				xkernel.get("isDoubleDiv").style.display = "none"
			}
		}
	},
	queryDepartment : function(b) {
		xkernel.get("departmentId").value = "0";
		rvalue = xkernel.ajax.call("department.queryForAjax", null, b);
		if (rvalue.state) {
			return rvalue.result
		}
		return null
	},
	selectDepartment : function(c, d) {
		xkernel.get("departmentId").value = c.id
	},
	queryContainer : function(h, g, e, f) {
		if (e < 3 && f == 0) {
			rvalue = xkernel.ajax.call("shipperOrder.selectUnionById", null, h, parseInt(g));
			if (rvalue.state) {
				shipperOrderJS.selectContainer(rvalue.result)
			}
		} else {
			Common.alert(shipperOrderJS.resource.get("EDIT"))
		}
		return null
	},
	selectContainer : function(b) {
		xkernel.get("sequence").value = b.sequence;
		xkernel.get("factoryId").value = b.factoryId;
		xkernel.get("factoryShortName").value = b.factoryShortName;
		xkernel.get("contact").value = b.contact;
		xkernel.get("telephone").value = b.telephone;
		xkernel.get("fax").value = b.fax;
		xkernel.get("address").value = b.address;
		xkernel.get("loadPlace").value = b.loadPlace;
		xkernel.get("state").value = b.state;
		xkernel.get("containerType").value = b.containerType;
		xkernel.get("appointTime").value = b.appointTime;
		xkernel.get("supplyTime").value = b.supplyTime;
		xkernel.get("cutOffTime").value = b.cutOffTime;
		xkernel.get("containerNo").value = b.containerNo;
		xkernel.get("sealNo").value = b.sealNo;
		xkernel.get("containerWeight").value = b.containerWeight;
		xkernel.get("isDoubleCon").value = b.isDoubleCon;
		if (b.isDoubleCon == 2) {
			xkernel.get("isChecked").checked = true
		} else {
			xkernel.get("isChecked").checked = false
		}
		shipperOrderJS.checkIsDoubleCon(xkernel.get("isChecked"));
		xkernel.get("otherBookingNo").value = b.otherBookingNo;
		xkernel.get("otherContainerNo").value = b.otherContainerNo;
		xkernel.get("otherSealNo").value = b.otherSealNo;
		xkernel.get("otherContainerWeight").value = b.otherContainerWeight;
		xkernel.get("isDifferentGet").value = b.isDifferentGet;
		if (b.isDifferentGet == 1) {
			xkernel.get("isDifferent").checked = true
		} else {
			xkernel.get("isDifferent").checked = false
		}
		xkernel.get("manageMode").value = b.manageMode;
		xkernel.get("getConPile").value = b.getConPile;
		xkernel.get("getConPlace").value = b.getConPlace;
		xkernel.get("returnConPile").value = b.returnConPile;
		xkernel.get("returnConPlace").value = b.returnConPlace;
		xkernel.get("stayNight").value = b.stayNight;
		xkernel.get("arriveTime").value = b.arriveTime;
		xkernel.get("leaveTime").value = b.leaveTime;
		xkernel.get("goodName").value = b.goodName;
		xkernel.get("cubage").value = b.cubage;
		xkernel.get("weight").value = b.weight;
		xkernel.get("cubage").value = b.cubage;
		xkernel.get("shipperRemark").value = b.shipperRemark;
		xkernel.get("followRemark").value = b.followRemark;
		xkernel.get("truckId").value = b.truckId;
		xkernel.get("truck").value = b.truck;
		xkernel.get("transportTeamId").value = b.transportTeamId;
		xkernel.get("transportTeam").value = b.transportTeam;
		xkernel.get("driverId").value = b.driverId;
		xkernel.get("driver").value = b.driver;
		xkernel.get("truckShelfId").value = b.truckShelfId;
		xkernel.get("truckShelf").value = b.truckShelf;
		xkernel.get("kilometres").value = b.kilometres;
		xkernel.get("factOilQuantity").value = b.factOilQuantity;
		shipperOrderJS.isHide(b.isLock)
	},
	submit_self : function(e) {
		var f = $table.getCheckedRows();
		if (f.length == 0) {
			Common.alert(shipperOrderJS.resource.get("SELECTED"));
			return
		}
		for ( var d = 0; d < f.length; d++) {
			f[d].cells[1].childNodes[1].value = f[d].cells[1].childNodes[2].value
		}
		if (confirm(shipperOrderJS.resource.get("ISCONFIRM"))) {
			xkernel.get("actionType").value = e;
			shipperOrder.submit();
			xkernel.get("actionType").value = "editSave"
		}
	},
	exportWaybill : function(g) {
		var i = $table.getCheckedRows();
		if (g == null) {
			return
		}
		if (i.length == 0) {
			Common.alert(shipperOrderJS.resource.get("SELECTED"));
			return
		}
		var f = "";
		var h = "";
		for ( var j = 0; j < i.length; j++) {
			f += i[j].cells[1].childNodes[4].value + ",";
			h += i[j].cells[1].childNodes[2].value + ","
		}
		window.document.forms[1].action = "waybill.action?actionType=exportConExcel&billType=" + g;
		window.document.forms[1].busiIds.value = f;
		window.document.forms[1].sequences.value = h;
		window.document.forms[1].target = "_self";
		window.document.forms[1].submit();
		xkernel.get("actionType").value = "editSave"
	},
	printWaybill : function() {
		var h = $table.getCheckedRows();
		if (h.length == 0) {
			Common.alert(shipperOrderJS.resource.get("SELECTED"));
			return
		}
		var f = "";
		var g = "";
		for ( var e = 0; e < h.length; e++) {
			f += h[e].cells[1].childNodes[4].value + ",";
			g += h[e].cells[1].childNodes[2].value + ","
		}
		Common.preview("waybill.action?actionType=print&billType=1&busiIds=" + f + "&sequences=" + g)
	},
	isHide : function(b) {
		if (b == 1) {
			xkernel.get("openCommandDiv").style.display = "none";
			xkernel.get("openPageDiv").style.width = "100%";
			xkernel.get("openPageDiv").style.textAlign = "center"
		} else {
			xkernel.get("openCommandDiv").style.display = "block";
			xkernel.get("openPageDiv").style.width = "50%";
			xkernel.get("openPageDiv").style.textAlign = "right"
		}
	},
	queryRoutes : function() {
		var f = xkernel.get("getConPlace").value;
		var e = xkernel.get("loadPlace").value;
		var d = xkernel.get("returnConPlace").value;
		if (e != "" && f != "" && d != "") {
			rvalue = xkernel.ajax.call("transportRoutes.queryForAjax", null, f, e, d);
			if (rvalue.state) {
				if (rvalue.result.length > 0) {
					xkernel.get("kilometres").value = rvalue.result[0].id;
					shipperOrderJS.setConsume(rvalue.result[0])
				}
			}
		} else {
			xkernel.get("kilometres").value = 0
		}
	},
	setConsume : function(f) {
		if (f.name > 0) {
			xkernel.get("standConsume").value = f.name;
			xkernel.get("factOilQuantity").value = f.name
		} else {
			var d = xkernel.get("kilometres").value;
			var e = xkernel.get("wastageOilKm").value;
			xkernel.get("standConsume").value = xkernel.util.toFixed(e * d / 100);
			xkernel.get("factOilQuantity").value = xkernel.get("standConsume").value
		}
	},
	selectConsume : function() {
		var c = xkernel.get("kilometres").value;
		var d = xkernel.get("wastageOilKm").value;
		xkernel.get("standConsume").value = xkernel.util.toFixed(d * c / 100);
		xkernel.get("factOilQuantity").value = xkernel.get("standConsume").value
	},
	checkIsDouble : function(b) {
		rvalue = xkernel.ajax.call("shipperOrder.checkIsDouble", null, b.value);
		if (rvalue.result.value != 0) {
			if (!confirm(shipperOrderJS.resource.get("CONTAINER"))) {
				b.value = "";
				b.focus()
			}
		}
	},
	hide : function(c) {
		var b = xkernel.get(c).style.display;
		if (b == "none") {
			xkernel.get(c).style.display = "block"
		} else {
			xkernel.get(c).style.display = "none"
		}
	},
	checkIsSend : function(b) {
		if (b.checked) {
			xkernel.get("isPortSend").value = 1
		} else {
			xkernel.get("isPortSend").value = 0
		}
	},
	quantity : function(e, f, g, h) {
		if (e) {
			if (xkernel.get("num4").value != "0" && f == "") {
				g.exception = "\u60a8\u6dfb\u52a0\u4e86\u81ea\u5b9a\u4e49\u67dc\u91cf\uff0c\u67dc\u578b\u4e0d\u5141\u8bb8\u4e3a\u7a7a.";
				e = false
			}
		}
		return e
	},
	datacheck : function(f) {
		var b = f.value;
		if (b == "") {
			return
		}
		var e = b.substring(0, 10);
		var c = e.split("-");
		var d = new Date();
		var g = (new Date(c[0], c[1], c[2]) - new Date(d.getFullYear(), d.getMonth() + 1, d.getDate())) / 86400000;
		if (g >= 30) {
			Common.alert("\u5f55\u5165\u7684\u505a\u67dc\u65e5\u671f\u5927\u4e8e\u5f53\u524d\u65e5\u671f " + g + "\u5929")
		} else {
			if (g <= -30) {
				Common.alert("\u5f55\u5165\u7684\u505a\u67dc\u65e5\u671f\u5c0f\u4e8e\u5f53\u524d\u65e5\u671f " + Math.abs(g) + "\u5929")
			}
		}
	},
	checkAppointTime : function(c, a, g, f) {
		if (c) {
			var e = xkernel.get("truckId").value;
			var b = xkernel.get("transportTeamId").value;
			var d = xkernel.get("returnTime").value;
			var h = xkernel.get("appointTime").value;
			if ((e != 0 || b != 0 || d != "") && h == "") {
				g.exception = "\u8f66\u724c\u53f7\u3001\u8f66\u961f\u3001\u8fd8\u67dc\u65f6\u95f4\u4e0d\u4e3a\u7a7a\u65f6\uff0c\u505a\u67dc\u65f6\u95f4\u4e0d\u80fd\u4e3a\u7a7a\uff01";
				c = false
			}
		}
		return c
	},
	addFactory : function(d) {
		var a = d.parentNode.parentNode;
		var b = document.createElement("TR");
		var d = xkernel.get("factoryTab");
		a.parentNode.insertBefore(d.rows[0].cloneNode(true), null);
		a.parentNode.insertBefore(d.rows[1].cloneNode(true), null);
		a.parentNode.insertBefore(d.rows[2].cloneNode(true), null);
		var g = '<a onclick="shipperOrderJS.delRow(this, 0)"><span class="red"><strong>\u5220\u9664</strong></span></a>';
		d.rows[d.rows.length - 1].cells[7].innerHTML = g;
		var c = d.rows.length - 2;
		var e = (d.rows.length - 1) / 3 + 1;
		d.rows[c - 1].cells[0].innerHTML = '<input type="hidden" id="loadTytpes" name="loadTytpes" value="0"><input type="hidden" id="moreIds" name="moreIds" value="0">\u5de5\u5382\u540d\u79f0' + e;
		d.rows[c - 1].cells[1].innerHTML = '<div class="box_div2XL"><input type="hidden" id="factoryId' + e + '" name="factoryIds"/><input type="text" id="factoryShortName' + e + '" check="type:string;nullable:true;maxlength:50;related:factoryId' + e + ';relatedValue:0;relatedNullable:true;" name="factoryShortNames" onkeydown="commbox.prepare(this,null,shipperOrderJS.selectFactory2,\'name\',\'code-name\');"/><iframe></iframe><select id="S_factory' + e + '"><option value="0" code=""></option></select></div>';
		d.rows[c - 1].cells[2].innerHTML = "\u8054\u7cfb\u4eba" + e;
		d.rows[c - 1].cells[3].innerHTML = '<div class="box_div2XL"><input type="text" id="contact' + e + '" name="contacts" check="type:string;nullable:true;maxlength:50;" onkeydown="commbox.prepare(this,null,shipperOrderJS.selectContact2,\'name\',\'code-name\');"/><iframe></iframe><select id="S_contact' + e + '"><option value="0" code=""></option></select></div>';
		d.rows[c - 1].cells[4].innerHTML = "\u8054\u7cfb\u7535\u8bdd" + e;
		d.rows[c - 1].cells[5].childNodes[0].id = "telephone" + e;
		d.rows[c - 1].cells[5].childNodes[0].value = "";
		d.rows[c - 1].cells[6].innerHTML = "\u88c5\u8d27\u5730\u70b9" + e;
		d.rows[c - 1].cells[7].innerHTML = '<div class="box_div2XL"><input type="text" id="loadPlace' + e + '" name="loadPlaces" check="type:string;nullable:true;maxlength:50;" onkeydown="commbox.prepare(this,null,null,\'name\',\'code-name\');"/><iframe></iframe><select id="S_loadPlace' + e + '"><option value="0" code=""></option></select></div>';
		d.rows[c].cells[0].innerHTML = "\u88c5\u8d27\u5730\u5740" + e;
		d.rows[c].cells[1].innerHTML = '<input type="text" id="address' + e + '" name="addresss" onchange="javascript:XWinAddress.setAddressByMap(\'address' + e + '\')" class="text8XL" check="type:string;nullable:true;maxlength:200;"/><a href="javascript:XWinAddress.setAddressByMap(\'address' + e + "')\">\u9009\u62e9</a>";
		d.rows[c].cells[2].innerHTML = "\u5230\u5382\u65f6\u95f4" + e;
		d.rows[c].cells[3].innerHTML = '<input type="text" id="arriveTime' + e + '" name="arriveTimes" class="text2XL" check="type:datetime;nullable:true;"/>&nbsp;<img onclick="WdatePicker({el:$dp.$(\'arriveTime' + e + '\'),dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" src="../image/jkx/date.gif" style="CURSOR: pointer;width: 13px">';
		d.rows[c].cells[4].innerHTML = "\u79bb\u5382\u65f6\u95f4" + e;
		d.rows[c].cells[5].innerHTML = '<input type="text" id="leaveTime' + e + '" name="leaveTimes" class="text2XL" check="type:datetime;nullable:true;"/>&nbsp;<img onclick="WdatePicker({el:$dp.$(\'leaveTime' + e + '\'),dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" src="../image/jkx/date.gif" style="CURSOR: pointer;width: 13px">';
		d.rows[c + 1].cells[0].innerHTML = "\u8ba2\u5355\u53f7" + e;
		d.rows[c + 1].cells[1].childNodes[0].id = "orderNo" + e;
		d.rows[c + 1].cells[1].childNodes[0].value = "";
		d.rows[c + 1].cells[2].innerHTML = "\u4e39\u9a6c\u58eb\u8ba2\u4ed3\u53f7" + e;
		d.rows[c + 1].cells[3].childNodes[0].id = "damcoBookingNo" + e;
		d.rows[c + 1].cells[3].childNodes[0].value = "";
		d.rows[c + 1].cells[4].innerHTML = "\u5730\u5740\u7ecf\u7eac\u5ea6" + e;
		d.rows[c + 1].cells[5].childNodes[0].id = "lng" + e;
		d.rows[c + 1].cells[5].childNodes[0].value = "0";
		d.rows[c + 1].cells[5].childNodes[1].id = "lat" + e;
		d.rows[c + 1].cells[5].childNodes[1].value = "0";
		var f = xkernel.get("customerId").value;
		if (parseInt(f) > 0) {
			xkernel.ajax.callCommbox("factory.commonBox", commbox.dataInit, "S_factory" + e, parseInt(f));
			xkernel.ajax.callCommbox("place.commonBox", commbox.dataInit, "S_loadPlace" + e, 0, 0)
		}
		xkernel.validate.checking()
	},
	trigger : function() {
		shipperOrderJS.addFactory(xkernel.get("addImg"))
	},
	delRow : function(d, e) {
		if (confirm("\u60a8\u786e\u5b9a\u8981\u6267\u884c\u6b64\u64cd\u4f5c\u5417\uff1f")) {
			var b, c, a;
			c = d.parentNode.parentNode;
			b = c.parentNode;
			a = c.rowIndex;
			b.deleteRow(a);
			b.deleteRow(a - 1);
			b.deleteRow(a - 2);
			if (e > 0) {
				rvalue = xkernel.ajax.call("transportOrder.deleteFactory", null, e)
			}
			xkernel.validate.checking()
		}
	},
	changeType : function(a) {
		if (a == 0) {
			xkernel.get("conTabDiv1").style.display = "block";
			xkernel.get("conTabDiv2").style.display = "block";
			xkernel.get("conTabDiv3").style.display = "block";
			xkernel.get("cargoTabDiv").style.display = "none";
			xkernel.get("bulkCargoContainerNo").style.display = "none"
		} else {
			xkernel.get("cargoTabDiv").style.display = "block";
			xkernel.get("bulkCargoContainerNo").style.display = "block";
			xkernel.get("conTabDiv1").style.display = "none";
			xkernel.get("conTabDiv2").style.display = "none";
			xkernel.get("conTabDiv3").style.display = "none"
		}
	}
};
