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
import com.guoli.hotel.net.request.bean.UserRegisterBean;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class ForgetPasswordActivity extends BaseActivity2 {

	private EditText phoneView;
	private String phone;
	private EditText identifyView;
	private String identify;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		showLeftReturnBtn(false, -1);
		phoneView = (EditText) findViewById(R.id.phone);
		identifyView = (EditText) findViewById(R.id.identify);
		findViewById(R.id.send).setOnClickListener(onSendClickListener);
	}

	@Override
	public int getContentId() {
		return R.layout.forget_activity;
	}

	View.OnClickListener onSendClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			phone = phoneView.getText().toString();
			identify = identifyView.getText().toString();

			if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(identify)) {
				Toast.makeText(ForgetPasswordActivity.this, "null", Toast.LENGTH_SHORT).show();
				return;
			}

			Request request = new GuoliRequest("userreg", new UserRegisterBean(phone, null, identify));
			Manager.getInstance().executePoset(request, sendResponseListener);

			setResult(RESULT_OK);
			finish();
		}
	};

	IResponseListener sendResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			Log.d("MSG", "onSuccess:" + response.getData().toString());
		}

		@Override
		public void onError(Response response) {
			if (!(response.result instanceof Exception)) {
				Log.d("MSG", "onError:" + response.toString());
			}
		}
	};

}
