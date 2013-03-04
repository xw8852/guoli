package com.guoli.hotel;

import android.app.Activity;
import android.os.Bundle;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;

import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.LoadBean;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		testCityList();
	}

	public void testCityList() {
		/***
		 *
		 * {"action":"system_citylist","platformType":"android"}
		 */
		Request request = new GuoliRequest("system_citylist", null);
		Manager.getInstance().executePoset(request, listener);
		}

	@MediumTest
	public void testLoading() {
		/**
		 *  {"action":"loading","param":{"version":"1.0"},"platformType":"android"}
		 */
		Request request = new GuoliRequest("loading", new LoadBean("1.0"));
		Manager.getInstance().executePoset(request, listener);
	}

	IResponseListener listener = new IResponseListener() {

		@Override
		public void onSuccess(Response arg0) {
			Log.d("MSG", "onSuccess:" + arg0.getData().toString());
		}

		@Override
		public void onError(Response arg0) {
			if (!(arg0.result instanceof Exception)) {
				Log.d("MSG", "onError:" + arg0.getData().toString());
			}
		}
	};
}
