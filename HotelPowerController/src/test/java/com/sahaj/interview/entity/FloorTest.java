package com.sahaj.interview.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.interview.PowerController.PowerController;
import com.sahaj.interview.builder.HotelBuilder;

public class FloorTest {
	private Floor f=null;
	private Hotel hotel=null;
	private PowerController pc=null;
	
	@Before
	public void setup() throws Exception{
		f=new Floor(1);
		SubCorridor sc1=new SubCorridor(1);
		SubCorridor sc2=new SubCorridor(2);
		SubCorridor sc3=new SubCorridor(3);
		List<SubCorridor> scList=new ArrayList<SubCorridor>();
		scList.add(sc1);
		scList.add(sc2);
		scList.add(sc3);
		MainCorridor mc1=new MainCorridor(1);
		MainCorridor mc2=new MainCorridor(2);
		List<MainCorridor> mcList=new ArrayList<MainCorridor>();
		mcList.add(mc1);
		mcList.add(mc2);
		f.setMainCorridorList(mcList);
		f.setSubCorridorList(scList);
		HotelBuilder hb=new HotelBuilder();
		hb.addBuilding();
		hb.addSingleFloor(1, f);
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
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testFloorInst() throws Exception{	
		assertNotNull(f);
		assertEquals("Main Corridor list size is wrong in the floor", 2, f.getMainCorridorList().size());
		assertEquals("Sub Corridor list size is wrong in the floor", 3, f.getSubCorridorList().size());
	}
	
	@Test
	public void testAlternateSubCorridorToSaveEnergy() throws Exception{
		assertEquals("Wrong alternate sub corridor number returned",
				2,f.returnAlternateSubCorridorToSaveEnergy(1));
		assertEquals("Wrong alternate sub corridor number returned",
				1,f.returnAlternateSubCorridorToSaveEnergy(2));
		f.getSubCorridorList().remove(0);
		assertEquals("Wrong alternate sub corridor number returned",
				3,f.returnAlternateSubCorridorToSaveEnergy(2));
		assertEquals("Wrong alternate sub corridor number returned",
				2,f.returnAlternateSubCorridorToSaveEnergy(3));
		f.getSubCorridorList().remove(0);
		assertEquals("Wrong alternate sub corridor number returned",
				3,f.returnAlternateSubCorridorToSaveEnergy(3));
		
	}
	
	@Test
	public void testGetMainCorridor() throws Exception{
		assertEquals("Wrong main corridor returned", 2, f.getMainCorridor(2).getCorridorNum());
		assertEquals("Wrong main corridor returned", 1, f.getMainCorridor(1).getCorridorNum());

	}
	
	@Test
	public void testGetSubCorridor() throws Exception{
		assertEquals("Wrong sub corridor returned", 2, f.getSubCorridor(2).getCorridorNum());
		assertEquals("Wrong sub corridor returned", 1, f.getSubCorridor(1).getCorridorNum());
		assertEquals("Wrong sub corridor returned", 3, f.getSubCorridor(3).getCorridorNum());


	}
}
