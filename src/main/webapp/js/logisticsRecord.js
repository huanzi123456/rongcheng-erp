(function(){
	var logisticsRecordBtn=$(".logistics-record-btn"),
		logisticsRecordBox=$(".logistics-record-box"),
		logistics_record_delBtn=$(".logistics-record_delBtn");
	logisticsRecordBtn.click(function(){
		// if ($(this).next(".logistics-record-box").css('display')=='block') {
			// $(this).next(".logistics-record-box").css('display','none');
		// }else{
		// 	logisticsRecordBox.css('display','none');
			$(this).next(".logistics-record-box").css('display','block');
		// }
	})
	
	logistics_record_delBtn.click(function(){
		logisticsRecordBox.css('display','none');
	})
})()
