package com.guoli.hotel.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.GetIdentifyBean;
import com.guoli.hotel.net.request.bean.UserRegisterBean;
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
			} else {
				Toast.makeText(RegisterActivity.this, "null", Toast.LENGTH_SHORT).show();
			}
		}
	};

	IResponseListener getResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			Log.d("MSG", "onSuccess:" + response.getData().toString());
		}

		@Override
		public void onError(Response response) {
			if (!(response.result instanceof Exception)) {
				Log.d("MSG", "onError:" + response.getData().toString());
			}
		}
	};

	View.OnClickListener onRegisterClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			identify = identifyView.getText().toString();
			multiPwd = multiPwdView.getText().toString();
			phone = phoneView.getText().toString();
			password = passwordView.getText().toString();

			if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(multiPwd)
					|| TextUtils.isEmpty(identify)) {
				Toast.makeText(RegisterActivity.this, "null", Toast.LENGTH_SHORT).show();
				return;
			}

			if (password.length() < 6 || password.length() > 12) {
				Toast.makeText(RegisterActivity.this, "pwd is error", Toast.LENGTH_SHORT).show();
				return;
			}

			if (multiPwd.length() < 6 || multiPwd.length() > 12) {
				Toast.makeText(RegisterActivity.this, "multiPwd is error", Toast.LENGTH_SHORT).show();
				return;
			}

			if (!password.equalsIgnoreCase(multiPwd)) {
				Toast.makeText(RegisterActivity.this, "pwd is different", Toast.LENGTH_SHORT).show();
				return;
			}

			Request request = new GuoliRequest("userreg", new UserRegisterBean(phone, password, identify));
			Manager.getInstance().executePoset(request, registerResponseListener);
			setResult(RESULT_OK);
			finish();
		}
	};

	IResponseListener registerResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			Log.d("MSG", "onSuccess:" + response.getData().toString());
		}

		@Override
		public void onError(Response response) {
			if (!(response.result instanceof Exception)) {
				Log.d("MSG", "onError:" + response.getData().toString());
			}
		}
	};

}
