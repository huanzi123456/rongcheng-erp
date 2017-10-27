//0.全局变量，打印通信
//备注：webSocket 是全局对象，不要每次发送请求丢去创建一个，
//做到webSocket对象重用，和打印组件保持长连接。
var webSocket;

//0.全局变量，确认下载
var confirmDownload;

//0.全局变量，取消下载
var cancelDownload;

$(function(){
	
	//1.检测打印组件相关信息
	checkCaiNiaoPrint();
	
	console.log("base:", webSocket);
});

/**
 * 1.检测打印组件相关信息
 * @returns
 * @author 赵滨
 */
function checkCaiNiaoPrint() {
	
	//关闭确认下载 定时器
	clearInterval(confirmDownload);
	
	//获取当前页面
	var http = window.location.protocol;
	
	//如果是https
	if (http == 'https:') {
		//握手
		webSocket = new WebSocket('wss://localhost:13529');
		
	//否则是http
	} else {
		//握手
		webSocket = new WebSocket('ws://localhost:13528');
	}
	
	// 监听握手错误
    webSocket.onerror = function(event) {
    	
    	//确认框，是否下载组件
    	confirmBox();
    	
    	//定时器 开始
    	confirmDownload = window.setInterval(function(){
    		
    		//如果是https
    		if (http == 'https:') {
    			//握手
    			webSocket = new WebSocket('wss://localhost:13529');
    			
    		//否则是http
    		} else {
    			//握手
    			webSocket = new WebSocket('ws://localhost:13528');
    		}
    		
    		//成功握手
    	    webSocket.onopen = function(event) {
    	    	//关闭确认下载 定时器
    	    	clearInterval(confirmDownload);
    	    	
        		alert("恭喜,您已经成功启动菜鸟打印组件!");
        		
        		return;
    	    };
    		
    	},3000);
    	
    	
    };
}

/**
 * 1.1.确认框，是否下载组件
 * @returns
 * @author 赵滨
 */
function confirmBox() {
	//点击确认
	if (confirm("您目前没有启动菜鸟打印组件，您确认下载组件吗?")) {
	//if (confirm("您目前没有启动菜鸟打印组件")) {
		
		//跳转页面，后期优化
		window.open("https://www.cainiao.com/markets/cnwww/erp");
		
		
	//点击取消
    } else {
    	
    }
}
