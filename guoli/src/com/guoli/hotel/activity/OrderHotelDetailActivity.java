package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class OrderHotelDetailActivity extends BaseActivity2 implements OnClickListener {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.order_detail);
        setLeftTitleBtn(R.string.back_btn, this);
        setRightTitleBtn(R.string.exit, this);
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
        default:
            break;
        }
    }

}
