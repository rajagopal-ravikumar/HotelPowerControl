package com.sahaj.interview.entity;

import java.util.ArrayList;
import java.util.List;

/** Represents the entity building within a hotel.
 * @author raj
 *
 */
public class Building {
	private String name;
	private int bNum;
	private List<Floor> floorList=null;
	private int numOfFloors;
	
	public Building(){
		name="Main Building";
		numOfFloors=2;
		bNum=1;
		floorList=new ArrayList<Floor>(numOfFloors);
	}
	
	public Building(String name, int bNum, int numOfFloors){
		this.name=name;
		this.bNum=bNum;
		this.numOfFloors=numOfFloors;
		floorList=new ArrayList<Floor>(numOfFloors);
	}
	
	public String getName() {
		return name;
	}
	public int getNumOfFloors() {
		return numOfFloors;
	}
	
	public List<Floor> getFloorList() {
		return floorList;
	}
	
	public int getBNum() {
		return bNum;
	}
	
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(Floor f: this.getFloorList()){
			sb.append(f.toString());
		}		
		return sb.toString();
	}
	
	/**
	 * @param floorNum
	 * @return the Floor Object corresponding to the given floor number from
	 *         this building.
	 */
	public Floor getFloor(int floorNum){
		for(Floor f : this.getFloorList()){
			if(f.getFloorNum()==floorNum){
				return f;
			}
		}
		return null;
	}
}
