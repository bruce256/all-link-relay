package com.bruce.http;

import com.bruce.constant.RelayConstants;
import com.bruce.saas.sql.mybatis.MerchantIdContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 吕胜 lvheng1
 * @date 2022/3/10
 **/
public class HttpReceiverHandler extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String merchantId = request.getHeader(RelayConstants.MERCHANT_ID);
		MerchantIdContext.put(merchantId);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 避免内存泄漏
		MerchantIdContext.remove();
	}
}
