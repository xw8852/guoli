/**
 * Project Name:SplashActivity
 * File Name:HotelListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26下午2:40:38
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity.hotel;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.adapter.RecommondHotelAdapter;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.bean.RecommendHotelInfo;
import com.guoli.hotel.bean.RecommendsInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.parse.RecommandHotelParse;
import com.guoli.hotel.widget.BottomTabbar;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelListActivity <br/>
 * @Description:    特别推荐页面
 * Date:     2013-1-26 下午2:40:38 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelListActivity extends BaseActivity implements OnItemClickListener {
    
    private ListView mListView;
    private RecommondHotelAdapter mAdapter;
    
    private static final String TAG = HotelListActivity.class.getSimpleName();
    
    public HotelListActivity(){
        mTitleTextId = R.string.recommend;
        mLayoutId = R.layout.hotel_recommend_layout;
        mRightDrawableId = R.drawable.btn_top_phone;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        new BottomTabbar(this, 1);
        showLeftBtn();
        showRightBtn();
        syncData();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.hotelListView);
        mListView.setOnItemClickListener(this);
    }
    

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListView.getAdapter() == null) {
            return;
        }
        RecommendHotelInfo info = (RecommendHotelInfo) mAdapter.getItem(position);
        if (info == null) {
            return;
        }
        enterHotelDetailActivity(info.getId());
    }
    
    @Override
    public void onBackPressed() {
        showExitDialog();
    }
    
    @Override
    protected void leftBtnClickEvent() {
        showExitDialog();
    }
    
    /**
     * 
     * updateListView:更新列表数据. <br/>
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void updateListView(List<RecommendHotelInfo> list){
        if (list == null || list.size() < 1) {
            return;
        }
        if (mAdapter == null) {
            mAdapter = new RecommondHotelAdapter(list, this);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addMore(list);
        }
    }
    
    /**
     * 
     * enterHotelDetailActivity:跳转到推荐酒店详细页面. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void enterHotelDetailActivity(String id){
        Intent intent = new Intent();
        intent.putExtra(RecommendDetailActivity.KEY_HOTEL_ID, id);
        intent.setClass(this, RecommendDetailActivity.class);
        startActivity(intent);
    }

    /**
     * 
     * syncData:(这里用一句话描述这个方法的作用). <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void syncData(){
        showLoadingDialog(R.string.loading_msg);
        GuoliRequest request = new GuoliRequest("hotel_recommen", null);
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mSyncLisenter);
    }
    
    private IResponseListener mSyncLisenter = new IResponseListener() {
        
        @Override
        public void onSuccess(Response resp) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            RecommendsInfo info = new RecommandHotelParse().parseResponse(resp);
            updateListView(info == null ? null : info.getList());
        }
        
        @Override
        public void onError(Response resp) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
        }
    };
}

