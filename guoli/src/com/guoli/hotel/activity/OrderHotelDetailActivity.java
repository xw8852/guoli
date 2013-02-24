package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderHotelDetailActivity extends BaseActivity2 {
    public static final String Status = "status";

    Button cancel_btn;
    Button pay_btn;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        int status = getIntent().getIntExtra(Status, 0);
        setTitle(R.string.order_detail);
        TextView tv = (TextView) findViewById(R.id.textView4);
        findViewById(R.id.btn_view).setVisibility(View.GONE);
        pay_btn = (Button) findViewById(R.id.button2);
        cancel_btn = (Button) findViewById(R.id.button1);
        switch (status) {
        case 0:
            tv.setText("待确认");
            cancel_btn.setText(R.string.detail_hotal_cancel);
            break;
        case 1:
            findViewById(R.id.btn_view).setVisibility(View.VISIBLE);
            findViewById(R.id.button1).setVisibility(View.GONE);
            cancel_btn = (Button) findViewById(R.id.button3);
            tv.setText("未付款");
            break;
        case 2:
            tv.setText("已付款");
            break;
        case 3:
            tv.setText("已结束");
            cancel_btn.setVisibility(View.GONE);
            break;
        }
    }

    @Override
    public int getContentId() {
        return R.layout.order_hotel_details;
    }

}
