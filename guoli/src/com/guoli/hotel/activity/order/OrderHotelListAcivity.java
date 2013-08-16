package com.guoli.hotel.activity.order;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.bean.OrderItemInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.GuoliResponse;
import com.guoli.hotel.net.request.bean.UnLoginBean;
import com.guoli.hotel.net.request.bean.UserOderListBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.guoli.hotel.widget.AbstractAdapter;
import com.guoli.hotel.widget.BottomTabbar;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

public class OrderHotelListAcivity extends BaseActivity2 implements
		OnItemClickListener {

	ListView mListView;
	OrderAdapter mAdapter;
	List<OrderItemInfo> mOrderItemInfos;
	Dialog mDialog;
	private RelativeLayout mDefaultLayout;
	

	GuoliRequest request;
	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		new BottomTabbar(this, 2);
		mListView = (ListView) findViewById(R.id.listView1);
		mDefaultLayout = (RelativeLayout) findViewById(R.id.noResultFoundView);

		setTitle(R.string.order_list_title);
		if (LoginUtils.isLogin==2) {
		    showRightExit();
			 request = new GuoliRequest(Action.Order.USER_ORDER_LIST,
					new UserOderListBean(LoginUtils.uid));
		}else if(LoginUtils.isLogin==1){
		    showRightCall();
			request=new GuoliRequest(Action.Order.UNLOGIN_SEARCH, new UnLoginBean(LoginUtils.mobile));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Manager.getInstance().executePoset(request,
				mUserOrderListResponseListener);
		mDialog=DialogUtils.showProgressDialog(this, "正在加载数据中...");
	}

	@Override
	public int getContentId() {
		return R.layout.order_hotel_list_activity;
	}

	class OrderAdapter extends AbstractAdapter<OrderItemInfo> {

		public OrderAdapter(Context context, List<OrderItemInfo> data) {
			super(context, data);
		}

		@Override
		public View CreateView(int position, View convertView,
				LayoutInflater inflater) {
			Holder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.order_hotel_list_item,
						null);
				holder = new Holder();
				holder.creat = (TextView) convertView
						.findViewById(R.id.textView5);
				holder.checkin_out = (TextView) convertView
						.findViewById(R.id.textView8);
				holder.shopName = (TextView) convertView
						.findViewById(R.id.textView4);
				holder.type = (TextView) convertView
						.findViewById(R.id.textView6);
				holder.count = (TextView) convertView
						.findViewById(R.id.textView1);
				holder.status = (TextView) convertView
						.findViewById(R.id.textView2);
				convertView.setTag(holder);
			}
			holder = (Holder) convertView.getTag();
			holder.shopName.setText(getItem(position).shopname);
			holder.creat.setText(getItem(position).creation);
			holder.type.setText(getItem(position).name);
			holder.checkin_out.setText(getResources().getString(
					R.string.order_list_timedate, getItem(position).checkin,
					getItem(position).checkout));
			holder.count.setText("￥" + getItem(position).paymentmoney);

			if ("0".equals(getItem(position).tradestatus)) {
				holder.status
						.setBackgroundResource(R.drawable.order_detail_pay_btn);
				holder.status.setText("未付款");
			} else {
				holder.status.setBackgroundColor(Color.TRANSPARENT);
			}
			/** 0-未付款（未成交） 1-已付款（成交） 2-取消（放弃） 3-交易关闭4-已退款，6-退款中，8-交易成功) */
			if ("1".equals(getItem(position).tradestatus)) {
				holder.status.setText("已付款");
			} else if ("2".equals(getItem(position).tradestatus)) {
				holder.status.setText("已取消");
			} else if ("3".equals(getItem(position).tradestatus)) {
				holder.status.setText("交易关闭");
			} else if ("4".equals(getItem(position).tradestatus)) {
				holder.status.setText("已退订");
			}  else if ("6".equals(getItem(position).tradestatus)) {
				holder.status.setText("退订中");
			} else if ("8".equals(getItem(position).tradestatus)) {
				holder.status.setText("已入住");
			}
			return convertView;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent=new Intent(this, OrderHotelDetailActivity.class);
		intent.putExtra(OrderHotelDetailActivity.PARAM_ORDER_NO, mAdapter.getItem(arg2).orderno);
		startActivity(intent);
	}

	public List<Orderstatus> getListData(boolean isLogin) {
		List<Orderstatus> statuses = new ArrayList<OrderHotelListAcivity.Orderstatus>();
		statuses.add(new Orderstatus(0));
		if (isLogin)
			statuses.add(new Orderstatus(1));
		statuses.add(new Orderstatus(2));
		statuses.add(new Orderstatus(3));
		return statuses;
	}

	class Orderstatus {
		/**
		 * 0：待确认 1：待付款 2：已付款 3：已结束
		 */
		public int status;

		public Orderstatus(int status) {
			super();
			this.status = status;
		}

		public Orderstatus() {
			super();
		}
	}

	IResponseListener mUserOrderListResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response arg0) {
			if(mDialog!=null&&mDialog.isShowing())mDialog.cancel();
			GuoliResponse<List<OrderItemInfo>> infos = null;
			if(arg0==null||arg0.result==null||"".equals(arg0.result.toString().trim())){
			    showDefaultViews();
			    return;
			}
			infos = new Gson().fromJson(arg0.result.toString(),
					new TypeToken<GuoliResponse<List<OrderItemInfo>>>() {
					}.getType());
			mOrderItemInfos = infos.result;
			if (mOrderItemInfos == null || mOrderItemInfos.size() < 1) {
			    showDefaultViews();
			    if (mAdapter != null) {
			        mAdapter.clear();
			    }
			    return;
			}
			hiddenDefaultViews();
			mAdapter = new OrderAdapter(OrderHotelListAcivity.this,
					mOrderItemInfos);
			mListView.setAdapter(mAdapter);
			mListView.setOnItemClickListener(OrderHotelListAcivity.this);
		}

		@Override
		public void onError(Response arg0) {
			if(mDialog!=null&&mDialog.isShowing())mDialog.cancel();
			showDefaultViews();
			Toast.makeText(OrderHotelListAcivity.this,
					ErrorCode.getErrorCodeString(arg0.errorCode),
					Toast.LENGTH_SHORT).show();
		}
	};
	
	private void showDefaultViews(){
	    mListView.setVisibility(View.GONE);
	    mDefaultLayout.setVisibility(View.VISIBLE);
	}
	
	private void hiddenDefaultViews(){
	    mListView.setVisibility(View.VISIBLE);
	    mDefaultLayout.setVisibility(View.GONE);
	}

	static class Holder {
		TextView creat;
		TextView checkin_out;
		TextView shopName;
		TextView type;
		TextView count;
		TextView status;
	}
}
