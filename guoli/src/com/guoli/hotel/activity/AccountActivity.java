package com.guoli.hotel.activity;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.BottomTabbar;

import android.os.Bundle;

public class AccountActivity extends BaseActivity2 {
    BottomTabbar mTabbar;
    
    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.account_title);
        mTabbar=new BottomTabbar(this, 3);
        showLeftReturnBtn(true, R.string.dialog_exit_message);
        setRightTitleBtn(R.string.exit, null);
        
    }

    @Override
    public int getContentId() {
        return R.layout.account_activity;
    }

}
