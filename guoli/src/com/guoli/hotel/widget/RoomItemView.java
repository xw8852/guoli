/**
 * Project Name:Guoli
 * File Name:RoomItemView.java
 * Package Name:com.guoli.hotel.widget
 * Date:2013-1-14下午10:06:14
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.LoginActivity;
import com.guoli.hotel.bean.RoomInfo;

/**
 * ClassName:RoomItemView <br/>
 * @Description:    商家详情页面的房间类型详细描述视图
 * Date:     2013-1-14 下午10:06:14 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RoomItemView extends View implements OnClickListener {
    
    private Context mCtx;
    private RoomInfo mInfo;
    private LinearLayout mRoomInfoLayout;
    
    public RoomItemView(Context ctx, RoomInfo info){
        super(ctx);
        mCtx = ctx;
        mInfo = info;
        initView();
    }

    public RoomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        if (mInfo == null || mCtx == null) {
            return;
        }
        View view = LayoutInflater.from(mCtx).inflate(R.layout.room_item_view, null);
        if (view == null) {
            return;
        }
        LayoutParams lp = view.getLayoutParams();
        Log.i("DEBUG", "initView()--->lp=" + lp + ", width=" + (lp == null ? null : lp.width)
                + ", height=" + (lp == null ? null : lp.height));
        RelativeLayout baseLayout = (RelativeLayout) view.findViewById(R.id.type_base_layout);
        if (baseLayout == null) {
            return;
        }
        baseLayout.setOnClickListener(this);
        mRoomInfoLayout = (LinearLayout) view.findViewById(R.id.room_info_type);
        TextView scheduleBtn = (TextView) view.findViewById(R.id.schedule_btn);
        if (scheduleBtn != null) {
            scheduleBtn.setOnClickListener(this);
        }
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.room_info_type:
            if (mRoomInfoLayout == null) {
                break;
            }
            int visibility = mRoomInfoLayout.getVisibility();
            if (View.GONE == visibility) {
                mRoomInfoLayout.setVisibility(View.VISIBLE);
            } else if (View.VISIBLE == visibility) {
                mRoomInfoLayout.setVisibility(View.GONE);
            }
            break;
        case R.id.schedule_btn:
            enterLoginActivity();
            break;
        default:
            break;
        }
    }

    /**
     * 
     * enterLoginActivity:跳转到登陆页面. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void enterLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(mCtx, LoginActivity.class);
        mCtx.startActivity(intent);
    }
}

