package com.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Map;

import org.testng.Reporter;
import org.yaml.snakeyaml.Yaml;
import static com.utility.PropertyReader.getProperty;

public class YamlReader {

	public static String yamlFilePath = null;

	public static String setYamlFilePath() throws IOException {
		String server = "";
		try {
			if (System.getProperty("server").contains("defaultserver") || System.getProperty("server").isEmpty())
				server = getProperty("Config.properties", "server").trim();
			else {
				server = System.getProperty("server");
			}
		} catch (NullPointerException e) {

			server = getProperty("Config.properties", "server").trim();
		}
		
		if (server.equalsIgnoreCase("qa")) {
			yamlFilePath = "src/test/resources/testdata/TestData_QA.yml";
		} else if (server.equalsIgnoreCase("prod")) {
			yamlFilePath = "src/test/resources/testdata/TestData_Prod.yml";
		} else {
			Reporter.log("Please Enter Correct value in cofig file", true);
		}
		System.out.println("Yaml file path ::" + yamlFilePath);
		return yamlFilePath;
	}

	@SuppressWarnings("unchecked")
	public static String getYamlValue(String token) {
		Reader reader = null;
		try {
			reader = new FileReader(yamlFilePath);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return null;
		}

		final Yaml yaml = new Yaml();
		Map<String, Object> map = (Map<String, Object>) yaml.load(reader);
		
		//System.out.println(Arrays.asList(map));
		
		String[] st = token.split("\\.");
		/*for(int i =0; i<= st.length-1; i++){
			System.out.println(st[i]);
		}*/
		
		return readMap(map, token).get(st[st.length - 1]).toString();
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> readMap(Map<String, Object> map, String token) {
		// System.out.println(token);
		System.out.println(Arrays.asList(map));
		if (token.contains(".")) {
			String[] st = token.split("\\.");
			map = readMap((Map<String, Object>) map.get(st[0]), token.replace(st[0] + ".", ""));
			//System.out.println(" readMap running ");
		}
		//System.out.println(Arrays.asList(map));
		return map;
	}

}
