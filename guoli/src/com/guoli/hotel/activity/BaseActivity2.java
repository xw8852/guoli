package com.guoli.hotel.activity;

import com.guoli.hotel.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseActivity2 extends Activity {
    private LinearLayout root;
    protected TextView mTitle;
    protected TextView mRightBtn;
    protected TextView mLeftBtn;
    
	@Override
	protected  final void onCreate(Bundle savedInstanceState) {
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity);
		root=(LinearLayout)findViewById(R.id.root);
		if(getContentId()>0){
		    root.addView(getLayoutInflater().inflate(getContentId(), null));
		}else{
		    root.addView(getContentView());
		}
		mTitle=(TextView)root.findViewById(R.id.titleBar).findViewById(R.id.title_text);
		mRightBtn=(TextView)root.findViewById(R.id.titleBar).findViewById(R.id.right_btn);
		mLeftBtn=(TextView)root.findViewById(R.id.titleBar).findViewById(R.id.left_btn);
		onAfterCreate(savedInstanceState);
	}
	
	public abstract void onAfterCreate(Bundle savedInstanceState);
	
	public abstract int getContentId();
	
	public View getContentView(){
		return null;
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#setTitle(int)
	 *
	 * <p>Title: setTitle</p> 
	 * <p>Description: 给当前activity设置标题</p> 
	 * @param resId 需要设置的标题字符串的资源id
	 * @see android.app.Activity#setTitle(int)
	 */
	public  void setTitle(int resId){
		mTitle.setText(resId);
	}
	/**
	 * 
	 * @Title: setTitle 
	 * @Description: 给当前activity设置标题
	 * @param title  需要设置的标题字符串
	 * @author xiaowei
	 * @date 2013-1-11 下午4:29:51
	 */
	public void setTitle(String title){
		mTitle.setText(title);
	}
	/**
	 * 
	 * @Title: getLeftButton 
	 * @Description: 获取标题栏上的左边按钮 
	 * @return    
	 * Button    返回类型 
	 * @author xiaowei
	 * @date 2013-1-11 下午4:35:12
	 */
	public TextView getLeftButton(){
		return mLeftBtn;
	}
	/**
	 * 
	 * @Title: getRightButton 
	 * @Description: 获取标题栏上的右边按钮 
	 * @return    
	 * Button    返回类型 
	 * @author xiaowei
	 * @date 2013-1-11 下午4:36:38
	 */
	public TextView getRightButton(){
		return mRightBtn;
	}

	/**
	 * 
	 * @Title: setLeftTitleBtn 
	 * @Description: 设置标题栏上的左边按钮  
	 * @param resId  按钮上显示的文本对应的资源id
	 * @param listener    按钮的点击事件监听
	 * void    返回类型 
	 * @author xiaowei
	 * @date 2013-1-11 下午4:41:49
	 */
	public void setLeftTitleBtn(int resId,View.OnClickListener listener){
		getLeftButton().setText(resId);
		getLeftButton().setOnClickListener(listener);
	}
	/**
	 * 
	 * @Title: setLeftBackgroundBtn 
	 * @Description: 设置标题栏上的左边按钮  
	 * @param backgroudResId 按钮的背景的资源id
	 * @param listener    按钮的点击事件监听
	 * void    返回类型 
	 * @author xiaowei
	 * @date 2013-1-11 下午4:41:52
	 */
	public void setLeftBackgroundBtn(int backgroudResId,View.OnClickListener listener){
		getLeftButton().setBackgroundResource(backgroudResId);
		getLeftButton().setOnClickListener(listener);
	}


	/**
	 * 
	 * @Title: setRightTitleBtn 
	 * @Description: 设置标题栏上的右边按钮 
	 * @param resId 按钮上显示的文本对应的资源id
	 * @param listener    按钮的点击事件监听
	 * void    返回类型 
	 * @author xiaowei
	 * @date 2013-1-11 下午4:42:05
	 */
	public void setRightTitleBtn(int resId,View.OnClickListener listener){
		getLeftButton().setOnClickListener(listener);
		getLeftButton().setText(resId);
	}
	/**
	 * 
	 * @Title: setRightBackgroundBtn 
	 * @Description: 设置标题栏上的右边按钮 
	 * @param backgroudResId 按钮的背景的资源id
	 * @param listener    按钮的点击事件监听
	 * void    返回类型 
	 * @author xiaowei
	 * @date 2013-1-11 下午4:42:02
	 */
	public void setRightBackgroundBtn(int backgroudResId,View.OnClickListener listener){
	    getLeftButton().setOnClickListener(listener);
        getLeftButton().setBackgroundResource(backgroudResId);
	}


}
