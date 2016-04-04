package com.sahaj.interview.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sahaj.interview.entity.Building;
import com.sahaj.interview.entity.Floor;
import com.sahaj.interview.entity.Hotel;

public class HotelBuilderTest {
	private HotelBuilder hb=new HotelBuilder();
	
	@Test
	public void addBuildingTest(){
		hb.addBuilding();
		Hotel ht=hb.build();
		assertEquals("Building not added to hotel by builder", 1, ht.getBuildingList().size());
		assertNotNull(ht.getBuildingList().get(0));
	}
	
	@Test
	public void addFloorTest(){
		hb.addBuilding();
		hb.addFloors(1);
		Hotel ht=hb.build();
		Building b=ht.getBuildingList().get(0);
		assertEquals("Floor not added to hotel by builder", 1, b.getFloorList().size());
		assertNotNull(b.getFloorList().get(0));
	}
	
	@Test
	public void addCorridorsTest(){
		hb.addBuilding();
		hb.addFloors(1);
		hb.addCorridors(1, 1);
		Hotel ht=hb.build();
		Building b=ht.getBuildingList().get(0);
		Floor f=b.getFloorList().get(0);
		assertEquals("Main Corridor not added to floor by builder", 1, f.getMainCorridorList().size());
		assertNotNull(f.getMainCorridorList().get(0));
		assertEquals("Sub Corridor not added to floor by builder", 1, f.getSubCorridorList().size());
		assertNotNull(f.getSubCorridorList().get(0));
	}
	
	
}
