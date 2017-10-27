package com.rongcheng.erp.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.format.Formatter;

//这里是将String类型转换成Timestamp类型
public class CustomTimestampConverter implements Formatter<Timestamp> {

	public String print(Timestamp object, Locale locale) {
		return null;
	}

	public Timestamp parse(String text, Locale locale) throws ParseException {
		SimpleDateFormat simpleDateFormat = null;
		if (text.matches("^\\d+$")) {
			return new Timestamp(new Long(text));
		} else if (text.matches("^\\d{4}(-\\d{2}){2}$")) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		} else if (text.matches("^\\d{4}(-\\d{2}){2}\\s\\d{2}(:\\d{2}){2}\\.\\d{3}$")) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		} else if (text.matches("^\\d{4}(-\\d{2}){2}\\s\\d{2}(:\\d{2}){2}$")) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		try {
			return new Timestamp(simpleDateFormat.parse(text).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 不是规定的格式时间字符串
		throw new RuntimeException("不合法的时间字符串格式");
	}
}