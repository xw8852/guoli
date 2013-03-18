package com.guoli.hotel.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public final class LoginUtils {
	private static final String IS_LOGIN="isLogin";
	private static final String UUID="uuid";
	
	/**
	 * 是否已经登录
	 */
	public static final boolean IsLogin(Context ctx){
//		return PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean(IS_LOGIN, false);
		return true;
	}
	/**
	 * 注销登录，溢出sharepreferece中的值
	 * @param ctx
	 */
	public static final void LoginOut(Context ctx){
		Editor editor=PreferenceManager.getDefaultSharedPreferences(ctx).edit();
		editor.remove(UUID);
		editor.remove(IS_LOGIN);
		editor.commit();
	}
	/**
	 * 登录之后，获取uuid
	 * @param ctx
	 * @return
	 */
	public static final String getUUID(Context ctx){
		return PreferenceManager.getDefaultSharedPreferences(ctx).getString(UUID, "17");
	}
	/**
	 * 保存登录
	 * @param ctx
	 * @param uuid
	 */
	public static final void saveLogin(Context ctx,String uuid){
		Editor editor=PreferenceManager.getDefaultSharedPreferences(ctx).edit();
		editor.putString(UUID, uuid);
		editor.putBoolean(IS_LOGIN, true);
		editor.commit();
	}
}
