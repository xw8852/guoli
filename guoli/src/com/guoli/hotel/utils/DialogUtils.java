package com.guoli.hotel.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.guoli.hotel.R;

public class DialogUtils {

	public static void showDialog(int title, int message, Context context) {
		showDialog(context.getString(title), context.getString(message), "",
				context.getString(R.string.dialog_sure), null, context);
	}

	public static void showDialog(int title, int message,
			DialogInterface.OnClickListener sureClickListener, Context context) {
		showDialog(title, message, R.string.dialog_cancel,
				R.string.dialog_sure, sureClickListener, context);
	}

	public static void showDialog(int title, int message, int cancelText,
			int sureText, DialogInterface.OnClickListener sureClickListener,
			Context context) {
		showDialog(context.getString(title), context.getString(message),
				context.getString(R.string.dialog_cancel),
				context.getString(R.string.dialog_sure), sureClickListener,
				context);
	}

	public static void showDialog(String title, String message, Context context) {
		showDialog(title, message, "", context.getString(R.string.dialog_sure),
				null, context);
	}

	public static void showDialog(String title, String message,
			DialogInterface.OnClickListener sureClickListener, Context context) {
		showDialog(title, message, context.getString(R.string.dialog_sure),
				context.getString(R.string.dialog_cancel), sureClickListener,
				context);
	}

	public static void showDialog(String title, String message,
			String cancelText, String sureText,
			DialogInterface.OnClickListener sureClickListener, Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		if (!TextUtils.isEmpty(cancelText)) {
			builder.setNegativeButton(cancelText, null);
		}
		if (!TextUtils.isEmpty(sureText)) {
			builder.setPositiveButton(sureText, sureClickListener);
		}
		builder.show();
	}

	/**
	 * 
	 * createDialogBuilder:创建一个包含有确认/取消按钮的弹出提示框. <br/>
	 * 
	 * @author maple
	 * @param ctx
	 * @param titleId
	 *            弹出框标题,id为-1时表示没有标题
	 * @param messageId
	 *            弹出框内容,id为-1时表示没有内容
	 * @param confirmListener
	 * @since JDK 1.6
	 */
	public static void showDialog(Context ctx, int titleId, int messageId,
			DialogInterface.OnClickListener confirmListener) {
		if (ctx == null) {
			return;
		}
		Builder builder = new Builder(ctx);
		if (titleId != -1) {
			builder.setTitle(titleId);
		}
		if (messageId != -1) {
			builder.setMessage(messageId);
		}
		builder.setNegativeButton(R.string.dialog_sure, confirmListener);
		builder.setPositiveButton(R.string.dialog_cancel,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}

	/**
	 * 
	 * createDialogBuilder:创建一个包含有确认/取消按钮的弹出提示框. <br/>
	 * 
	 * @author maple
	 * @param ctx
	 * @param titleId
	 *            弹出框标题,id为-1时表示没有标题
	 * @param messageId
	 *            弹出框内容,id为-1时表示没有内容
	 * @param confirmListener
	 * @since JDK 1.6
	 */
	public static void showDialog(Context ctx, String title, String message,
			DialogInterface.OnClickListener confirmListener) {
		if (ctx == null) {
			return;
		}
		Builder builder = new Builder(ctx);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		if (!TextUtils.isEmpty(message)) {
			builder.setMessage(message);
		}
		builder.setNegativeButton(R.string.dialog_sure, confirmListener);
		builder.setPositiveButton(R.string.dialog_cancel,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}

	public static Dialog showProgressDialog(Context ctx, String message,
			String title, boolean isCancelable) {
		ProgressDialog mDialog = new ProgressDialog(ctx);
		mDialog.setMessage(message);
		mDialog.setTitle(title);
		mDialog.setCancelable(isCancelable);
		mDialog.show();
		return mDialog;
	}

	public static Dialog showProgressDialog(Context ctx,int messageId,int titleId,boolean isCancelable){
    	return showProgressDialog(ctx, ctx.getResources().getString(messageId), ctx.getResources().getString(titleId), isCancelable);
    }
	
	public static Dialog showProgressDialog(Context ctx,String message){
		ProgressDialog mDialog = new ProgressDialog(ctx);
		mDialog.setMessage(message);
		mDialog.show();
		return mDialog;
	}
	
	public static Dialog showProgressDialog(Context ctx,int messageId){
		return showProgressDialog(ctx, ctx.getResources().getString(messageId));
	}
}
