package com.bruce.constant;

/**
 * 常量类
 *
 * @author 吕胜 lvheng1
 * @date 2022/3/10
 **/
public class RelayConstants {
	
	public static final String MERCHANT_ID       = "merchantId";
	public static final String LINK_ID           = "link_id";
	/**
	 * header里的参数如果带下划线会被nginx拦截掉
	 */
	public static final String LINK_ID_IN_HEADER = "link-id";
}
