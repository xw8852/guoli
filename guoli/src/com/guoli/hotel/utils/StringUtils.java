package com.guoli.hotel.utils;

public class StringUtils {
	/**
	 * 判断给定字符串是否空白串。<br>
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串<br>
	 * 若输入字符串为null或空字符串，返回true
	 * 
	 * @return boolean
	 */
	public static boolean isBlank(String input) {
		if (input == null || "".equals(input))
			return true;

		// 如果字符串中包含 空格、制表符、回车符、换行符，则说明 是空白串
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符转为int
	 * 
	 * @param str
	 * @return
	 */
	public static int toInt(String str) {
		if (null == str || "".equals(str)) {
			return 0;
		}
		int it = -1;
		try {
			it = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return it;
	}
}