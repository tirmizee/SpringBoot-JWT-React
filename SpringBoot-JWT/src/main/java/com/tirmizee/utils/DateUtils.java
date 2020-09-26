package com.tirmizee.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtils {
	
	public static final FastDateFormat FORMAT_DDMMYYYY_HHMM = FastDateFormat.getInstance("ddMMyyyy HH:mm", new Locale("TH", "th"));
	
	public static boolean between(Date date, Date startDate, Date endDate) {
		return date.after(startDate) && date.before(endDate);
	}
	
	public static LocalDateTime toLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
}
