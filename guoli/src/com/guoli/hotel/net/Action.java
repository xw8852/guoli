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
        public static final String MobileValidate="system_mobilecheck";
        /**意见反馈*/
        public static final String FEEDBACK="system_feedback";
        /**首页加载*/
        public static final String SYSTEM_LOADING = "system_loading";
        /**帮助*/
        public static final String SYSTEM_HELP = "system_help";
        /**关于我们*/
        public static final String SYSTEM_ABOUT = "system_about";
    }
    
    /**
     * 
     * ClassName: User <br/>
     * date: 2013-3-20 下午3:20:08 <br/>
     * @Description:用户接口
     * @author maple
     * @version Action
     * @since JDK 1.6
     */
    public static final class User{
        /**个人信息编辑接口*/
        public static final String USER_UPDATE_INFO = "user_updateinfo";

        /**
         * 非会员登录
         */
        public static final String USER_UNLOGIN="user_mblogin";
    }
    
    /**
     * ClassName: Hotel <br/>
     * date: 2013-4-2 下午3:49:15 <br/>
     * 
     * @Description:    酒店接口
     * @author maple
     * @version Action
     * @since JDK 1.6
     */
    public static final class Hotel{
        /**酒店图片列表*/
        public static final String HOTEL_PIC = "hotel_hotelpic";
    }
}
