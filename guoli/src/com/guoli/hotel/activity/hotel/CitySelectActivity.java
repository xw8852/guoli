/**
 * Project Name:SplashActivity
 * File Name:CitySelectActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-25下午7:21:11
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

package com.guoli.hotel.activity.hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.CallActivity;
import com.guoli.hotel.adapter.CityInfoListAdapter;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.bean.CityInfo;
import com.guoli.hotel.net.request.bean.CityRequestParams;
import com.guoli.hotel.net.response.bean.CityResponseParams;
import com.guoli.hotel.parse.CitysParse;
import com.guoli.hotel.utils.ToastUtil;
import com.guoli.hotel.widget.SosUniversalListView;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

/**
 * ClassName:CitySelectActivity <br/>
 * 
 * @Description: 城市选择页面 Date: 2013-1-25 下午7:21:11 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class CitySelectActivity extends CallActivity implements OnItemClickListener {
    /** 城市名称关键字 */
    public final static String KEY_CITYINFO = "cityInfo";
    private SosUniversalListView mListView;
    private EditText mKeyWordView;

    public CitySelectActivity() {
        mLayoutId = R.layout.city_select_layout;
        mTitleTextId = R.string.city_select;
        mRightDrawableId = R.drawable.btn_top_phone;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showLeftBtn();
        showRightBtn();
        loadCityData();
    }

    /***
     * 
     * loadCityData:. 从服务器端读取城市列表数据<br/>
     * 
     * @author maple
     * @since JDK 1.6
     */
    private void loadCityData() {
        showLoadingDialog(R.string.loading_msg);
        CityRequestParams cityParams = new CityRequestParams();
        cityParams.setCityCode("");
        Request request = new GuoliRequest("system_citylist", cityParams);
        Log.i("CitySelectActivity", "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mLoadListener);
    }

    @Override
    protected void findViews() {
        mListView = (SosUniversalListView) findViewById(R.id.cityListView);
        mKeyWordView = (EditText) findViewById(R.id.cityNameKey);
        ImageView deleteBtn = (ImageView) findViewById(R.id.deleteBtn);
        ImageView searchBtn = (ImageView) findViewById(R.id.searchBtn);
        deleteBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == null) {
            return;
        }
        Adapter adapter = parent.getAdapter();
        if (!(adapter instanceof CityInfoListAdapter)) {
            return;
        }
        CityInfoListAdapter listAdapter = (CityInfoListAdapter) adapter;
        CityInfo info = listAdapter.getItem(position);
        if (info == null) {
            return;
        }
        backToSearchHotelActivity(info);
        finish();
    }
    
    private void backToSearchHotelActivity(CityInfo info){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_CITYINFO, info);
        intent.putExtras(bundle);
        setResult(SearchHotelActivity.PAGE_CITY, intent);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.searchBtn:

            break;
        case R.id.deleteBtn:
            mKeyWordView.setText("");
            break;
        default:
            break;
        }
    }
    
    @Override
    public void onBackPressed() {
        CityInfo info = getIntent().getParcelableExtra(KEY_CITYINFO);
        backToSearchHotelActivity(info);
        super.onBackPressed();
    }

    /**
     * 
     * refreshListView:刷新listView. <br/>
     * 
     * @author maple
     * @since JDK 1.6
     */
    private void refreshListView(LinkedHashMap<String, List<CityInfo>> map) {
        if (mListView == null) {
            return;
        }
        CityInfo info = getIntent().getParcelableExtra(KEY_CITYINFO);
        CityInfoListAdapter adapter = new CityInfoListAdapter(map, this, info);
        mListView.setPinnedHeaderView(findViewById(R.id.nameTitle));
        mListView.setAdapter(adapter);
        // if (adapter == null) {
        // adapter = new CityInfoListAdapter(citys, this);
        // mListView.setAdapter(adapter);
        // return;
        // }
        // adapter.changeData(citys);
    }

    IResponseListener mLoadListener = new IResponseListener() {

        @Override
        public void onSuccess(Response resp) {
            Log.i("CitySelectActivity", "response=" + (resp == null ? null : resp.result));
            dismissLoadingDialog();

            // 解析服务器返回结果为数组类型
            CityResponseParams respParams = new CitysParse().parseResponse(resp);
            if (respParams == null) {
                return;
            }
            // 刷新listView
            refreshListView(onSortList(respParams.mHostList, respParams.getList()));
            Editor editor = PreferenceManager.getDefaultSharedPreferences(CitySelectActivity.this).edit();
            editor.putString("CityList", new Gson().toJson(resp));
            editor.commit();
        }

        @Override
        public void onError(Response resp) {
            dismissLoadingDialog();
            ToastUtil.show(ErrorCode.getErrorCodeString(resp.errorCode));
            if (PreferenceManager.getDefaultSharedPreferences(CitySelectActivity.this).contains("CityList")) {
                String str = PreferenceManager.getDefaultSharedPreferences(CitySelectActivity.this).getString("CityList", "");
                if(!"".equals(str)){
                    onSuccess(new Gson().fromJson(str, Response.class));
                }
            }
        }
    };

    public LinkedHashMap<String, List<CityInfo>> onSortList(List<CityInfo> mHost, List<CityInfo> others) {
        LinkedHashMap<String, List<CityInfo>> map = new LinkedHashMap<String, List<CityInfo>>();
        if (mHost == null)
            mHost = new ArrayList<CityInfo>();
        if (others == null)
            others = new ArrayList<CityInfo>();
        Collections.sort(others, mCityComparator);
        List<CityInfo> tempList;
        map.put("热门城市", mHost);
        for (CityInfo cityInfo : others) {
            if (cityInfo == null)
                continue;
            tempList = map.get(cityInfo.getFirstChar());
            if (tempList == null) {
                tempList = new ArrayList<CityInfo>();
            }
            tempList.add(cityInfo);
            map.put(cityInfo.getFirstChar(), tempList);
        }
        return map;
    }

    Comparator<CityInfo> mCityComparator = new Comparator<CityInfo>() {

        @Override
        public int compare(CityInfo object1, CityInfo object2) {
            if (object1 == null || object2 == null)
                return 0;
            if (object1.getFirstChar() == null || object2.getFirstChar() == null)
                return 0;
            if (object1.getFirstChar().length() < 1 || object2.getFirstChar().length() < 1)
                return 0;

            return object1.getFirstChar().charAt(0) - object2.getFirstChar().charAt(0);
        }
    };
}
