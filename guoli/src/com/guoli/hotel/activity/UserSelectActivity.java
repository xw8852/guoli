/**
 * Project Name:SplashActivity
 * File Name:UserSelectActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-28下午10:36:33
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guoli.hotel.R;

/**
 * ClassName:UserSelectActivity <br/>
 * @Description:    选择入住人页面
 * Date:     2013-1-28 下午10:36:33 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UserSelectActivity extends BaseActivity {
    
    public static final String KEY_USERS= "users";
    
    public UserSelectActivity(){
        mTitleTextId = R.string.user_select;
        mLayoutId = R.layout.user_select_layout;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {
        TextView addBtn = (TextView) findViewById(R.id.addNewUserView);
        addBtn.setOnClickListener(this);
        TextView commitBtn = (TextView) findViewById(R.id.commitBtn);
        commitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.commitBtn:
            
            break;
        case R.id.addNewUserView:
            
            break;
        default:
            break;
        }
    }
}

