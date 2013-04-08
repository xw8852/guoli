package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;

public class AboutUsActivity extends BaseActivity2{

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.about_item);
        showLeftReturnBtn(false, -1);
        showRightCall();
        loadData();
    }


    @Override
    public int getContentId() {
        return R.layout.about_us;
    }

    private void loadData() {
        
    }
}
