package com.sahaj.interview.Criteria;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.interview.PowerController.PowerController;
import com.sahaj.interview.builder.HotelBuilder;
import com.sahaj.interview.criteria.PowerCriteria;
import com.sahaj.interview.entity.Floor;
import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.motion.Motion.MotionPlace;

public class PowerCriteriaTest {

	private Hotel hotel=null;
	PowerController pc=null;
	PowerCriteria pcrit=null;
	Floor f=null;
	private static final int numOfFloors=1;
	private static final int numOfMc=2;
	private static final int numOfSc=5;
	
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
	}
	
	@Test
	public void testCurrentMCPowConsumption() throws Exception{
		assertEquals("Current Power consumption calculation is wrong for Main Corridors", 
				30, pcrit.currentMCPowConsumption(f));
		
	}
	
	@Test
	public void testCurrentSCPowConsumption() throws Exception{
		assertEquals("Current Power consumption calculation is wrong for Sub Corridors", 
				50, pcrit.currentSCPowConsumption(f));
		MotionPlace mp=new MotionPlace(1, 1, 2, true);
		pc.switchOnSubCorridorLight(mp);
		assertEquals("Current Power consumption calculation is wrong for Sub Corridors", 
				45, pcrit.currentSCPowConsumption(f));
		pc.switchOffSubCorridorLight(mp);
		
	}
	
	@Test
	public void testCurrentPowConsumptionOfFloor() throws Exception{
		assertEquals("Current Power consumption calculation is wrong for Floor", 
				80, pcrit.currentPowConsumptionOfFloor(f));
		
	}
	
	@Test
	public void testMaxLimitForSubCorridor() throws Exception{
		assertEquals("Max power consumption limit for sub corridors calculation is wrong",
						50, pcrit.maxLimitForSubCorridor(f));
	}
	
	@Test
	public void testMaxLimitForMainCorridor() throws Exception{
		assertEquals("Max power consumption limit for main corridors calculation is wrong",
						30, pcrit.maxLimitForMainCorridor(f));
	}
	
	@Test
	public void testMaxLimitForFloor() throws Exception{
		assertEquals("Max power consumption limit for floor calculation is wrong",
						80, pcrit.maxLimitForFloor(f));
	}
	
	@Test
	public void testIsUnderUtilized() throws Exception{
		assertEquals("Under Utilization calculation is wrong", false, pcrit.isUnderUtilized(f));
		MotionPlace mp=new MotionPlace(1, 1, 2, true);
		pc.switchOnSubCorridorLight(mp);		
		assertEquals("Under Utilization calculation is wrong", false, pcrit.isUnderUtilized(f));
		pc.switchOffSubCorridorLight(mp);		
	}
	
	@Test
	public void testIsCriteriaMetForFloor() throws Exception{
		assertEquals("Max limit criteria for floor calculation is wrong", 
				true, pcrit.isCriteriaMetForFloor(f));
	}
	
	@Test
	public void testIsCriteriaMetForBuilding() throws Exception{
		assertEquals("Max limit criteria for building calculation is wrong", 
				true, pcrit.isCriteriaMetForBuilding(hotel.getBuildingList().get(0)));
	}
}
