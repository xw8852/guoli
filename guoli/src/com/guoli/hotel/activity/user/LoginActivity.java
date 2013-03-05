package com.guoli.hotel.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.UserRegisterBean;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class LoginActivity extends BaseActivity2 {

	public static final int RESULT_LOGIN_OK = 0x0001;
	public static final int RESULT_UN_LOGIN = 0x0002;

	private EditText password;
	private String mPassword;
	private EditText userID;
	private String mUserID;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.login);
		findViewById(R.id.textView6).setOnClickListener(onForgetPassword);
		findViewById(R.id.textView7).setOnClickListener(onRegistUser);
		findViewById(R.id.button2).setOnClickListener(onLoginListener);
		findViewById(R.id.button1).setOnClickListener(onUnLogClickListener);

		password = (EditText) findViewById(R.id.textView5);
		userID = (EditText) findViewById(R.id.textView3);
	}

	@Override
	public int getContentId() {
		return R.layout.login_activity;
	}

	/**
	 * 点击 注册用户
	 * 
	 * @param v
	 */
	View.OnClickListener onRegistUser = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), RESULT_OK);
		}
	};

	/**
	 * 点击 忘记密码
	 * 
	 * @param v
	 */
	View.OnClickListener onForgetPassword = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivityForResult(new Intent(LoginActivity.this, ForgetPasswordActivity.class), RESULT_OK);
		}
	};
	/**
	 * 登录
	 */
	View.OnClickListener onLoginListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			mPassword = password.getText().toString();
			mUserID = userID.getText().toString();

			if (TextUtils.isEmpty(mPassword) || TextUtils.isEmpty(mUserID)) {
				Toast.makeText(LoginActivity.this, "null", Toast.LENGTH_SHORT).show();
				return;
			}

			Request request = new GuoliRequest("userlogin", new UserRegisterBean(mUserID, mPassword, null));
			Manager.getInstance().executePoset(request, loginResponseListener);

			setResult(RESULT_LOGIN_OK);
			finish();
		}
	};

	IResponseListener loginResponseListener = new IResponseListener() {

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

	/**
	 * 非会员直接预订
	 */
	View.OnClickListener onUnLogClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			setResult(RESULT_UN_LOGIN);
			finish();
		}
	};

}
