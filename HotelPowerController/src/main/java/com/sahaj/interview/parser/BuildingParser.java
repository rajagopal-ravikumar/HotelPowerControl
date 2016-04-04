package com.sahaj.interview.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildingParser extends Parser {
	public static final Pattern BUILDING_PATTERN=Pattern.compile("\\bBuilding\\s\\d+\\d*\\b", Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see com.sahaj.interview.parser.Parser#parseDetails(java.lang.String)
	 */
	@Override
	public int parseDetails(String s) {
		Matcher matcher=BUILDING_PATTERN.matcher(s);
		return returnNum(matcher);
	}
	/* (non-Javadoc)
	 * @see com.sahaj.interview.parser.Parser#returnNum(java.util.regex.Matcher)
	 */
	@Override
	public int returnNum(Matcher m){
		int buildingNum=1;
		if(m.find()){
			String buildingDetails=m.group();
			String[] buildingDetailsSplit=buildingDetails.split(" ");
			buildingNum=Integer.valueOf(buildingDetailsSplit[buildingDetailsSplit.length-1]);			
		}
		return buildingNum;
	}
	
}
