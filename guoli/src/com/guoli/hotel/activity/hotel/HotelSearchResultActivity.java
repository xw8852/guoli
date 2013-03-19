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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.UpdateActivity;
import com.guoli.hotel.adapter.HotelAdapter;
import com.guoli.hotel.bean.AreaInfo;
import com.guoli.hotel.bean.BussinessInfo;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.bean.HotelListInfo;
import com.guoli.hotel.bean.SearchInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRoom;
import com.guoli.hotel.parse.HotelListInfoParse;
import com.guoli.hotel.utils.ResourceUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelSearchResultActivity <br/>
 * 
 * @Description: 酒店查询结果页面 Date: 2013-1-11 下午12:36:31 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
@SuppressLint("ValidFragment")
public class HotelSearchResultActivity extends UpdateActivity implements OnItemClickListener {
    /** 城市 */
    private TextView mCityView;
    /** 入住日期 */
    private TextView mOccupancyView;
    /** 离店日期 */
    private TextView mLeaveView;
    /** 商家数 */
    private TextView mCountView;
    /** 商家列表 */
    private ListView mListView;
    /** 排序按钮 */
    private TextView mSortFilterView;
    /** 价格过滤按钮 */
    private TextView mPriceFilterView;
    /** 位置过滤按钮 */
    private TextView mLocationFilterView;
    /** 商家列表适配器 */
    private HotelAdapter mListAdapter;
    /** 区域过滤标记 */
    public static final int ORDER_FILTER = 1;
    public static final int PRICE_FILTER = 3;
    public static final int LOCATION_FILTER = 5;
    /** 搜索条件对象 */
    public static final String KEY_SEARCHINFO = "searchInfo";

    private static final String TAG = HotelSearchResultActivity.class.getSimpleName();

    private SearchInfo mSearchInfo;

    public HotelSearchResultActivity() {
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
     * 
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private SearchInfo getSearchInfo() {
        Intent intent = getIntent();
        if (intent == null) { return null; }
        Bundle bundle = intent.getExtras();
        if (bundle == null) { return null; }
        Object obj = bundle.get(KEY_SEARCHINFO);
        if (!(obj instanceof SearchInfo)) { return null; }
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

    private void initBusinessCountView(int count) {
        String desc = getResources().getString(R.string.business_count_desc);
        desc = String.format(desc, count);
        mCountView.setText(desc);
    }

    @Override
    protected void loadNetworkData() {
        // 从网络服务器拉取数据并加载显示
        filterData(mSearchInfo);
    }
    
    /***
     * 
     * filterData:使用指定的参数过滤加载数据. <br/>
     * @author maple
     * @param info
     * @since JDK 1.6
     */
    private void filterData(SearchInfo info){
        if (info == null) {
            return;
        }
        showLoadingDialog(R.string.loading_msg);
        GuoliRequest request = new GuoliRequest("hotel_qry", info);
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
        // TODO 进入地图模式
        Intent intent = new Intent();
        intent.setClass(this, HotelsMapActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
        if (mListAdapter == null) { return; }
        HotelInfo info = mListAdapter.getItem(position);
        if (info == null) { return; }
        enterHotelDetailActivity(info.getId());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
        case R.id.sort_type:
            intent = new Intent();
            intent.setClass(this, SortListActivity.class);
            startActivityForResult(intent, ORDER_FILTER);
            break;
        case R.id.price_filter:
            intent = new Intent();
            intent.setClass(this, PriceListActivity.class);
            startActivityForResult(intent, PRICE_FILTER);
            break;
        case R.id.area_filter:
            intent = new Intent();
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
            setAreaFilterView(data);
            break;
        case ORDER_FILTER:
            setSortFilterView(data);
            break;
        case PRICE_FILTER:
            setPriceFilterView(data);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * enterHotelDetailActivity:跳转到酒店详细页面. <br/>
     * 
     * @author maple
     * @since JDK 1.6
     */
    private void enterHotelDetailActivity(String id) {
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
     * 
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void updateListView(List<HotelInfo> list) {
        if (list == null) { return; }
        if (mListAdapter == null) {
            mListAdapter = new HotelAdapter(list, this);
            mListView.setAdapter(mListAdapter);
        } else {
            mListAdapter.clear();
            mListAdapter.addMore(list);
        }
    }

    /** 数据同步监听 */
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

    /**
     * 
     * setAreaFilterView:设置区域视图的值. <br/>
     * @author maple
     * @param intent
     * @since JDK 1.6
     */
    private void setAreaFilterView(Intent intent){
        Object obj = getObject(intent, AreaListActivity.KEY_AREA);
        if (!(obj instanceof AreaInfo)) {
            return;
        }
        AreaInfo info = (AreaInfo) obj;
        if (mLocationFilterView == null) { return; }
        String value = info.getName();
        mLocationFilterView.setText(value);
        mLocationFilterView.setTag(info);
        
        if (mSearchInfo == null) {
            return;
        }
        int type = info instanceof BussinessInfo ? 2 : 1;
        String code = ResourceUtils.getInstance(this).getKey(R.array.order_key, R.array.order_value, value);
        mSearchInfo.setAreaType(type + "");
        mSearchInfo.setArea(code);
        filterData(mSearchInfo); 
    }
    
    /**
     * 
     * setPriceFilterView:设置价格过滤条件并从服务器获取符合条件的数据. <br/>
     * @author maple
     * @param intent
     * @since JDK 1.6
     */
    private void setPriceFilterView(Intent intent){
        if (intent == null) {
            return;
        }
        String value = intent == null ? "" : intent.getStringExtra(PriceListActivity.KEY_PRICE);
        setViewText(mPriceFilterView, value);
        if (mSearchInfo == null) {
            return;
        }
        String price = ResourceUtils.getInstance(this).getKey(R.array.price_key, R.array.price_value, value);
        mSearchInfo.setPrice(price);
        filterData(mSearchInfo);
    }
    
    /**
     * 
     * setSortFilterView:设置排序条件并从服务器获取符合条件的数据. <br/>
     * @author maple
     * @param intent
     * @since JDK 1.6
     */
    private void setSortFilterView(Intent intent){
        if (intent == null) {
            return;
        }
        String value = intent == null ? "" : intent.getStringExtra(SortListActivity.KEY_ORDER);
        setViewText(mSortFilterView, value);
        if (mSearchInfo == null) {
            return;
        }
        String orderKey = ResourceUtils.getInstance(this).getKey(R.array.order_key, R.array.order_value, value);
        mSearchInfo.setOrderKey(orderKey);
        filterData(mSearchInfo);
    }
    
    /**
     * 
     * getObject:获取intent中bundle内指定key对应的值. <br/>
     * @author maple
     * @param intent
     * @param key
     * @return
     * @since JDK 1.6
     */
    private Object getObject(Intent intent, String key){
        if (TextUtils.isEmpty(key) || intent == null) {
            return null;
        }
        Bundle bundle = intent.getExtras();
        return bundle == null ? null : bundle.get(key);
    }
    
    /**
     * 
     * setViewText:设置textView的值. <br/>
     * 
     * @author maple
     * @param view
     * @param text
     * @since JDK 1.6
     */
    private void setViewText(TextView view, String text) {
        if (view == null) { return; }
        view.setText(text == null ? "" : text);
    }
}
