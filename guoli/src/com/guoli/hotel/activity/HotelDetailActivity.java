/**
 * Project Name:Guoli
 * File Name:HotDetailActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-11下午5:08:51
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.RoomInfo;
import com.guoli.hotel.widget.RoomItemView;

/**
 * ClassName:HotelDetailActivity <br/>
 * 
 * @Description: 酒店详情页面 Date: 2013-1-11 下午5:08:51 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class HotelDetailActivity extends UpdateActivity {
    
    private LinearLayout mRoomsLayout;

    public HotelDetailActivity() {
        mLayoutId = R.layout.recommended_hotel_detail;
        mTitleTextId = R.string.hotel_detail;
        mRightDrawableId = R.drawable.btn_top_phone;
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
        //TODO 根据接口返回的酒店房间类型数据初始化房间类型信息
        List<RoomInfo> list = new ArrayList<RoomInfo>();
        for (int index = 0 ; index < 3 ; index++) {
            RoomInfo info = new RoomInfo();
            list.add(info);
        }
        initRoomsTypeViews(list);
    }

    @Override
    protected void loadNetworkData() {

        // TODO Auto-generated method stub
        
        initViews();
    }

    @Override
    protected void loadLocalData() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void findViews() {
        TextView collectBtn = (TextView) findViewById(R.id.collection_btn);
        // TODO framelayout布局
        View picLayout = findViewById(R.id.pic_layout);
        RelativeLayout addressLayout = (RelativeLayout) findViewById(R.id.address_layout);
        RelativeLayout historyLayout = (RelativeLayout) findViewById(R.id.history_layout);
        RelativeLayout dateLayout = (RelativeLayout) findViewById(R.id.occupancy_and_leave_layout);
        mRoomsLayout = (LinearLayout) findViewById(R.id.room_type_layout);

        collectBtn.setOnClickListener(this);
        picLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);
        historyLayout.setOnClickListener(this);
        dateLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.collection_btn:

            break;
        case R.id.pic_layout:

            break;
        case R.id.address_layout:

            break;
        case R.id.history_layout:

            break;
        case R.id.occupancy_and_leave_layout:

            break;

        default:
            break;
        }
    }

    @Override
    protected void rightBtnClickEvent() {
        super.rightBtnClickEvent();
        //TODO 拨号
    }
    
    /**
     * 
     * initRoomsTypeViews:初始化房间类型视图. <br/>
     * @author maple
     * @param roomInfos
     * @since JDK 1.6
     */
    private void initRoomsTypeViews(List<RoomInfo> roomInfos){
        if (roomInfos == null || roomInfos.size() < 1) {
            return;
        }
        int size = roomInfos.size();
        mRoomsLayout.removeAllViews();
        for (int index = 0 ; index < size ; index++) {
            RoomInfo info = roomInfos.get(index);
            if (info == null) {
                continue;
            }
            RoomItemView itemView = new RoomItemView(this, info);
            Log.i("DEBUG", "itemView.width=" + itemView.getWidth() + ", itemView.height=" + itemView.getHeight());
//            mRoomsLayout.addView(itemView);
            mRoomsLayout.addView(itemView, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        }
    }
}
