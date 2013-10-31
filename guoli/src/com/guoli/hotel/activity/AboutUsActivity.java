package com.guoli.hotel.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.parse.BaseParse;
import com.guoli.hotel.utils.DialogUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

public class AboutUsActivity extends BaseActivity2{
    
    private Dialog mProgressDialog;
    private static final String TAG = AboutUsActivity.class.getSimpleName();

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.about_item);
        showLeftReturnBtn(false, -1);
        showRightCall();
        loadData();
    }


    @Override
    public int getContentId() {
        return R.layout.about_us;
    }

    private void loadData() {
        showDialog();
        GuoliRequest request = new GuoliRequest(Action.General.SYSTEM_ABOUT, "");
        Manager.getInstance().executePoset(request, mSyncLisenter);
    }

    private IResponseListener mSyncLisenter = new IResponseListener() {
        
        @Override
        public void onSuccess(Response resp) {
            Log.i(TAG, "onSuccess()---> response=" + (resp == null ? null : resp.result));
            dismissDialog();
            BaseParse<AboutInfo> parse = new BaseParse<AboutInfo>();
            AboutInfo info = parse.parse(resp, AboutInfo.class);
            if (info == null) {
                return;
            }
            TextView aboutView = (TextView) findViewById(R.id.aboutView);
            aboutView.setText(info.getAbout());
        }
        
        @Override
        public void onError(Response resp) {
            Log.i(TAG, "onError()---> response=" + (resp == null ? null : resp.result));
            dismissDialog();
            
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

     private class AboutInfo {
         
         private String about;

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }
     }
}
