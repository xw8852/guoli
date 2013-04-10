package com.guoli.hotel.activity.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.bean.OrderInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.JsonUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * Project Name:Guoli
 * File Name:OrderConfirmActivity.java
 * Package Name:
 * Date:2013-2-24下午4:58:49
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

/**
 * ClassName:OrderConfirmActivity <br/>
 * 
 * @Description: 订单支付页面 Date: 2013-2-24 下午4:58:49 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class OrderConfirmActivity extends BaseActivity2 implements OnClickListener {
    OrderPayInfo mInfo;
    Dialog mDialog;
    LinearLayout mBankViews;
    RadioGroup group;
    TextView confirmPayBtn;
    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.order_confirm);
        setLeftTitleBtn(R.string.back_btn, this);
        setRightTitleBtn(R.string.exit, this);
        mInfo = (OrderPayInfo) new Gson().fromJson(getIntent().getStringExtra("ORDER"), OrderPayInfo.class);
         confirmPayBtn = (TextView) findViewById(R.id.commitBtn);
        confirmPayBtn.setOnClickListener(this);
        showOrder(mInfo);
        getPaymentInfo();
        mBankViews = (LinearLayout) findViewById(R.id.banks);
        group = (RadioGroup) findViewById(R.id.radioGroup1);
        group.setOnCheckedChangeListener(mChangeListener);
    }

    RadioGroup.OnCheckedChangeListener mChangeListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==R.id.moneyPay){
                confirmPayBtn.setEnabled(false);
                mBankViews.setVisibility(View.VISIBLE);
            }else if(checkedId==R.id.creditCardPay){
                confirmPayBtn.setEnabled(true);
                mBankViews.setVisibility(View.GONE);
            }
        }
    };

    /**
     * 向服务器请求支付相关的数据
     */
    private void getPaymentInfo() {
        GuoliRequest request = new GuoliRequest("order_orderpay", new PaymentInfo(mInfo.orderno));
        Manager.getInstance().executePoset(request, mPaymentResponseListener);
        mDialog = DialogUtils.showProgressDialog(this, "正在加载数据中...");
    }

    IResponseListener mPaymentResponseListener = new IResponseListener() {

        @Override
        public void onSuccess(Response arg0) {
            if (mDialog != null && mDialog.isShowing())
                mDialog.cancel();
            System.out.println("mPaymentResponseListener:" + arg0.result);
            try {
                HashMap<String, String> maps = JsonUtils.convertJsonToHashMap(arg0.result.toString());
                if (maps.containsKey("message")) {
                    ToastUtil.show(maps.get("message"));
                }
                if (maps.containsKey("orderinfo")) {
                    mInfo = new Gson().fromJson(maps.get("orderinfo"), OrderPayInfo.class);
                    showOrder(mInfo);
                }
                if (maps.containsKey("bankresult")) {
                    List<BankInfo> banks = new Gson().fromJson(maps.get("bankresult"), new TypeToken<ArrayList<BankInfo>>() {
                    }.getType());
                    mBankViews.removeAllViews();
                    for (int i = 0; i < banks.size(); i++) {
                        View view = getLayoutInflater().inflate(R.layout.pay_bank_item, null);
                        TextView tv = (TextView) view.findViewById(R.id.bankNo);
                        tv.setText("账号: " + banks.get(i).cardno);
                        tv = (TextView) view.findViewById(R.id.bankName);
                        tv.setText("账号: " + banks.get(i).bank);
                        tv = (TextView) view.findViewById(R.id.name);
                        tv.setText("账号: " + banks.get(i).name);
                        mBankViews.addView(view);
                        if(i%2!=0){
                            view.setBackgroundColor(0xf2f2f2);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Response arg0) {
            if (mDialog != null && mDialog.isShowing())
                mDialog.cancel();
            Toast.makeText(OrderConfirmActivity.this, ErrorCode.getErrorCodeString(arg0.errorCode), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getContentId() {
        return R.layout.order_confirm;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.left_btn:
            onBackPressed();
            break;
        case R.id.right_btn:

            break;
        case R.id.commitBtn:
            Uri uri=Uri.parse("http://m.guoli.com/pay/"+mInfo.payfile);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
            onBackPressed();
            break;

        default:
            break;
        }
    }

    public void showOrder(OrderInfo mInfo) {
        TextView tv = (TextView) findViewById(R.id.orderNumber);
        tv.setText(mInfo.orderno);
        tv = (TextView) findViewById(R.id.orderTotal);
        tv.setText("￥" + mInfo.paymentmoney);
        tv = (TextView) findViewById(R.id.textView09);
        tv.setText(mInfo.shopname);
        tv = (TextView) findViewById(R.id.textView11);
        tv.setText(mInfo.address);
        tv = (TextView) findViewById(R.id.textView13);
        tv.setText(mInfo.phone);
        tv = (TextView) findViewById(R.id.textView15);
        tv.setText(mInfo.name);
        tv = (TextView) findViewById(R.id.textView17);
        tv.setText(mInfo.buynum + "间");
        tv = (TextView) findViewById(R.id.textView19);
        tv.setText(getString(R.string.order_list_timedate, mInfo.checkintime, mInfo.checkoutime));
    }
    
    public class OrderPayInfo extends OrderInfo{
        public String payfile;
    }

    public class BankInfo {
        public String cardno;
        public String bank;
        public String name;
    }

    public class PaymentInfo {
        public String orderno;

        public PaymentInfo(String orderno) {
            super();
            this.orderno = orderno;
        }

    }
}
