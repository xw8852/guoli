package com.guoli.hotel.activity.user;

import java.util.HashMap;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.GetIdentifyBean;
import com.guoli.hotel.net.request.bean.UserRegisterBean;
import com.guoli.hotel.utils.DialogUtils;
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
		passwordView = (EditText) findViewById(R.id.pwd);
		phoneView = (EditText) findViewById(R.id.phone);
		multiPwdView = (EditText) findViewById(R.id.multi_pwd);
		identifyView = (EditText) findViewById(R.id.identify);
		findViewById(R.id.send).setOnClickListener(onRegisterClickListener);
		findViewById(R.id.get_identify).setOnClickListener(getIdentifyClickListener);
	}

	@Override
	public int getContentId() {
		return R.layout.regist_activity;
	}

	View.OnClickListener getIdentifyClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			phone = phoneView.getText().toString().trim();
			if (!TextUtils.isEmpty(phone)) {
				Request request = new GuoliRequest("system_mobilecheck", new GetIdentifyBean(phone, null));
				Manager.getInstance().executePoset(request, getResponseListener);
				
				dialog = DialogUtils.showProgressDialog(RegisterActivity.this, "注册中...");
			} else {
				Toast.makeText(RegisterActivity.this, "null", Toast.LENGTH_SHORT).show();
			}
		}
	};

	IResponseListener getResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
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
				Toast.makeText(RegisterActivity.this, "短信已发送", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onError(Response response) {
			if (null != dialog && dialog.isShowing()) {
				dialog.cancel();
			}
			
			if (!(response.result instanceof Exception)) {
				Log.d("MSG", "onError:" + response.getData().toString());
			}
		}
	};

	View.OnClickListener onRegisterClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			phone = phoneView.getText().toString();
			identify = identifyView.getText().toString();
			multiPwd = multiPwdView.getText().toString().trim();
			password = passwordView.getText().toString().trim();

			if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(multiPwd)
					|| TextUtils.isEmpty(identify)) {
				Toast.makeText(RegisterActivity.this, "null", Toast.LENGTH_SHORT).show();
				return;
			}

			if (password.length() < 6 || password.length() > 12) {
				Toast.makeText(RegisterActivity.this, "pwd error", Toast.LENGTH_SHORT).show();
				return;
			}

			if (multiPwd.length() < 6 || multiPwd.length() > 12) {
				Toast.makeText(RegisterActivity.this, "multiPwd error", Toast.LENGTH_SHORT).show();
				return;
			}

			if (!password.equalsIgnoreCase(multiPwd)) {
				Toast.makeText(RegisterActivity.this, "pwd different", Toast.LENGTH_SHORT).show();
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
//				RegisterUserInfo info = new Gson().fromJson(new Gson().toJson(map.get("userinfo")),
//						RegisterUserInfo.class);
				setResult(RESULT_OK);
				finish();
			}
		}

		@Override
		public void onError(Response response) {
			if (!(response.result instanceof Exception)) {
				Log.d("MSG", "onError:" + response.getData().toString());
			}
		}
	};

}
