/**
 * Project Name:SplashActivity
 * File Name:AreaListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26下午12:28:56
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity.hotel;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.CityRequestParams;
import com.guoli.hotel.net.response.bean.AreaInfo;
import com.guoli.hotel.net.response.bean.LocationResponse;
import com.guoli.hotel.parse.AreaParase;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

/**
 * ClassName:AreaListActivity <br/>
 * @Description:    行政区域选择页面
 * Date:     2013-1-26 下午12:28:56 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AreaListActivity extends BaseActivity implements OnItemClickListener, OnCheckedChangeListener {
    
    private String mCityCode;
    private ListView mListView;
    private RadioGroup mTabBar;
    private ArrayAdapter<AreaInfo> mAdapter;
    /**行政区域数据*/
    private List<AreaInfo> mBarrioInfos;
    /**商圈数据*/
    private List<AreaInfo> mBusinessInfos;
    
    public static final String KEY_AREA = "area";
    
    public AreaListActivity(){
        mTitleTextId = R.string.hotel_area;
        mLayoutId = R.layout.area_list_layout;
    }
    
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showLeftBtn();
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int checkedId = mTabBar.getCheckedRadioButtonId();
        AreaInfo info = null;
        switch (checkedId) {
        case R.id.areaAdminRadioBtn:
            if (mBarrioInfos == null || !(mBarrioInfos.size() > position)) {
                break;
            }
            info = mBarrioInfos.get(position);
            break;
        case R.id.areaShoppingRadioBtn:
            if (mBusinessInfos == null || !(mBusinessInfos.size() > position)) {
                break;
            }
            info = mBusinessInfos.get(position);
            break;
        default:
            break;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_AREA, info);
        intent.putExtras(bundle);
        setResult(SearchHotelActivity.PAGE_AREA, intent);
        finish();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.areaListView);
        mListView.setOnItemClickListener(this);
        mTabBar = (RadioGroup) findViewById(R.id.radioGroup);
        mTabBar.setOnCheckedChangeListener(this);
        mTabBar.check(R.id.areaAdminRadioBtn);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
        case R.id.areaAdminRadioBtn:
            updateListView(mBarrioInfos);
            break;
        case R.id.areaShoppingRadioBtn:
            updateListView(mBusinessInfos);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * updateListView:更新当前页面listView中的数据. <br/>
     * @author maple
     * @param array
     * @since JDK 1.6
     */
    private void updateListView(List<AreaInfo> list){
        if (list == null) {
            return;
        }
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<AreaInfo>(this, R.layout.item_single_layout, list);
            mListView.setAdapter(mAdapter);
            return;
        }
        mAdapter.clear();
        for (AreaInfo info : list) {
            if (info == null) {
                continue;
            }
            mAdapter.add(info);
        }
        mAdapter.notifyDataSetChanged();
    }
    
    /**
     * 
     * loadData:从服务器端加载数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void loadData(){
        showLoadingDialog(R.string.loading_msg);
        CityRequestParams params = new CityRequestParams();
        params.setCityCode(mCityCode);
        Request request = new GuoliRequest("system_arealist", params);
        Manager.getInstance().executePoset(request, mLoadListener);
    }
    
    private IResponseListener mLoadListener = new IResponseListener() {
        @Override
        public void onSuccess(Response resp) {
            dismissLoadingDialog();
            LocationResponse locationResp = new AreaParase().parseResponse(resp);
            if (locationResp == null) {
                return;
            }
            mBarrioInfos = locationResp.getBarrioInfos();
            mBusinessInfos = locationResp.getBusinessInfos();
            updateListView(mBarrioInfos);
        }
        
        @Override
        public void onError(Response resp) {
            dismissLoadingDialog();
        }
    };
}

