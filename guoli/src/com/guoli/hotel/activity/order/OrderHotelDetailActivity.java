package com.guoli.hotel.activity.order;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class OrderHotelDetailActivity extends BaseActivity2 implements OnClickListener {
    public static final String Status = "status";

    Button cancel_btn;
    Button pay_btn;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.order_detail);
        int status = getIntent().getIntExtra(Status, 1);
        setLeftTitleBtn(R.string.back_btn, this);
        setRightTitleBtn(R.string.exit, this);
        setTitle(R.string.order_detail);
        TextView tv = (TextView) findViewById(R.id.textView4);
        findViewById(R.id.btn_view).setVisibility(View.GONE);
        pay_btn = (Button) findViewById(R.id.button2);
        cancel_btn = (Button) findViewById(R.id.button1);
        pay_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.left_btn:
            finish();
            break;
        case R.id.right_btn:
            //TODO
            break;
        case R.id.button1:
            //TODO
            break;
        case R.id.button2:
            //TODO 跳转到订单确认页面
            Intent intent = new Intent();
            intent.setClass(this, OrderConfirmActivity.class);
            startActivity(intent);
            break;
        default:
            break;
        }
    }

}
