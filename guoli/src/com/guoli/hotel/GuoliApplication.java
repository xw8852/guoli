package com.guoli.hotel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;

import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Controller;

public class GuoliApplication extends Controller {
    public String packageName = "com.guoli.hotel";
    
    Timer mTimer;
    @Override
    public void onCreate() {
        super.onCreate();
        startTimer();
    }

    public void startTimer(){
        if(mTimer!=null)return;
        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                if(!isActived()){
                    mTimer.cancel();
                    mTimer=null;
                    clearImageCache();
                    LoginUtils.isLogin=0;
                    LoginUtils.memberMobile="";
                    LoginUtils.mobile="";
                    LoginUtils.uid="";
                    LoginUtils.username=""; 
                }
            }
        }, 1000, 1000);
    }

    /**
     * @Title: isActived
     * @Description: 判断当前程序是否在运行
     * @return boolean 返回类型
     * @author xiaowei
     * @date 2012-7-13 上午9:30:35
     */
    public boolean isActived() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        boolean isActived = false;
        List<RunningTaskInfo> mRunServices = am.getRunningTasks(30);
        for (RunningTaskInfo info : mRunServices) {
            if (info.topActivity.getPackageName().equals(packageName)) {
                isActived = true;
                break;
            }
        }
        return isActived;
    }
}
