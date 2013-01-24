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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.RoomInfo;

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
    
    private ListView mRoomsListView;
    private RoomAdapter mAdapter;
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
        for (int index = 0 ; index < 5 ; index++) {
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
        View addressLayout =  findViewById(R.id.address_layout);
        View historyLayout =  findViewById(R.id.history_layout);
        RelativeLayout dateLayout = (RelativeLayout) findViewById(R.id.occupancy_and_leave_layout);
        mRoomsListView = (ListView)findViewById(R.id.listView1);
        collectBtn.setOnClickListener(this);
        picLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);
        historyLayout.setOnClickListener(this);
        dateLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
        case R.id.collection_btn:

            break;
        case R.id.pic_layout:

            break;
        case R.id.address_layout:
            intent = new Intent();
            intent.setClass(this, HotelLocationActivity.class);
            startActivity(intent);
            break;
        case R.id.history_layout:
            intent = new Intent();
            intent.setClass(this, HotelInfoActivity.class);
            startActivity(intent);
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
       mAdapter=new RoomAdapter(roomInfos);
       mRoomsListView.setAdapter(mAdapter);
       mRoomsListView.setOnItemClickListener(mRoomOnItemClickListener);
    }
    
    AdapterView.OnItemClickListener mRoomOnItemClickListener=new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Holder holder=(Holder)arg1.getTag();
            RoomModel model=mAdapter.getItem(arg2);
            if(model.isShowMore){
                holder.more.setVisibility(View.GONE);
                model.isShowMore=false;
            }else{
                holder.more.setVisibility(View.VISIBLE);
                model.isShowMore=true;
            }
        }
    };
    
    
    class RoomAdapter extends BaseAdapter{
        List<RoomModel> models;
        
        public RoomAdapter(List<RoomInfo> roomInfos) {
            super();
            models=new ArrayList<HotelDetailActivity.RoomModel>();
            for (RoomInfo roomInfo : roomInfos) {
                RoomModel model=new RoomModel();
                model.mInfo=roomInfo;
                models.add(model);
            }
        }

        @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public RoomModel getItem(int position) {
            return models.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.recommended_hotel_detail_room_item, null);
                Holder holder=new Holder();
                holder.simple = view.findViewById(R.id.simple_room);
                holder.more = view.findViewById(R.id.more_room);
               
                view.setTag(holder);
            }
            Holder holder = (Holder)view.getTag();
            if(getItem(position).isShowMore){
                holder.more.setVisibility(View.VISIBLE);
            } else 
                holder.more.setVisibility(View.GONE);
            return view;
        }
        
    }
    
    static class RoomModel{
        RoomInfo mInfo;
        boolean isShowMore;
    }
    static class Holder{
        View simple;
        View more;
    }
}
