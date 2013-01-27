package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        findViewById(R.id.send).setOnClickListener(onRegisterClickListener);
    }

    @Override
    public int getContentId() {
        return R.layout.regist_activity;
    }

    View.OnClickListener onRegisterClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            setResult(RESULT_OK);
            finish();
        }
    };
}
