package com.guoli.hotel.net;

public final class Action {

	
	/**
	 * 订单相关的Action
	 * @author Josn
	 *
	 */
	public static final class Order{
		/**
		 * 非会员查询订单
		 */
		public static final String UNLOGIN_SEARCH="order_mobileorder";
		/**
		 * 会员订单列表
		 */
		public static final String USER_ORDER_LIST="order_userorder";
	}
}
