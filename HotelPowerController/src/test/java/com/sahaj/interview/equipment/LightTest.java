package com.sahaj.interview.equipment;

import org.junit.Test;

import com.sahaj.interview.equipment.Equipment.State;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LightTest {
	
	@Test
	public void instTest() throws Exception{
		Light l=new Light(1);
		assertNotNull(l);
		assertEquals("light number is wrong", 1, l.getlNum());
		assertEquals("light state is wrong", State.OFF, l.getEquipmentState());
	}

}
