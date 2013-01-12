package com.guoli.hotel.activity;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.BottomTabbar;

import android.os.Bundle;
import android.view.View;

public class MoreActivity extends BaseActivity2 {

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.more_title);
        findViewById(R.id.item1).setOnClickListener(mItemListener_0);
        findViewById(R.id.item2).setOnClickListener(mItemListener_1);
        findViewById(R.id.item3).setOnClickListener(mItemListener_2);
        new BottomTabbar(this, 4);
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
            
        }
    };
    /**
     * “反馈”的点击事件
     */
    View.OnClickListener mItemListener_1=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            
        }
    };
    /**
     * “关于我们的”的点击事件
     */
    View.OnClickListener mItemListener_2=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            
        }
    };
}
