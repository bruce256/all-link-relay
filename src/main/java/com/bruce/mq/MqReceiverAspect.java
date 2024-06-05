package com.bruce.mq;

import com.bruce.constant.RelayConstants;
import com.bruce.saas.sql.mybatis.MerchantIdContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 收消息的时候，把merchantId写入threadlocal
 *
 * @author 吕胜 lvheng1
 * @date 2022/3/8
 **/
@Slf4j
@Aspect
public class MqReceiverAspect {
	
	@Before("execution(* com.midea.finance.framework.message.selector.MQMessageHandler.handlerMessage(..))")
	public void add(JoinPoint joinPoint) {
		try {
			MessageExt message = (MessageExt) joinPoint.getArgs()[0];
			
			MerchantIdContext.put(message.getProperties().get(RelayConstants.MERCHANT_ID));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@After("execution(* com.midea.finance.framework.message.selector.MQMessageHandler.handlerMessage(..))")
	public void removeMerchantId(JoinPoint joinPoint) {
		// 防止内存泄漏
		MerchantIdContext.remove();
	}
}
