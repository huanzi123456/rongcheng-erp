//0.全局变量，打印组件
var LODOP;

$(function(){
	
	//1.检查是否安装Lodop
	checkLodop();
	
})

/**
 * 1.检查是否安装Lodop
 * @returns
 * @author 赵滨
 */
function checkLodop() {
	//必须在加载对象之后链接
	setTimeout(function () {
		try{
			//获取对象
            LODOP = getLodop();
		    //存在版本
			if (LODOP.VERSION) {
				//存在云版本
				if (LODOP.CVERSION) {
//					console.log("当前有C-Lodop云打印可用!\n C-Lodop版本:"+LODOP.CVERSION+"(内含Lodop"+LODOP.VERSION+")");
					
				} else {
//					console.log("本机已成功安装了Lodop控件！\n 版本号:"+LODOP.VERSION);
				}
			};
			//关闭提示
			$("#CLodop_Setup").css("display", "none");
		}catch(err){
			//显示提示
			$("#CLodop_Setup").css("display", "block");
		}
	}, 1000); 
}
