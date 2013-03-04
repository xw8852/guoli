package com.guoli.hotel.activity.order;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.widget.AbstractAdapter;
import com.guoli.hotel.widget.BottomTabbar;

public class OrderHotelListAcivity extends BaseActivity2 implements OnItemClickListener {
    public static final String PARAMS_LOGIN = "login";

    ListView mListView;
    OrderAdapter mAdapter;
    public boolean isLogin;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        new BottomTabbar(this, 2);
        isLogin = getIntent().getBooleanExtra(PARAMS_LOGIN, false);
        mListView = (ListView) findViewById(R.id.listView1);
        mAdapter=new OrderAdapter(this, getListData(isLogin));
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        setTitle(R.string.order_list_title);
       
    }

    @Override
    public int getContentId() {
        return R.layout.order_hotel_list_activity;
    }

    class OrderAdapter extends AbstractAdapter<Orderstatus> {

        public OrderAdapter(Context context, List<Orderstatus> data) {
            super(context, data);
        }

        @Override
        public View CreateView(int position, View convertView, LayoutInflater inflater) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.order_hotel_list_item, null);
            }
            TextView tv=(TextView)convertView.findViewById(R.id.textView2);
            if(getItem(position).status==1){
                tv.setBackgroundResource(R.drawable.order_detail_pay_btn);
            }else{
                tv.setBackgroundColor(color.transparent);
            }
            switch (getItem(position).status) {
                case 0:tv.setText("待确认");break;
                case 1:tv.setText("付款");break;
                case 2:tv.setText("已付款");break;
                case 3:tv.setText("已结束");break;
            }
            return convertView;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent=new Intent(this, OrderHotelDetailActivity.class);
        intent.putExtra(OrderHotelDetailActivity.Status, mAdapter.getItem(arg2).status);
        startActivity(intent);
    }

    public List<Orderstatus> getListData(boolean isLogin) {
        List<Orderstatus> statuses = new ArrayList<OrderHotelListAcivity.Orderstatus>();
        statuses.add(new Orderstatus(0));
        if (isLogin)
            statuses.add(new Orderstatus(1));
        statuses.add(new Orderstatus(2));
        statuses.add(new Orderstatus(3));
        return statuses;
    }

    class Orderstatus {
        /**
         * 0：待确认 1：待付款 2：已付款 3：已结束
         */
        public int status;

        public Orderstatus(int status) {
            super();
            this.status = status;
        }

        public Orderstatus() {
            super();
        }

    }
}
