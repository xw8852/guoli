/**
 * Date:2012-12-31上午10:46:24
 * Copyright (c) 2012
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.utils.DialogUtils;

/**
 * ClassName:BaseDetailActivity <br/>
 * @Description:    没有数据加载的视图基类
 * Date:     2012-12-31 上午10:46:24 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    /**布局文件Id*/
    protected int mLayoutId;
    /**左侧button的文字*/
    protected int mLeftTextId;
    /**右侧button的文字*/
    protected int mRightTextId;
    /**标题*/
    protected int mTitleTextId;
    /**左侧的button背景*/
    protected int mLeftDrawableId;
    /**右侧的button背景*/
    protected int mRightDrawableId;
    
    private ProgressDialog mProgressDialog;
    
    /**
     * 
     * Creates a new instance of BaseActivity.
     *子类要初始化对应的titlebar时,需覆盖该构造函数并设置对应的文字及背景
     */
    public BaseActivity(){
        mLeftTextId = R.string.back_btn;
        mLeftDrawableId = R.drawable.return_btn_bg;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(arg0);
        setContentView(mLayoutId);
        findViews();
        setTitle();
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
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    
    private android.content.DialogInterface.OnClickListener exitListener
        = new android.content.DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    }; 
    
    /**
     * 
     * showLoadingDialog:显示数据加载框. <br/>
     * 
     * @author maple
     * @since JDK 1.6
     */
    protected void showLoadingDialog(int msgId) {
        dismissLoadingDialog();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(msgId));
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }
    
    /***
     * 
     * dismissDialog:关闭数据加载弹出框. <br/>
     * 
     * @author maple
     * @since JDK 1.6
     */
    protected void dismissLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing() && !isFinishing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
    
    /**
     * 
     * showExitDialog:弹出退出应用程序的提示框. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void showExitDialog(){
        DialogUtils.showDialog(R.string.dialog_title, R.string.dialog_exit_message, exitListener, this);
    }

    /**
     * 
     * leftBtnClickEvent:标题栏左侧按钮事件处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void leftBtnClickEvent(){
        finish();
    }
    
    /**
     * 
     * leftBtnClickEvent:标题栏右侧按钮事件处理. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void rightBtnClickEvent() {}

    /**
     * 
     * findViews:获取需要使用的视图对象. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void findViews();
}

