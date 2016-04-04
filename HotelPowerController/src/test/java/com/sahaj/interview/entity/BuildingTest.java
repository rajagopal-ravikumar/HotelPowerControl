package com.sahaj.interview.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BuildingTest {
	@Test
	public void testBuildingInst() throws Exception{
		Building b=new Building();
		assertNotNull(b);
		b=new Building("A", 1, 2);
		assertEquals("Wrong building name", "A" , b.getName());
		assertEquals("Wrong building number", 1 , b.getBNum());
	}
}
