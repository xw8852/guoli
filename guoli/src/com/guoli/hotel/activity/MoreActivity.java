package com.guoli.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.BottomTabbar;

public class MoreActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.more_title);
        findViewById(R.id.item1).setOnClickListener(mItemListener_0);
        findViewById(R.id.item2).setOnClickListener(mItemListener_1);
        findViewById(R.id.item3).setOnClickListener(mItemListener_2);
        new BottomTabbar(this, 4);
//        showLeftReturnBtn(true, R.string.dialog_exit_message);
        showRightCall();
    }

    @Override
    public int getContentId() {
        return R.layout.more_activity;
    }
    /**
     * “帮助”的点击事件
     */
    View.OnClickListener mItemListener_0=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MoreActivity.this, HelpActivity.class));
        }
    };
    /**
     * “反馈”的点击事件
     */
    View.OnClickListener mItemListener_1=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MoreActivity.this, FeedBackActivity.class));
        }
    };
    /**
     * “关于我们的”的点击事件
     */
    View.OnClickListener mItemListener_2=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MoreActivity.this, AboutUsActivity.class));
        }
    };
}
