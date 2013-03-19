/**
 * Project Name:Guoli
 * File Name:OrderListActivity.java
 * Package Name:com.guoli.hotel.activity.hotel
 * Date:2013-3-19下午8:47:47
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

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
 * ClassName:OrderListActivity <br/>
 * @Description:
 * Date:     2013-3-19 下午8:47:47 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SortListActivity extends BaseActivity implements OnItemClickListener {

    private String[] mOrders;
    private ListView mListView;
    public static final String KEY_ORDER = "order";
    
    public SortListActivity(){
        mLayoutId = R.layout.order_list_layout;
        mTitleTextId = R.string.sort_title;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }
    
    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.orderListView);
        mListView.setOnItemClickListener(this);
        mOrders = getResources().getStringArray(R.array.order_value);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_single_layout, mOrders);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View parent, int position, long id) {
        if (mOrders == null || !(mOrders.length > position)) {
            return;
        }
        commit(mOrders[position]);
    }
    
    /**
     * 
     * commit:设置完成返回上一级时需要做的一些处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void commit(String order){
        Intent intent = new Intent();
        intent.putExtra(KEY_ORDER, order);
        setResult(HotelSearchResultActivity.ORDER_FILTER, intent);
        finish();
    }

}

