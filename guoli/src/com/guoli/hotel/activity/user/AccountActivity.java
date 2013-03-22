package com.guoli.hotel.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.activity.FavoriteActivity;
import com.guoli.hotel.activity.order.OrderAuthenticActivity;
import com.guoli.hotel.utils.LoginUtils;
import com.guoli.hotel.widget.BottomTabbar;

public class AccountActivity extends BaseActivity2 implements View.OnClickListener {
	BottomTabbar mTabbar;

	private TextView nickNameView;
	private String nicknameTitle;
	private String nickname = "Jason";

	public static final int INDEX_USER_EDIT = 1;

	@Override
	public void onAfterCreate(Bundle savedInstanceState) {
		setTitle(R.string.account_title);

		if (LoginUtils.isLogin != 2) {
			startActivityForResult(new Intent(this, LoginActivity.class), 0);
		}

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
			startActivityForResult(intent, INDEX_USER_EDIT);
			break;
		case R.id.textView2:
			startActivity(new Intent(this, EditPasswordActivity.class));
			break;
		case R.id.textView3:
			startActivity(new Intent(this, OrderAuthenticActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

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
		switch (requestCode) {
		case INDEX_USER_EDIT:
			if (data == null) {
				break;
			}
			String newNickname = data.getStringExtra("newNickname");
			nickNameView.setText(nicknameTitle + newNickname);
			break;
		default:
			break;
		}
	}

}
