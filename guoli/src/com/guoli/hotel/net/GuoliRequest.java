package com.guoli.hotel.net;

import com.google.gson.Gson;
import com.msx7.core.command.model.IParams;
import com.msx7.core.command.model.Request;

public class GuoliRequest extends Request {
	public static final String URL_GUOLI = "http://api.guoli.com/doaction.php?type=json";
    private static final String DEFAULT_PLATFORM_TYPE = "android";

	public GuoliRequest(String action, Object param) {
		super();
		this.Params = new GuoliParam(action, param);
		this.url = URL_GUOLI;
	}

	public class GuoliParam implements IParams {
		public String action;
		public String platformType = DEFAULT_PLATFORM_TYPE;
		public Object param;

		public GuoliParam(String action, Object param) {
			super();
			this.action = action;
			this.param = param;
		}

		@Override
		public String toParams() {
			return new Gson().toJson(this);
		}
	}
}
