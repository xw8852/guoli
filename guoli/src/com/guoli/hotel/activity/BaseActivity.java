/**
 * Project Name:OneByOne2.6
 * File Name:BaseDetailActivity.java
 * Package Name:com.yhiker.playmate.ui.citytour.scenicshops
 * Date:2012-12-31上午10:46:24
 * Copyright (c) 2012
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import android.app.Activity;
import android.os.Bundle;

import com.guoli.hotel.utils.NetUtils;

/**
 * ClassName:BaseDetailActivity <br/>
 * @Description:    视图页面数据加载的基类
 * Date:     2012-12-31 上午10:46:24 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class BaseActivity extends Activity {
    /**布局文件Id*/
    protected int mLayoutId;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(mLayoutId);
        initPassParams();
        findViews();
        loadData();
    }

    /**
     * 
     * loadData:加载数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void loadData(){
        if (!NetUtils.isNetworkWell(this)) {
            loadLocalData();
            initViews();
            return;
        }
        showLoadingProgressBar();
        loadNetworkData();
    }
    
    /**
     * 
     * showLoadingProgressBar:显示数据加载loading框. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void showLoadingProgressBar(){
        
    }
    
    /**
     * 
     * closeLoadingProgressBar:隐藏数据加载loading框. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void closeLoadingProgressBar(){
        
    }

    /**
     * 
     * findViews:获取需要使用的视图对象. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void findViews();
    /***
     * 
     * initPassParams:初始化从其他页面传递过来的参数信息. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void initPassParams();
    /**
     * 
     * initViews:初始化视图信息. <br/>
     * 在数据加载完毕后调用该方法.<br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void initViews();
    /***
     * 
     * loadNetworkData:加载网络数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void loadNetworkData();
    /**
     * 
     * loadLocalData:加载本地数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void loadLocalData();
}

