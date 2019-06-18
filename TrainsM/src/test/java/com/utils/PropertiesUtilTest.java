package com.utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class PropertiesUtilTest {

	@Test
	public void testReadProperties() {
		try {
			PropertiesUtil.readProperties("stationRoute.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetProperty() {
		System.out.println(PropertiesUtil.getProperty("stationRoute2"));
	}

	@Test
	public void testgetProps() {
		try {
			PropertiesUtil.readProperties("stationRoute.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> list=PropertiesUtil.getProps();
		for (String val : list) {
			System.out.println(val);
		}
		
	}
	
}
