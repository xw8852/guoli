package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;
import android.view.View;

public class ForgetPasswordActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        showLeftReturnBtn(false, -1);
        findViewById(R.id.send).setOnClickListener(onSendClickListener);
    }

    @Override
    public int getContentId() {
        return R.layout.forget_activity;
    }

    
    View.OnClickListener onSendClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            setResult(RESULT_OK);
            finish();
        }
    };
}
