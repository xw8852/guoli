package com.guoli.hotel.activity.order;

import java.util.HashMap;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.bean.OrderInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.JsonUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

import android.text.TextUtils;
import android.view.View;

public class OrderCancelActivity extends BaseActivity2 implements View.OnClickListener {
    OrderInfo mInfo;
    private Dialog mDialog;
    EditText mContent;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.detail_hotal_order_cancel);
        mInfo = new Gson().fromJson(getIntent().getStringExtra("ORDER"), OrderInfo.class);
        showInfo(mInfo);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        mContent = (EditText) findViewById(R.id.content);
        if (LoginUtils.isLogin == 2) {
            showRightExit();
        } else {
            showRightCall();
        }
        setLeftTitleBtn(R.string.back_btn, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public int getContentId() {
        return R.layout.order_cancel_activity;
    }

    private void showInfo(OrderInfo info) {
        TextView tv = (TextView) findViewById(R.id.orderNo);
        tv.setText(mInfo.orderno);
        tv = (TextView) findViewById(R.id.orderHotel);
        tv.setText(mInfo.shopname);
        tv = (TextView) findViewById(R.id.roomType);
        tv.setText(mInfo.name);
        tv = (TextView) findViewById(R.id.time);
        tv.setText(mInfo.checkintime + "至" + mInfo.checkoutime);
        tv = (TextView) findViewById(R.id.checkInPeople);
        tv.setText(mInfo.checkinpeople);
        tv = (TextView) findViewById(R.id.pay);
        tv.setText(mInfo.paymentmoney);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button2:
            if (TextUtils.isEmpty(mContent.getText().toString())) {
                ToastUtil.show("请填写您的退订原因");
                return;
            }
            DialogUtils.showDialog("", "确认退订？", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("orderno", mInfo.orderno);
                    if (LoginUtils.isLogin == 1) {
                        map.put("uid", "0");
                        map.put("mobile", LoginUtils.mobile);
                    } else if (LoginUtils.isLogin == 2) {
                        map.put("uid", LoginUtils.uid);
                        map.put("mobile", LoginUtils.memberMobile);
                    }
                    map.put("content", mContent.getText().toString());
                    Request request = new GuoliRequest(Action.Order.OrderUnSubScribe, map);
                    mDialog = DialogUtils.showProgressDialog(OrderCancelActivity.this, "正在退订..");
                    Manager.getInstance().executePoset(request, mOrderCacelResponseListener);
                }
            }, this);
            break;
        case R.id.button3:
            startActivity(new Intent(OrderCancelActivity.this, OrderAuthenticActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            break;
        default:

            break;
        }

    }

    IResponseListener mOrderCacelResponseListener = new IResponseListener() {

        @Override
        public void onSuccess(Response arg0) {
            if (mDialog != null && mDialog.isShowing())
                mDialog.cancel();
            if (arg0.result != null) {
                HashMap<String, String> maps = JsonUtils.convertJsonToHashMap(arg0.result.toString());
                if ("1".equals(maps.get("success"))) {
                    Toast.makeText(OrderCancelActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OrderCancelActivity.this, OrderAuthenticActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                    return;
                }
            }

            Toast.makeText(OrderCancelActivity.this, "未知网络异常", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Response arg0) {
            if (mDialog != null && mDialog.isShowing())
                mDialog.cancel();
            Toast.makeText(OrderCancelActivity.this, ErrorCode.getErrorCodeString(arg0.errorCode), Toast.LENGTH_SHORT).show();
        }
    };
}
