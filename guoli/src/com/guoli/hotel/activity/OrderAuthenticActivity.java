package com.guoli.hotel.activity;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.BottomTabbar;

import android.content.Intent;
import android.os.Bundle;

public class OrderAuthenticActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.order_title_search);
        startActivityForResult(new Intent(this, LoginActivity.class), 0);
        new BottomTabbar(this, 2);
    }

    @Override
    public int getContentId() {
        return R.layout.order_authentic_activity;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==LoginActivity.RESULT_UN_LOGIN){
            
        }else if(resultCode==LoginActivity.RESULT_LOGIN_OK){
            startActivity(new Intent(this, OrderHotelListAcivity.class));
        }
    }

}
