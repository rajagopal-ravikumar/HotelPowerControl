package com.sahaj.interview.criteria;

import com.sahaj.interview.entity.Building;
import com.sahaj.interview.entity.Floor;

/**
 * Interface to manage criteria that needs to be enforced by the controllers
 * (Example : PowerController )
 * 
 * @author raj
 * 
 */
public interface Criteria {
	public static final int LIGHT_POWER=5;
	public static final int AC_POWER=10;
	
	/**
	 * @param b
	 * @return true if the power consumption is not above the specified limit for each floor in the building.
	 */
	public boolean isCriteriaMetForBuilding(Building b);
	
	/**
	 * @param f
	 * @return true if the power consumption of the floor f satisfies the property : 	 * 
	 * (Number of Main corridors * 15) + (Number of sub corridors * 10)
	 */
	public boolean isCriteriaMetForFloor(Floor f);
	
	/**
	 * @param f
	 * @return true if the power consumption of the floor is below the limit.
	 *         false otherwise. specifically this method will be used to turn ON
	 *         ACs that have been turned OFF previously to save energy. So this
	 *         method will return true if the following property is satisfied :
	 *         (Max Power Consumption limit for floor)-(Current total power
	 *         consumption of floor)>=AC POWER(10)
	 */
	public boolean isUnderUtilized(Floor f);
	
	/**
	 * @param b
	 * @return the maximum power consumption limit for the building.
	 */
	public int maxLimitForBuilding(Building b);
	
	/**
	 * @param f
	 * @return the maximum power consumption limit for the floor.
	 */
	public int maxLimitForFloor(Floor f);
	
}
