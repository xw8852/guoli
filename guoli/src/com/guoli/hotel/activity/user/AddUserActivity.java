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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;

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
			String personname = nameView.getText().toString().trim();
			Intent data = new Intent();
			data.putExtra("personname", personname);
			setResult(RESULT_OK, data);
			finish();
			break;
		default:
			break;
		}
	}

}
