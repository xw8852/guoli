/**
 * Project Name:SplashActivity
 * File Name:OccupancyListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-28下午7:38:24
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.adapter.AbstractAdapter;
import com.guoli.hotel.bean.CheckInfo;

/**
 * ClassName:OccupancyListActivity <br/>
 * @Description:    入住日期清单页面
 * Date:     2013-1-28 下午7:38:24 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CheckListActivity extends BaseActivity {
    
    private ListView mListView;
    
    public CheckListActivity(){
        mTitleTextId = R.string.occupancy_list;
        mLayoutId = R.layout.check_list_layout;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        List<CheckInfo> list = getList();
        updateListView(list);
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.occupancyListView);
    }

    private List<CheckInfo> getList(){
        ArrayList<CheckInfo> list = new ArrayList<CheckInfo>();
        for (int index = 0 ; index < 10 ; index++) {
            CheckInfo info = new CheckInfo();
            info.setDate("12-11 (星期二)");
            info.setPrice("￥999");
            info.setName("双早");
            list.add(info);
        }
        return list;
    }
    
    /**
     * 
     * updateListView:更新列表数据. <br/>
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void updateListView(List<CheckInfo> list){
        if (mListView == null || list == null || list.size() < 1) {
            return;
        }
        CheckInfoAdapter adapter = (CheckInfoAdapter) mListView.getAdapter();
        if (adapter == null) {
            adapter = new CheckInfoAdapter(list, this);
            mListView.setAdapter(adapter);
            return;
        }
        adapter.clear();
        adapter.changeData(list);
    }

    private class CheckInfoAdapter extends AbstractAdapter<CheckInfo> {
        
        public CheckInfoAdapter(List<CheckInfo> data, Context context) {
            super(data, context);
        }


        @Override
        public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
            CheckInfo info = getItem(position);
            if (info == null) {
                return null;
            }
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_check_layout, null);
                holder = new ViewHolder();
                holder.dateView = (TextView) convertView.findViewById(R.id.dateItemView);
                holder.priceView = (TextView) convertView.findViewById(R.id.priceItemView);
                holder.nameView = (TextView) convertView.findViewById(R.id.nameItemView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.dateView.setText(info.getDate());
            holder.priceView.setText(info.getPrice());
            holder.nameView.setText(info.getName());
            return convertView;
        }
        
        private class ViewHolder {
            TextView dateView;
            TextView priceView;
            TextView nameView;
        }
    }
    
}

