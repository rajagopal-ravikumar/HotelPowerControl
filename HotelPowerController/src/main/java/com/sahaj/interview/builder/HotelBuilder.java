package com.sahaj.interview.builder;

import com.sahaj.interview.entity.Building;
import com.sahaj.interview.entity.Corridor;
import com.sahaj.interview.entity.Floor;
import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.entity.MainCorridor;
import com.sahaj.interview.entity.SubCorridor;


/**
 * @author raj 
 * Builder to build hotel object with buildings, floors, main
 *         corridors and sub corridors.
 */
public class HotelBuilder {
	private Hotel hotel;
	
	public HotelBuilder(){
		hotel=new Hotel();		
	}
	
	/**
	 * creates a building object and adds to the hotel object.
	 */
	public void addBuilding(){
		Building bldg=new Building();
		hotel.getBuildingList().add(bldg);
	}
	
	/**
	 * Creates numOfFloors floor objects and adds to the hotel building.
	 * 
	 * @param numOfFloors
	 */
	public void addFloors(int numOfFloors){
		int numOfBuildings=hotel.getBuildingList().size();
		for(int i=0;i<numOfBuildings;i++){
			for(int j=0;j<numOfFloors;j++){				
				Floor fl=new Floor(j+1);
				hotel.getBuildingList().get(i).getFloorList().add(fl);
			}		
		}		
	}
	/**Adds the given floor object to the building with building number bNum.
	 * @param bNum
	 * @param f
	 */
	public void addSingleFloor(int bNum, Floor f){
		hotel.getBuilding(bNum).getFloorList().add(f);
	}
	
	/**
	 * Adds numOfMainCorridor main corridors and numOfSubCorridor sub corridors
	 * to the hotel building.
	 * 
	 * @param numOfMainCorridor
	 * @param numOfSubCorridor
	 */
	public void addCorridors(int numOfMainCorridor, int numOfSubCorridor){
		for(Building b: hotel.getBuildingList()){
			for(Floor f: b.getFloorList()){
				for(int i=0;i<numOfMainCorridor;i++){
					MainCorridor mc=new MainCorridor(i+1);
					f.getMainCorridorList().add(mc);
				}
				for(int i=0;i<numOfSubCorridor;i++){
					SubCorridor sc=new SubCorridor(i+1);
					f.getSubCorridorList().add(sc);
				}
			}
		}
		
	}
	
	/**
	 * Returns the built hotel object
	 * 
	 * @return : hotel
	 */
	public Hotel build(){
		return hotel;
	}
}
