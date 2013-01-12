package com.guoli.hotel.utils;

import com.guoli.hotel.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

public class DialogUtils {
    
    public static void showDialog(int title,int message,Context context){
        showDialog(context.getString(title), context.getString(message),
                "", context.getString(R.string.dialog_sure), null, context);
    }
    
    public static  void showDialog(int title,int message,DialogInterface.OnClickListener sureClickListener,Context context){
        showDialog(title, message, R.string.dialog_cancel,
                R.string.dialog_sure,sureClickListener,context);
    }
    
    public static  void showDialog(int title,int message,int cancelText,int sureText,
            DialogInterface.OnClickListener sureClickListener,Context context){
        showDialog(context.getString(title), context.getString(message),
                context.getString(R.string.dialog_cancel), 
                context.getString(R.string.dialog_sure),sureClickListener, context);
    }
    
    public static  void showDialog(String title,String message,Context context){
        showDialog(title, message, "", context.getString(R.string.dialog_sure), null, context);
    }
    
    public static  void showDialog(String title,String message,DialogInterface.OnClickListener sureClickListener,Context context){
        showDialog(title, message, context.getString(R.string.dialog_cancel),
                context.getString(R.string.dialog_cancel),sureClickListener,context);
    }
    
    public static  void showDialog(String title,String message,String cancelText,
            String sureText,DialogInterface.OnClickListener sureClickListener,Context context){
        AlertDialog.Builder builder=new  AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        if(!TextUtils.isEmpty(cancelText)){
            builder.setNegativeButton(cancelText, null);
        }
        if(!TextUtils.isEmpty(sureText)){
            builder.setPositiveButton(sureText, sureClickListener);
        }
        builder.show();
    }
}
