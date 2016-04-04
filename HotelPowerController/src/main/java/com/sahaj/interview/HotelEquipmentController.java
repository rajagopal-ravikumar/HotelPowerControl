package com.sahaj.interview;

import java.util.Scanner;

import com.sahaj.interview.PowerController.PowerController;
import com.sahaj.interview.builder.HotelBuilder;
import com.sahaj.interview.entity.Building;
import com.sahaj.interview.entity.Hotel;
import com.sahaj.interview.motion.Motion;
import com.sahaj.interview.motion.Motion.MotionPlace;
import com.sahaj.interview.parser.InputParser;

/**
 * @author raj
 *
 */
public class HotelEquipmentController{
	/**
	 * Entry point of the Application. User needs to enter number of floors,
	 * number of main corridors per floor and number of sub corridors per floor
	 * and optionally number of buildings in the hote.. Application builds the
	 * hotel and initializes the equipment in each floor.
	 * Once initialization is done, application will be waiting for Sensor input
	 * in a command prompt like mode. Typing "quit"+<Enter Key> will stop the 
	 * application.
	 * 
	 * @param args
	 */
	public static void main(String [] args){
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Number of floors: ");
		int numOfFloors=scanner.nextInt();
		System.out.print("Main corridors per floor: ");
		int numOfMainCorridor=scanner.nextInt();
		System.out.print("Sub corridors per floor: ");
		int numOfSubCorridor=scanner.nextInt();
		HotelBuilder hb=new HotelBuilder();
		hb.addBuilding();
		hb.addFloors(numOfFloors);
		hb.addCorridors(numOfMainCorridor, numOfSubCorridor);
		Hotel ht=hb.build();
		PowerController pc=new PowerController(ht);
		pc.setHotelInitialState();
		for(Building b: ht.getBuildingList()){
			System.out.println(b.toString());
		}
		scanner.nextLine();
		/*
		 * Wait for the sensor input to detect motion to switch ON sub corridor
		 * lights or detect lack of motion to switch OFF sub corridor lights.
		 */
		System.out.print("Sensor_Input>>");		
		while(scanner.hasNextLine()){			
			String sensorInput=scanner.nextLine();
			if(sensorInput.equalsIgnoreCase("quit")){
				break;
			}
			InputParser ip=new InputParser();
			MotionPlace mp=ip.parseSenInput(sensorInput, ht);
			if(mp==null){
				System.out.print("Sensor_Input>>");
				continue;
			}
			Motion m=new Motion(pc);
			m.setMp(mp);
			for(Building b: ht.getBuildingList()){
				System.out.println(b.toString());
			}
			System.out.print("Sensor_Input>>");
		}
		System.out.println("Thank You");
		
	}	
	
}
