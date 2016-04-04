package com.sahaj.interview.parser;

import java.util.regex.Matcher;

public abstract class Parser {
	
	/**
	 * @param m
	 * @return the integer number followed by the entity which was matched from
	 *         a specific pattern. Entity could be of three types here :
	 *         Building/Floor/Corridor.
	 */
	public int returnNum(Matcher m){
		int num=0;
		if(m.find()){
			String floorDetails=m.group();
			String[] floorDetailsSplit=floorDetails.split(" ");
			num=Integer.valueOf(floorDetailsSplit[floorDetailsSplit.length-1]);			
		}
		return num;
	}

	/**
	 * @param s
	 * @return the integer number followed by the entity being parsed Entity
	 *         could be of three types here : Building/Floor/Corridor.
	 */
	public abstract int parseDetails(String s);
}
