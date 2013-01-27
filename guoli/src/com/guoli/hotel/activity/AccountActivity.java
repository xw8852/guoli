package com.guoli.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.BottomTabbar;

public class AccountActivity extends BaseActivity2 implements View.OnClickListener{
    BottomTabbar mTabbar;
    
    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.account_title);
        mTabbar=new BottomTabbar(this, 3);
        showLeftReturnBtn(true, R.string.dialog_exit_message);
        setRightTitleBtn(R.string.exit, null);
        findViewById(R.id.textView1).setOnClickListener(this);
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.textView3).setOnClickListener(this);
        findViewById(R.id.textView4).setOnClickListener(this);
    }

    @Override
    public int getContentId() {
        return R.layout.account_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.textView1:
            startActivity(new Intent(this, EditUserInfoActivity.class));
            break;
        case R.id.textView2:
            startActivity(new Intent(this, EditPasswordActivity.class));
            break;
        case R.id.textView3:
            startActivity(new Intent(this, OrderHotelListAcivity.class));
            break;
        case R.id.textView4:
            startActivity(new Intent(this, FavoriteActivity.class));
            break;
        case R.id.textView5:
            startActivity(new Intent(this, FavoriteActivity.class));
            break;
        default:
            break;
        }
    }

}
