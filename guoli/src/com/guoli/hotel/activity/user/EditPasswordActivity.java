package com.guoli.hotel.activity.user;

import java.util.HashMap;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.EditPasswordBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class EditPasswordActivity extends BaseActivity2 implements OnClickListener {

    private Dialog dialog;
    private Button confirmBtn;
    private EditText oldPasswordView;
    private EditText newPasswordView;
    private EditText surePasswordView;
    private String oldPassword;
    private String newPassword;
    private String surePassword;

    private static final int IS_FORGET_ACT = 1 * 300;
    private static final int IS_EDIT_ACT = 2 * 300;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        setTitle(R.string.modify_password);
        showLeftReturnBtn(false, -1);
        confirmBtn = (Button) findViewById(R.id.confirm_edit);
        oldPasswordView = (EditText) findViewById(R.id.old_password);
        newPasswordView = (EditText) findViewById(R.id.new_password);
        surePasswordView = (EditText) findViewById(R.id.sure_password);
        confirmBtn.setOnClickListener(this);
        initView();
    }
    int isForget;
    private void initView() {
         isForget = getIntent().getIntExtra("FogetActivity", 0);
        if (isForget == ForgetPasswordActivity.FORGET_ACT) {
            TextView view = (TextView) findViewById(R.id.oldPassword);
            View line = (View) findViewById(R.id.line1);
            oldPasswordView.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            confirmBtn.setTag(IS_FORGET_ACT);
        } else {
            confirmBtn.setTag(IS_EDIT_ACT);
            isForget=IS_EDIT_ACT;
        }

    }

    @Override
    public int getContentId() {
        return R.layout.edit_password;
    }

    @Override
    public void onClick(View v) {
        newPassword = newPasswordView.getText().toString().trim();
        surePassword = surePasswordView.getText().toString().trim();
        oldPassword = oldPasswordView.getText().toString().trim();
        if (TextUtils.isEmpty(oldPassword)&&isForget==IS_EDIT_ACT) {
            DialogUtils.showDialog("提示", "请输入旧密码", EditPasswordActivity.this);
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            DialogUtils.showDialog("提示", "请输入新密码", EditPasswordActivity.this);
            return;
        }

        if (TextUtils.isEmpty(surePassword)) {
            DialogUtils.showDialog("提示", "请输入确认密码", EditPasswordActivity.this);
            return;
        }

        if (newPassword.length() < 6 || newPassword.length() > 12) {
            DialogUtils.showDialog("提示", "密码长度不正确", EditPasswordActivity.this);
            return;
        }

        if (surePassword.length() < 6 || surePassword.length() > 12) {
            DialogUtils.showDialog("提示", "确认密码长度不正确", EditPasswordActivity.this);
            return;
        }

        if (!surePassword.equalsIgnoreCase(newPassword)) {
            DialogUtils.showDialog("提示", "确认密码与密码不一致", EditPasswordActivity.this);
            return;
        }

        switch (isForget) {
        case IS_FORGET_ACT:
            String mobile = getIntent().getStringExtra("mobile");

            Request request1 = new GuoliRequest("user_newpwd", new EditPasswordBean(mobile, newPassword));
            Manager.getInstance().executePoset(request1, editPswListener1);
            dialog = DialogUtils.showProgressDialog(EditPasswordActivity.this, "提交中...");
            break;
        case IS_EDIT_ACT:
            if (oldPassword.length() < 6 || oldPassword.length() > 12) {
                DialogUtils.showDialog("提示", "密码长度不正确", EditPasswordActivity.this);
                return;
            }

            Request request2 = new GuoliRequest("user_changepwd", new EditPasswordBean(LoginUtils.uid, LoginUtils.memberMobile, oldPassword, newPassword));
            Manager.getInstance().executePoset(request2, editPswListener2);
            dialog = DialogUtils.showProgressDialog(EditPasswordActivity.this, "提交中...");
            break;
        default:
            break;
        }
    }

    IResponseListener editPswListener1 = new IResponseListener() {

        @Override
        public void onSuccess(Response response) {
            if (null != dialog && dialog.isShowing()) {
                dialog.cancel();
            }

            if (null == response) {
                return;
            }

            Log.d("MSG", "onSuccess:" + response.getData().toString());
            HashMap<String, Object> map = new Gson().fromJson(response.result.toString(), new TypeToken<HashMap<String, Object>>() {
            }.getType());
            if ("1".equalsIgnoreCase(map.get("success").toString())) {
                ToastUtil.show(map.get("message").toString());
                finish();
                return;
            }
            ToastUtil.show(map.get("message").toString());
        }

        @Override
        public void onError(Response response) {
            if (null != dialog && dialog.isShowing()) {
                dialog.cancel();
            }

            Log.i("tag", "response=" + (response == null ? null : response.result));
            ToastUtil.show(ErrorCode.getErrorCodeString(response.errorCode));
        }
    };

    IResponseListener editPswListener2 = new IResponseListener() {

        @Override
        public void onSuccess(Response response) {
            if (null != dialog && dialog.isShowing()) {
                dialog.cancel();
            }

            if (null == response) {
                return;
            }

            Log.d("MSG", "onSuccess:" + response.getData().toString());
            HashMap<String, Object> map = new Gson().fromJson(response.result.toString(), new TypeToken<HashMap<String, Object>>() {
            }.getType());
            if ("1".equalsIgnoreCase(map.get("success").toString())) {
                ToastUtil.show(map.get("message").toString());
                finish();
                return;
            }
            ToastUtil.show(map.get("message").toString());
        }

        @Override
        public void onError(Response response) {
            if (null != dialog && dialog.isShowing()) {
                dialog.cancel();
            }

            Log.i("tag", "response=" + (response == null ? null : response.result));
            ToastUtil.show(ErrorCode.getErrorCodeString(response.errorCode));
        }
    };

}
