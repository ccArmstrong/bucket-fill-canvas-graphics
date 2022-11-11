package com.canvas.utils;

public class DataUtils {

	public static boolean isDigital(String chString) {
		try {
			Integer.parseInt(chString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
