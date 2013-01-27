package com.guoli.hotel.activity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.guoli.hotel.R;

public class EditUserInfoActivity extends BaseActivity2 {

	private static final int DATE_PICKER_ID = 1;
	private RadioGroup rgGender;
	private TextView birthday;
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
		birthday = (TextView) findViewById(R.id.birthdayValue);
		nDate = birthday.getText().toString().split("-");
		mYear = Integer.parseInt(nDate[0]);
		mMonth = Integer.parseInt(nDate[1]);
		mDay = Integer.parseInt(nDate[2]);

		birthday.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_PICKER_ID);
			}
		});

		rgGender = (RadioGroup) findViewById(R.id.genderValue);
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
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			birthday.setText(year + "-" + (monthOfYear + 101 + "").substring(1)
					+ "-" + (dayOfMonth + 100 + "").substring(1));
		}
	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:
			return new DatePickerDialog(this, onDateSetListener, mYear,
					mMonth - 1, mDay); // 显示的默认时间
		}
		return null;
	}

  

}
