package com.guoli.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guoli.hotel.R;

public class LoginActivity extends BaseActivity2 {
    
    public static final int RESULT_LOGIN_OK=0x0001;
    public static final int RESULT_UN_LOGIN=0x0002;
    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.login);
        findViewById(R.id.textView6).setOnClickListener(onForgetPassword);
        findViewById(R.id.textView7).setOnClickListener(onRegistUser);
        findViewById(R.id.button2).setOnClickListener(onLoginListener);
        findViewById(R.id.button1).setOnClickListener(onUnLogClickListener);
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
            startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class),RESULT_OK);
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
            startActivityForResult(new Intent(LoginActivity.this, ForgetPasswordActivity.class),RESULT_OK);
        }
    };
    /**
     * 登录
     */
    View.OnClickListener onLoginListener =new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            setResult(RESULT_LOGIN_OK);
            finish();
        }
    };
    /**
     * 非会员直接预订
     */
    View.OnClickListener onUnLogClickListener=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            setResult(RESULT_UN_LOGIN);
            finish();
        }
    };
    
}
