package com.sahaj.interview.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.sahaj.interview.PowerController.PowerController;
import com.sahaj.interview.builder.HotelBuilder;
import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.motion.Motion.MotionPlace;

public class InputParserTest {
	InputParser ip=null;
	private Hotel hotel=null;
	PowerController pc=null;
	private static final int numOfFloors=1;
	private static final int numOfMc=2;
	private static final int numOfSc=5;
	
	@Before
	public void setup() throws Exception {
		ip=new InputParser();
		HotelBuilder hb=new HotelBuilder();
		hb.addBuilding();
		hb.addFloors(numOfFloors);
		hb.addCorridors(numOfMc, numOfSc);
		hotel=hb.build();
		pc=new PowerController(hotel);
		/*
		 * Initialize the hotel Equipments.
		 * Main Corridor Lights and ACs should be Switched ON
		 * Sub Corridor Lights should be Switched OFF and
		 * Sub Corridor ACs should be switched ON.
		 */
		pc.setHotelInitialState();
	}
	
	@Test
	public void testParseSenInput() throws Exception{
		String s1="movement detected in floor 1, sub corridor 2";
		String s2="no movement detected in floor 2, sub corridor 3";
		String s3="no movement detected in floor 2";
		//MotionPlace object corresponding to s1
		MotionPlace mp1=new MotionPlace(1, 1, 2, true);
		//MotionPlace object corresponding to s2
		MotionPlace mp2=new MotionPlace(1, 2, 3, false);
		
		MotionPlace resultMp1=ip.parseSenInput(s1, hotel);
		assertNotNull(resultMp1);
		assertEquals("Motion place object returned is wrong", mp1, resultMp1);
		
		MotionPlace resultMp2=ip.parseSenInput(s2, hotel);
		assertNotNull(resultMp2);
		assertEquals("Motion place object returned is wrong", mp2, resultMp2);
		
		MotionPlace resultMp3=ip.parseSenInput(s3, hotel);
		assertNull(resultMp3);

	}
	
}
