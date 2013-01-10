/**
 * Project Name:Guoli
 * File Name:TitleBarActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-10下午4:36:49
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

/**
 * ClassName:TitleBarActivity <br/>
 * @Description:
 * Date:     2013-1-10 下午4:36:49 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class TitleBarActivity extends BaseActivity implements OnClickListener {
    /**左侧button的文字*/
    protected int mLeftTextId = R.string.back_btn;
    /**右侧button的文字*/
    protected int mRightTextId;
    /**标题*/
    protected int mTitleTextId;
    /**左侧的button背景*/
    protected int mLeftDrawableId = R.drawable.return_btn_bg;
    /**右侧的button背景*/
    protected int mRightDrawableId;
    
    @Override
    protected void onCreate(Bundle arg0) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(arg0);
        setTitle();
    }
    /**
     * 
     * setTitle:设置标题. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void setTitle(){
        TextView view = getTitleView();
        if (view == null) {
            return;
        }
        view.setText(mTitleTextId);
    }
    
    /**
     * 
     * showReturnBtn:显示左侧的返回按钮. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void showLeftBtn(){
        TextView view = getLeftButton();
        if (view == null) {
            return;
        }
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(this);
        if (mLeftDrawableId != 0) {
            view.setBackgroundResource(mLeftDrawableId);
        }
        if (mLeftTextId != 0) {
            view.setText(mLeftTextId);
        }
    }
    
    /**
     * 
     * showRightBtn:显示右侧的button. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void showRightBtn(){
        TextView view = getRightButton();
        if (view == null) {
            return;
        }
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(this);
        if (mRightDrawableId != 0) {
            view.setBackgroundResource(mRightDrawableId);
        }
        if (mRightTextId != 0) {
            view.setText(mRightTextId);
        }
        
    }
    
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.left_btn:
            leftBtnClickEvent();
            break;
        case R.id.right_btn:
            rightBtnClickEvent();
            break;
        default:
            break;
        }
    }

    /**
     * 
     * leftBtnClickEvent:标题栏左侧按钮事件处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void leftBtnClickEvent(){
        finish();
    }
    /**
     * 
     * leftBtnClickEvent:标题栏右侧按钮事件处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void rightBtnClickEvent(){
        
    }
}

