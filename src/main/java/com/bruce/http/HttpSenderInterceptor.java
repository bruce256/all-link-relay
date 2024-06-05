package com.bruce.http;

import com.bruce.constant.RelayConstants;
import com.bruce.saas.sql.mybatis.MerchantIdContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 发送http请求时，在header中加入商户id信息
 *
 * @author 吕胜 lvheng1
 * @date 2022/3/10
 **/
public class HttpSenderInterceptor implements ClientHttpRequestInterceptor {
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		request.getHeaders().add(RelayConstants.MERCHANT_ID, MerchantIdContext.getMerchantId());
		return execution.execute(request, body);
	}
}
