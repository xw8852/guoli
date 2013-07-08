package com.guoli.hotel.activity.user;

import java.util.HashMap;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.bean.UserInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.UserInfoBean;
import com.guoli.hotel.utils.DateUtils;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class EditUserInfoActivity extends BaseActivity2 {

	private static final int DATE_PICKER_ID = 1;
	private RadioGroup rgGender;
	private RadioButton manBtn;
	private RadioButton womanBtn;
	private TextView birthdayView;
	private EditText nicknameView; // 姓名
	private EditText addressView;
	private TextView usernameView; // 昵称
	private TextView phoneView;
	private EditText postCodeView;
	private int mYear;
	private int mMonth;
	private int mDay;
	private Dialog mProgressDialog;

	@Override
	public int getContentId() {
		return R.layout.edit_user_info;
	};

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		findViewById(R.id.confirm).setOnClickListener(confirmOnClickListener);
		usernameView = (TextView) findViewById(R.id.nickname);
		nicknameView = (EditText) findViewById(R.id.name);
		manBtn = (RadioButton) findViewById(R.id.gender_man);
		womanBtn = (RadioButton) findViewById(R.id.gender_woman);
		birthdayView = (TextView) findViewById(R.id.birthday);
		addressView = (EditText) findViewById(R.id.adress);
		phoneView = (TextView) findViewById(R.id.phone);
		postCodeView = (EditText) findViewById(R.id.postcode);
		setTitle(R.string.edit_user_info);
		showLeftReturnBtn(false, 1);
		showRightExit();
		getUserInfo();
	}

	private void getUserInfo() {
		Request request = new GuoliRequest("user_viewinfo", new UserInfoBean(LoginUtils.uid));
		Manager.getInstance().executePoset(request, getInfoListener);
	}

	IResponseListener getInfoListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			if (response == null) {
				return;
			}
			Log.d("MSG", "onSuccess:" + response.getData().toString());

			HashMap<String, Object> map = new Gson().fromJson(response.result.toString(),
					new TypeToken<HashMap<String, Object>>() {
					}.getType());
			if ("1".equalsIgnoreCase(map.get("success").toString())) {
				UserInfo info = new Gson().fromJson(new Gson().toJson(map.get("userinfo")), UserInfo.class);
				initLayoutView(info.getuserName(), info.getNickname(), info.getBirthday(), info.getMobile(),
						info.getAddress(), info.getPostcode());
				initGender(info.getGender());
			}
		}

		@Override
		public void onError(Response response) {
			if (response == null) {
				return;
			}
			Log.d("MSG", "onError:" + response.toString());
		}
	};

	private void initLayoutView(String username, String nickname, String birthday, String mobile, String address,
			String postcode) {
		usernameView.setText(username);
		nicknameView.setText(nickname);
		birthdayView.setText(birthday);
		phoneView.setText(mobile);
		addressView.setText(address);
		postCodeView.setText(postcode);
		initBirthdayView();
	}

	View.OnClickListener confirmOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (TextUtils.isEmpty(nicknameView.getText().toString().trim())) {
				Toast.makeText(EditUserInfoActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			commit();
		}
	};

	private void initBirthdayView() {
		String date = birthdayView.getText().toString();
		Log.i("date", "date = " + date);
		if (!"".equalsIgnoreCase(date)) {
			mYear = DateUtils.getYearFromString(date, DateUtils.FORMAT_DATE_YYMMDD);
			mMonth = DateUtils.getMonthFromString(date, DateUtils.FORMAT_DATE_YYMMDD);
			mDay = DateUtils.getDayFromString(date, DateUtils.FORMAT_DATE_YYMMDD);
			Log.i("date", "mYear = " + mYear + "mMonth = " + mMonth + "mDay = " + mDay);
		} else {
			getSystemTime();
		}

		birthdayView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_PICKER_ID);
			}
		});

		rgGender = (RadioGroup) findViewById(R.id.gender);
		// rgGender.setOnCheckedChangeListener(new
		// RadioGroup.OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(RadioGroup group, int checkedId) {
		// switch (checkedId) {
		// case R.id.gender_man:
		// break;
		// case R.id.gender_woman:
		// break;
		// default:
		// break;
		// }
		// }
		// });
	}

	private DatePickerDialog.OnDateSetListener onDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			birthdayView.setText(year + "-" + (monthOfYear + 101 + "").substring(1) + "-"
					+ (dayOfMonth + 100 + "").substring(1));
		}
	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:
			return new DatePickerDialog(this, onDateSetListener, mYear, mMonth, mDay); // 显示的默认时间
		}
		return null;
	}

	/**
	 * 初始化性别 0为男 1为女
	 * 
	 * @param gender
	 */
	private void initGender(String gender) {
		if ("0".equalsIgnoreCase(gender)) {
			manBtn.setChecked(true);
		} else if ("1".equalsIgnoreCase(gender)) {
			womanBtn.setChecked(true);
		} else {
			manBtn.setChecked(false);
			womanBtn.setChecked(false);
		}
	}

	/**
	 * 
	 * commit:提交编辑后的用户信息. <br/>
	 * 
	 * @author maple
	 * @since JDK 1.6
	 */
	private void commit() {
		UserInfo info = new UserInfo();
		info.setUid(LoginUtils.uid);
		info.setNickname(nicknameView.getText().toString());
		info.setGender(getGender());
		info.setBirthday(birthdayView.getText().toString());
		info.setAddress(addressView.getText().toString());
		info.setPostcode(postCodeView.getText().toString());

		// TODO 弹出数据加载框
		showDialog();
		GuoliRequest request = new GuoliRequest(Action.User.USER_UPDATE_INFO, info);
		Manager.getInstance().executePoset(request, mCommitLisenter);
	}

	private IResponseListener mCommitLisenter = new IResponseListener() {
		@Override
		public void onSuccess(Response response) {
			// TODO 数据提交成功时
			Log.d("MSG", "onSuccess:" + response.getData().toString());
			dismissDialog();
			finish();
		}

		@Override
		public void onError(Response arg0) {
			dismissDialog();
			String msg = getResources().getString(R.string.dialog_msg_commit_failed);
			DialogUtils.showDialog("", msg, EditUserInfoActivity.this);
		}
	};

	/***
	 * 
	 * getGender:获取性别字段. <br/>
	 * 
	 * @author maple
	 * @return
	 * @since JDK 1.6
	 */
	private String getGender() {
		int id = rgGender.getCheckedRadioButtonId();
		if (R.id.gender_man == id) {
			return "0";
		}
		if (R.id.gender_woman == id) {
			return "1";
		}
		return "";
	}

	private Dialog showDialog() {
		return mProgressDialog = mProgressDialog == null ? DialogUtils.showProgressDialog(this,
				R.string.loading_msg_commit) : mProgressDialog;
	}

	private void dismissDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 获取系统时间
	 */
	private void getSystemTime() {
		Time time = new Time();
		time.setToNow();
		mYear = time.year;
		mMonth = time.month;
		mDay = time.monthDay;
	}

}
