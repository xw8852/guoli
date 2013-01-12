package com.guoli.hotel.activity;

import com.guoli.hotel.R;
import com.guoli.hotel.utils.DialogUtils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseActivity2 extends Activity {
    private LinearLayout root;
    protected TextView mTitle;
    protected TextView mRightBtn;
    protected TextView mLeftBtn;
    protected boolean isShowDialog;

    private CancelClickListener mCancelClickListener;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        root = (LinearLayout) findViewById(R.id.root);
        if (getContentId() > 0) {
           getLayoutInflater().inflate(getContentId(),root,true);
        } else {
            root.addView(getContentView());
        }
        mTitle = (TextView) root.findViewById(R.id.titleBar).findViewById(R.id.title_text);
        mRightBtn = (TextView) root.findViewById(R.id.titleBar).findViewById(R.id.right_btn);
        mLeftBtn = (TextView) root.findViewById(R.id.titleBar).findViewById(R.id.left_btn);
        onAfterCreate(savedInstanceState);
    }

    public abstract void onAfterCreate(Bundle savedInstanceState);

    public abstract int getContentId();

    public View getContentView() {
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#setTitle(int)
     * 
     *      <p>
     *      Title: setTitle
     *      </p>
     *      <p>
     *      Description: 给当前activity设置标题
     *      </p>
     * @param resId
     *            需要设置的标题字符串的资源id
     * @see android.app.Activity#setTitle(int)
     */
    public void setTitle(int resId) {
        mTitle.setText(resId);
    }

    /**
     * 
     * @Title: setTitle
     * @Description: 给当前activity设置标题
     * @param title
     *            需要设置的标题字符串
     * @author xiaowei
     * @date 2013-1-11 下午4:29:51
     */
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    /**
     * 
     * @Title: getLeftButton
     * @Description: 获取标题栏上的左边按钮
     * @return Button 返回类型
     * @author xiaowei
     * @date 2013-1-11 下午4:35:12
     */
    public TextView getLeftButton() {
        return mLeftBtn;
    }

    /**
     * 
     * @Title: getRightButton
     * @Description: 获取标题栏上的右边按钮
     * @return Button 返回类型
     * @author xiaowei
     * @date 2013-1-11 下午4:36:38
     */
    public TextView getRightButton() {
        return mRightBtn;
    }

    /**
     * 
     * @Title: setLeftTitleBtn
     * @Description: 设置标题栏上的左边按钮
     * @param resId
     *            按钮上显示的文本对应的资源id
     * @param listener
     *            按钮的点击事件监听 void 返回类型
     * @author xiaowei
     * @date 2013-1-11 下午4:41:49
     */
    public void setLeftTitleBtn(int resId, View.OnClickListener listener) {
        getLeftButton().setText(resId);
        getLeftButton().setOnClickListener(listener);
        getLeftButton().setVisibility(View.VISIBLE);
    }

    /**
     * 
     * @Title: setLeftBackgroundBtn
     * @Description: 设置标题栏上的左边按钮
     * @param backgroudResId
     *            按钮的背景的资源id
     * @param listener
     *            按钮的点击事件监听 void 返回类型
     * @author xiaowei
     * @date 2013-1-11 下午4:41:52
     */
    public void setLeftBackgroundBtn(int backgroudResId, View.OnClickListener listener) {
        getLeftButton().setBackgroundResource(backgroudResId);
        getLeftButton().setOnClickListener(listener);
        getLeftButton().setVisibility(View.VISIBLE);
    }

    /**
     * 
     * @Title: setRightTitleBtn
     * @Description: 设置标题栏上的右边按钮
     * @param resId
     *            按钮上显示的文本对应的资源id
     * @param listener
     *            按钮的点击事件监听 void 返回类型
     * @author xiaowei
     * @date 2013-1-11 下午4:42:05
     */
    public void setRightTitleBtn(int resId, View.OnClickListener listener) {
        getRightButton().setOnClickListener(listener);
        getRightButton().setText(resId);
        getRightButton().setVisibility(View.VISIBLE);
    }

    /**
     * 
     * @Title: setRightBackgroundBtn
     * @Description: 设置标题栏上的右边按钮
     * @param backgroudResId
     *            按钮的背景的资源id
     * @param listener
     *            按钮的点击事件监听 void 返回类型
     * @author xiaowei
     * @date 2013-1-11 下午4:42:02
     */
    public void setRightBackgroundBtn(int backgroudResId, View.OnClickListener listener) {
        getRightButton().setOnClickListener(listener);
        getRightButton().setBackgroundResource(backgroudResId);
        getRightButton().setVisibility(View.VISIBLE);
    }

    /**
     * 将标题栏左边按钮设置成返回按钮
     * 
     * @param isShowDialog
     *            是否弹出提示对话框
     * @param message
     *            当 isShowDialog 为true，该值有效，弹出对话的内容信息
     */
    public void showLeftReturnBtn(boolean isShowDialog, final int message) {
        mCancelClickListener = new CancelClickListener(message, isShowDialog);  
        setLeftTitleBtn(R.string.back_btn, mCancelClickListener);
    }

    @Override
    public final void onBackPressed() {
        if (mCancelClickListener != null)
            mCancelClickListener.onClick(null);
    }

    /**
     * 弹出提示对话框
     * 
     * @param Message
     *            对话框消息内容
     */
    public void showTipsDialog(int Message) {
        DialogUtils.showDialog(R.string.dialog_title, Message, mExitClickListener, this);
    }

    private void exitActivity() {
        super.onBackPressed();
    }

    DialogInterface.OnClickListener mExitClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            exitActivity();
        }
    };

    private class CancelClickListener implements View.OnClickListener {
        int message;
        boolean isShowDialog;

        public CancelClickListener(int message, boolean isShowDialog) {
            super();
            this.message = message;
            this.isShowDialog = isShowDialog;
        }

        @Override
        public void onClick(View v) {
            if (isShowDialog) {
                showTipsDialog(message);
            } else
                exitActivity();
        }

    }
}
