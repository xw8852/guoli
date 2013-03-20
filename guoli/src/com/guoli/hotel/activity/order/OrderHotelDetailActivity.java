package com.guoli.hotel.activity.order;

import java.util.HashMap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.bean.OrderInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.OrderDetailBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

public class OrderHotelDetailActivity extends BaseActivity2 implements
		OnClickListener {
	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_MOBILE = "mobile";
	public static final String PARAM_ORDER_NO = "orderno";
	public static final String Status = "status";

	Button cancel_btn;
	Button pay_btn;
	Dialog mDialog;
	OrderInfo mOrderIndo;
	
	boolean isLogin;
	String mobile;
	String orderno;
	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.order_detail);
		setLeftTitleBtn(R.string.back_btn, this);
		setRightTitleBtn(R.string.exit, this);
		setTitle(R.string.order_detail);
		findViewById(R.id.btn_view).setVisibility(View.GONE);
		findViewById(R.id.button1).setVisibility(View.GONE);
		pay_btn = (Button) findViewById(R.id.button2);
		cancel_btn = (Button) findViewById(R.id.button1);
		findViewById(R.id.button3).setOnClickListener(this);
		pay_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
		findViewById(R.id.scrollView1).setVisibility(View.GONE);
		isLogin=getIntent().getBooleanExtra(PARAM_LOGIN, false);
		orderno=getIntent().getStringExtra(PARAM_ORDER_NO);
		GuoliRequest request=null;
		if(isLogin){
			request=new GuoliRequest(Action.Order.OrderDetail, new OrderDetailBean(orderno, LoginUtils.uid));
		}else{
			mobile=getIntent().getStringExtra(PARAM_MOBILE);
			request=new GuoliRequest(Action.Order.OrderDetail, OrderDetailBean.buildBean(orderno, mobile));
		}
		Manager.getInstance().executePoset(request, mOrderDetailIResponseListener);
		mDialog=DialogUtils.showProgressDialog(this, "正在加载数据中...");
	}

	@Override
	public int getContentId() {
		return R.layout.order_hotel_details;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_btn:
			finish();
			break;
		case R.id.right_btn:
			
			break;
		case R.id.button1:
			// TODO 退订
			break;
		case R.id.button2:
			// TODO 跳转到订单确认页面
			Intent intent = new Intent();
			intent.setClass(this, OrderConfirmActivity.class);
			startActivity(intent);
			break;
		case R.id.button3:
		 // TODO: 取消订单
		    break;
		default:
			break;
		}
	}
	
	public void showInfo(OrderInfo info){
		//TODO:订单状态
		TextView tv=(TextView)findViewById(R.id.textView4);
		/** 0-未付款（未成交） 1-已付款（成交） 2-取消（放弃） 3-交易关闭4-已退款，6-退款中，8-交易成功) */
		if ("0".equals(info.tradestatus)) {
			tv.setText("已付款");
		}else if ("1".equals(info.tradestatus)) {
			tv.setText("已付款");
		} else if ("2".equals(info.tradestatus)) {
			tv.setText("取消");
		} else if ("3".equals(info.tradestatus)) {
			tv.setText("交易关闭");
		} else if ("4".equals(info.tradestatus)) {
			tv.setText("已退款");
		}  else if ("6".equals(info.tradestatus)) {
			tv.setText("退款中");
		} else if ("8".equals(info.tradestatus)) {
			tv.setText("交易成功");
		}
		//TODO:订单编号
		tv=(TextView)findViewById(R.id.textView5);
		tv.setText(info.productcode);
		//TODO:金额
		tv=(TextView)findViewById(R.id.textView6);
		tv.setText("￥"+info.paymentmoney);
		//TODO:酒店名称
		tv=(TextView)findViewById(R.id.textView09);
		tv.setText(info.shopname);
		//TODO:酒店地址
		tv=(TextView)findViewById(R.id.textView11);
		tv.setText(info.address);
		//TODO:酒店电话w
		tv=(TextView)findViewById(R.id.textView13);
		tv.setText(info.phone);	
		//TODO:房间类型
		tv=(TextView)findViewById(R.id.textView15);
		tv.setText(info.name);
		//TODO:房间数目
		tv=(TextView)findViewById(R.id.textView17);
		tv.setText(info.buynum+"间");
		//TODO:签入-签出
		tv=(TextView)findViewById(R.id.textView19);
		tv.setText(getResources().getString(R.string.order_list_timedate, info.checkintime,info.checkoutime));
		//TODO:入住人
		tv=(TextView)findViewById(R.id.textView24);
		tv.setText(info.checkinpeople);
		//TODO：联系人
		tv=(TextView)findViewById(R.id.textView25);
		tv.setText(info.linkman);
		//TODO:联系人号码
		tv=(TextView)findViewById(R.id.textView26);
		tv.setText(info.mobile);	
//		未付款-可以显示“付款”、取消订单；已付款-“退订”；交易关闭、已退款、退款中、交易成功-则什么都不显示 
		/**
		 * 0-未付款（未成交） 1-已付款（成交） 2-取消（放弃） 3-交易关闭4-已退款，6-退款中，8-交易成功)
		 */
		if("0".equals(info.tradestatus)){
			findViewById(R.id.btn_view).setVisibility(View.VISIBLE);
			if(!"1".equals(info.ispay)){
				findViewById(R.id.btn_view).findViewById(R.id.button2).setVisibility(View.GONE);
			}
		}else if("1".equals(info.tradestatus)){
			findViewById(R.id.button1).setVisibility(View.VISIBLE);
		}
		findViewById(R.id.scrollView1).setVisibility(View.VISIBLE);
	}

	IResponseListener mOrderDetailIResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response arg0) {
			Log.d("MSG", arg0.result.toString());
			if(mDialog!=null&&mDialog.isShowing())mDialog.cancel();
			HashMap<String, Object> map=new Gson().fromJson(arg0.result.toString(), new TypeToken<HashMap<String,Object>>(){}.getType());
			mOrderIndo=new Gson().fromJson(new Gson().toJson(map.get("orderinfo")),OrderInfo.class);
			showInfo(mOrderIndo);
		}

		@Override
		public void onError(Response arg0) {
			if(mDialog!=null&&mDialog.isShowing())mDialog.cancel();
			Toast.makeText(OrderHotelDetailActivity.this,
					ErrorCode.getErrorCodeString(arg0.errorCode),
					Toast.LENGTH_SHORT).show();
		}
	};

}
