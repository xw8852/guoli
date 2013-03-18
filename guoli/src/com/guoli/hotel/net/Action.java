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
        /** 10. 入住价格清单 */
        public static final String OrderPriceList="order_ordprice";
        /** 11. 订单价格计算 */
        public static final String OrderPriceCount="order_ordsumpri";
        /** 12. 订单提交 */
        public static final String OrderSubmit="order_submit";
        /** 15. 订单详情 */
        public static final String OrderDetail="order_details";
        /** 16. 取消订单 */
        public static final String Ordercancel="order_cancel";
        /** 17. 订单退订 */
        public static final String OrderUnSubScribe="order_unsubscribe";
    }
    /**
     * 通用
     * @author John
     *
     */
    public static final class General{
        /**手机验证码*/
        public static final String MobileValidate="";
        
    }
}
