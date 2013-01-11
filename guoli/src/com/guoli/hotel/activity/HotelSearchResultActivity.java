/**
 * Project Name:Guoli
 * File Name:HotelSearchResultActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-11下午12:36:31
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;

/**
 * ClassName:HotelSearchResultActivity <br/>
 * @Description:    酒店查询结果页面
 * Date:     2013-1-11 下午12:36:31 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelSearchResultActivity extends UpdateActivity {
    
    public HotelSearchResultActivity(){
        mLayoutId = R.layout.hotel_search_result;
        mRightDrawableId = R.drawable.return_btn_bg;
        mRightTextId = R.string.map_mode;
        mTitleTextId = R.string.search_hotel_title;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        showRightBtn();
    }

    @Override
    protected void initPassParams() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void initViews() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void loadNetworkData() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void loadLocalData() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void findViews() {

        // TODO Auto-generated method stub

    }

}

