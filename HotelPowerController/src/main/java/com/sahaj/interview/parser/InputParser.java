package com.sahaj.interview.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.motion.Motion.MotionPlace;

/**
 * Parses the sensor inputs. In case of error/exception prints a friendly
 * instruction to the user in stdout.
 * 
 * @author raj
 * 
 */
public class InputParser {
	public static final Pattern MOVEMENT_PATTERN=Pattern.compile("^Movement", Pattern.CASE_INSENSITIVE);
	public static final Pattern IDLE_PATTERN=Pattern.compile("^No Movement", Pattern.CASE_INSENSITIVE);	
	
	public InputParser() {
	}
	
	/**
	 * This method parses sensor input to see if its a movement/no movement
	 * pattern and then constructs the motion place object (representing the
	 * building number, floor number and sub corridor number) where motion/lack
	 * of motion is detected after further parsing.
	 * 
	 * @param s
	 * @param ht
	 * @return MotionPlace object representing where motion/lack of motion is
	 *         detected.
	 */
	public MotionPlace parseSenInput(String s, Hotel ht){
		boolean motionDetected=false;
		Matcher movementMatcher=null;	
		Matcher idleMatcher=null;
		movementMatcher=MOVEMENT_PATTERN.matcher(s);
		idleMatcher=IDLE_PATTERN.matcher(s);
		int floorNum=0;
		int subCorridorNum=0;
		int buildingNum=0;
		Parser bp=new BuildingParser();
		Parser fp=new FloorParser();
		Parser sp=new CorridorParser();
		
		MotionPlace mp=null;
		try{
			if(movementMatcher.find()){
				//Movement pattern detected
				motionDetected=true;
				buildingNum=bp.parseDetails(s);
				floorNum=fp.parseDetails(s);
				subCorridorNum=sp.parseDetails(s);	
				if(buildingNum!=0 && floorNum!=0 
						&& subCorridorNum!=0 && buildingNum<=ht.getBuildingList().size()
						&& floorNum<=ht.getBuilding(buildingNum).getFloorList().size()
						&& subCorridorNum<=ht.getBuilding(buildingNum).getFloor(floorNum).getSubCorridorList().size()){
					mp=new MotionPlace(buildingNum, floorNum, subCorridorNum, motionDetected);				
				}else{
					showMessageToUser(ht, buildingNum, floorNum, subCorridorNum);
				}
			}
			
			else if(idleMatcher.find()){
				//No Movement pattern detected.
				motionDetected=false;
				buildingNum=bp.parseDetails(s);
				floorNum=fp.parseDetails(s);
				subCorridorNum=sp.parseDetails(s);		
				if(buildingNum!=0 && floorNum!=0 && subCorridorNum!=0){
					mp=new MotionPlace(buildingNum, floorNum, subCorridorNum, motionDetected);				
				}else{
					showMessageToUser(ht, buildingNum, floorNum, subCorridorNum);
				}			
			}else{
				showMessageToUser(ht, buildingNum, floorNum, subCorridorNum);
			}
		}
		catch(ArrayIndexOutOfBoundsException ae){
			showMessageToUser(ht, buildingNum, floorNum, subCorridorNum);				
		}
		catch(NumberFormatException nfe){
			showMessageToUser(ht, buildingNum, floorNum, subCorridorNum);					
		}
		catch(Exception e){
			showMessageToUser(ht, buildingNum, floorNum, subCorridorNum);				
		}		
		return mp;
	}
	
	/**
	 * This method prints a friendly instruction to the user helping on the
	 * accepted inputs by the application in case of a run time exception due to
	 * bad input values.
	 * 
	 * @param ht
	 * @param buildingNum
	 * @param floorNum
	 * @param subCorridorNum
	 */
	private void showMessageToUser(Hotel ht, int buildingNum, int floorNum, int subCorridorNum){
		/*
		 * Prints the range of floor numbers and sub corridor numbers based on
		 * the initial numbers given by the user to construct the hotel and
		 * initialize it.
		 */
		
		String floorRange= "[1-"+ht.getBuilding(1).getFloorList().size()+"]";
		String subCorridorRange="[1-"+ht.getBuilding(1).getFloor(1).getSubCorridorList().size()+"]";
		if(floorNum==0){
			//Problem with the input floor number.
			System.out.println("Floor details missing/wrong format in the input. " +
					"Please enter valid floor details");
		}
		if(subCorridorNum==0){
			//Problem with the sub corridor  number.
			System.out.println("Sub Corridor details missing/wrong format in the input. " +
					"Please enter valid Sub Corridor details");
		}
		if(floorNum>ht.getBuilding(1).getFloorList().size()){
			System.out.println("Floor number entered is not within the range. " +
					"Please enter valid floor details");
		}
		else if(subCorridorNum>ht.getBuilding(1).getFloor(1).getSubCorridorList().size()){
			System.out.println("Sub Corridor number entered is not within the range. " +
					"Please enter valid Sub Corridor details");
		}
		System.out.println("Valid Input should be one of the following forms - ");
		System.out.println("----------------------------------------------------");
		System.out.println("TO REPORT MOVEMENT : Movement  in Floor <floorNum"+floorRange+">,"  +
							"Sub Corridor <subCorridorNum"+	subCorridorRange+">");
		System.out.println("TO REPORT LACK OF MOVEMENT : No movement  in Floor <floorNum"+floorRange+">,"  +
							"Sub Corridor <subCorridorNum"+	subCorridorRange+"> for a minute");
		System.out.println("TO QUIT THIS PROGRAM : quit");
		System.out.println("----------------------------------------------------");

	}	
}
