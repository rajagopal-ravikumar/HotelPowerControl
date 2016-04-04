package com.sahaj.interview.PowerController;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.sahaj.interview.criteria.Criteria;
import com.sahaj.interview.criteria.PowerCriteria;
import com.sahaj.interview.entity.Building;
import com.sahaj.interview.entity.Floor;
import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.entity.MainCorridor;
import com.sahaj.interview.entity.SubCorridor;
import com.sahaj.interview.motion.Motion;
import com.sahaj.interview.motion.Motion.MotionPlace;

/**
 * Represents the Power Controller which responds to sensor inputs Makes sure
 * that the power consumption criteria is enforced by switching OFF ACs in sub
 * corridors whereever possible. Also makes sure that in case of under
 * utilization of energy, ACs that were switched OFF previously is switched ON 
 * @author raj
 * 
 */
public class PowerController implements Observer {
	private Hotel hotel;
	private Map<MotionPlace, MotionPlace> alternateSubCorridorToSaveEnergy=new HashMap<MotionPlace, MotionPlace>();
	Criteria pc=new PowerCriteria();
	public PowerController(Hotel hotel){
		this.hotel=hotel;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		/*
		 * Responds to change in motion. if motion detected : switch on sub
		 * corridor light if lack of motion detected for a minute : switch off
		 * sub corridor light.
		 */
		Motion m=(Motion)o;
		if(m.getMp().getMotionDetected()){
			switchOnSubCorridorLight(m.getMp());
		}
		if(!(m.getMp().getMotionDetected())){
			switchOffSubCorridorLight(m.getMp());
		}
		
	}
	
	/**
	 * sets the initial stateof the hotel. will initialize all equipments in sub
	 * corridors and main corridors.
	 */
	public void setHotelInitialState() {
		for(Building b : hotel.getBuildingList()){
			for (Floor f: b.getFloorList()){
				for(MainCorridor mc : f.getMainCorridorList()){
					mc.initializeAllEquipments();
				}
				for(SubCorridor sc : f.getSubCorridorList()){
					sc.initializeAllEquipments();
				}
			}
		}
		
	}
	
	/**
	 * Switches ON sub corridor light where motion is detected.
	 * Checks if power consumption criteria is met.
	 * If criteria is not met, will look for a sub corridor where
	 * AC can be turned OFF to conserve energy.
	 * @param mp
	 */
	public void switchOnSubCorridorLight(MotionPlace mp){
		int alternateScNum;
		Floor f=this.hotel.getBuilding(mp.getbNum()).getFloor(mp.getfNum());
		SubCorridor sc=f.getSubCorridor(mp.getsNum());
		sc.turnOnLight(1);
		PowerCriteria pc= new PowerCriteria();
		if(!pc.isCriteriaMetForFloor(f)){
			alternateScNum=f.returnAlternateSubCorridorToSaveEnergy(mp.getsNum());
			if(alternateScNum!=0){
				MotionPlace alternateMp=new MotionPlace(mp.getbNum(), mp.getfNum(), alternateScNum, false);
				alternateSubCorridorToSaveEnergy.put(mp, alternateMp);
				Floor alternateFloor=this.hotel.getBuilding(alternateMp.getbNum()).getFloor(alternateMp.getfNum());				
				SubCorridor alternateSc=alternateFloor.getSubCorridor(alternateMp.getsNum());
				alternateSc.turnOffAC(1);
			}			
		}
	}

	/**
	 * Switches OFF sub corridor light where lack of motion is detected. Checks
	 * if power consumption is under the limit. If so, will look for a sub
	 * corridor where AC was turned OFF previously to conserve energy that can
	 * be turned ON now.
	 * 
	 * @param mp
	 */
	
	public void switchOffSubCorridorLight(MotionPlace mp){
		Floor f=this.hotel.getBuilding(mp.getbNum()).getFloor(mp.getfNum());
		SubCorridor sc=f.getSubCorridor(mp.getsNum());
		sc.turnOffLight(1);
		PowerCriteria pc= new PowerCriteria();		
		if(pc.isUnderUtilized(f)){
			//Under utilization - look for a sub corridor where AC can be turned on.
			MotionPlace alternateMp=alternateSubCorridorToSaveEnergy.get(mp);			
			if(alternateMp==null){
				for (MotionPlace mpIt : alternateSubCorridorToSaveEnergy.keySet())
				{
					alternateMp=alternateSubCorridorToSaveEnergy.get(mpIt);
					if(alternateMp!=null){
						mp=mpIt;
						break;
					}
					
				}
			}
			alternateSubCorridorToSaveEnergy.remove(mp);
			Floor alternateFloor=this.hotel.getBuilding(alternateMp.getbNum()).getFloor(alternateMp.getfNum());
			SubCorridor alternateSc=alternateFloor.getSubCorridor(alternateMp.getsNum());
			alternateSc.turnOnAC(1);				
		}			
	}	
}
