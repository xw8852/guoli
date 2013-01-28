/**
 * Project Name:Guoli
 * File Name:HotelsMapActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-11下午4:15:52
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.guoli.hotel.R;

/**
 * ClassName:HotelsMapActivity <br/>
 * @Description:    酒店列表的地图模式
 * Date:     2013-1-11 下午4:15:52 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelsMapActivity extends UpdateActivity {
    /**城市*/
    private TextView mCityView;
    /**入住日期*/
    private TextView mOccupancyView;
    /**离店日期*/
    private TextView mLeaveView;
    /**商家数*/
    private TextView mCountView;
    public HotelsMapActivity(){
        mLayoutId = R.layout.hotels_map_mode;
        mTitleTextId = R.string.hotels_map_title;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {
        mCityView = (TextView) findViewById(R.id.city_text);
        mOccupancyView = (TextView) findViewById(R.id.occupancy_date);
        mLeaveView = (TextView) findViewById(R.id.leave_date);
        mCountView = (TextView) findViewById(R.id.count_view);
    }

    @Override
    protected void initPassParams() {
        
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void initViews() {
        mCityView.setText("上海");
        mOccupancyView.setText("2013-01-11");
        mLeaveView.setText("2013-01-21");
        initBusinessCountView(1098);
    }

    private void initBusinessCountView(int count) {
        String desc = getResources().getString(R.string.result_count_desc);
        desc = String.format(desc, count);
        mCountView.setText(desc);
    }

    @Override
    protected void loadNetworkData() {
        initViews();
    }

    @Override
    protected void loadLocalData() {
        
        // TODO Auto-generated method stub
        
    }

}

