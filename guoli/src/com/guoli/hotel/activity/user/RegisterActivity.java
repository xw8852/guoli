package com.guoli.hotel.activity.user;

import java.util.HashMap;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.GetIdentifyBean;
import com.guoli.hotel.net.request.bean.UserRegisterBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class RegisterActivity extends BaseActivity2 {

	private EditText phoneView;
	private String phone;
	private EditText passwordView;
	private String password;
	private EditText multiPwdView;
	private String multiPwd;
	private EditText identifyView;
	private String identify;
	private Dialog dialog;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.register);
		passwordView = (EditText) findViewById(R.id.pwd);
		phoneView = (EditText) findViewById(R.id.phone);
		multiPwdView = (EditText) findViewById(R.id.multi_pwd);
		identifyView = (EditText) findViewById(R.id.identify);
		findViewById(R.id.send).setOnClickListener(onRegisterClickListener);
		findViewById(R.id.get_identify).setOnClickListener(getIdentifyClickListener);
		showLeftReturnBtn(false, 1);
	}

	@Override
	public int getContentId() {
		return R.layout.regist_activity;
	}

	View.OnClickListener getIdentifyClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			phone = phoneView.getText().toString().trim();

			if (TextUtils.isEmpty(phone)) {
				DialogUtils.showDialog("提示", "请输入手机号码", RegisterActivity.this);
				return;
			}

			if (!UserController.isMoblieNumber(phoneView)) {
				DialogUtils.showDialog("提示", "手机号码号码格式不正确", RegisterActivity.this);
				return;
			}

			Request request = new GuoliRequest("system_mobilecheck", new GetIdentifyBean(phone, null));
			Manager.getInstance().executePoset(request, getResponseListener);

			dialog = DialogUtils.showProgressDialog(RegisterActivity.this, "请求正在发送中,请稍等...");
		}
	};
	
	private Handler mHandler = new Handler();

	IResponseListener getResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(final Response response) {
		    mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (null != dialog && dialog.isShowing()) {
                        dialog.cancel();
                    }
                    if (null == response) {
                        return;
                    }
                    Log.d("MSG", "onSuccess:" + response.getData().toString());
                    HashMap<String, Object> map = new Gson().fromJson(response.result.toString(),
                            new TypeToken<HashMap<String, Object>>() {
                            }.getType());
                    String success = map.get("success").toString();
                    if ("1".equalsIgnoreCase(success)) {
                        DialogUtils.showDialog("提示", "短信已发送", RegisterActivity.this);
                    }
                }
            }, 1000);
		}

		@Override
		public void onError(final Response response) {
		    mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (null != dialog && dialog.isShowing()) {
                        dialog.cancel();
                    }
                    if (!(response.result instanceof Exception)) {
                        Log.d("MSG", "onError:" + response.toString());
                    }
                }
            }, 1000);
			
		}
	};

	View.OnClickListener onRegisterClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			phone = phoneView.getText().toString();
			identify = identifyView.getText().toString();
			multiPwd = multiPwdView.getText().toString().trim();
			password = passwordView.getText().toString().trim();

			if (TextUtils.isEmpty(phone)) {
				DialogUtils.showDialog("提示", "请输入手机号码", RegisterActivity.this);
				return;
			}

			if (TextUtils.isEmpty(password)) {
				DialogUtils.showDialog("提示", "请输入密码", RegisterActivity.this);
				return;
			}

			if (password.length() < 6 || password.length() > 12) {
				DialogUtils.showDialog("提示", "密码长度不正确", RegisterActivity.this);
				return;
			}

			if (TextUtils.isEmpty(multiPwd)) {
				DialogUtils.showDialog("提示", "请输入确认密码", RegisterActivity.this);
				return;
			}

			if (multiPwd.length() < 6 || multiPwd.length() > 12) {
				DialogUtils.showDialog("提示", "确认密码长度不正确", RegisterActivity.this);
				return;
			}

			if (!password.equalsIgnoreCase(multiPwd)) {
				DialogUtils.showDialog("提示", "确认密码与密码不一致", RegisterActivity.this);
				return;
			}

			if (TextUtils.isEmpty(identify)) {
				DialogUtils.showDialog("提示", "请输入验证码", RegisterActivity.this);
				return;
			}

			Request request = new GuoliRequest("user_reg", new UserRegisterBean(phone, password, identify));
			Manager.getInstance().executePoset(request, registerResponseListener);
		}
	};

	IResponseListener registerResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			if (null == response) {
				return;
			}
			Log.d("MSG", "onSuccess:" + response.getData().toString());
			HashMap<String, Object> map = new Gson().fromJson(response.result.toString(),
					new TypeToken<HashMap<String, Object>>() {
					}.getType());
			if ("1".equals(map.get("success").toString())) {
				// RegisterUserInfo info = new Gson().fromJson(new
				// Gson().toJson(map.get("userinfo")),
				// RegisterUserInfo.class);
				ToastUtil.show(map.get("message").toString());
				setResult(RESULT_OK);
				finish();
				return;
			}
			ToastUtil.show(map.get("message").toString());
		}

		@Override
		public void onError(Response response) {
			if (!(response.result instanceof Exception)) {
				Log.d("MSG", "onError:" + response.toString());
				ToastUtil.show("注册失败");
			}
		}
	};

}
