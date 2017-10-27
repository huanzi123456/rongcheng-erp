
//1.获取请求的UUID，指定长度和进制
//getUUID(len, radix);
	
//2.构造request对象
//getRequestObject(cmd);

/***
 * 
 * 1.获取请求的UUID，指定长度和进制,如 
 * getUUID(8, 2)   //"01001010" 8 character (base=2)
 * getUUID(8, 10) // "47473046" 8 character ID (base=10)
 * getUUID(8, 16) // "098F4D35"。 8 character ID (base=16)
 *
 */
function getUUID(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    radix = radix || chars.length; 
    if (len) {
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
    } else {
      var r;
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
      uuid[14] = '4';
      for (i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random()*16;
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
      }
    }
    return uuid.join('');
}
/***
 * 2.构造request对象
 */
function getRequestObject(cmd) {
    var request  = new Object();
    request.requestID=getUUID(8, 16);
    request.version="1.0";
    request.cmd=cmd;
    return request;
}
/***
 * 获取自定义区数据以及模板URL
 * waybillNO 电子面单号
 */
function getCustomAreaData(waybillNO){
    //获取waybill对应的自定义区的JSON object，此处的ajaxGet函数是伪代码
    var jsonObject = ajaxGet(waybillNO);
    var ret = new Object();
    ret.templateURL=jsonObject.content.templateURL;
    ret.data=jsonObject.content.data;
    return ret;
}
/***
 * 获取电子面单Json 数据
 * waybillNO 电子面单号
 */
function getWaybillJson(waybillNO){
    //获取waybill对应的json object，此处的ajaxGet函数是伪代码
    var jsonObject = ajaxGet(waybillNO);
    return jsonObject;
}

/***
 * 转换为json数据
 * @param waybillNO
 * @returns
 */
function ajaxGet(waybillNO) {
	
	if (!isJson(waybillNO)) {
		waybillNO = $.parseJSON(waybillNO);
	}
	return waybillNO;
	
}

/***** 判断是否为json对象 *******
* @param obj: 对象（可以是jq取到对象）
* @return isjson: 是否是json对象 true/false
*/
function isJson(obj){
var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length; 
return isjson;
}

/**
 * 打印电子面单
 * printer 指定要使用那台打印机
 * waybillArray 要打印的电子面单的数组
 */
/*function doPrint(printer, waybillArray)
{
    var request = getRequestObject("erp");
    request.task = new Object();
    request.task.taskID = getUUID(8,10);
    request.task.preview = false;
    request.task.printer = printer;
    var documents = new Array();
    for(i=0;i<waybillArray.length;i++) {
         var doc = new Object();
         doc.documentID = waybillArray[i];
         var content = new Array();
         var waybillJson = getWaybillJson(waybillArray[i]);
         var customAreaData = getCustomAreaData(waybillArray[i]);
         content.push(waybillJson,customAreaData);
         doc.content = content;
         documents.push(doc);
    }
    socket.send(JSON.stringify(request));
}*/