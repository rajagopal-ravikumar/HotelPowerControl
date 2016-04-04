package com.sahaj.interview.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BuildingParserTest {
	BuildingParser bp=new BuildingParser();

	@Test
	public void parseDetailsTest() throws Exception{
		String s1="Movement detected in building 1";
		String s2="No Movement detected in building 2";
		String s3="Movement detected in Building 3";
		String s4="Movement detected in floor 2";
		//Movement detected
		assertEquals("Building number parsed is wrong", 1, bp.parseDetails(s1));
		//No Movement detected
		assertEquals("Building number parsed is wrong", 2, bp.parseDetails(s2));	
		//Case Insensitive
		assertEquals("Building number parsed is wrong", 3, bp.parseDetails(s3));
		//No building word in the input string
		assertEquals("default Building number parsed is wrong", 1, bp.parseDetails(s4));

	}

}
