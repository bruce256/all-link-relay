package com.bruce;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.MDC;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}
	
	@Test
	public void mdcTest() {
		String value = "value";
		MDC.put("key", value);
		
		new Thread(() -> {
			String v = MDC.get("key");
			System.out.println(v);
			Assert.assertTrue(StringUtils.equals(v, value));
		}).start();
	}
}
