package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;

public class ForgetPasswordActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        showLeftReturnBtn(false, -1);
    }

    @Override
    public int getContentId() {
        return R.layout.forget_activity;
    }

}
