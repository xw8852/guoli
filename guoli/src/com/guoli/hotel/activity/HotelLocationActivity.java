/**
 * Project Name:SplashActivity
 * File Name:HotelLocationActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-24下午6:12:28
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import android.os.Bundle;

import com.guoli.hotel.R;

/**
 * ClassName:HotelLocationActivity <br/>
 * @Description:
 * Date:     2013-1-24 下午6:12:28 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelLocationActivity extends BaseActivity {
    
    public HotelLocationActivity(){
        mLayoutId = R.layout.hotel_location_map;
        mTitleTextId = R.string.hotel_location_map;
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

