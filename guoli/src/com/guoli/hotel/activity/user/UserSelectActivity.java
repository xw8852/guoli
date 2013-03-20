/**
 * Project Name:SplashActivity
 * File Name:UserSelectActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-28下午10:36:33
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

package com.guoli.hotel.activity.user;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.activity.user.UserListAdapter.ViewHolder;
import com.guoli.hotel.utils.DialogUtils;

/**
 * ClassName:UserSelectActivity <br/>
 * 
 * @Description: 选择入住人页面 Date: 2013-1-28 下午10:36:33 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class UserSelectActivity extends BaseActivity implements OnItemClickListener {

	private List<UserName> names = new ArrayList<UserName>();
	private List<UserName> selectNames = new ArrayList<UserName>();
	public static final String KEY_USERS = "users";
	private static final int ACTIVITY_CREATE = 0;
	private UserListAdapter adapter;
	private ListView userListView;
	private UserName name = new UserName();

	public static final int TAG_IS_INVISIBLE = 1;
	public static final int TAG_IS_VISIBLE = 2;

	public UserSelectActivity() {
		mTitleTextId = R.string.user_select;
		mLayoutId = R.layout.user_select_list_layout;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		showLeftBtn();
	}

	@Override
	protected void findViews() {
		TextView addBtn = (TextView) findViewById(R.id.add_new_user);
		addBtn.setOnClickListener(this);
		Button commitBtn = (Button) findViewById(R.id.commitBtn);
		commitBtn.setOnClickListener(this);

		adapter = new UserListAdapter(this, names);
		userListView = (ListView) findViewById(R.id.user_list);
		userListView.setOnItemClickListener(this);
		userListView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.commitBtn:

			break;
		case R.id.add_new_user:
			startActivityForResult(new Intent(this, AddUserActivity.class), ACTIVITY_CREATE);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ACTIVITY_CREATE && resultCode == RESULT_OK) {
			UserName name = new UserName();
			name.name = data.getExtras().getString("name");
			names.add(name);
			adapter.notifyDataSetChanged();
		}
	}

	public class UserName {
		String name;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		ViewHolder holder = (ViewHolder) view.getTag();
		name.name = holder.nameView.getText().toString();

		if (holder.selectBtn.getVisibility() == View.INVISIBLE
				&& (Integer) holder.selectBtn.getTag() == TAG_IS_INVISIBLE) {
			holder.selectBtn.setVisibility(View.VISIBLE);
			holder.selectBtn.setTag(TAG_IS_VISIBLE);

			selectNames.add(name);
		} else {
			holder.selectBtn.setVisibility(View.INVISIBLE);
			holder.selectBtn.setTag(TAG_IS_INVISIBLE);

			selectNames.remove(name);
		}

		final int _position = position;
		holder.deleteBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogUtils.showDialog(UserSelectActivity.this, "", "are you sure ?",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								names.remove(names.get(_position));
								adapter.notifyDataSetChanged();
							}
						});
			}
		});
	}

}
