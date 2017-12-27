package com.bimils.myapp.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	
	public static String number(long number, String pattern) {
		DecimalFormat fomatter = new DecimalFormat(pattern);	
		return fomatter.format(number);
	}
	
	public static String date(Date date, String pattern) {
		SimpleDateFormat fomatter = new SimpleDateFormat(pattern);
		return fomatter.format(date);
	}
	
	
	

}
