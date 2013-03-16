package com.guoli.hotel.activity.hotel;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;

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
    
    private String[] mLevels;
    
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
        mLevels = getResources().getStringArray(R.array.level_value);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_single_layout, mLevels);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mLevels == null || !(mLevels.length > position)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_LEVEL, mLevels[position]);
        setResult(SearchHotelActivity.PAGE_LEVEL, intent);
        finish();
    }

}
