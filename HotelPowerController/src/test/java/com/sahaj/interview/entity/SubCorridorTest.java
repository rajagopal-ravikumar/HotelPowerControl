package com.sahaj.interview.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.interview.PowerController.PowerController;
import com.sahaj.interview.builder.HotelBuilder;
import com.sahaj.interview.equipment.Equipment.State;

public class SubCorridorTest {
	private Hotel hotel=null;	
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
		pc.setHotelInitialState();
		f=hotel.getBuildingList().get(0).getFloorList().get(0);
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void instTest() throws Exception{
		SubCorridor sc=new SubCorridor(1);
		assertNotNull(sc);
		assertEquals("Wrong number of lights", 1, sc.getLightList().size());
		assertEquals("Wrong number of air conditioners", 1, sc.getAirConditionerList().size());		
	}
	
	@Test
	public void testGetNumOfEquipmentsTurnedOn() throws Exception{
		SubCorridor sc=f.getSubCorridor(1);
		assertEquals("Wrong number of lights turned on", 0 , sc.getNumOfLightsTurnedOn());
		assertEquals("Wrong number of ACs turned on", 1, sc.getNumOfACsTurnedOn());
	}
	
	@Test
	public void testInitializeAllEquipments() throws Exception{
		SubCorridor sc2=f.getSubCorridor(2);
		sc2.initializeAllEquipments();
		assertEquals("Wrong number of lights turned on", 0 , sc2.getNumOfLightsTurnedOn());
		assertEquals("Wrong number of ACs turned on", 1, sc2.getNumOfACsTurnedOn());		
	}
	
	@Test
	public void testShutdownAllEquipments() throws Exception{
		SubCorridor sc2=f.getSubCorridor(2);
		sc2.shutdownAllEquipments();
		assertEquals("Wrong number of lights turned on", 0 , sc2.getNumOfLightsTurnedOn());
		assertEquals("Wrong number of ACs turned on", 0, sc2.getNumOfACsTurnedOn());	
	}
	
	@Test
	public void testGetLight() throws Exception{
		SubCorridor sc=f.getSubCorridor(1);
		assertEquals("Wrong light returned", 1 , sc.getLight(1).getlNum());
	}
	
	@Test
	public void testGetAc() throws Exception{
		SubCorridor sc=f.getSubCorridor(1);
		assertEquals("Wrong AC returned", 1 , sc.getAc(1).getaNum());
	}
	
	@Test
	public void testSwitchEquipment() throws Exception{
		SubCorridor sc=f.getSubCorridor(1);
		sc.turnOffAC(1);
		sc.turnOnLight(1);
		assertEquals("AC not turned off in sub corridor 1", State.OFF, sc.getAc(1).getEquipmentState());	
		assertEquals("Light not turned on in sub corridor 1", State.ON, sc.getLight(1).getEquipmentState());
		sc=f.getSubCorridor(2);
		sc.turnOffLight(1);
		sc.turnOnAC(1);
		assertEquals("AC not turned on in sub corridor 2", State.ON, sc.getAc(1).getEquipmentState());	
		assertEquals("Light not turned off in sub corridor 2", State.OFF, sc.getLight(1).getEquipmentState());
	}

}
