package com.canvas.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterGraphic {

	private static Map<String, String> cmdGraphicMap = new ConcurrentHashMap<>();
	
	static {
		try {
			Properties prop = new Properties();
			//ClassLoader classLoader = RegisterGraphic.class.getClassLoader();
			InputStream in = RegisterGraphic.class.getResourceAsStream("graphics.properties");
			prop.load(in);
			
			Set<Object> propKeys = prop.keySet();
			if (propKeys != null && propKeys.size() > 0) {
				for (Object objKey : propKeys) {
					cmdGraphicMap.put((String)objKey, prop.getProperty((String)objKey));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getGraphicClassName(String cmd) {
		return cmdGraphicMap.get(cmd);
	}
	
	public static boolean isGraphicCmd(String cmd) {
		if (cmdGraphicMap.containsKey(cmd)) {
			return true;
		}
		return false;
	}
	
}
