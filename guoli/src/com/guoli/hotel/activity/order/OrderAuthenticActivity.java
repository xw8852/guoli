package com.guoli.hotel.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.activity.user.LoginActivity;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.UnLoginBean;
import com.guoli.hotel.utils.LoginUtils;
import com.guoli.hotel.widget.BottomTabbar;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

public class OrderAuthenticActivity extends BaseActivity2 {
	EditText mPhoneEditText;
	EditText mValidateEditText;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.order_title_search);
		//检查是否已经登录
		if (0==LoginUtils.isLogin)
			startActivityForResult(new Intent(this, LoginActivity.class), 0);
		else
			onLoginSuccess();
		new BottomTabbar(this, 2);
		findViewById(R.id.search_btn).setOnClickListener(mSearchListener);
		mPhoneEditText = (EditText) findViewById(R.id.editText1);
		mValidateEditText = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public int getContentId() {
		return R.layout.order_authentic_activity;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == LoginActivity.RESULT_LOGIN_OK) {
			onLoginSuccess();
		}
	}
	/**
	 * 登录成功之后的操作
	 */
	public void onLoginSuccess() {
		Intent intent = new Intent(this, OrderHotelListAcivity.class);
		intent.putExtra(OrderHotelListAcivity.PARAMS_LOGIN, true);
		startActivity(intent);
		finish();
	}
	/**
	 * 非注册用户查询成功之后的切换
	 * @param bundle
	 */
	public void onSuccessSwitchOrderList(Bundle bundle) {
		Intent intent = new Intent(OrderAuthenticActivity.this,
				OrderHotelListAcivity.class);
		intent.putExtra(OrderHotelListAcivity.PARAMS_LOGIN, false);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	View.OnClickListener mSearchListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Manager.getInstance().executePoset(
					new GuoliRequest(Action.Order.UNLOGIN_SEARCH,
							new UnLoginBean(mPhoneEditText.getText()
									.toString())), mUnLoginResponseListener);

		}
	};

	IResponseListener mUnLoginResponseListener = new IResponseListener() {

		@Override
		public void onSuccess(Response arg0) {

		}

		@Override
		public void onError(Response arg0) {
			Toast.makeText(OrderAuthenticActivity.this,
					ErrorCode.getErrorCodeString(arg0.errorCode),
					Toast.LENGTH_SHORT).show();
		}
	};

}
