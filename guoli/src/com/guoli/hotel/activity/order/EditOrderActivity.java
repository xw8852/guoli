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
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.guoli.hotel.bean.OrderSubmitInfo;
import com.guoli.hotel.bean.RoomTypeInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRoom;
import com.guoli.hotel.utils.DigitalUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

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
    /**日期*/
    private TextView mDateView;
    /**房型*/
    private TextView mRoomTypeView;
    /**更多要求*/
    private TextView mMoreRequireView;
    /**订单总额*/
    private TextView mTotalCostView;
    /**入住人*/
    private TextView mUserNameView;
    /**联系人*/
    /**是否开发票*/
    private RadioGroup mRadioGroup;
    /**抬头*/
    private EditText mInvoiceTitleView;
    /**收件人名称*/
    private EditText mRecipientNameView;
    /**收件人手机号码*/
    private EditText mRecipientPoneView;
    /**邮寄地址*/
    private EditText mRecipientAddessView;
    /**邮编*/
    private EditText mRecipientPostCodeView;
    /**特别提示*/
    private TextView mSpecialNoticeView; 
    
    private HotelRoom mHotelRoom;
    private RoomTypeInfo mRoomTypeInfo;
    
    public static final int PAGE_ROOM_COUNT = 0;
    public static final int PAGE_MORE_REQUIRE = 1;
    public static final int PAGE_CHECK_LIST = 2;
    public static final int PAGE_USER_ADD = 3;
    
    private static final String TAG = EditOrderActivity.class.getSimpleName();
    
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
        mRoomTypeInfo=new Gson().fromJson(getIntent().getStringExtra(ROOMI_TYPE), RoomTypeInfo.class);
        mHotelRoom=new Gson().fromJson(getIntent().getStringExtra(HOTEL_ROOM), HotelRoom.class);
        initViews();
    }

    @Override
    protected void findViews() {
        TextView tv=(TextView)findViewById(R.id.hotel_name);
        tv.setText(getIntent().getStringExtra("HOTEL_NAME"));
        mDateView = (TextView) findViewById(R.id.date_content_view);
        mRoomTypeView = (TextView) findViewById(R.id.room_type_content_view);
        mRoomCountView = (TextView) findViewById(R.id.room_count_content_view);
        mMoreRequireView = (TextView) findViewById(R.id.more_require_content_view);
        mTotalCostView = (TextView) findViewById(R.id.order_cost_cost_view);
        
        mUserNameView = (TextView) findViewById(R.id.guest_content_view);
        
        mRadioGroup = (RadioGroup) findViewById(R.id.invoice_potion_group);
        mInvoiceTitleView = (EditText) findViewById(R.id.invoice_title_content_view);
        mRecipientNameView = (EditText) findViewById(R.id.invoice_receiver_content_view);
        mRecipientPoneView = (EditText) findViewById(R.id.invoice_receiver_phone_content_view);
        mRecipientAddessView = (EditText) findViewById(R.id.invoice_mail_address_content_view);
        mRecipientPostCodeView = (EditText) findViewById(R.id.invoice_post_code_content_view);
         
        mSpecialNoticeView = (TextView) findViewById(R.id.specialNoticeView);
        
        mRoomCountView.setOnClickListener(this);
        mMoreRequireView.setOnClickListener(this);
        mRadioGroup.setOnCheckedChangeListener(this);
        findViewById(R.id.orderCostDetailView).setOnClickListener(this);
        TextView addUserBtn = (TextView) findViewById(R.id.add_btn);
        addUserBtn.setOnClickListener(this);
        TextView commitBtn = (TextView) findViewById(R.id.commitBtn);
        commitBtn.setOnClickListener(this);
    }
    
    private void initViews(){
        if (mRoomTypeInfo != null) {
            mRoomTypeView.setText(mRoomTypeInfo.getName());
        }
        if (mHotelRoom != null) {
            String resDate = getString(R.string.order_date_period);
            mDateView.setText(String.format(resDate, mHotelRoom.getStartDate(), mHotelRoom.getEndDate()));
        }
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
        OrderSubmitInfo info = getOrderSubmitInfo();
        if (info == null) {
            return;
        }
        showLoadingDialog(R.string.loading_msg_commit);
        GuoliRequest request = new GuoliRequest(Action.Order.OrderSubmit, info);
        Manager.getInstance().executePoset(request, mCommitLisenter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
        case PAGE_ROOM_COUNT:
            String area = data == null ? "" : data.getStringExtra(HotelCountActivity.KEY_ROOM_COUNT);
            setViewText(mRoomCountView, area);
            setTotalCostView();
            break;
        case PAGE_MORE_REQUIRE:
            String content = data == null ? "" : data.getStringExtra(MoreRequireActivity.KEY_MORE_REQUIRE);
            setViewText(mMoreRequireView, content);
            break;
        default:
            break;
        }
    }
    
    private void setTotalCostView(){
        String text = (String) mRoomCountView.getText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        text = text.substring(0, text.length() - 1);
        int count = DigitalUtils.convertToInt(text);
        if (count < 0) {
            return;
        }
        int cost = (int) (count * (mRoomTypeInfo == null ? 0 : mRoomTypeInfo.getActprice()));
        mTotalCostView.setText(cost + "");
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
    
    private OrderSubmitInfo getOrderSubmitInfo(){
        if (mHotelRoom == null || mRoomTypeInfo == null) {
            return null;
        }
        OrderSubmitInfo info = new OrderSubmitInfo();
        info.setHotelId(mHotelRoom.getId());
        info.setStartDate(mHotelRoom.getStartDate());
        info.setEndDate(mHotelRoom.getEndDate());
        info.setRoomId(mRoomTypeInfo.getPid());
        info.setCount(DigitalUtils.convertToInt((String) mRoomCountView.getText()));
        info.setMoreRequire((String) mMoreRequireView.getText());
        //TODO 入住人
//        info.setInPeople(inPeople);
        TextView contactNameView = (TextView) findViewById(R.id.contact_name_view);
        info.setContactName((String) contactNameView.getText());
        TextView contactPhoneView = (TextView) findViewById(R.id.contact_phone_view);
        info.setContactPhone((String) contactPhoneView.getText());
        //TODO 是否需要发票
//        info.setIsInvoice(isInvoice);
        info.setInvoiceTitle(mInvoiceTitleView.getText().toString());
        info.setRecipientName(mRecipientNameView.getText().toString());
        info.setRecipientAddress(mRecipientAddessView.getText().toString());
        info.setRecipientPostCode(mRecipientPostCodeView.getText().toString());
        //TODO 收件人电话
        return info;
    }
    
    private IResponseListener mCommitLisenter = new IResponseListener() {
        
        @Override
        public void onSuccess(Response response) {
            dismissLoadingDialog();
            Intent intent = new Intent();
            //TODO
//            intent.putExtra(CommitOrderSuccessActivity.KEY_RESPONSE_NOTICE, value);
            intent.setClass(EditOrderActivity.this, CommitOrderSuccessActivity.class);
            startActivity(intent);
        }
        
        @Override
        public void onError(Response response) {
            dismissLoadingDialog();
        }
    };
}

