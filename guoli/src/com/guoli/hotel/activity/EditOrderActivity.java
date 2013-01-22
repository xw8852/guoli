/**
 * Project Name:SplashActivity
 * File Name:EditOrderActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-22下午12:15:03
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import com.guoli.hotel.R;

/**
 * ClassName:EditOrderActivity <br/>
 * @Description:    订单编辑页面
 * Date:     2013-1-22 下午12:15:03 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class EditOrderActivity extends BaseActivity {
    
    public EditOrderActivity(){
        mTitleTextId = R.string.order_edit_title;
        mLayoutId = R.layout.edit_order;
    }

    @Override
    protected void findViews() {
        showLeftBtn();
        showRightBtn();
    }

}

