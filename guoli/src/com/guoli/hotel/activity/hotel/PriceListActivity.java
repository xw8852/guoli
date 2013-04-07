/**
 * Project Name:SplashActivity
 * File Name:PriceListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26上午11:36:16
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

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
 * ClassName:PriceListActivity <br/>
 * @Description:    价格列表
 * Date:     2013-1-26 上午11:36:16 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class PriceListActivity extends BaseActivity implements OnItemClickListener {
    
    public static final String KEY_PRICE = "price";
    
    private ListView mListView;
    
    private SingleTextAdapter mAdapter;
    
    public PriceListActivity(){
        mLayoutId = R.layout.price_list_layout;
        mTitleTextId = R.string.hotel_price;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.priceListView);
        mListView.setOnItemClickListener(this);
        String value = getIntent().getStringExtra(KEY_PRICE);
        mAdapter = new SingleTextAdapter(convertToList(), this, value);
        mListView.setAdapter(mAdapter);
    }

    
    /**
     * 
     * commit:设置完成返回上一级时需要做的一些处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void commit(MapInfo info){
        if (info == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_PRICE, info.getValue());
        setResult(SearchHotelActivity.PAGE_PRICE, intent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        commit(mAdapter == null ? null : mAdapter.getItem(position));
    }
    
    private List<MapInfo> convertToList(){
        String[] keys = getResources().getStringArray(R.array.price_key);
        String[] values = getResources().getStringArray(R.array.price_value);
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

