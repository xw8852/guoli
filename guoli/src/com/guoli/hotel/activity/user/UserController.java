package com.guoli.hotel.activity.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.TextView;

public class UserController {
	
	/**
	 * 判断手机号码
	 */
	public static boolean isMoblieNumber(TextView tv) {
		if (!isMoblieNumber(tv.getText().toString())) { return false; }
		return true;
	}

	public static boolean isMoblieNumber(String numbers) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-3,5-9])|(18[0-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(numbers);
		return matcher.matches();
	}

}
