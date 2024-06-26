package com.bruce.utils;

import com.bruce.constant.RelayConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author 吕胜 lvheng1
 * @date 2022/3/10
 **/
public class LinkIdUtils {
	
	/**
	 * 生成一个新的link_id
	 *
	 * @return
	 */
	public static String generate() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获取当前线程的link_id，如果没有，则新建
	 *
	 * @return
	 */
	public static String getLinkIdOfCurrentThread() {
		String linkId = MDC.get(RelayConstants.LINK_ID);
		if (org.apache.commons.lang3.StringUtils.isBlank(linkId)) {
			linkId = LinkIdUtils.generate();
			MDC.put(RelayConstants.LINK_ID, linkId);
		}
		return linkId;
	}
	
	/**
	 * 将linkId写入到线程上下文
	 *
	 * @param linkId
	 */
	public static void putLinkId(String linkId) {
		if (StringUtils.isBlank(linkId)) {
			linkId = LinkIdUtils.generate();
		}
		MDC.put(RelayConstants.LINK_ID, linkId);
	}
}
