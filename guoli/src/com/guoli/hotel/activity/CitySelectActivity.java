/**
 * Project Name:SplashActivity
 * File Name:CitySelectActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-25下午7:21:11
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * ClassName:CitySelectActivity <br/>
 * @Description:    城市选择页面
 * Date:     2013-1-25 下午7:21:11 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CitySelectActivity extends BaseActivity implements OnItemClickListener {
    /**城市名称关键字*/
    public final static String KEY_CITY_NAME = "cityName";
    
    private String[] citys = new String[]{"上海","北京","广州","深圳","香港","东京","三亚","厦门","青岛","杭州","大连","成都"};
    
    private ListView mListView;
    private EditText mKeyWordView;
    
    
    public CitySelectActivity() {
        mLayoutId = R.layout.city_select_layout;
        mTitleTextId = R.string.city_select;
        mRightDrawableId = R.drawable.btn_top_phone;
    }
    
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showLeftBtn();
        showRightBtn();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.cityListView);
        mKeyWordView = (EditText) findViewById(R.id.cityNameKey);
        ImageView deleteBtn = (ImageView) findViewById(R.id.deleteBtn);
        ImageView searchBtn = (ImageView) findViewById(R.id.searchBtn);
        deleteBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, citys);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = citys[position];
        if (TextUtils.isEmpty(name)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_CITY_NAME, name);
        setResult(SearchHotelActivity.PAGE_CITY, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.searchBtn:
            
            break;
        case R.id.deleteBtn:
            mKeyWordView.setText("");
            break;
        default:
            break;
        }
    }
}

