package com.sahaj.interview.motion;

import java.util.Observable;

import com.sahaj.interview.PowerController.PowerController;


/**
 * Represents the motion object which is observed by the power controller in
 * order to toggle the equipment state in the place where motion is detected.
 * 
 * @author raj
 * 
 */
public class Motion extends Observable {

	private PowerController pc=null;
	private MotionPlace mp=null;
	
	public Motion(PowerController pc) {		
		this.pc=pc;
		mp=new MotionPlace();
		this.addObserver(this.pc);
	}
	
	
	
	/**
	 * @param mp
	 *            Sets the motion place object (mp) of this motion object. Any
	 *            change to the motion place object notifies the power contoller
	 *            which is a observer of Motion.
	 */
	public void setMp(MotionPlace mp) {
		this.mp = mp;
		setChanged();
		notifyObservers();
	}
	public MotionPlace getMp() {
		return mp;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + mp.hashCode();				
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj==null || obj.getClass() != this.getClass() ){
			return false;
		}
		if (obj == this) {
			return true;
		}
		Motion m=(Motion) obj;
		return this.mp.equals(m.mp);
	}		
	
	
	/**
	 * Represents the place(building number, floor number and sub corridor
	 * number) where motion was detected.
	 * 
	 * @author raj
	 * 
	 */
	public static class MotionPlace{
		private int bNum;
		private int fNum;
		private int sNum;
		private boolean motionDetected;
		public MotionPlace() {
		}
		
		public MotionPlace(int bNum, int fNum, int sNum, boolean motionDetected) {
			this.bNum=bNum;
			this.fNum=fNum;
			this.sNum=sNum;	
			this.motionDetected=motionDetected;			
		}
		public int getbNum() {
			return bNum;
		}
		public int getfNum() {
			return fNum;
		}
		public int getsNum() {
			return sNum;
		}
		public boolean getMotionDetected(){
			return motionDetected;
		}
		
		@Override
		public int hashCode(){
			final int prime = 31;
			int result = 1;
			result = prime * result + new Integer(bNum).hashCode();
			result = prime * result + new Integer(fNum).hashCode();
			result = prime * result + new Integer(sNum).hashCode();			
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if( obj==null || obj.getClass() != this.getClass() ){
				return false;
			}
			if (obj == this) {
				return true;
			}
			MotionPlace mp=(MotionPlace) obj;
			return this.bNum==mp.bNum && this.fNum==mp.fNum && this.sNum==mp.sNum;
		}		
	}
	
}
