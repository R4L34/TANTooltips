package com.r4l.tan_tooltips.reference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHandler {

	public static boolean preg_match(String str, String regex) {
		
		Pattern MY_PATTERN = Pattern.compile(regex);
		Matcher m = MY_PATTERN.matcher(str);
		
		return m.find();
		
	}
	
	public static String preg_replace(String str, String regex, String replacement) {
		return str.replaceAll(regex, replacement);
	}

}
