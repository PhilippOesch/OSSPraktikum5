package de.hfu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testIstErstesHalbjahr() {
		assertEquals(true, Util.istErstesHalbjahr(1));
		assertEquals(true, Util.istErstesHalbjahr(6));
		assertEquals(false, Util.istErstesHalbjahr(7));
		assertEquals(false, Util.istErstesHalbjahr(12));
	}
}
