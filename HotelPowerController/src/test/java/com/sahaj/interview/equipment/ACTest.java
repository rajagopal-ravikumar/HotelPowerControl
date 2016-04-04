package com.sahaj.interview.equipment;

import org.junit.Test;

import com.sahaj.interview.equipment.Equipment.State;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ACTest {
	@Test
	public void instTest() throws Exception{
		AirConditioner ac=new AirConditioner(1);
		assertNotNull(ac);
		assertEquals("ac number is wrong", 1, ac.getaNum());
		assertEquals("ac state is wrong", State.OFF, ac.getEquipmentState());
	}
}
