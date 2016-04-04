package com.sahaj.interview.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CorridorParserTest {
	CorridorParser cp=new CorridorParser();

	@Test
	public void parseDetailsTest() throws Exception{
		String s1="Movement detected in Sub Corridor 1";
		String s2="No Movement detected in sub corridor 2";
		String s3="No Movement detected in subcorridor 3";
		String s4="No Movement detected in floor 3";

		assertEquals("Corridor number parsed is wrong", 1, cp.parseDetails(s1));
		
		//Case Insensitive
		assertEquals("Corridor number parsed is wrong", 2, cp.parseDetails(s2));	
		
		//No space between the words Sub and Corridor
		assertEquals("Corridor number parsed is wrong", 3, cp.parseDetails(s3));
		
		//No sub corridor word in the input string
		assertEquals("Error corridor number parsed is wrong", 0, cp.parseDetails(s4));
	}

}
