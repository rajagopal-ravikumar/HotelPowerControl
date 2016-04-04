package com.sahaj.interview.entity;


/** Interface to represent corridors in a floor.
 * Corridors can be of two types : Main Corridor
 * and sub corridor.
 * 
 * @author raj
 *
 */
public interface Corridor {
	/**
	 * Initialize all equipments in this corridor.
	 * If its a sub corridor, Lights need to be switched OFF and ACs need to be switched ON
	 * If its a main corridor, Lights and ACs need to be switched ON.
	 */
	public void initializeAllEquipments();
	/**
	 * Shutdown all equipments in this corridor.
	 * If its a sub corridor, Lights and ACs need to be Switched OFF
	 * If its a main corridor, Lights and ACs need to be switched OFF
	 */
	public void shutdownAllEquipments();
}
