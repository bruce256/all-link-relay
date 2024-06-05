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
import com.bruce.utils.LinkIdUtils;

/**
 * 消费方塞入merchantId
 *
 * @author 吕胜 lvheng1
 * @date 2022/3/9
 **/
@Activate(group = Constants.PROVIDER)
public class DubboProviderFilter implements Filter {
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		// before invoking
		processMerchantId();
		
		Result result = invoker.invoke(invocation);
		
		// after invoking 避免内存泄漏
		MerchantIdContext.remove();
		return result;
	}
	
	private void processMerchantId() {
		String merchantId = RpcContext.getContext().getAttachment(RelayConstants.MERCHANT_ID);
		MerchantIdContext.put(merchantId);
	}
	
	private void processLinkId() {
		String linkId = RpcContext.getContext().getAttachment(RelayConstants.LINK_ID);
		LinkIdUtils.putLinkId(linkId);
	}
}
