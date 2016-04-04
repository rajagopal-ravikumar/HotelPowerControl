package com.sahaj.interview.entity;

import java.util.ArrayList;
import java.util.List;

import com.sahaj.interview.equipment.AirConditioner;
import com.sahaj.interview.equipment.Equipment;
import com.sahaj.interview.equipment.Light;
import com.sahaj.interview.equipment.Equipment.State;

/**
 * Represents the sub corridor object within a floor.
 * 
 * @author raj
 * 
 */
public class SubCorridor implements Corridor{
	private List<Light> lightList=null;
	private List<AirConditioner> airConditionerList=null;
	private int corridorNum;
	public SubCorridor(int corridorNum){
		this.corridorNum=corridorNum;
		Light light=new Light(1);
		AirConditioner airCond=new AirConditioner(1);
		lightList=new ArrayList<Light>();
		lightList.add(light);
		airConditionerList=new ArrayList<AirConditioner>();
		airConditionerList.add(airCond);
	}
	public SubCorridor(List<Light> lightList, List<AirConditioner> airConditionerList) {
		this.lightList=lightList;
		this.airConditionerList=airConditionerList;
	}
	
	public List<AirConditioner> getAirConditionerList() {
		return airConditionerList;
	}
	public int getCorridorNum() {
		return corridorNum;
	}
	public List<Light> getLightList() {
		return lightList;
	}
	
	/* (non-Javadoc)
	 * @see com.sahaj.interview.entity.Corridor#initializeAllEquipments()
	 */
	@Override
	public void initializeAllEquipments() {
		for(Equipment e: lightList){
			e.switchOFF();
		}
		for(Equipment e: airConditionerList){
			e.switchON();
		}
		
	}
	
	/**
	 * @param lightNum
	 *            Turn the equipment state of the light object corresponding to
	 *            lightNum to State.OFF
	 */
	public void turnOffLight(int lightNum){
		this.getLight(lightNum).setEquipmentState(State.OFF);
	}
	
	/**
	 * @param lightNum
	 *            Turn the equipment state of the light object corresponding to
	 *            lightNum to State.ON
	 */
	public void turnOnLight(int lightNum){
		this.getLight(lightNum).setEquipmentState(State.ON);
	}
	
	/**
	 * @param acNum
	 *            Turn the equipment state of the AC object corresponding to
	 *            acNum to State.OFF
	 */
	public void turnOffAC(int acNum){
		this.getAc(acNum).setEquipmentState(State.OFF);
	}
	
	/**
	 * @param acNum
	 *            Turn the equipment state of the AC object corresponding to
	 *            acNum to State.ON
	 */
	
	public void turnOnAC(int acNum){
		this.getAc(acNum).setEquipmentState(State.ON);
	}

	/* (non-Javadoc)
	 * @see com.sahaj.interview.entity.Corridor#shutdownAllEquipments()
	 */
	@Override
	public void shutdownAllEquipments() {
		for(Equipment e: lightList){
			e.switchOFF();
		}
		for(Equipment e: airConditionerList){
			e.switchOFF();
		}
		
	}

	/**
	 * @return the number of lights turned on in this sub corridor currently.
	 */
	public int getNumOfLightsTurnedOn(){
		int numOfLightsTurnedOn=0;
		for(Equipment eq : this.getLightList()){
			if(eq.getEquipmentState()==State.ON){
				numOfLightsTurnedOn++;
			}
		}
		return numOfLightsTurnedOn;
	}

	/**
	 * @return the number of ACs turned on in this sub corridor currently.
	 */
	
	public int getNumOfACsTurnedOn(){
		int numOfACsTurnedOn=0;
		for(Equipment eq : this.getAirConditionerList()){
			if(eq.getEquipmentState()==State.ON){
				numOfACsTurnedOn++;
			}
		}
		return numOfACsTurnedOn;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("	Sub Corridor "+this.corridorNum);
		for(int i=0;i<lightList.size();i++){
			sb.append("\n");
			sb.append("		Light "+lightList.get(i).getlNum()+" : "+lightList.get(i).getEquipmentState());
		}
		for(int i=0;i<airConditionerList.size();i++){
			sb.append("\n");
			sb.append("		AC "+airConditionerList.get(i).getaNum()+" : "+airConditionerList.get(i).getEquipmentState());
		}
		return sb.toString();
	}
	
	/**
	 * @param lightNum
	 * @return the light object corresponding to the given lightNum
	 */
	public Light getLight(int lightNum){
		for(Light l: this.lightList){
			if(l.getlNum()==lightNum){
				return l;
			}
		}
		return null;
	}
	
	/**
	 * @param aNum
	 * @return the AC object corresponding to the given aNum.
	 */
	public AirConditioner getAc(int aNum){
		for(AirConditioner ac: this.airConditionerList){
			if(ac.getaNum()==aNum){
				return ac;
			}
		}
		return null;
	}
	
	
}
