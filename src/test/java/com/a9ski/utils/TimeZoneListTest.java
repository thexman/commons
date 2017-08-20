package com.a9ski.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.a9ski.utils.TimeZoneList.NamedTimeZone;

public class TimeZoneListTest {

	@Test
	public void testGetInstance() {
		final NamedTimeZone tz = TimeZoneList.getInstance().getNamedTimeZone("Europe/Helsinki");
		assertEquals(tz, TimeZoneList.getInstance().getNamedTimeZone("Europe/Riga"));

		assertEquals("Europe/Sofia", TimeZoneList.getInstance().getNamedTimeZone("Europe/Sofia").getTimeZone().getID());
	}

}
