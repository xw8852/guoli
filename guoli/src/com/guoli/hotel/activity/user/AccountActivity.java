package com.guoli.hotel.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.activity.FavoriteActivity;
import com.guoli.hotel.activity.order.OrderHotelListAcivity;
import com.guoli.hotel.widget.BottomTabbar;

public class AccountActivity extends BaseActivity2 implements View.OnClickListener {
	BottomTabbar mTabbar;

	private TextView nickNameView;
	private String nicknameTitle;
	private String nickname = "Jason";

	public static final int DEFAULT_REQUEST_CODE = 1;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.account_title);
		mTabbar = new BottomTabbar(this, 3);
		showLeftReturnBtn(true, R.string.dialog_exit_message);
		setRightTitleBtn(R.string.exit, null);

		nicknameTitle = getResources().getString(R.string.nickname_title);
		nickNameView = (TextView) findViewById(R.id.textView1);
		nickNameView.setText(nicknameTitle + nickname);
		nickNameView.setOnClickListener(this);
		findViewById(R.id.textView2).setOnClickListener(this);
		findViewById(R.id.textView3).setOnClickListener(this);
		findViewById(R.id.textView4).setOnClickListener(this);
		findViewById(R.id.textView5).setOnClickListener(this);
	}

	@Override
	public int getContentId() {
		return R.layout.account_activity;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textView1:
			Intent intent = new Intent();
			intent.putExtra("nickname", nickname);
			intent.setClass(AccountActivity.this, EditUserInfoActivity.class);
			startActivityForResult(intent, DEFAULT_REQUEST_CODE);
			break;
		case R.id.textView2:
			startActivity(new Intent(this, EditPasswordActivity.class));
			break;
		case R.id.textView3:
			startActivity(new Intent(this, OrderHotelListAcivity.class));
			break;
		case R.id.textView4:
			startActivity(new Intent(this, FavoriteActivity.class));
			break;
		case R.id.textView5:
			startActivity(new Intent(this, UserSelectActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == DEFAULT_REQUEST_CODE && resultCode == RESULT_OK) {
			String newNickname = data.getStringExtra("newNickname");
			nickNameView.setText(nicknameTitle + newNickname);
		}
	}

}
