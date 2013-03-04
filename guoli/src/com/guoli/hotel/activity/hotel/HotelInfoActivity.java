/**
 * Project Name:SplashActivity
 * File Name:HotelInfoActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-24下午11:05:29
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity.hotel;

import android.os.Bundle;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;

/**
 * ClassName:HotelInfoActivity <br/>
 * @Description:
 * Date:     2013-1-24 下午11:05:29 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelInfoActivity extends BaseActivity {
    
    public HotelInfoActivity(){
        mLayoutId = R.layout.hotel_info_layout;
        mTitleTextId = R.string.hotel_info;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {

    }

}

