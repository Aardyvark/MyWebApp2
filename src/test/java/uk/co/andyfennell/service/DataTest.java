package uk.co.andyfennell.service;

import static org.junit.Assert.*;

import org.junit.Test;


public class DataTest {

	@Test
	public void test() {
		DataSimple data = new DataSimple();
		assertEquals("Hello there", data.getMessage());
	}

	@Test
	public void testGetCountries() {
		DataSimple data = new DataSimple();
		assertTrue(!data.getCountries().equals(null));
		assertTrue(data.getCountries().length != 0);
		assertEquals("England", data.getCountries()[0]);
	}
	
	@Test
	public void testGetCountryList() {
		DataSimple data = new DataSimple();
		assertTrue(null != data.getCountryList());
	}
	
}
