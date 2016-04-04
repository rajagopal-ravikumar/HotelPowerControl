package com.sahaj.interview.entity;

import java.util.ArrayList;
import java.util.List;

import com.sahaj.interview.equipment.Equipment.State;

/**
 * Represents the floor within a building in the hotel.
 * 
 * @author raj
 * 
 */
public class Floor {
	private int floorNum;
	private List<MainCorridor> mainCorridorList=null;
	private List<SubCorridor> subCorridorList=null;	
	public Floor(int floorNum){
		this.floorNum=floorNum;
		mainCorridorList=new ArrayList<MainCorridor>();
		subCorridorList=new ArrayList<SubCorridor>();
	}
	public int getFloorNum() {
		return floorNum;
	}
	public List<MainCorridor> getMainCorridorList() {
		return mainCorridorList;
	}
	public List<SubCorridor> getSubCorridorList() {
		return subCorridorList;
	}	
	public void setMainCorridorList(List<MainCorridor> mainCorridorList) {
		this.mainCorridorList = mainCorridorList;
	}
	public void setSubCorridorList(List<SubCorridor> subCorridorList) {
		this.subCorridorList = subCorridorList;
	}
	
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("\n");
		sb.append("Floor "+this.getFloorNum());
		for(MainCorridor mc: this.getMainCorridorList()){
			sb.append("\n");
			sb.append(mc.toString());
		}
		for(SubCorridor sc: this.getSubCorridorList()){
			sb.append("\n");
			sb.append(sc.toString());
		}
		return sb.toString();
	}
	
	
	/**
	 * @param sNum
	 * @return the alternate sub corridor number where AC can be switched OFF to
	 *         save energy and meet the power consumption criteria.
	 */
	public int returnAlternateSubCorridorToSaveEnergy(int sNum){
		if(this.getSubCorridorList().size()==1){
			return sNum;
		}
		for(SubCorridor sc: this.getSubCorridorList()){
			if(sc.getCorridorNum()==sNum){
				continue;
			}
			else{
				if(sc.getAirConditionerList().get(0).getEquipmentState()==State.ON){
					return sc.getCorridorNum();
				}
			}
		}
		return 0;
	}
	
	
	/**
	 * @param mcNum
	 * @return the main corridor object corresponding to mcNum.
	 */
	public MainCorridor getMainCorridor(int mcNum){
		for(MainCorridor mc : this.getMainCorridorList()){
			if(mc.getCorridorNum()==mcNum){
				return mc;
			}
		}
		return null;		
		
	}
	
	/**
	 * @param scNum
	 * @return the sub corridor object corresponding to scNum.
	 */
	public SubCorridor getSubCorridor(int scNum){
		for(SubCorridor sc : this.getSubCorridorList()){
			if(sc.getCorridorNum()==scNum){
				return sc;
			}
		}
		return null;		
		
	}
}
