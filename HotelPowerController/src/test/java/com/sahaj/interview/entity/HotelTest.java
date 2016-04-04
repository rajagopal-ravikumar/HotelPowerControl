package com.sahaj.interview.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sahaj.interview.builder.HotelBuilder;

public class HotelTest {
	
	@Test
	public void testHotelInst() throws Exception{
		Hotel h=new Hotel();
		HotelBuilder hb=new HotelBuilder();
		hb.addBuilding();
		h=hb.build();
		Building b=h.getBuilding(1);
		assertNotNull(h);
		assertNotNull(b);
		assertEquals("Wrong default number of buildings in the hotel ",
						1, h.getBuildingList().size());
		assertEquals("Wrong city name for hotel", "Chennai" , h.getCity());
		assertEquals("Wrong building number ", 1 , b.getBNum());
	}
}
