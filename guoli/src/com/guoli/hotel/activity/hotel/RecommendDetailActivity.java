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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.CallActivity;
import com.guoli.hotel.bean.RecommendInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRecommendInfo;
import com.guoli.hotel.net.request.bean.HotelRoom;
import com.guoli.hotel.net.response.bean.RecommendRespInfo;
import com.guoli.hotel.parse.RecommendRespParase;
import com.guoli.hotel.utils.ImageUtil;
import com.guoli.hotel.utils.DiscountUtils;
import com.guoli.hotel.utils.NumberUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Controller;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
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

    private  RecommendRespInfo mRespInfo;
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
        findViewById(R.id.nowBookBtn).setOnClickListener(mBookLisenter);
    }
    
    private OnClickListener mBookLisenter = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            HotelRoom room = new HotelRoom();
            room.setId(getIntent().getStringExtra(KEY_HOTEL_ID));
            room.setStartDate(mRespInfo == null ? "" : mRespInfo.getStartDate());
            room.setEndDate(mRespInfo == null ? "" : mRespInfo.getEndDate());
            bundle.putParcelable(HotelDetailActivity.KEY_REQUEST, room);
            intent.putExtras(bundle);
            intent.setClass(RecommendDetailActivity.this, HotelDetailActivity.class);
            startActivity(intent);
        }
    };

    private IResponseListener mSyncLisenter = new IResponseListener() {

        @Override
        public void onSuccess(Response resp) {
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            dismissLoadingDialog();
             mRespInfo = new RecommendRespParase().parseResponse(resp);
            if (mRespInfo == null) {
                return;
            }
            initViews(mRespInfo.getRecommendInfo());
        }

        @Override
        public void onError(Response resp) {
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            dismissLoadingDialog();
            ToastUtil.show(ErrorCode.getErrorCodeString(resp.errorCode));
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
//        ((TextView)findViewById(R.id.hotelDiscountView)).setText(String.format(res, discount));
        ((TextView)findViewById(R.id.hotelDiscountView)).setText(
        		DiscountUtils.formatDiscount(res, discount));
        res = getResources().getString(R.string.period_date);
        ((TextView)findViewById(R.id.hotelBriefView)).setText(info.getBrief());
        ((TextView)findViewById(R.id.hotelNoticeView)).setText("sfdfsfsdfsdf");
        ((TextView)findViewById(R.id.dateLifeView)).setText(String.format(res, info.getDate()));
        String countText = "";
        if (mRespInfo != null) {
            res = getResources().getString(R.string.pic_count);
            countText = String.format(res, mRespInfo.getCount());
        }
        ((TextView)findViewById(R.id.picCountView)).setText(countText);
        ((RatingBar)findViewById(R.id.hotelLevelView)).setRating(info.getLevel());
        ImageView mView=(ImageView)findViewById(R.id.hotelImgView);
        res = null;
        Controller.getApplication().loadThumbnailImage(ImageUtil.getThumbnailImageUrl(info.getPicPath(),info.getPicName()), mView, R.drawable.hotel_default);
    }
}
