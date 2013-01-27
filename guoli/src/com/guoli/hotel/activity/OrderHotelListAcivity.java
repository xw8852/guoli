package com.guoli.hotel.activity;

import java.util.List;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.AbstractAdapter;
import com.guoli.hotel.widget.BottomTabbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class OrderHotelListAcivity extends BaseActivity2 {

    ListView mListView;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        new BottomTabbar(this, 2);
        mListView = (ListView) findViewById(R.id.listView1);
        mListView.setAdapter(new OrderAdapter(this, null));

    }

    @Override
    public int getContentId() {
        return R.layout.order_hotel_list_activity;
    }

    class OrderAdapter extends AbstractAdapter<String> {

        public OrderAdapter(Context context, List<String> data) {
            super(context, data);
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public String getItem(int position) {
            return "";
        }

        @Override
        public View CreateView(int position, View convertView, LayoutInflater inflater) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.order_hotel_list_item, null);
            }
            return convertView;
        }

    }
}
