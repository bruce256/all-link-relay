package com.bruce.mq;

import com.bruce.constant.RelayConstants;
import com.bruce.saas.sql.mybatis.MerchantIdContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 发消息的时候带上merchantId
 *
 * @author 吕胜 lvheng1
 * @date 2022/3/8
 **/
@Slf4j
@Aspect
public class MqSenderAspect {
	
	@Before("execution(* com.midea.finance.framework.message.producer.MideaDefaultMQProducer.send*(..))")
	public void add(JoinPoint joinPoint) {
		Message message = (Message) joinPoint.getArgs()[0];
		
		addMerchantId(message);
	}
	
	private void addMerchantId(Message message) {
		message.getProperties().put(RelayConstants.MERCHANT_ID, MerchantIdContext.getMerchantId());
	}
	
}
