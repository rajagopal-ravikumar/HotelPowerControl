package com.sahaj.interview.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloorParser extends Parser {
	public static final Pattern FLOOR_PATTERN=Pattern.compile("\\bFloor\\s\\d+\\d*\\b", Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see com.sahaj.interview.parser.Parser#parseDetails(java.lang.String)
	 */
	@Override
	public int parseDetails(String s) {
		Matcher matcher=FLOOR_PATTERN.matcher(s);
		return returnNum(matcher);
	}
}
