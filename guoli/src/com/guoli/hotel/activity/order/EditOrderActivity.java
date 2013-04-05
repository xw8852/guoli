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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.guoli.hotel.utils.DateUtils;
import com.guoli.hotel.utils.DigitalUtils;
import com.guoli.hotel.utils.JsonUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
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
    private TextView mCheckInUserView;
    /**联系人*/
    private TextView mContactNameView;
    private TextView mContactPhoneView;
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
        mContactNameView = (TextView) findViewById(R.id.contact_name_view);
        mContactPhoneView = (TextView) findViewById(R.id.contact_phone_view);
        
        mCheckInUserView = (TextView) findViewById(R.id.guest_content_view);
        
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
            int stopDays = getStopAtDays();
            findViewById(R.id.orderCostDetailView).setVisibility(stopDays > 1 ? View.VISIBLE : View.GONE);
        }
        mContactNameView.setText(LoginUtils.username);
        mContactPhoneView.setText(LoginUtils.memberMobile);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
        case R.id.invoice_false_btn:
            findViewById(R.id.invoiceDetailLayout).setVisibility(View.GONE);
            break;
        case R.id.invoice_true_btn:
            findViewById(R.id.invoiceDetailLayout).setVisibility(View.VISIBLE);
            break;

        default:
            break;
        }
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
            intent.putExtra(UserSelectActivity.KEY_FROM_PAGE, UserSelectActivity.FROM_PAGE_EDIT_ORDER);
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
            //用户只住一天,则订单总额详细不出现
            break;
        case PAGE_MORE_REQUIRE:
            String content = data == null ? "" : data.getStringExtra(MoreRequireActivity.KEY_MORE_REQUIRE);
            setViewText(mMoreRequireView, content);
            break;
        case PAGE_USER_ADD:
            ArrayList<String> list = data == null ? null : data.getStringArrayListExtra(UserSelectActivity.KEY_CHECK_IN_USER);
            setCheckInUsersView(list);
            break;
        default:
            break;
        }
    }
    
    /**
     * 
     * getRoomCount:获取预定的房间数. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private int getRoomCount(){
        String text = (String) mRoomCountView.getText();
        if (TextUtils.isEmpty(text)) {
            return -1;
        }
        text = text.substring(0, text.length() - 1);
        return DigitalUtils.convertToInt(text);
    }
    
    /**
     * 
     * setTotalCostView:设置订单总额. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void setTotalCostView(){
        int count = getRoomCount();
        if (count < 0) {
            return;
        }
        float actPrice = mRoomTypeInfo == null ? 0 : mRoomTypeInfo.getActprice();
        Log.i(TAG, "setTotalCostView()---> actPrice=" + actPrice + ", price=" + (mRoomTypeInfo == null ? 0 : mRoomTypeInfo.getPrice()));
        int cost = (int) (count * actPrice);
        mTotalCostView.setText(cost + "");
    }
    /**
     * 
     * getStopAtDays:获取入住天数. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private int getStopAtDays(){
        if (mHotelRoom == null) {
            return 0;
        }
        long startTimes = DateUtils.date2Long(mHotelRoom.getStartDate(), DateUtils.FORMAT_DATE_YYMMDD);
        long endTimes = DateUtils.date2Long(mHotelRoom.getEndDate(), DateUtils.FORMAT_DATE_YYMMDD);
        long increment = endTimes - startTimes;
        if (endTimes <= startTimes) {
            return 0;
        }
        return (int) (increment/DateUtils.MILLIS_FOR_ONE_DAY);
    }
    
    /**
     * 
     * setCheckInUsersView:设置入住人信息. <br/>
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void setCheckInUsersView(List<String> list){
        if (list == null || list.size() < 1) {
            return;
        }
        int size = list.size();
        StringBuilder buffer = new StringBuilder();
        for (int index = 0 ; index < size ; index++) {
            buffer.append(list.get(index));
            if (index < size - 1) {
                buffer.append(",");
            }
        }
        mCheckInUserView.setText(buffer.toString());
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
    
    /***
     * 
     * getInvoiceState:是否需要开发票. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private int getInvoiceState(){
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.invoice_true_btn) {
            return 1;
        }
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.invoice_false_btn) {
            return 0;
        }
        return -1;
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
        info.setCount(DigitalUtils.convertToInt(getRoomCount()+""));
        info.setMoreRequire((String) mMoreRequireView.getText());
        info.setInPeople(getCheckInUsers());
        info.setContactName(mContactNameView.getText().toString());
        info.setContactPhone(mContactPhoneView.getText().toString());
        info.setIsInvoice(getInvoiceState());
        info.setInvoiceTitle(mInvoiceTitleView.getText().toString());
        info.setRecipientName(mRecipientNameView.getText().toString());
        info.setRecipientPhone(mRecipientPoneView.getText().toString());
        info.setRecipientAddress(mRecipientAddessView.getText().toString());
        info.setRecipientPostCode(mRecipientPostCodeView.getText().toString());
        info.setUid(LoginUtils.uid);
        return info;
    }
    
    private String getCheckInUsers(){
        String userNames = mCheckInUserView.getText().toString();
        userNames.replaceAll("\n", ",");
        return userNames;
    }
    
    private IResponseListener mCommitLisenter = new IResponseListener() {
        
        @Override
        public void onSuccess(Response response) {
            dismissLoadingDialog();
            Log.i(TAG, "onSuccess()---->" + (response == null ? null : response.getData().toString()));
            if (response == null || response.getData() == null) {
                return;
            }
            Intent intent = new Intent();
            HashMap<String, String> map = JsonUtils.convertJsonToHashMap(response.getData().toString());
            String notice = map.get("message");
            intent.putExtra(CommitOrderSuccessActivity.KEY_RESPONSE_NOTICE, notice);
            Log.i(TAG, "onSuccess()----> notice=" + notice);
            intent.setClass(EditOrderActivity.this, CommitOrderSuccessActivity.class);
            startActivity(intent);
            finish();
        }
        
        @Override
        public void onError(Response response) {
            Log.i(TAG, "onError()---->");
            dismissLoadingDialog();
            ToastUtil.show(ErrorCode.getErrorCodeString(response.errorCode));
        }
    };
}

