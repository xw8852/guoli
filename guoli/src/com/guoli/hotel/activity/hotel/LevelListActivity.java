package com.guoli.hotel.activity.hotel;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.adapter.SingleTextAdapter;
import com.guoli.hotel.bean.MapInfo;

/**
 * Project Name:SplashActivity
 * File Name:LevelListActivity.java
 * Package Name:
 * Date:2013-1-26下午12:08:04
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

/**
 * ClassName:LevelListActivity <br/>
 * @Description:    酒店星级选择页面
 * Date:     2013-1-26 下午12:08:04 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class LevelListActivity extends BaseActivity implements OnItemClickListener {
    
    public static final String KEY_LEVEL = "level";
    
    private ListView mListView;
    
    private SingleTextAdapter mAdapter;
    
    public LevelListActivity(){
        mTitleTextId = R.string.hotel_level;
        mLayoutId = R.layout.level_list_layout;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.levelListView);
        mListView.setOnItemClickListener(this);
        String value = getIntent().getStringExtra(KEY_LEVEL);
        mAdapter = new SingleTextAdapter(convertToList(), this, value);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mAdapter == null) {
            return;
        }
        MapInfo info = mAdapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra(KEY_LEVEL, info == null ? "" : info.getValue());
        setResult(SearchHotelActivity.PAGE_LEVEL, intent);
        finish();
    }

    private List<MapInfo> convertToList(){
        String[] keys = getResources().getStringArray(R.array.level_key);
        String[] values = getResources().getStringArray(R.array.level_value);
        if (keys == null || values == null || keys.length != values.length) {
            return null;
        }
        List<MapInfo> list = new ArrayList<MapInfo>();
        int length = keys.length;
        for (int index = 0 ; index < length ; index++) {
            MapInfo info = new MapInfo();
            info.setKey(keys[index]);
            info.setValue(values[index]);
            list.add(info);
        }
        return list;
    }
}

