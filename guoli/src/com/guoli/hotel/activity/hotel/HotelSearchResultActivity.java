/**
 * Project Name:Guoli
 * File Name:HotelSearchResultActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-11下午12:36:31
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
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.UpdateActivity;
import com.guoli.hotel.adapter.HotelAdapter;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.bean.HotelListInfo;
import com.guoli.hotel.bean.SearchInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRoom;
import com.guoli.hotel.parse.HotelListInfoParse;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelSearchResultActivity <br/>
 * @Description:    酒店查询结果页面
 * Date:     2013-1-11 下午12:36:31 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelSearchResultActivity extends UpdateActivity implements OnItemClickListener {
    /**城市*/
    private TextView mCityView;
    /**入住日期*/
    private TextView mOccupancyView;
    /**离店日期*/
    private TextView mLeaveView;
    /**商家数*/
    private TextView mCountView;
    /**商家列表*/
    private ListView mListView;
    /**排序按钮*/
    private TextView mSortFilterView;
    /**价格过滤按钮*/
    private TextView mPriceFilterView;
    /**位置过滤按钮*/
    private TextView mLocationFilterView;
    /**商家列表适配器*/
    private HotelAdapter mListAdapter;
    /**区域过滤标记*/
    private static final int LOCATION_FILTER = 5;
    /**搜索条件对象*/
    public static final String KEY_SEARCHINFO = "searchInfo";
    
    private static final String TAG = HotelSearchResultActivity.class.getSimpleName();
    
    private SearchInfo mSearchInfo;
    
    public HotelSearchResultActivity(){
        mLayoutId = R.layout.hotel_search_result;
        mRightDrawableId = R.drawable.return_btn_bg;
        mRightTextId = R.string.map_mode;
        mTitleTextId = R.string.search_hotel_title;
    }
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        showRightBtn();
    }
    
    /**
     * 
     * getSearchInfo:获取搜索页面传递过来的搜索参数对象. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private SearchInfo getSearchInfo(){
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return null;
        }
        Object obj =  bundle.get(KEY_SEARCHINFO);
        if (!(obj instanceof SearchInfo)) {
            return null;
        }
        return (SearchInfo) obj;
    }

    @Override
    protected void initPassParams() {
        mSearchInfo = getSearchInfo();
        Log.i(TAG, "onCreate()---> mSearchInfo=" + (mSearchInfo == null ? null : mSearchInfo));
    }

    @Override
    protected void initViews() {
    }
    
    private void initBusinessCountView(int count){
        String desc = getResources().getString(R.string.business_count_desc);
        desc = String.format(desc, count);
        mCountView.setText(desc);
    }

    @Override
    protected void loadNetworkData() {
        //从网络服务器拉取数据并加载显示
        showLoadingDialog(R.string.loading_msg);
        GuoliRequest request = new GuoliRequest("hotel_qry", mSearchInfo);
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mListener);
    }

    @Override
    protected void loadLocalData() {

        // TODO Auto-generated method stub

    }

    @Override
    protected void findViews() {
        mCityView = (TextView) findViewById(R.id.city_text);
        mOccupancyView = (TextView) findViewById(R.id.occupancy_date);
        mLeaveView = (TextView) findViewById(R.id.leave_date);
        mCountView = (TextView) findViewById(R.id.count_view);
        mListView = (ListView) findViewById(R.id.hotel_list);
        mListView.setOnItemClickListener(this);
        mSortFilterView = (TextView) findViewById(R.id.sort_type);
        mPriceFilterView = (TextView) findViewById(R.id.price_filter);
        mLocationFilterView = (TextView) findViewById(R.id.area_filter);
        mSortFilterView.setOnClickListener(this);
        mPriceFilterView.setOnClickListener(this);
        mLocationFilterView.setOnClickListener(this);
    }
    
    @Override
    protected void rightBtnClickEvent() {
        super.rightBtnClickEvent();
        //TODO 进入地图模式
        Intent intent = new Intent();
        intent.setClass(this, HotelsMapActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
        if (mListAdapter == null) {
            return;
        }
        HotelInfo info = mListAdapter.getItem(position);
        if (info == null) {
            return;
        }
        enterHotelDetailActivity(info.getId());
    }
    
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.sort_type:
            
            break;
        case R.id.price_filter:
            
            break;
        case R.id.area_filter:
            Intent intent = new Intent();
            intent.setClass(this, AreaListActivity.class);
            startActivityForResult(intent, LOCATION_FILTER);
            break;
        default:
            break;
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case LOCATION_FILTER:
            String area = data == null ? "" : data.getStringExtra(AreaListActivity.KEY_AREA);
            mLocationFilterView.setText(area);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * enterHotelDetailActivity:跳转到酒店详细页面. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void enterHotelDetailActivity(String id){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        HotelRoom room = new HotelRoom();
        room.setId(id);
        room.setStartDate(mSearchInfo.getStartDate());
        room.setEndDate(mSearchInfo.getEndDate());
        bundle.putParcelable(HotelDetailActivity.KEY_REQUEST, room);
        intent.putExtras(bundle);
        intent.setClass(this, HotelDetailActivity.class);
        startActivity(intent);
    }
    
    /**
     * 
     * updateListView:刷新酒店列表. <br/>
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void updateListView(List<HotelInfo> list){
        if (list == null) {
            return;
        }
        if (mListAdapter == null) {
            mListAdapter = new HotelAdapter(list, this);
            mListView.setAdapter(mListAdapter);
        } else {
            mListAdapter.clear();
            mListAdapter.addMore(list);
        }
    }
    
    /**数据同步监听*/
    private IResponseListener mListener = new IResponseListener() {
        
        @Override
        public void onSuccess(Response resp) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            HotelListInfo info = new HotelListInfoParse().parseResponse(resp);
            if (info == null) {
                return;
            }
            List<HotelInfo> list = info.getList();
            updateListView(list);
            mCityView.setText(mSearchInfo.getCityName());
            mOccupancyView.setText(mSearchInfo.getStartDate());
            mLeaveView.setText(mSearchInfo.getEndDate());
            initBusinessCountView(list == null ? 0 : list.size());
        }
        
        @Override
        public void onError(Response resp) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            
        }
    };
}

