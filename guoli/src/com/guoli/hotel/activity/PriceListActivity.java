/**
 * Project Name:SplashActivity
 * File Name:PriceListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26上午11:36:16
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.guoli.hotel.R;

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
    
    private String[] mPrices = new String[]{"不限","￥1000以下","￥1000-1500","￥1500-2000","￥2000以上"};
    
    public PriceListActivity(){
        mLayoutId = R.layout.price_list_layout;
        mTitleTextId = R.string.hotel;
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
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mPrices);
        mListView.setAdapter(adapter);
    }

    
    /**
     * 
     * commit:设置完成返回上一级时需要做的一些处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void commit(String price){
        Intent intent = new Intent();
        intent.putExtra(KEY_PRICE, price);
        setResult(SearchHotelActivity.PAGE_PRICE, intent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mPrices == null || !(mPrices.length > position)) {
            return;
        }
        commit(mPrices[position]);
    }
}

