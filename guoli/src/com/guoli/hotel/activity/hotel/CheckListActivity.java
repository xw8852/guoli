/**
 * Project Name:SplashActivity
 * File Name:OccupancyListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-28下午7:38:24
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity.hotel;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.adapter.AbstractAdapter;
import com.guoli.hotel.bean.CheckInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.OrderPriceRequest;
import com.guoli.hotel.net.response.bean.OrderPriceResponse;
import com.guoli.hotel.parse.BaseParse;
import com.guoli.hotel.utils.DigitalUtils;
import com.guoli.hotel.utils.ResourceUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

/**
 * ClassName:OccupancyListActivity <br/>
 * @Description:    入住日期清单页面
 * Date:     2013-1-28 下午7:38:24 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CheckListActivity extends BaseActivity {
    
    private ListView mListView;
    private OrderPriceRequest mRequest;
    private String[] mBreakfasts;
    private static final String TAG = CheckListActivity.class.getSimpleName();
    
    public CheckListActivity(){
        mTitleTextId = R.string.occupancy_list;
        mLayoutId = R.layout.check_list_layout;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        mBreakfasts = getResources().getStringArray(R.array.breakfast);
        mRequest = getIntent().getParcelableExtra("orderPriceRequest");
        Log.i(TAG, "onCreate()---> mRequest=" + (mRequest == null ? null : mRequest.toString()));
        loadNetworkData();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.occupancyListView);
    }
    
    private void loadNetworkData(){
        showLoadingDialog(R.string.loading_msg);
        Request request = new GuoliRequest(Action.Hotel.ORDER_ORDPRICE, mRequest);
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mLoadListener);
    }
    
    private IResponseListener mLoadListener = new IResponseListener() {
        
        @Override
        public void onSuccess(Response resp) {
            Log.i(TAG, "onSuccess()---> response=" + (resp == null ? null : resp.result));
            dismissLoadingDialog();
            if (resp == null || resp.result == null) {
                return;
            }
            OrderPriceResponse info = new BaseParse<OrderPriceResponse>().parse(resp, OrderPriceResponse.class);
            if (info == null) {
                return;
            }
            updateListView(info.getList());
        }
        
        @Override
        public void onError(Response resp) {
            Log.i(TAG, "onError()---> response=" + (resp == null ? null : resp.result));
            dismissLoadingDialog();
            ToastUtil.show(ErrorCode.getErrorCodeString(resp.errorCode));
        }
    };

    /**
     * 
     * updateListView:更新列表数据. <br/>
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void updateListView(List<CheckInfo> list){
        if (mListView == null || list == null || list.size() < 1) {
            return;
        }
        CheckInfoAdapter adapter = (CheckInfoAdapter) mListView.getAdapter();
        if (adapter == null) {
            adapter = new CheckInfoAdapter(list, this);
            mListView.setAdapter(adapter);
            return;
        }
        adapter.clear();
        adapter.changeData(list);
    }

    private class CheckInfoAdapter extends AbstractAdapter<CheckInfo> {
        
        public CheckInfoAdapter(List<CheckInfo> data, Context context) {
            super(data, context);
        }


        @Override
        public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
            CheckInfo info = getItem(position);
            if (info == null) {
                return null;
            }
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_check_layout, null);
                holder = new ViewHolder();
                holder.dateView = (TextView) convertView.findViewById(R.id.dateItemView);
                holder.priceView = (TextView) convertView.findViewById(R.id.priceItemView);
                holder.nameView = (TextView) convertView.findViewById(R.id.nameItemView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //日期
            initDateView(holder.dateView, info);
            //价格
            initPriceView(holder.priceView, info);
            //餐饮
            initBreakfastView(holder.nameView, info);
            return convertView;
        }
        
        private class ViewHolder {
            TextView dateView;
            TextView priceView;
            TextView nameView;
        }
        
        private void initDateView(TextView view, CheckInfo info){
            if (view == null || info == null) {
                return;
            }
            ResourceUtils utils = ResourceUtils.getInstance(CheckListActivity.this);
            String value = utils.getValue(R.array.week_key, R.array.week_value, info.getWeek());
            String date = info.getDate();
            if (TextUtils.isEmpty(value)) {
                view.setText(date);
                return;
            }
            view.setText(date + String.format(getString(R.string.date_weekday), value));
        }
        
        private void initPriceView(TextView view, CheckInfo info){
            if (view == null || info == null) {
                return;
            }
            double price = DigitalUtils.convertToDouble(info.getPrice());
            if (price == -1) {
                return;
            }
            int intPrice = (int) price;
            String desc = getString(R.string.order_money_content);
            view.setText(String.format(desc, intPrice+""));
        }
        
        private void initBreakfastView(TextView view, CheckInfo info){
            if (view == null || info == null) {
                return;
            }
            String breakfast = info.getBreakfast();
            if (TextUtils.isEmpty(breakfast)) {
                return;
            }
            int index = DigitalUtils.convertToInt(breakfast);
            if (index < 0 || index > mBreakfasts.length - 1) {
                return;
            }
            view.setText(mBreakfasts[index]);
        }
    }
    
}

