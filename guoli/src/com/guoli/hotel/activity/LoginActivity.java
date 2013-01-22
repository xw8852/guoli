package com.guoli.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guoli.hotel.R;

public class LoginActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        findViewById(R.id.textView6).setOnClickListener(onForgetPassword);
        findViewById(R.id.textView7).setOnClickListener(onRegistUser);
    }

    @Override
    public int getContentId() {
        return R.layout.login_activity;
    }

    /**
     * 点击 注册用户
     * 
     * @param v
     */
    View.OnClickListener onRegistUser=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    };

   
   
    /**
     * 点击 忘记密码
     * 
     * @param v
     */
    View.OnClickListener onForgetPassword=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
        }
    };
}
