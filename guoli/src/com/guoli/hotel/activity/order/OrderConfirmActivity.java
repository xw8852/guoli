package com.guoli.hotel.activity.order;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.bean.OrderInfo;

/**
 * Project Name:Guoli
 * File Name:OrderConfirmActivity.java
 * Package Name:
 * Date:2013-2-24下午4:58:49
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

/**
 * ClassName:OrderConfirmActivity <br/>
 * 
 * @Description: 订单支付页面 Date: 2013-2-24 下午4:58:49 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class OrderConfirmActivity extends BaseActivity2 implements OnClickListener {
    OrderInfo mInfo;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.order_confirm);
        setLeftTitleBtn(R.string.back_btn, this);
        setRightTitleBtn(R.string.exit, this);
        mInfo = (OrderInfo) new Gson().fromJson(getIntent().getStringExtra("ORDER"), OrderInfo.class);
        TextView confirmPayBtn = (TextView) findViewById(R.id.commitBtn);
        confirmPayBtn.setOnClickListener(this);
        showOrder(mInfo);
    }

    @Override
    public int getContentId() {
        return R.layout.order_confirm;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.left_btn:

            break;
        case R.id.right_btn:

            break;
        case R.id.commitBtn:

            break;

        default:
            break;
        }
    }

    public void showOrder(OrderInfo mInfo) {
        TextView tv = (TextView) findViewById(R.id.orderNumber);
        tv.setText(mInfo.orderno);
        tv = (TextView) findViewById(R.id.orderTotal);
        tv.setText("￥"+mInfo.paymentmoney);
        tv = (TextView) findViewById(R.id.textView09);
        tv.setText("￥"+mInfo.shopname);
        tv = (TextView) findViewById(R.id.textView11);
        tv.setText("￥"+mInfo.address);
        tv = (TextView) findViewById(R.id.textView13);
        tv.setText("￥"+mInfo.phone);
        tv = (TextView) findViewById(R.id.textView15);
        tv.setText(mInfo.name);
        tv = (TextView) findViewById(R.id.textView17);
        tv.setText(mInfo.buynum+"间");
        tv = (TextView) findViewById(R.id.textView19);
        tv.setText(getString(R.string.order_list_timedate, mInfo.checkintime,mInfo.checkoutime));
    }

}
