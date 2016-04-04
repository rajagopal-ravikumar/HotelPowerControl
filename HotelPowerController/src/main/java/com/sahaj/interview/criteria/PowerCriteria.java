package com.sahaj.interview.criteria;

import com.sahaj.interview.entity.Building;
import com.sahaj.interview.entity.Floor;
import com.sahaj.interview.entity.MainCorridor;
import com.sahaj.interview.entity.SubCorridor;

/**
 * Represents Power Criteria to be enforced by the power controller. Provides
 * helper methods that can be used by the controller classes to check if the
 * power consumption criteria is satisfied every time when a motion is detected
 * in the sub corridor. This class also provides helper methods that can help
 * power controller identify if the power consumption is under the limits.
 * 
 * @author raj
 * 
 */
public class PowerCriteria implements Criteria {
	
	/* (non-Javadoc)
	 * @see com.sahaj.interview.criteria.Criteria#isCriteriaMetForBuilding(com.sahaj.interview.entity.Building)
	 */
	@Override
	public boolean isCriteriaMetForBuilding(Building b) {
		for(Floor f: b.getFloorList()){
			if(!this.isCriteriaMetForFloor(f)){
				return false;				
			}
		}
		return true;
	}
	
	/**
	 * @param f
	 * @return current total power consumption of the floor.
	 */
	public int currentPowConsumptionOfFloor(Floor f){
		return currentMCPowConsumption(f)+currentSCPowConsumption(f);
	}
	
	/**
	 * @param f
	 * @return current total power consumption of all main corridors in Floor f
	 */
	public int currentMCPowConsumption(Floor f){
		int mcPowerConsumption=0;
		for(MainCorridor mc: f.getMainCorridorList()){
			int numOfLightsTurnedOn=mc.getNumOfLightsTurnedOn();
			int numOfACsTurnedOn=mc.getNumOfACsTurnedOn();
			mcPowerConsumption=mcPowerConsumption+(numOfLightsTurnedOn*LIGHT_POWER)+(numOfACsTurnedOn*AC_POWER);
		}
		return mcPowerConsumption;
	}
	/**
	 * @param f
	 * @return current total power consumption of all sub corridors in Floor f
	 */
	public int currentSCPowConsumption(Floor f){
		int scPowerConsumption=0;
		for(SubCorridor sc: f.getSubCorridorList()){
			int numOfLightsTurnedOn=sc.getNumOfLightsTurnedOn();
			int numOfACsTurnedOn=sc.getNumOfACsTurnedOn();
			scPowerConsumption=scPowerConsumption+(numOfLightsTurnedOn*LIGHT_POWER)+(numOfACsTurnedOn*AC_POWER);
		}
		return scPowerConsumption;
	}
	
	/* (non-Javadoc)
	 * @see com.sahaj.interview.criteria.Criteria#isUnderUtilized(com.sahaj.interview.entity.Floor)
	 */
	@Override
	public boolean isUnderUtilized(Floor f) {
		return ((maxLimitForFloor(f))-(currentPowConsumptionOfFloor(f)))>=AC_POWER;
		
	}
	
	/* (non-Javadoc)
	 * @see com.sahaj.interview.criteria.Criteria#isCriteriaMetForFloor(com.sahaj.interview.entity.Floor)
	 */
	@Override
	public boolean isCriteriaMetForFloor(Floor f) {
		return (currentPowConsumptionOfFloor(f))<=(maxLimitForFloor(f));
	}

	/* (non-Javadoc)
	 * @see com.sahaj.interview.criteria.Criteria#maxLimitForBuilding(com.sahaj.interview.entity.Building)
	 */
	@Override
	public int maxLimitForBuilding(Building b) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sahaj.interview.criteria.Criteria#maxLimitForFloor(com.sahaj.interview.entity.Floor)
	 */
	@Override
	public int maxLimitForFloor(Floor f) {		
		return maxLimitForMainCorridor(f)+maxLimitForSubCorridor(f);
	}
	
	/**
	 * @param f
	 * @return the maximum power consumption combining for all sub corridors in
	 *         a floor f. Calculated (as number of sub corridors in the
	 *         floor)*10
	 */
	public int maxLimitForSubCorridor(Floor f){		
		return f.getSubCorridorList().size()*10;
	}
	
	/**
	 * @param f
	 * @return the maximum power consumption combining for all main corridors in
	 *         a floor f. Calculated (as number of main corridors in the
	 *         floor)*15
	 */
	
	public int maxLimitForMainCorridor(Floor f){		
		return f.getMainCorridorList().size()*15;
	}

}
