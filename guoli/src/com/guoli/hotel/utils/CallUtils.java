/**
 * Project Name:Guoli
 * File Name:Tools.java
 * Package Name:com.guoli.hotel.utils
 * Date:2013-2-22下午5:02:38
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.guoli.hotel.R;
import com.guoli.hotel.common.Config;

/**
 * ClassName:Tools <br/>
 * @Description:    电话相关的功能类
 * Date:     2013-2-22 下午5:02:38 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CallUtils {
    
    private Context mCtx;
    
    public CallUtils(Context ctx){
        mCtx = ctx;
    }

    /**
     * 
     * callServer:拨打客服电话. <br/>
     * @author maple
     * @since JDK 1.6
     */
    public void callServer(){
        String phoneNumber = Config.CALL_NUMBER;
        String msg = mCtx.getResources().getString(R.string.dialog_call_notice);
        msg = String.format(msg, phoneNumber);
        DialogUtils.showDialog(mCtx, "", msg, new CallOnClickListener(phoneNumber));
    }
    
    /**拨打电话的事件*/
    private class CallOnClickListener implements android.content.DialogInterface.OnClickListener{

        private String phoneNumber;
        
        /**
         * 
         * Creates a new instance of CallOnClickListener.
         *
         * @param phoneNumber   要拨打的电话号码,不能为空
         */
        public CallOnClickListener(String phoneNumber){
            if (!TextUtils.isEmpty(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            }
        }
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Uri uri = Uri.parse("tel:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            mCtx.startActivity(intent);
        }
    }
}

