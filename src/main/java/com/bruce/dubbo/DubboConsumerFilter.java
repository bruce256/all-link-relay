package com.bruce.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.bruce.constant.RelayConstants;
import com.bruce.saas.sql.mybatis.MerchantIdContext;

/**
 * 消费方塞入merchantId
 *
 * @author 吕胜 lvheng1
 * @date 2022/3/9
 **/
@Activate(group = Constants.CONSUMER)
public class DubboConsumerFilter implements Filter {
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		RpcContext.getContext().setAttachment(RelayConstants.MERCHANT_ID, MerchantIdContext.getMerchantId());
		return invoker.invoke(invocation);
	}
}
