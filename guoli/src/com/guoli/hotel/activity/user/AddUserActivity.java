/**
 * Project Name:Guoli
 * File Name:AddUserActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-10下午4:34:29
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

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
import com.guoli.hotel.net.request.bean.FavoriteUserBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

/**
 * ClassName:AddUserActivity <br/>
 * 
 * @Description: 新增入住人页面 Date: 2013-1-10 下午4:34:29 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class AddUserActivity extends BaseActivity2 implements OnClickListener {

	private EditText nameView;
	private Dialog dialog;
	private String name;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		findViewById(R.id.commit_btn).setOnClickListener(this);
		nameView = (EditText) findViewById(R.id.user_name);
		setTitle(R.string.user_new);
	}

	@Override
	public int getContentId() {
		return R.layout.add_user;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commit_btn:
			name = nameView.getText().toString().trim();
			if (TextUtils.isEmpty(name)) {
				Toast.makeText(AddUserActivity.this, "null", Toast.LENGTH_SHORT).show();
				return;
			}

			Request request = new GuoliRequest("user_addperson", new FavoriteUserBean(LoginUtils.uid, name, null));
			Manager.getInstance().executePoset(request, addUserListener);

			dialog = DialogUtils.showProgressDialog(AddUserActivity.this, "提交中...");
			break;
		default:
			break;
		}
	}

	IResponseListener addUserListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			if (null == response) {
				return;
			}

			if (null != dialog && dialog.isShowing()) {
				dialog.cancel();
			}

			Log.d("MSG", "onSuccess:" + response.getData().toString());
			HashMap<String, Object> map = new Gson().fromJson(response.result.toString(),
					new TypeToken<HashMap<String, Object>>() {
					}.getType());
			if ("1".equalsIgnoreCase(map.get("success").toString())) {
				String id = map.get("id").toString();
				Intent data = new Intent();
				data.putExtra("personname", name);
				data.putExtra("id", id);
				setResult(RESULT_OK, data);
				Toast.makeText(AddUserActivity.this, map.get("message").toString(), Toast.LENGTH_SHORT).show();
				finish();
			}

			Toast.makeText(AddUserActivity.this, map.get("message").toString(), Toast.LENGTH_SHORT).show();
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
