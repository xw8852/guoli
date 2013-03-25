/**
 * Project Name:SplashActivity
 * File Name:EditOrderActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-22下午12:15:03
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.CallActivity;
import com.guoli.hotel.activity.MoreRequireActivity;
import com.guoli.hotel.activity.hotel.CheckListActivity;
import com.guoli.hotel.activity.hotel.HotelCountActivity;
import com.guoli.hotel.activity.user.UserSelectActivity;
import com.guoli.hotel.bean.RoomTypeInfo;
import com.guoli.hotel.net.request.bean.HotelRoom;

/**
 * ClassName:EditOrderActivity <br/>
 * @Description:    订单编辑页面
 * Date:     2013-1-22 下午12:15:03 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class EditOrderActivity extends CallActivity implements OnCheckedChangeListener {
    public static final String HOTEL_ROOM="HOTELROOM";
    public static final String ROOMI_TYPE="ROOMTYPE";
    
    /**房间数*/
    private TextView mRoomCountView;
    /**更多要求*/
    private TextView mMoreView;
    /**发票抬头*/
    private RadioGroup mRadioGroup;
    private HotelRoom mHotelRoom;
    private RoomTypeInfo mRoomTypeInfo;
    
    public static final int PAGE_ROOM_COUNT = 0;
    public static final int PAGE_MORE_REQUIRE = 1;
    public static final int PAGE_CHECK_LIST = 2;
    public static final int PAGE_USER_ADD = 3;
    
    public EditOrderActivity(){
        mTitleTextId = R.string.order_edit_title;
        mLayoutId = R.layout.edit_order;
        mRightDrawableId = R.drawable.btn_top_phone;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        showRightBtn();
        mHotelRoom=new Gson().fromJson(getIntent().getStringExtra(HOTEL_ROOM), HotelRoom.class);
        mRoomTypeInfo=new Gson().fromJson(getIntent().getStringExtra(ROOMI_TYPE), RoomTypeInfo.class);
    }

    @Override
    protected void findViews() {
        TextView tv=(TextView)findViewById(R.id.hotel_name);
        tv.setText(getIntent().getStringExtra("HOTEL_NAME"));
        mRoomCountView = (TextView) findViewById(R.id.room_count_content_view);
        mRoomCountView.setOnClickListener(this);
        mMoreView = (TextView) findViewById(R.id.more_require_content_view);
        mMoreView.setOnClickListener(this);
        mRadioGroup = (RadioGroup) findViewById(R.id.invoice_potion_group);
        mRadioGroup.setOnCheckedChangeListener(this);
        //订单总额详细
        TextView orderCostView = (TextView) findViewById(R.id.orderCostDetailView);
        orderCostView.setOnClickListener(this);
        TextView addUserBtn = (TextView) findViewById(R.id.add_btn);
        addUserBtn.setOnClickListener(this);
        TextView commitBtn = (TextView) findViewById(R.id.commitBtn);
        commitBtn.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        
    }
    
    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
        case R.id.room_count_content_view:
            intent = new Intent();
            intent.setClass(this, HotelCountActivity.class);
            startActivityForResult(intent, PAGE_ROOM_COUNT);
            break;
        case R.id.more_require_content_view:
            intent = new Intent();
            intent.setClass(this, MoreRequireActivity.class);
            startActivityForResult(intent, PAGE_MORE_REQUIRE);
            break;
        case R.id.orderCostDetailView:
            intent = new Intent();
            intent.setClass(this, CheckListActivity.class);
            startActivity(intent);
            break;
        case R.id.add_btn:
            intent = new Intent();
            intent.setClass(this, UserSelectActivity.class);
            startActivityForResult(intent, PAGE_USER_ADD);
            break;
        case R.id.commitBtn:
            commitOrder();
            break;

        default:
            break;
        }
    }

    /**
     * 
     * commitOrder:提交订单. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void commitOrder() {
        Intent intent = new Intent();
        intent.setClass(this, CommitOrderSuccessActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
        case PAGE_ROOM_COUNT:
            String area = data == null ? "" : data.getStringExtra(HotelCountActivity.KEY_ROOM_COUNT);
            setViewText(mRoomCountView, area);
            break;
        case PAGE_MORE_REQUIRE:
            String content = data == null ? "" : data.getStringExtra(MoreRequireActivity.KEY_MORE_REQUIRE);
            setViewText(mMoreView, content);
            break;
        default:
            break;
        }
    }
    
    /**
     * 
     * setViewText:设置textView的值. <br/>
     * 
     * @author maple
     * @param view
     * @param text
     * @since JDK 1.6
     */
    private void setViewText(TextView view, String text) {
        if (view == null) { return; }
        view.setText(text == null ? "" : text);
    }
}

