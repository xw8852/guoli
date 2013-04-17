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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.UpdateActivity;
import com.guoli.hotel.adapter.HotelAdapter;
import com.guoli.hotel.bean.AreaInfo;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.bean.HotelListInfo;
import com.guoli.hotel.bean.SearchInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRoom;
import com.guoli.hotel.parse.HotelListInfoParse;
import com.guoli.hotel.utils.DigitalUtils;
import com.guoli.hotel.utils.ResourceUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
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
    
    private TextView mLoadMoreBtn;
    private LinearLayout mLoadingLayout;
    private LinearLayout mFooterView;
    
    private int operationType = OPERATION_DEFAULT;
    
    private static final int OPERATION_DEFAULT = 0;
    private static final int OPERATION_FILTER = 1;
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

    private void initBusinessCountView(String count) {
        int total = DigitalUtils.convertToInt(count);
        String desc = getResources().getString(R.string.business_count_desc);
        desc = String.format(desc, total);
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
        Log.i(TAG, "filterData()---> request=" + request.Params.toParams());
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

        mFooterView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.load_more_foot, null);
        mLoadMoreBtn = (TextView) mFooterView.findViewById(R.id.loadMoreBtn);
        mLoadingLayout = (LinearLayout) mFooterView.findViewById(R.id.loading);
        mLoadMoreBtn.setOnClickListener(mLoadMoreDataLisenter);
        mListView.addFooterView(mFooterView);
        
        TextView rightBtn = getRightButton();
        rightBtn.setTextSize(14);
        rightBtn.setPadding(10, 0, 10, 0);
    }

    @Override
    protected void rightBtnClickEvent() {
        super.rightBtnClickEvent();
        //进入地图模式
        Intent intent = new Intent();
        List<HotelInfo> list = mListAdapter == null ? null : mListAdapter.getList();
        intent.putExtra("SearchInfo", mSearchInfo);
        intent.putExtra(HotelsMapActivity.KEY_HOTEL_LIST, new Gson().toJson(list));
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
            operationType = OPERATION_FILTER;
            mSearchInfo.setPageNum(String.valueOf(1));
            intent = new Intent();
            intent.setClass(this, SortListActivity.class);
            startActivityForResult(intent, ORDER_FILTER);
            break;
        case R.id.price_filter:
            operationType = OPERATION_FILTER;
            mSearchInfo.setPageNum(String.valueOf(1));
            intent = new Intent();
            intent.setClass(this, PriceListActivity.class);
            startActivityForResult(intent, PRICE_FILTER);
            break;
        case R.id.area_filter:
            operationType = OPERATION_FILTER;
            mSearchInfo.setPageNum(String.valueOf(1));
            intent = new Intent();
            intent.putExtra(AreaListActivity.KEY_CITY_CODE, (mSearchInfo == null ? "" : mSearchInfo.getCityCode()));
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
    
    private void showDefaultNoticeView(){
        findViewById(R.id.noResultFoundView).setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
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
        mLoadingLayout.setVisibility(View.GONE);
        if (list == null || list.size() == 0) {
            showDefaultNoticeView();
            return; 
        }
        Log.i(TAG, "updateListView()---> list.size=" + list.size());
        if (mListAdapter == null) {
            mListAdapter = new HotelAdapter(list, this);
            mListView.setAdapter(mListAdapter);
        } else {
            if (OPERATION_FILTER == operationType) {
                mListAdapter.clear();
            }
            mListAdapter.addMore(list);
        }
        String count = mCountView.getText().toString().trim();
        int hotelTotal = DigitalUtils.convertToInt(count.substring(0, count.length() - 1));
        int loadCount = mListAdapter == null ? 0 : mListAdapter.getCount();
        if (loadCount == hotelTotal) {
            mListView.removeFooterView(mFooterView);
        } else {
            if (mListView.getFooterViewsCount() == 0) {
                mListView.addFooterView(mFooterView);
            }
            mFooterView.setVisibility(View.VISIBLE);
            mLoadMoreBtn.setText(R.string.load_more_data);
            mLoadMoreBtn.setVisibility(View.VISIBLE);
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
                showDefaultNoticeView();
            return;
            }
            mCityView.setText(mSearchInfo.getCityName());
            mOccupancyView.setText(mSearchInfo.getStartDate());
            mLeaveView.setText(mSearchInfo.getEndDate());
            initBusinessCountView(info.getTotal());
            List<HotelInfo> list = info.getList();
            updateListView(list);
        }

        @Override
        public void onError(Response resp) {
            dismissLoadingDialog();
            if (mListAdapter != null && mListAdapter.getCount() == 0) {
                showDefaultNoticeView();
                return;
            }
            mLoadingLayout.setVisibility(View.GONE);
            mLoadMoreBtn.setVisibility(View.VISIBLE);
            mLoadMoreBtn.setText(R.string.loading_failed);
            mSearchInfo.setPageNum(""+(DigitalUtils.convertToInt(mSearchInfo.getPageNum()) - 1));
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            ToastUtil.show(ErrorCode.getErrorCodeString(resp.errorCode));
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
        int type = intent.getIntExtra(AreaListActivity.KEY_TYPE, 0);
        mSearchInfo.setAreaType(type + "");
        mSearchInfo.setArea(info.getCode());
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
    
    private OnClickListener mLoadMoreDataLisenter = new OnClickListener() {
        @Override
        public void onClick(View v) {
            operationType = OPERATION_DEFAULT;
            v.setVisibility(View.GONE);
            mLoadingLayout.setVisibility(View.VISIBLE);
            int pageNumber = DigitalUtils.convertToInt(mSearchInfo.getPageNum()) + 1;
            mSearchInfo.setPageNum(pageNumber + "");
            filterData(mSearchInfo);
        }
    };
}
