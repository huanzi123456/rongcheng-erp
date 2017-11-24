(function(){
	
	var arr=[0,8,17,50,99];
	/*
	* 以上数据，是留给数据库插值时，留的API。
	*/
	/*这里模拟从数据库拿到的值，把他们插入数组，然后求取他们的最大值。*/
	var mathMax=Math.max.apply(null, arr),
		bar_type=document.querySelectorAll('.bar_type'),
		bl=null;;

	for (var i = arr.length - 1; i >= 0; i--) {

		if (arr[i] == mathMax) {
			bar_type[i].style.width='100%';
			bar_type[i].innerText=arr[i];
		}

		if (arr[i] == 0) {
			bar_type[i].style.width='5%';
			bar_type[i].innerText=arr[i];
		}

		if (arr[i]>0 && arr[i]<mathMax) {
			bl=(arr[i]/mathMax)*95;
			bar_type[i].style.width=bl+5+'%';
			bar_type[i].innerText=arr[i];
		}
	}

	/*
	* 店铺7天经营数据
	*/
	var chart;
	var categoriesArr=['11.11', '11.12', '11.13', '11.14', '11.15', '11.16', '11.17'];
	$(document).ready(function() {
	    chart = new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',          //放置图表的容器
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            defaultSeriesType: 'line'   
	        },
	        title: {
	            text: '　'
	        },
	        xAxis: {//X轴数据
	            categories: categoriesArr,
	            labels: {
	                align: 'center',
	                style: { font: 'normal 13px 宋体' }
	            }
	        },
	        yAxis: {//Y轴显示文字
	            title: {
	                text: '　'
	            }
	        },
	        tooltip: {
	            enabled: true,
	            formatter: function() {
	                return '<b>' + this.x + '</b><br/>' + this.series.name + ': ' + Highcharts.numberFormat(this.y, 1);
	            }
	        },
	        plotOptions: {
	            line: {
	                dataLabels: {
	                    enabled: true
	                },
	                enableMouseTracking: true
	            }
	        },
	        series: [{
	            name: '下单量',
	            data: [55, 7, 99, 18, 80, 21, 75]
	        }, {
	            name: '发货量',
	            data: [55, 5, 55, 13, 80, 11, 35]
	        }]
	    });

	}); 


})()
