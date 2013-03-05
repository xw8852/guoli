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

	private EditText phone;
	private String mPhone;
	private EditText pwd;
	private String mPwd;
	private EditText multiPwd;
	private String mMultiPwd;
	private EditText identify;
	private String mIdentify;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		pwd = (EditText) findViewById(R.id.pwd);
		phone = (EditText) findViewById(R.id.phone);
		multiPwd = (EditText) findViewById(R.id.multi_pwd);
		identify = (EditText) findViewById(R.id.identify);
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
			mPhone = phone.getText().toString().trim();
			if (!TextUtils.isEmpty(mPhone)) {
				Request request = new GuoliRequest("system_mobilecheck", new GetIdentifyBean(mPhone, null));
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
			mIdentify = identify.getText().toString();
			mMultiPwd = multiPwd.getText().toString();
			mPhone = phone.getText().toString();
			mPwd = pwd.getText().toString();

			if (TextUtils.isEmpty(mPhone) || TextUtils.isEmpty(mPwd) || TextUtils.isEmpty(mMultiPwd)
					|| TextUtils.isEmpty(mIdentify)) {
				Toast.makeText(RegisterActivity.this, "null", Toast.LENGTH_SHORT).show();
				return;
			}

			if (mPwd.length() < 6 || mPwd.length() > 12) {
				Toast.makeText(RegisterActivity.this, "pwd is error", Toast.LENGTH_SHORT).show();
				return;
			}

			if (mMultiPwd.length() < 6 || mMultiPwd.length() > 12) {
				Toast.makeText(RegisterActivity.this, "multiPwd is error", Toast.LENGTH_SHORT).show();
				return;
			}

			if (!mPwd.equalsIgnoreCase(mMultiPwd)) {
				Toast.makeText(RegisterActivity.this, "pwd is different", Toast.LENGTH_SHORT).show();
				return;
			}

			Request request = new GuoliRequest("userreg", new UserRegisterBean(mPhone, mPwd, mIdentify));
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
