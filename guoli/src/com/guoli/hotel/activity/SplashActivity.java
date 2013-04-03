/**
 * Project Name:Guoli
 * File Name:GlitterScreamActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-8下午1:18:58
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.guoli.hotel.GuoliApplication;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.hotel.SearchHotelActivity;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.utils.JsonUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * ClassName:GlitterScreamActivity <br/>
 * @Description: 闪屏页面
 * Date:     2013-1-8 下午1:18:58 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        loadAppVersion();
        delayToNextPage();
        ((GuoliApplication)getApplication()).startTimer();
    }

    private void delayToNextPage(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, SearchHotelActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 1000l);
    }
    
    private void loadAppVersion(){
        VersionInfo info = new VersionInfo();
        info.setVersion("1.0");
        GuoliRequest request = new GuoliRequest(Action.General.SYSTEM_LOADING, info);
        Log.i("SplashActivity", "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mLisenter);
    }
    
    private  IResponseListener mLisenter = new IResponseListener() {
        
        @Override
        public void onSuccess(Response response) {
            Log.i("SplashActivity", "response=" + (response == null ? null : response.result));
            Map<String, Object> map = JsonUtils.convertToMap((String) (response == null ? "" : response.result));
            LoginUtils.appVersion = (String) map.get("curver");
//            delayToNextPage();
        }
        
        @Override
        public void onError(Response response) {
            Log.i("SplashActivity", "response=" + (response == null ? null : response.result));
            
        }
    };
    
    private class VersionInfo {
        private String version;

        @SuppressWarnings("unused")
        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}

