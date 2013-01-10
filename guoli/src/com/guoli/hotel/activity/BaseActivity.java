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
import android.widget.TextView;

import com.guoli.hotel.R;

/**
 * ClassName:BaseDetailActivity <br/>
 * @Description:    没有数据加载的视图基类
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
        findViews();
    }

    /**
     * 
     * getLeftButton:获取标题栏左侧的button. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    protected TextView getLeftButton(){
        return (TextView) findViewById(R.id.left_btn);
    }
    /**
     * 
     * getRightButton:获取标题栏右侧的button. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    protected TextView getRightButton(){
        return (TextView) findViewById(R.id.right_btn);
    }
    /**
     * 
     * getTitleView:获取标题. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    protected TextView getTitleView(){
        return (TextView) findViewById(R.id.title_text);
    }

    /**
     * 
     * findViews:获取需要使用的视图对象. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void findViews();
}

