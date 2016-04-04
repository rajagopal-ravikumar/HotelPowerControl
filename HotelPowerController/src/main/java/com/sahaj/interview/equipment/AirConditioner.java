package com.sahaj.interview.equipment;


/**
 * Represents the air conditioner equipment
 * 
 * @author raj
 * 
 */
public class AirConditioner extends Equipment {	
	private int aNum;
	public int getaNum() {
		return aNum;
	}

	public AirConditioner(int aNum) {
		this.aNum=aNum;
		this.equipmentState=State.OFF;
	}
	public AirConditioner(int aNum, State equipmentState) {
		this.aNum=aNum;
		this.equipmentState=equipmentState;
	}
		
}
