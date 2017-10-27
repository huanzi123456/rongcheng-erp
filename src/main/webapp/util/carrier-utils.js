/* 快递名称数组，对应快递id为：1,2,3。。。 */
var carrier = [ "顺丰", "中通", "申通", "圆通", "韵达", "EMS", "汇通", "宅急送", "京东快递", "天天快递", "德邦快递", "德邦物流", "邮政小包", "EMS经济快递", "百世物流", "龙邦", "速尔", "全峰", "国通", "快捷快递", "优速", "中国邮政", "安能物流", "佳龙快运", "华宇物流" ];
var carrierLength = carrier.length;
function getCarrierName(id) {
	if (isNaN(id) || id < 0 || carrierLength <= id) {
		return "";
	} else {
		return carrier[id - 1];
	}
}
function getCarrierId(name) {
	if (typeof (name) != "string") {
		return "";
	} else {
		for (var i = 0; i < carrier.length; i++) {
			if (carrier[i] == name) {
				return i + 1;
			}
		}
		return -1;
	}
}