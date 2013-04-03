package com.guoli.hotel.activity.user;

import java.util.HashMap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class ForgetPasswordActivity extends BaseActivity2 implements OnClickListener {

	private EditText phoneView;
	private String phone;
	private EditText identifyView;
	private String inputIdentify;
	private Dialog dialog;
	public static final int FORGET_ACT = 4;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.forget_password);
		showLeftReturnBtn(false, -1);
		phoneView = (EditText) findViewById(R.id.phone);
		identifyView = (EditText) findViewById(R.id.identify);
		findViewById(R.id.send).setOnClickListener(this);
		findViewById(R.id.get_identify).setOnClickListener(this);
	}

	@Override
	public int getContentId() {
		return R.layout.forget_activity;
	}

	@Override
	public void onClick(View v) {
		phone = phoneView.getText().toString().trim();
		
		switch (v.getId()) {
		case R.id.get_identify:
			if (!UserController.isMoblieNumber(phoneView)) {
				ToastUtil.show("请输入正确的手机号码");
				return;
			}

			if (!TextUtils.isEmpty(phone)) {
				Request request = new GuoliRequest("system_mobilecheck", new GetIdentifyBean(phone, null));
				Manager.getInstance().executePoset(request, getResponseListener);

				dialog = DialogUtils.showProgressDialog(ForgetPasswordActivity.this, "发送中...");
			} else {
				Toast.makeText(ForgetPasswordActivity.this, "null", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.send:
			inputIdentify = identifyView.getText().toString();

			if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(inputIdentify)) {
				ToastUtil.show("不能为空");
				return;
			}

			if (inputIdentify.length() != 6) {
				ToastUtil.show("验证码位数不对");
				return;
			}

			Request request = new GuoliRequest("system_checkidcode", new UserRegisterBean(phone, inputIdentify));
			Manager.getInstance().executePoset(request, sendResponseListener);
			dialog = DialogUtils.showProgressDialog(ForgetPasswordActivity.this, "验证中...");

			break;
		default:
			break;
		}
	}

	/**
	 * 获取验证码IResponseListener
	 */
	IResponseListener getResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			if (null != dialog && dialog.isShowing()) {
				dialog.cancel();
			}

			if (null == response) {
				return;
			}
			Log.i("MSG", "onSuccess:" + response.getData().toString());
			HashMap<String, Object> map = new Gson().fromJson(response.result.toString(),
					new TypeToken<HashMap<String, Object>>() {
					}.getType());
			String success = map.get("success").toString();
			if ("1".equalsIgnoreCase(success)) {
				Toast.makeText(ForgetPasswordActivity.this, "短信已发送", Toast.LENGTH_SHORT).show();
			}
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

	/**
	 * 校验验证码IResponseListener
	 */
	IResponseListener sendResponseListener = new IResponseListener() {

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
				Intent intent = new Intent();
				intent.putExtra("FogetActivity", FORGET_ACT);
				intent.putExtra("mobile", phone);
				intent.setClass(ForgetPasswordActivity.this, EditPasswordActivity.class);
				startActivity(intent);
				setResult(RESULT_OK);
				finish();
			} else {
				ToastUtil.show("手机号码与验证码不匹配");
			}
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
