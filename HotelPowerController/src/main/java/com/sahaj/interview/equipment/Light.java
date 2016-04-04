package com.sahaj.interview.equipment;

/**
 * Represents the light equipment.
 * @author raj
 *
 */
public class Light extends Equipment {
	private int lNum;
	public int getlNum() {
		return lNum;
	}
	
	public Light(int lNum) {
		this.lNum=lNum;
		this.equipmentState=State.OFF;
	}
	public Light(int lNum, State equipmentState) {
		this.lNum=lNum;
		this.equipmentState=equipmentState;
	}
	
}
