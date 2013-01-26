/**
 * Project Name:SplashActivity
 * File Name:DateSelectActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-24下午11:16:59
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.activity;

import android.os.Bundle;

import com.guoli.hotel.R;

/**
 * ClassName:DateSelectActivity <br/>
 * 
 * @Description: 选择日期页面 Date: 2013-1-24 下午11:16:59 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public abstract class DateSelectActivity extends BaseActivity {

    public static final String KEY_OCCUPANCY_DATE = "occupancyDate";

    public DateSelectActivity() {
        mLayoutId = R.layout.select_date;
        mTitleTextId = R.string.date_select;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {

    }

    @Override
    public void onBackPressed() {
        commit();
        super.onBackPressed();
    }
    
    @Override
    protected void leftBtnClickEvent() {
        commit();
        super.leftBtnClickEvent();
    }
    
    /***
     * 
     * commit:设置好时间返回上级页面时的一些处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void commit();
}
