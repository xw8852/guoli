package com.guoli.hotel.activity.user;

import java.util.HashMap;

import android.app.Dialog;
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
import com.guoli.hotel.net.request.bean.EditPasswordBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class EditPasswordActivity extends BaseActivity2 implements OnClickListener {

	private Dialog dialog;
	private EditText oldPasswordView;
	private EditText newPasswordView;
	private EditText surePasswordView;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.modify_password);
		findViewById(R.id.confirm_edit).setOnClickListener(this);
		oldPasswordView = (EditText) findViewById(R.id.old_password);
		newPasswordView = (EditText) findViewById(R.id.new_password);
		surePasswordView = (EditText) findViewById(R.id.sure_password);
	}

	@Override
	public int getContentId() {
		return R.layout.edit_password;
	}

	@Override
	public void onClick(View v) {
		String oldPassword = oldPasswordView.getText().toString().trim();
		String newPassword = newPasswordView.getText().toString().trim();
		String surePassword = surePasswordView.getText().toString().trim();

		if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(surePassword)) {
			Toast.makeText(EditPasswordActivity.this, "null", Toast.LENGTH_SHORT).show();
			return;
		}

		if (oldPassword.length() < 6 || oldPassword.length() > 12) {
			Toast.makeText(EditPasswordActivity.this, "oldPassword error", Toast.LENGTH_SHORT).show();
			return;
		}

		if (newPassword.length() < 6 || newPassword.length() > 12) {
			Toast.makeText(EditPasswordActivity.this, "newPassword error", Toast.LENGTH_SHORT).show();
			return;
		}

		if (!surePassword.equalsIgnoreCase(newPassword)) {
			Toast.makeText(EditPasswordActivity.this, "newPassword different", Toast.LENGTH_SHORT).show();
			return;
		}

		Request request = new GuoliRequest("user_changepwd", new EditPasswordBean(LoginUtils.uid,
				LoginUtils.memberMobile, oldPassword, newPassword));
		Manager.getInstance().executePoset(request, editPswListener);
		
		dialog = DialogUtils.showProgressDialog(EditPasswordActivity.this, "提交中...");
	}

	IResponseListener editPswListener = new IResponseListener() {

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
			if ("1".equalsIgnoreCase(map.get("success").toString())) {
				Toast.makeText(EditPasswordActivity.this, map.get("message").toString(), Toast.LENGTH_SHORT).show();
				finish();
			}
		}

		@Override
		public void onError(Response response) {
			if (null != dialog && dialog.isShowing()) {
				dialog.cancel();
			}
			Log.d("MSG", "onError:" + response.getData().toString());
		}
	};

}
