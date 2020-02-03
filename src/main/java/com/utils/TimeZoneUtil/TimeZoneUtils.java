package com.utils.TimeZoneUtil;

import java.util.Date;
import java.util.TimeZone;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.TimeZoneUtil
 * @ClassName: ThreadPoolUtils
 * @Author: zhangqiang
 * @Description: 时区转换工具
 * @Date: 2019/10/24 6:53 下午
 * @Version: 1.0
 */
public class TimeZoneUtils {

	private static TimeZone T_Z_UTC = TimeZone.getTimeZone("UTC");
	private static TimeZone T_Z_DEFAULT = TimeZone.getDefault();

	/**
	 * 将本地时区转化为UTC
	 * @param date
	 * @return
	 */
	public static Date default2UTC(Date date) {
		Long targetTime = date.getTime() - T_Z_DEFAULT.getRawOffset() + T_Z_UTC.getRawOffset();
		return new Date(targetTime);
	}
	
	/**
	 * 将本地时区转化为UTC
	 * @param time
	 * @return
	 */
	public static Date default2UTC(Long time) {
		Long targetTime = time - T_Z_DEFAULT.getRawOffset() + T_Z_UTC.getRawOffset();
		return new Date(targetTime);
	}
	
	/**
	 * 将UTC转化为本地时区
	 * @param date
	 * @return
	 */
	public static Date UTC2Default(Date date) {
		Long targetTime = date.getTime() - T_Z_UTC.getRawOffset() + T_Z_DEFAULT.getRawOffset();
		return new Date(targetTime);
	}
	
	/**
	 * 将UTC转化为本地时区
	 * @param time
	 * @return
	 */
	public static Date UTC2Default(Long time) {
		Long targetTime = time - T_Z_UTC.getRawOffset() + T_Z_DEFAULT.getRawOffset();
		return new Date(targetTime);
	}

	public static void main(String[] args) {
		Date utcDate = default2UTC(new Date());
		System.out.println("UTC : " + utcDate);

		Date localDate = UTC2Default(utcDate.getTime());

		System.out.println("Local : " + localDate);
	}

}
