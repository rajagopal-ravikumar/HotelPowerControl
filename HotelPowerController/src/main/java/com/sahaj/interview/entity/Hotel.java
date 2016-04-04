package com.sahaj.interview.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the hotel object containing buildings.
 * 
 * @author raj
 * 
 */
public class Hotel {
	public static final String name="Sahaj Hotels";
	private String city;
	private List<Building> buildingList;
	private int numOfBuildings;
	
	public Hotel(){
		city="Chennai";
		buildingList=new ArrayList<Building>();
		numOfBuildings=1;		
	}
	
	public Hotel(String city, int numOfBuildings){
		this.city=city;
		this.numOfBuildings=numOfBuildings;
		buildingList=new ArrayList<Building>(numOfBuildings);
	}
	
	public List<Building> getBuildingList() {
		return buildingList;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getNumOfBuildings() {
		return numOfBuildings;
	}
	
	/**
	 * @param bNum
	 * @return the building object corresponding to the given bNum.
	 */
	public Building getBuilding(int bNum){
		for (Building b : this.getBuildingList()){
			if(b.getBNum()==bNum){
				return b;
			}
		}
		return null;
	}
}
