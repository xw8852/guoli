/**
 * Project Name:Guoli
 * File Name:GlitterScreamActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-8下午1:18:58
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.guoli.hotel.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        delayToNextPage();
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
}

