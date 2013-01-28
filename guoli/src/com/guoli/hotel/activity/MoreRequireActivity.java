/**
 * Project Name:SplashActivity
 * File Name:MoreRequireActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-28下午7:23:36
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.guoli.hotel.R;

/**
 * ClassName:MoreRequireActivity <br/>
 * @Description:    更多要求页面
 * Date:     2013-1-28 下午7:23:36 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MoreRequireActivity extends BaseActivity implements OnClickListener {
    
    private EditText mRequireText;
    
    public MoreRequireActivity(){
        mLayoutId = R.layout.more_require_layout;
        mTitleTextId = R.string.more_require_title;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
    }

    @Override
    protected void findViews() {
        mRequireText = (EditText) findViewById(R.id.moreRequireView);
        TextView commitBtn = (TextView) findViewById(R.id.commitBtn);
        commitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.commitBtn:
            
            break;
        default:
            break;
        }
    }
}

