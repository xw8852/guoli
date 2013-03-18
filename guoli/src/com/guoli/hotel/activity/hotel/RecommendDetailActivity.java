/**
 * Project Name:Guoli
 * File Name:RecommendDetailActivity.java
 * Package Name:com.guoli.hotel.activity.hotel
 * Date:2013-3-16下午9:03:03
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.activity.hotel;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.CallActivity;
import com.guoli.hotel.bean.RecommendInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRecommendInfo;
import com.guoli.hotel.net.response.bean.RecommendRespInfo;
import com.guoli.hotel.parse.RecommendRespParase;
import com.guoli.hotel.utils.NumberUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * ClassName:RecommendDetailActivity <br/>
 * 
 * @Description: 推荐酒店详情页面 Date: 2013-3-16 下午9:03:03 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class RecommendDetailActivity extends CallActivity {

    public static final String KEY_HOTEL_ID = "hotelId";
    private static final String TAG = RecommendDetailActivity.class.getSimpleName();

    public RecommendDetailActivity() {
        mLayoutId = R.layout.recommend_detail;
        mTitleTextId = R.string.recommend;
        mRightDrawableId = R.drawable.btn_top_phone;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        showRightBtn();
        loadNetworkData();
    }

    private void loadNetworkData() {
        showLoadingDialog(R.string.loading_msg);
        String id = getIntent().getStringExtra(KEY_HOTEL_ID);
        HotelRecommendInfo info = new HotelRecommendInfo();
        info.setId(id);
        GuoliRequest request = new GuoliRequest("hotel_rechotel", info);
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mSyncLisenter);
    }

    @Override
    protected void findViews() {
        // TODO Auto-generated method stub
    }

    private IResponseListener mSyncLisenter = new IResponseListener() {

        @Override
        public void onSuccess(Response resp) {
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            dismissLoadingDialog();
            RecommendRespInfo respInfo = new RecommendRespParase().parseResponse(resp);
            if (respInfo == null) {
                return;
            }
            initViews(respInfo.getRecommendInfo());
        }

        @Override
        public void onError(Response resp) {
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            dismissLoadingDialog();

        }
    };

    private void initViews(RecommendInfo info) {
        if (info == null) { return; }
        ((TextView)findViewById(R.id.hotelNameView)).setText(info.getName());
        String res = getResources().getString(R.string.address);
        ((TextView)findViewById(R.id.hotelAdressView)).setText(String.format(res, info.getAddress()));
        res = getResources().getString(R.string.price_desc);
        ((TextView)findViewById(R.id.hotelPriceView)).setText(String.format(res, info.getGuoliPrice()));
        res = getResources().getString(R.string.discount_desc);
        String discount = NumberUtils.formatDiscount(info.getDiscount(), this);
        ((TextView)findViewById(R.id.hotelDiscountView)).setText(String.format(res, discount));
        res = getResources().getString(R.string.period_date);
        ((TextView)findViewById(R.id.hotelBriefView)).setText(info.getBrief());
        ((TextView)findViewById(R.id.hotelNoticeView)).setText("sfdfsfsdfsdf");
        ((TextView)findViewById(R.id.dateLifeView)).setText(String.format(res, info.getDate()));
        ((RatingBar)findViewById(R.id.hotelLevelView)).setNumStars(info.getLevel());
        
    }
}
