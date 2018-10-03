package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class PropertyReader {

    private static String defaultConfigFile = "Configuration.properties";
  
    public PropertyReader() {
    	
    }

    /**
     *
     * This method will get the properties pulled from a property file
     * @return value of the property
     * @throws IOException 
     * 
     */  
    
    public static String getProperty(String propFile, String Property) throws IOException {
    	Properties prop = new Properties();
    	InputStream input = null;
    	
    	try{
			input = new FileInputStream(propFile);
    		
    		prop.load(input);
    		
    		Enumeration<?> e = prop.propertyNames();
    		Map<String, String> map = new HashMap<String, String>();
    		while (e.hasMoreElements()) {
    			String key = (String) e.nextElement();
    			String value = prop.getProperty(key);
    		
    			map.put(key, value);
    		}
    		
    		String property = map.get(Property);
    		
			return property;

    	}
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
		return Property;
    }
    
    public static String getProperty(String property) throws IOException{
        return getProperty(defaultConfigFile, property);
    }
}
