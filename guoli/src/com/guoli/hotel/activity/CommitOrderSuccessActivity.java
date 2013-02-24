/**
 * Project Name:Guoli
 * File Name:CommitOrderSuccessActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-2-24下午2:21:46
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.guoli.hotel.R;

/**
 * ClassName:CommitOrderSuccessActivity <br/>
 * @Description:    订单提交成功页面
 * Date:     2013-2-24 下午2:21:46 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CommitOrderSuccessActivity extends BaseActivity {
    
    public CommitOrderSuccessActivity(){
        mLayoutId = R.layout.commit_order_success;
        mTitleTextId = R.string.commit_order_sucess_title;
    }

    @Override
    protected void findViews() {
        TextView continueBtn = (TextView) findViewById(R.id.continue_reserve_btn);
        TextView lookBtn = (TextView) findViewById(R.id.look_over_order_btn);
        continueBtn.setOnClickListener(this);
        lookBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.condition_and_result_layout:
            //跳转到酒店详情页面
            enterToHotelDetailActivity();
            break;
        case R.id.look_over_order_btn:
            //跳转到订单详情页面
            enterToOrderDetailActivity();
            break;
        default:
            break;
        }
    }

    /**
     * 
     * enterToHotelDetailActivity:跳转到酒店详情页面. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void enterToHotelDetailActivity() {
        startActivity(HotelDetailActivity.class, this);
    }

    /***
     * 
     * enterToOrderDetailActivity:跳转到订单详情页面. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void enterToOrderDetailActivity() {
        startActivity(OrderHotelDetailActivity.class, this);
    }
    
    /**
     * 
     * startActivity:跳转到指定页面. <br/>
     * @author maple
     * @param cls   指定activity类名
     * @param ctx
     * @since JDK 1.6
     */
    private void startActivity(Class<?> cls, Context ctx){
        if (ctx == null || cls == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(ctx, cls);
        startActivity(intent);
    }
}

