/**
 * Project Name:SplashActivity
 * File Name:HotelInfoActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-24下午11:05:29
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity.hotel;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.bean.HotelDetailInfo;
import com.guoli.hotel.bean.HotelParamsInfo;
import com.guoli.hotel.bean.TrafficInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.response.bean.HotelRespInfo;
import com.guoli.hotel.parse.HotelInfoParase;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelInfoActivity <br/>
 * @Description:
 * Date:     2013-1-24 下午11:05:29 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelInfoActivity extends BaseActivity {
    
    public static final String KEY_HOTEL_SHOPINFO_ID = "hotelId";
    private static final String TAG = HotelInfoActivity.class.getSimpleName();
    
    public HotelInfoActivity(){
        mLayoutId = R.layout.hotel_info_layout;
        mTitleTextId = R.string.hotel_info;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        loadNetwork();
    }

    @Override
    protected void findViews() {

    }
    
    private void loadNetwork(){
        showLoadingDialog(R.string.loading_msg);
        String id = getIntent().getStringExtra(KEY_HOTEL_SHOPINFO_ID);
        HotelParamsInfo info = new HotelParamsInfo();
        info.setId(id);
        GuoliRequest request = new GuoliRequest("hotel_hotelinfo", info);
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mListener);
    }

    
    private IResponseListener mListener = new IResponseListener() {
        
        @Override
        public void onSuccess(Response resp) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            HotelRespInfo info = new HotelInfoParase().parseResponse(resp);
            initViews(info);
        }
        
        @Override
        public void onError(Response resp) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            ToastUtil.show(ErrorCode.getErrorCodeString(resp.errorCode));
        }
    };
    
    private void initViews(HotelRespInfo respInfo) {
        if (respInfo == null) {
            return;
        }
        HotelDetailInfo  info = respInfo.getHotelInfo();
        if (info == null) {
            return;
        }
        ((TextView)findViewById(R.id.hotelNameView)).setText(info.getName());
        ((TextView)findViewById(R.id.hotelPhoneView)).setText(info.getPhone());
        //特别提示
        ((TextView)findViewById(R.id.hotelNoticeView)).setText(info.getNotice());
        ((TextView)findViewById(R.id.hotelPhoneView)).setText(info.getPhone());
        //附加选项
//        ((TextView)findViewById(R.id.attachedOperationsView)).setText("");
        //餐饮设施
        ((TextView)findViewById(R.id.repastFacilityView)).setText(info.getDiningFacility());
        //娱乐健身
        ((TextView)findViewById(R.id.entertainmentFacilityView)).setText(info.getEntertainmentFacility());
        //客房设施
        ((TextView)findViewById(R.id.roomFacilityView)).setText(info.getRoomFacility());
        //信用卡
        ((TextView)findViewById(R.id.allowCreditcardView)).setText(info.getCreditCard());
        //交通位置
        initTrafficView(info.getTrafficInfos());
        //酒店简介&#160;
        String introduce = info.getIntroduce();
        if (introduce != null) {
//            introduce.replaceAll("&nbsp;", "&#160;");
            ((TextView)findViewById(R.id.hotelIntroductionView)).setText(introduce);
        }
    }

    /***
     * 
     * initTrafficView:初始化交通视图. <br/>
     * @author maple
     * @param info
     * @since JDK 1.6
     */
    private void initTrafficView(List<TrafficInfo> list) {
        if (list == null) {
            return;
        }
        LinearLayout parentView = (LinearLayout) findViewById(R.id.trafficLayout);
        for (TrafficInfo info : list) {
            View view = LayoutInflater.from(this).inflate(R.layout.hotel_traffic_item, null);
            ((TextView)view.findViewById(R.id.locationView)).setText(info.getName());
            ((TextView)view.findViewById(R.id.distanceView)).setText(info.getDistance());
            parentView.addView(view);
        }
    }
}

