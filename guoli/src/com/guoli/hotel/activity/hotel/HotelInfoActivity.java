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
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.bean.HotelDetailInfo;
import com.guoli.hotel.bean.HotelParamsInfo;
import com.guoli.hotel.bean.TrafficInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.response.bean.HotelRespInfo;
import com.guoli.hotel.parse.HotelInfoParase;
import com.msx7.core.Manager;
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
//        ((TextView)findViewById(R.id.hotelNoticeView)).setText("");
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
        initTrafficView(info.getTrafficInfo());
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
    private void initTrafficView(TrafficInfo info) {
        if (info == null) {
            return;
        }
        StringBuilder buffer = new StringBuilder();
        List<String> list = info.getCityCenter();
        final String LINE = "\n";
        if (list != null && list.size() > 0) {
            for (String center : list) {
                buffer.append(center).append(LINE);
            }
        }
        list = info.getAirPorts();
        if (list != null && list.size() > 0) {
            for (String desc : list) {
                buffer.append(desc).append(LINE);
            }
        }
        list = info.getRailwayStations();
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int index = 0 ; index < size ; index++) {
                if (index == size - 1) {
                    buffer.append(list.get(index));
                    continue;
                }
                buffer.append(list.get(index)).append(LINE);
            }
        }
        ((TextView)findViewById(R.id.trafficView)).setText(buffer.toString());
    }
}

