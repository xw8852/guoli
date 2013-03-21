package com.guoli.hotel.utils;

public final class LoginUtils {
	/**
	 * 是否已经登录
	 * 0:未登录
	 * 1：非会员登录
	 * 2：会员登录
	 */
	public static int isLogin = 0;
	/**
	 * UUID，会员用户登录之后保存的Uid
	 */
	public static String uid = "";
	/**
	 * 非会员登录的之后，保存的手机号码
	 */
	public static String mobile = null;
}
