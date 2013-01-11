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

import java.util.ArrayList;
import java.util.List;

import com.guoli.hotel.R;
import com.guoli.hotel.adapter.HotelAdapter;
import com.guoli.hotel.bean.HotelInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * ClassName:HotelSearchResultActivity <br/>
 * @Description:    酒店查询结果页面
 * Date:     2013-1-11 下午12:36:31 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelSearchResultActivity extends UpdateActivity implements OnItemClickListener {
    /**城市*/
    private TextView mCityView;
    /**入住日期*/
    private TextView mOccupancyView;
    /**离店日期*/
    private TextView mLeaveView;
    /**商家数*/
    private TextView mCountView;
    /**商家列表*/
    private ListView mListView;
    /**商家列表适配器*/
    private HotelAdapter<HotelInfo> mListAdapter;
    
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
        // TODO 模拟数据
        mCityView.setText("上海");
        mOccupancyView.setText("2013-01-11");
        mLeaveView.setText("2013-01-21");
        initBusinessCountView(1098);
    }
    
    private void initBusinessCountView(int count){
        String desc = getResources().getString(R.string.business_count_desc);
        desc = String.format(desc, count);
        mCountView.setText(desc);
    }

    @Override
    protected void loadNetworkData() {
        //TODO 从网络服务器拉取数据并加载显示
        //虚拟的数据,仅供演示使用
        List<HotelInfo> infoList = new ArrayList<HotelInfo>();
        for (int index = 0 ; index < 20 ; index++) {
            HotelInfo info = new HotelInfo();
            info.setName("酒店" + index);
            info.setAddress("地址" + index);
            info.setArea("区域" + index);
            info.setLevel((3+index)%5);
            info.setPrice(0 + index);
            info.setDiscount((float) (1.2 + index * 0.1));
            info.setPhoneNumber("10201110" + index);
            infoList.add(info);
        }
        if (mListAdapter == null) {
            mListAdapter = new HotelAdapter<HotelInfo>(infoList, this);
            mListView.setAdapter(mListAdapter);
        } else {
            mListAdapter.clear();
            mListAdapter.addMore(infoList);
        }
        initViews();
    }

    @Override
    protected void loadLocalData() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void findViews() {
        mCityView = (TextView) findViewById(R.id.city_text);
        mOccupancyView = (TextView) findViewById(R.id.occupancy_date);
        mLeaveView = (TextView) findViewById(R.id.leave_date);
        mCountView = (TextView) findViewById(R.id.count_view);
        mListView = (ListView) findViewById(R.id.hotel_list);
        mListView.setOnItemClickListener(this);
    }
    
    @Override
    protected void rightBtnClickEvent() {
        super.rightBtnClickEvent();
        //TODO 进入地图模式
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
        
    }

}

