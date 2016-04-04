package com.sahaj.interview.Motion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.sahaj.interview.PowerController.PowerController;
import com.sahaj.interview.builder.HotelBuilder;
import com.sahaj.interview.criteria.PowerCriteria;
import com.sahaj.interview.entity.Floor;
import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.entity.SubCorridor;
import com.sahaj.interview.equipment.Equipment.State;
import com.sahaj.interview.motion.Motion;
import com.sahaj.interview.motion.Motion.MotionPlace;

public class MotionTest {
	private Hotel hotel=null;
	PowerController pc=null;
	PowerCriteria pcrit=null;
	Floor f=null;
	private static final int numOfFloors=1;
	private static final int numOfMc=2;
	private static final int numOfSc=5;
	private Motion m=null;
	@Before
	public void setup() throws Exception {
		HotelBuilder hb=new HotelBuilder();
		hb.addBuilding();
		hb.addFloors(numOfFloors);
		hb.addCorridors(numOfMc, numOfSc);
		hotel=hb.build();
		pc=new PowerController(hotel);	
		pcrit=new PowerCriteria();
	
		/*
		 * Initialize the hotel Equipments.
		 * Main Corridor Lights and ACs should be Switched ON
		 * Sub Corridor Lights should be Switched OFF and
		 * Sub Corridor ACs should be switched ON.
		 */
		pc.setHotelInitialState();
		f=hotel.getBuildingList().get(0).getFloorList().get(0);
		m=new Motion(pc);
	}
	
	
	@Test
	public void instTest() throws Exception{
		assertNotNull(m);		
	}
	
	@Test
	public void instMotionPlaceTest() throws Exception{
		MotionPlace mp= new MotionPlace(1, 1, 2, true);
		assertNotNull(mp);		
	}
	
	@Test
	public void testSetMp() throws Exception{
		
		//Building 1, Floor 1, Sub Corridor 2 motion detected
		MotionPlace mp= new MotionPlace(1, 1, 2, true);
		m.setMp(mp);
		
		//Now test whether sub corridor 2 light is turned on.
		SubCorridor sc2=f.getSubCorridor(2);
		assertEquals("Light not turned on in sub corridor 2 after motion detected", 
				State.ON , sc2.getLight(1).getEquipmentState());
		
	}
}
