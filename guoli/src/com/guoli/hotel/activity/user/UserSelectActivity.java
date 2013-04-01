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

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity;
import com.guoli.hotel.activity.order.EditOrderActivity;
import com.guoli.hotel.activity.user.UserListAdapter.ViewHolder;
import com.guoli.hotel.bean.FavoriteUserInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.GuoliResponse;
import com.guoli.hotel.net.request.bean.FavoriteUserBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

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

	private List<FavoriteUserInfo> favoriteInfos = new ArrayList<FavoriteUserInfo>();
	private ArrayList<String> selectNames = new ArrayList<String>();
	public static final String KEY_USERS = "users";
	private static final int ACTIVITY_CREATE = 0;
	private UserListAdapter adapter;
	private ListView userListView;
	private String name;
	private Dialog dialog;
	public static final int TAG_IS_INVISIBLE = 1;
	public static final int TAG_IS_VISIBLE = 2;
	public static final String KEY_CHECK_IN_USER = "checkInUser";
	public static final String KEY_FROM_PAGE = "fromPage";
	public static final int FROM_PAGE_EDIT_ORDER = 11;

	public UserSelectActivity() {
		mTitleTextId = R.string.user_select;
		mLayoutId = R.layout.user_select_list_layout;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		showLeftBtn();
		if(LoginUtils.isLogin==2)
		    getUser();
		else{
		    adapter=new UserListAdapter(this,favoriteInfos);
		    userListView.setAdapter(adapter);
		}
	}

	@Override
	protected void findViews() {
		TextView addBtn = (TextView) findViewById(R.id.add_new_user);
		addBtn.setOnClickListener(this);
		Button commitBtn = (Button) findViewById(R.id.commitBtn);
		int fromPageIndex = getIntent().getIntExtra(KEY_FROM_PAGE, 0);
		if (fromPageIndex == FROM_PAGE_EDIT_ORDER) {
		    commitBtn.setVisibility(View.VISIBLE);
		    commitBtn.setOnClickListener(this);
		} else {
		    commitBtn.setVisibility(View.GONE);
		}
		userListView = (ListView) findViewById(R.id.user_list);
		userListView.setOnItemClickListener(this);
	}

	private void getUser() {
		Request request = new GuoliRequest("user_myperson", new FavoriteUserBean(LoginUtils.uid, 1));
		Manager.getInstance().executePoset(request, getUserlistener);

		dialog = DialogUtils.showProgressDialog(UserSelectActivity.this, "获取中...");
	}

	IResponseListener getUserlistener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			if (null != dialog && dialog.isShowing()) {
				dialog.cancel();
			}
			if (response == null || response.result == null||"".equals(response.result.toString().trim())) {
			    return;
			}
			Log.d("MSG", "onSuccess:" + response.getData().toString());
			GuoliResponse<List<FavoriteUserInfo>> infos = new Gson().fromJson(response.result.toString(),
					new TypeToken<GuoliResponse<List<FavoriteUserInfo>>>() {
					}.getType());
			favoriteInfos = infos.result;
			adapter = new UserListAdapter(UserSelectActivity.this, favoriteInfos);
			userListView.setAdapter(adapter);
		}

		@Override
		public void onError(Response response) {
			if (null != dialog && dialog.isShowing()) {
				dialog.cancel();
			}
			if (response == null || response.getData() == null) {
			    return;
			}
		}
	};

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.commitBtn:
		    backToEditOrderPage();
			break;
		case R.id.add_new_user:
			startActivityForResult(new Intent(this, AddUserActivity.class), ACTIVITY_CREATE);
			break;
		default:
			break;
		}
	}

	/***
	 * 
	 * backToEditOrderPage:返回到订单编辑页面. <br/>
	 * @author maple
	 * @since JDK 1.6
	 */
	private void backToEditOrderPage() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(KEY_CHECK_IN_USER, selectNames);
        setResult(EditOrderActivity.PAGE_USER_ADD, intent);
        finish();
    }

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ACTIVITY_CREATE && resultCode == RESULT_OK) {
			FavoriteUserInfo info = new FavoriteUserInfo();
			info.personname = data.getExtras().getString("personname");
			info.id = data.getExtras().getString("id");
			favoriteInfos.add(info);
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg0) {
		ViewHolder holder = (ViewHolder) view.getTag();
		name = holder.nameView.getText().toString();

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
	}

}