/**
 * Project Name:SplashActivity
 * File Name:HotelCountActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-28下午7:08:30
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

package com.guoli.hotel.activity.hotel;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.activity.order.EditOrderActivity;
import com.guoli.hotel.adapter.SingleTextAdapter;
import com.guoli.hotel.bean.MapInfo;

/**
 * ClassName:HotelCountActivity <br/>
 * 
 * @Description: Date: 2013-1-28 下午7:08:30 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class HotelCountActivity extends BaseActivity implements OnItemClickListener {

    public static final String KEY_ROOM_COUNT = "roomCount";

    private ListView mListView;

    private String[] mRooms = new String[] { "1间", "2间", "3间", "4间", "5间" };

    public HotelCountActivity() {
        mTitleTextId = R.string.room_counts;
        mLayoutId = R.layout.room_count_layout;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.roomCountListView);
        mListView.setOnItemClickListener(this);
        String value = getIntent().getStringExtra("roomCount");
        SingleTextAdapter adapter = new SingleTextAdapter(convertToList(mRooms), this, value);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mRooms == null || !(mRooms.length > position)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_ROOM_COUNT, mRooms[position]);
        setResult(EditOrderActivity.PAGE_ROOM_COUNT, intent);
        finish();
    }


    private List<MapInfo> convertToList(String[] rooms){
        if (rooms == null || rooms.length == 0) {
            return null;
        }
        List<MapInfo> list = new ArrayList<MapInfo>();
        for (String room : rooms) {
            if (TextUtils.isEmpty(room)) {
                continue;
            }
            MapInfo info = new MapInfo();
            info.setValue(room);
            list.add(info);
        }
        return list;
    }
}
