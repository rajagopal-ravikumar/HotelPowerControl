package com.sahaj.interview.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.interview.PowerController.PowerController;
import com.sahaj.interview.builder.HotelBuilder;
import com.sahaj.interview.criteria.PowerCriteria;

public class MainCorridorTest {
	
	private Hotel hotel=null;
	private PowerCriteria pcrit=null;
	private PowerController pc=null;
	Floor f=null;
	private static final int numOfFloors=1;
	private static final int numOfMc=2;
	private static final int numOfSc=5;

	@Before
	public void setup() throws Exception{
		HotelBuilder hb=new HotelBuilder();
		hb.addBuilding();
		hb.addFloors(numOfFloors);
		hb.addCorridors(numOfMc, numOfSc);
		hotel=hb.build();
		pc=new PowerController(hotel);	
		pcrit=new PowerCriteria();
		pc.setHotelInitialState();
		f=hotel.getBuildingList().get(0).getFloorList().get(0);
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void instTest() throws Exception{
		MainCorridor mc=new MainCorridor(1);
		assertNotNull(mc);
		assertEquals("Wrong number of lights", 1, mc.getLightList().size());
		assertEquals("Wrong number of air conditioners", 1, mc.getAirConditionerList().size());		
	}
	
	@Test
	public void testGetNumOfEquipmentsTurnedOn() throws Exception{
		MainCorridor mc=f.getMainCorridor(1);
		assertEquals("Wrong number of lights turned on", 1 , mc.getNumOfLightsTurnedOn());
		assertEquals("Wrong number of ACs turned on", 1, mc.getNumOfACsTurnedOn());
	}
	
	@Test
	public void testInitializeAllEquipments() throws Exception{
		MainCorridor mc2=f.getMainCorridor(2);
		mc2.initializeAllEquipments();
		assertEquals("Wrong number of lights turned on", 1 , mc2.getNumOfLightsTurnedOn());
		assertEquals("Wrong number of ACs turned on", 1, mc2.getNumOfACsTurnedOn());		
	}
	
	@Test
	public void testShutdownAllEquipments() throws Exception{
		MainCorridor mc2=f.getMainCorridor(2);
		mc2.shutdownAllEquipments();
		assertEquals("Wrong number of lights turned on", 0 , mc2.getNumOfLightsTurnedOn());
		assertEquals("Wrong number of ACs turned on", 0, mc2.getNumOfACsTurnedOn());	
	}
	
	@Test
	public void testGetLight() throws Exception{
		MainCorridor mc=f.getMainCorridor(1);
		assertEquals("Wrong light returned", 1 , mc.getLight(1).getlNum());
	}
	
	@Test
	public void testGetAc() throws Exception{
		MainCorridor mc=f.getMainCorridor(1);
		assertEquals("Wrong AC returned", 1 , mc.getAc(1).getaNum());
	}
	
}
