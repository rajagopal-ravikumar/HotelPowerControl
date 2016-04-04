package com.sahaj.interview.PowerController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.interview.builder.HotelBuilder;
import com.sahaj.interview.entity.Floor;
import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.entity.MainCorridor;
import com.sahaj.interview.entity.SubCorridor;
import com.sahaj.interview.equipment.AirConditioner;
import com.sahaj.interview.equipment.Equipment;
import com.sahaj.interview.equipment.Equipment.State;
import com.sahaj.interview.equipment.Light;
import com.sahaj.interview.motion.Motion;
import com.sahaj.interview.motion.Motion.MotionPlace;


public class PowerControllerTest {
	
	private Hotel hotel=null;
	PowerController pc=null;
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
	public void initializeTest() throws Exception {		
		/*
		 * Check if the hotel is built using the specs
		 * numOfBuildings, numOfFloors, numOfMc, numOfSc
		 */
		assertNotNull("Hotel object not initialized",hotel);
		assertEquals("hotel not initialized with correct number of buildings", 1 , hotel.getBuildingList().size() );
		assertEquals("hotel not initialized with correct number of floors", 1 , hotel.getBuildingList().get(0).getFloorList().size() );
		assertEquals("hotel not initialized with correct number of Main Corridors", 2 , hotel.getBuildingList().get(0).
				getFloorList().get(0).getMainCorridorList().size() );
		assertEquals("hotel not initialized with correct number of Sub Corridors", 5 , hotel.getBuildingList().get(0).
				getFloorList().get(0).getSubCorridorList().size() );
		
		//Now check the equipment state in main corridor 
		//followed by sub corridor
		List<Floor> floorList=new ArrayList<Floor>();
		floorList=hotel.getBuildingList().get(0).getFloorList();
		for(Floor f: floorList){
			List<MainCorridor> mcList=new ArrayList<MainCorridor>();
			mcList=f.getMainCorridorList();
			for(MainCorridor mc: mcList){
				List<Light> lightList=new ArrayList<Light>();
				lightList=mc.getLightList();
				for(Equipment l: lightList){
					assertEquals("Light not switched ON in initial hotel state for "+
							"Floor "+f.getFloorNum()+" Main Corridor"+mc.getCorridorNum(),
							State.ON, l.getEquipmentState());
				}
				List<AirConditioner> acList=new ArrayList<AirConditioner>();
				acList=mc.getAirConditionerList();
				for(Equipment ac: acList){
					assertEquals("AC not switched ON in initial hotel state for "+
							"Floor "+f.getFloorNum()+" Main Corridor"+mc.getCorridorNum(),
							State.ON, ac.getEquipmentState());
				}				
			}
			List<SubCorridor> scList=new ArrayList<SubCorridor>();
			mcList=f.getMainCorridorList();
			for(SubCorridor sc: scList){
				List<Light> lightList=new ArrayList<Light>();
				lightList=sc.getLightList();
				for(Equipment l: lightList){
					assertEquals("Light not switched OFF in initial hotel state for "+
							"Floor "+f.getFloorNum()+" Sub Corridor"+sc.getCorridorNum(),
							State.OFF, l.getEquipmentState());
				}
				List<AirConditioner> acList=new ArrayList<AirConditioner>();
				acList=sc.getAirConditionerList();
				for(Equipment ac: acList){
					assertEquals("AC not switched ON in initial hotel state for "+
							"Floor "+f.getFloorNum()+" Sub Corridor"+sc.getCorridorNum(),
							State.ON, ac.getEquipmentState());
				}				
			}
		}
	}
	
	@Test
	public void testUpdate() throws Exception{
		//Movement in Building 1, Floor1, Sub Corridor 2
		Motion m=new Motion(pc);
		MotionPlace mp=new MotionPlace(1, 1, 2, true);
		m.setMp(mp);	
		assertEquals("Light not switched ON in floor 1, Sub corridor 2 after motion detected", State.ON,
					hotel.getBuildingList().get(0).getFloorList().get(0).getSubCorridorList().get(1).
					getLightList().get(0).getEquipmentState());
		//Lack of Movement in Building 1, Floor1, Sub Corridor 2
		mp=new MotionPlace(1, 1, 2, false);
		m.setMp(mp);		
		assertEquals("Light not switched OFF in floor 1, Sub corridor 2 after no motion detected for a minute", State.OFF,
				hotel.getBuildingList().get(0).getFloorList().get(0).getSubCorridorList().get(1).
				getLightList().get(0).getEquipmentState());		
	}
	
	@Test
	public void testSwitchOnSubCorridorLight() throws Exception{
		MotionPlace mp=new MotionPlace(1, 1, 2, true);
		Floor f=hotel.getBuildingList().get(0).getFloorList().get(0);
		SubCorridor sc2=f.getSubCorridorList().get(1);
		SubCorridor sc3=f.getSubCorridorList().get(2);
		SubCorridor sc1=f.getSubCorridorList().get(0);
		SubCorridor sc4=f.getSubCorridorList().get(3);
		SubCorridor sc5=f.getSubCorridorList().get(4);
		
		//Switch ON Sub Corridor 2 light due to motion detection
		pc.switchOnSubCorridorLight(mp);
		//Verify if Sub corridor 2 light is switched ON
		assertEquals("Light not switched ON in floor 1, Sub corridor 2 after motion detected",
					State.ON, sc2.getLightList().get(0).getEquipmentState() );
		
		//Verify if Sub Corridor 1 AC is switched OFF to keep power consumption within limit
		assertEquals("AC not switched OFF in floor 1, Sub corridor 1 to keep power consumption within limit",
					State.OFF, sc1.getAirConditionerList().get(0).getEquipmentState());
		
		mp=new MotionPlace(1, 1, 3, true);
		
		//Switch ON Sub Corridor 3 light due to motion detection
		pc.switchOnSubCorridorLight(mp);
		//Verify if Sub corridor 3 light is switched ON
		assertEquals("Light not switched ON in floor 1, Sub corridor 3 after motion detected",
				     State.ON, sc3.getLightList().get(0).getEquipmentState());
		
		//No ACs should be switched OFF by the above method call, because power consumption is within limits.
		assertEquals("AC  switched OFF in floor 1, Sub corridor 4 even when power consumption is within limits",
					State.ON, sc4.getAirConditionerList().get(0).getEquipmentState() );
		assertEquals("AC  switched OFF in floor 1, Sub corridor 5 even when power consumption is within limits",
					State.ON, sc5.getAirConditionerList().get(0).getEquipmentState());		
		
	}
	
	@Test
	public void testSwitchOffSubCorridorLight() throws Exception{
		MotionPlace mp=new MotionPlace(1, 1, 2, true);
		Floor f=hotel.getBuildingList().get(0).getFloorList().get(0);
		SubCorridor sc2=f.getSubCorridorList().get(1);
		SubCorridor sc3=f.getSubCorridorList().get(2);
		SubCorridor sc1=f.getSubCorridorList().get(0);
			
		//Switch OFF Sub Corridor 2 light due to lack off motion 
		pc.switchOffSubCorridorLight(mp);
		
		//Verify if Sub corridor 2 light is switched OFF
		assertEquals("Light not switched OFF in floor 1, Sub corridor 2 after lack of motion",
						State.OFF, sc2.getLightList().get(0).getEquipmentState());
		
		
		mp=new MotionPlace(1, 1, 3, true);
		
		//Switch OFF Sub Corridor 3 light due to lack of motion
		pc.switchOffSubCorridorLight(mp);
		
		//Verify if Sub corridor 3 light is switched OFF
		assertEquals("Light not switched OFF in floor 1, Sub corridor 3 after lack of motion",
				 		State.OFF, sc3.getLightList().get(0).getEquipmentState());
		
		/*
		 * Sub Corridor 1 AC which was switched OFF earlier to keep
		 * power consumption within limits should be switched ON
		 * now.
		 */
		assertEquals("AC  not switched ON in floor 1, Sub corridor 1 even when power consumption is underutilized",
						State.ON, sc1.getAirConditionerList().get(0).getEquipmentState());			
		
	}
}
