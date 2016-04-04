package com.sahaj.interview.equipment;

/**
 * Represents the equipments in corridors. Equipment can be of two types :
 * lights and AirConditioners.
 * 
 * @author raj
 * 
 */
public abstract class Equipment {
	protected State equipmentState;
	public static enum State{
		ON, OFF;
	}
	
	/**
	 * @return STATE.ON after changing the current equipment state of this
	 *         equipment to STATE.ON
	 */
	public State switchON(){
		this.equipmentState=State.ON;
		return this.equipmentState;
	}
	
	/**
	 * @return STATE.OFF after changing the current equipment state of this
	 *         equipment to STATE.OFF
	 */
	
	public State switchOFF(){
		this.equipmentState=State.OFF;
		return this.equipmentState;
	}
	public State getEquipmentState() {
		return equipmentState;
	}
	public void setEquipmentState(State equipmentState) {
		this.equipmentState = equipmentState;
	}
}
