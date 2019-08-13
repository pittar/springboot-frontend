package ca.pitt.demo.spring.rest.frontend.values;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RandomValueTest {

	@Test
	public void testGetter() {
		RandomValue rv = new RandomValue(7);
		assertEquals("Should get 7,", Integer.valueOf(7), rv.getValue());
	}

}
