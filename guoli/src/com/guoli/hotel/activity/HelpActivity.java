package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;

public class HelpActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.help_item);
        showLeftReturnBtn(false, -1);
    }

    @Override
    public int getContentId() {
        return R.layout.help_activity;
    }

}
