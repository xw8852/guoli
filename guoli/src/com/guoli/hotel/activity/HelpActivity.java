package com.guoli.hotel.activity;

import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.guoli.hotel.R;
import com.guoli.hotel.bean.HelpInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.parse.BaseParse;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.NetUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

public class HelpActivity extends BaseActivity2 {
    
    private Dialog mLoadingDialog;
    private static final String TAG = HelpActivity.class.getSimpleName();

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.help_item);
        showLeftReturnBtn(false, -1);
        showRightCall();
        loadData();
    }

    @Override
    public int getContentId() {
        return R.layout.help_activity;
    }

    private void loadData(){
        if (!NetUtils.isNetworkWell(this)) {
            Log.w(TAG, "网络连接不可用......");
            return;
        }
        showLoadingDialog();
        GuoliRequest request = new GuoliRequest(Action.General.SYSTEM_HELP, "");
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mSyncLisenter);
    }

    private IResponseListener mSyncLisenter = new IResponseListener() {
        @Override
        public void onSuccess(Response resp) {
            dismissLoadingDialog();
            String json =  resp == null ? null : (String)resp.result;
            Log.i(TAG, "response=" + json);
            HelpResponse helpResponse = new BaseParse<HelpResponse>().parse(resp, HelpResponse.class);
            if (helpResponse == null) {
                return;
            }
            initViews(helpResponse.getList());
        }
        
        @Override
        public void onError(Response resp) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (resp == null ? null : resp.result));
            
        }
    };
    
    private void initViews(List<HelpInfo> list){
        if (list == null) {
            return;
        }
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        for (HelpInfo info : list) {
            if (info == null) {
                continue;
            }
            View view = LayoutInflater.from(this).inflate(R.layout.help_item, null);
            TextView questView = (TextView) view.findViewById(R.id.questView);
            TextView answerView = (TextView) view.findViewById(R.id.answerView);
            questView.setText(info.getQuest());
            answerView.setText(info.getAnswer());
            rootLayout.addView(view);
        }
    }
    
    private void showLoadingDialog(){
        mLoadingDialog = mLoadingDialog == null ? DialogUtils.showProgressDialog(this, R.string.loading_msg) : mLoadingDialog;
    }
    
    private void dismissLoadingDialog(){
        if (mLoadingDialog != null && mLoadingDialog.isShowing() && !isFinishing()) {
            mLoadingDialog.dismiss();
        }
    }
    
    private class HelpResponse{
        
        @SerializedName("help")
        private List<HelpInfo> list;

        public List<HelpInfo> getList() {
            return list;
        }

        public void setList(List<HelpInfo> list) {
            this.list = list;
        }
    }
}
