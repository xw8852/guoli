/**
 * Project Name:SplashActivity
 * File Name:HotelListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26下午2:40:38
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.guoli.hotel.R;
import com.guoli.hotel.adapter.HotelAdapter;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.widget.BottomTabbar;

/**
 * ClassName:HotelListActivity <br/>
 * @Description:    特别推荐页面
 * Date:     2013-1-26 下午2:40:38 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelListActivity extends BaseActivity implements OnItemClickListener {
    
    private ListView mListView;
    
    public HotelListActivity(){
        mTitleTextId = R.string.recommend;
        mLayoutId = R.layout.hotel_recommend_layout;
        mRightDrawableId = R.drawable.btn_top_phone;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        new BottomTabbar(this, 1);
        showLeftBtn();
        showRightBtn();
        updateListView(getList());
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.hotelListView);
        mListView.setOnItemClickListener(this);
    }
    

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListView.getAdapter() == null) {
            return;
        }
        HotelInfo info = (HotelInfo) mListView.getAdapter().getItem(position);
        if (info == null) {
            return;
        }
        enterHotelDetailActivity();
    }
    
    @Override
    public void onBackPressed() {
        showExitDialog();
    }
    
    @Override
    protected void leftBtnClickEvent() {
        showExitDialog();
    }
    
    /**
     * 
     * updateListView:更新列表数据. <br/>
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void updateListView(List<HotelInfo> list){
        if (list == null || list.size() < 1) {
            return;
        }
        @SuppressWarnings("unchecked")
        HotelAdapter<HotelInfo> adapter = (HotelAdapter<HotelInfo>) mListView.getAdapter();
        if (adapter == null) {
            adapter = new HotelAdapter<HotelInfo>(list, this);
            mListView.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addMore(list);
        }
    }
    
    /**
     * 
     * enterHotelDetailActivity:跳转到酒店详细页面. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void enterHotelDetailActivity(){
        Intent intent = new Intent();
        intent.setClass(this, HotelDetailActivity.class);
        startActivity(intent);
    }

    //模拟的数据
    private List<HotelInfo> getList(){
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
        return infoList;
    }
}

