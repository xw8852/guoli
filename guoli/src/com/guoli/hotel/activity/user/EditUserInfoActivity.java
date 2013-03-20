package com.guoli.hotel.activity.user;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.bean.UserInfo;
import com.guoli.hotel.net.Action;
import com.guoli.hotel.net.GuoliRequest;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

public class EditUserInfoActivity extends BaseActivity2 {

	private static final int DATE_PICKER_ID = 1;
	private RadioGroup rgGender;
	private TextView birthdayView;
	private EditText nicknameView;
	private EditText addressView;
	private EditText nameView;
	private EditText phoneView;
	private EditText postCodeView;
	private String nickname;
	private String[] nDate;
	private int mYear;
	private int mMonth;
	private int mDay;

	@Override
	public int getContentId() {
		return R.layout.edit_user_info;
	};

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		initBirthdayView();
		nicknameView = (EditText) findViewById(R.id.nickname);
		addressView = (EditText) findViewById(R.id.adress);
		nameView = (EditText) findViewById(R.id.name);
		phoneView = (EditText) findViewById(R.id.phone);
		postCodeView = (EditText) findViewById(R.id.postcode);
		nickname = getIntent().getStringExtra("nickname");
		nicknameView.setText(nickname);
		setTitle(R.string.edit_user_info);
		findViewById(R.id.confirm).setOnClickListener(confirmOnClickListener);
	}

	View.OnClickListener confirmOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			/*String newNickname = nicknameView.getText().toString();
			if (!newNickname.equalsIgnoreCase(nickname)) {
				Intent intent = new Intent();
				intent.putExtra("newNickname", newNickname);
				setResult(RESULT_OK, intent);
				finish();
			}*/
		    commit();
		}
	};

	private void initBirthdayView() {
		birthdayView = (TextView) findViewById(R.id.birthday);
		nDate = birthdayView.getText().toString().split("-");
		mYear = Integer.parseInt(nDate[0]);
		mMonth = Integer.parseInt(nDate[1]);
		mDay = Integer.parseInt(nDate[2]);

		birthdayView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_PICKER_ID);
			}
		});

		rgGender = (RadioGroup) findViewById(R.id.gender);
		rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.gender_man:

					break;
				case R.id.gender_woman:

					break;
				default:
					break;
				}
			}
		});
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
			return new DatePickerDialog(this, onDateSetListener, mYear, mMonth - 1, mDay); // 显示的默认时间
		}
		return null;
	}

	/**
	 * 
	 * commit:提交编辑后的用户信息. <br/>
	 * @author maple
	 * @since JDK 1.6
	 */
	private void commit(){
	    UserInfo info = new UserInfo();
	    info.setNickname(nicknameView.getText().toString());
//	    info.setUid(uid);
//	    info.setName(nameView.getText().toString());
	    info.setGender(getGender());
	    info.setBirthday(birthdayView.getText().toString());
//	    info.setPhone(phoneView.getText().toString());
	    info.setAddress(addressView.getText().toString());
	    info.setPostcode(postCodeView.getText().toString());
	    
	    GuoliRequest request = new GuoliRequest(Action.User.USER_UPDATE_INFO, info);
	    Manager.getInstance().executePoset(request, mCommitLisenter);
	    //TODO 弹出数据加载框
	}
	
	private IResponseListener mCommitLisenter = new IResponseListener() {
        @Override
        public void onSuccess(Response arg0) {
            //TODO 数据提交成功时
            //如果该页面是订单编辑中跳转来的则,返回到订单编辑页面
            //如果该页面是用户管理中跳转来的,则关闭当前页面
//            EditUserInfoActivity.this.finish();
        }
        
        @Override
        public void onError(Response arg0) {
            
        }
    };
    
    private String getGender(){
        int id = rgGender.getCheckedRadioButtonId();
        if (R.id.gender_man == id) {
            return "0";
        }
        if (R.id.gender_woman == id) {
            return "1";
        }
        return "";
    }
}
