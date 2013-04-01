package com.guoli.hotel.utils;


import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.msx7.core.Controller;

/**
 * 
 * 保持toast是单例显示
 * @author Carl
 */
public class ToastUtil {
	private static Toast toast;
	private static Context context = Controller.getApplication();

	public enum ToastDisplayTime {
		TOAST_DISPLAY_LONG,
		TOAST_DISPLAY_SHORT
	}
	
	private ToastUtil() {
	}

	private static void checkToast() {
		if (toast == null) {
			toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
		}
	}

	private static void setToastDisplayTime(ToastDisplayTime time){
		if(time==ToastDisplayTime.TOAST_DISPLAY_LONG)
			toast.setDuration(Toast.LENGTH_LONG);
		else {
			toast.setDuration(Toast.LENGTH_SHORT);
		}
	}
	
	public static void show(String msg, ToastDisplayTime time) {
		checkToast();
		toast.setText(msg);
		setToastDisplayTime(time);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}

	public static void show(int msg, ToastDisplayTime time) {
		checkToast();
		toast.setText(msg);
		setToastDisplayTime(time);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}

	public static void show(String msg) {
		show(msg,ToastDisplayTime.TOAST_DISPLAY_SHORT);
	}

	public static void show(int msg) {
		show(msg,ToastDisplayTime.TOAST_DISPLAY_SHORT);
	}
}
