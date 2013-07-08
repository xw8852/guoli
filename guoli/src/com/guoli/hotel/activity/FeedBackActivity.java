package com.guoli.hotel.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.FeedbackInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.utils.DialogUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * 
 * ClassName: FeedBackActivity <br/>
 * date: 2013-3-21 下午2:41:46 <br/>
 * 
 * @Description:    反馈页面
 * @author maple
 * @version 
 * @since JDK 1.6
 */
public class FeedBackActivity extends BaseActivity2{
    
    private Dialog mProgressDialog;
    private static final String TAG = FeedBackActivity.class.getSimpleName();
    
    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.feedback);
        showLeftReturnBtn(false, -1);
        showRightCall();
        ((Button)findViewById(R.id.feedbackBtn)).setOnClickListener(mFeedbackListener);
    }

    @Override
    public int getContentId() {
        return R.layout.feedback;
    }

    /**
     * 
     * feedbackCommit:提交意见反馈内容. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void feedbackCommit(){
        showDialog();
        FeedbackInfo info = new FeedbackInfo();
        info.setContent(getFeedbackContent());
//        info.setAddress(getMailAddress());
        GuoliRequest request = new GuoliRequest(Action.General.FEEDBACK, info);
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mFeedbackCommitListener);
    }
    
    private String getFeedbackContent(){
        return ((EditText)findViewById(R.id.feedbackContentView)).getText().toString();
    }
    
    /**
     * 
     * getFromMailAddress:获取发件人的邮箱地址. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    /*private String getMailAddress(){
        return ((EditText)findViewById(R.id.feedbackMailAddressView)).getText().toString();
    }*/
    
    /**反馈按钮监听*/
    private OnClickListener mFeedbackListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            feedbackCommit();
        }
    };
    
    /**意见反馈提交到服务器的监听*/
    private IResponseListener mFeedbackCommitListener = new IResponseListener() {
        
        @Override
        public void onSuccess(Response resp) {
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            dismissDialog();
            finish();
        }
        
        @Override
        public void onError(Response resp) {
            Log.i(TAG, "onError()---> response=" + (resp == null ? null : resp.result));
            dismissDialog();
            String msg = getResources().getString(R.string.dialog_msg_commit_failed);
            DialogUtils.showDialog("", msg, FeedBackActivity.this);
        }
    };
    
    private Dialog showDialog(){
        return mProgressDialog = mProgressDialog == null ? DialogUtils.showProgressDialog(this, R.string.loading_msg_commit) : mProgressDialog;
     }
     
     private void dismissDialog(){
         if (mProgressDialog != null && !isFinishing()) {
             mProgressDialog.dismiss();
         }
     }
}
