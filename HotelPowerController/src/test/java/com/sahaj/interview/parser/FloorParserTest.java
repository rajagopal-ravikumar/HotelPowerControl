package com.sahaj.interview.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FloorParserTest {
	FloorParser fp=new FloorParser();
	
	@Test
	public void parseDetailsTest() throws Exception{
		String s1="Movement detected in Floor 1";
		String s2="No Movement detected in floor 2";
		String s3="No Movement detected in subcorridor 3";

		assertEquals("Floor number parsed is wrong", 1, fp.parseDetails(s1));
		
		//Case Insensitive
		assertEquals("Floor number parsed is wrong", 2, fp.parseDetails(s2));	
		
		//No floor word in the input string
		assertEquals("Error floor number parsed is wrong", 0, fp.parseDetails(s3));
	}
}
