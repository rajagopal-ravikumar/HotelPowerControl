package com.sahaj.interview.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorridorParser extends Parser {
	public static final  Pattern CORRIDOR_PATTERN=Pattern.compile("\\bSub\\s*Corridor\\s\\d+\\d*\\b", Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see com.sahaj.interview.parser.Parser#parseDetails(java.lang.String)
	 */
	@Override
	public int parseDetails(String s) {
		Matcher matcher=CORRIDOR_PATTERN.matcher(s);		
		return returnNum(matcher);
	}
}
