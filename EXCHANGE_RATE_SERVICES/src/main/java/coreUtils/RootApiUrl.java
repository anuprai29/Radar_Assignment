package coreUtils;

import java.io.FileInputStream;
import java.util.Properties;
import io.restassured.RestAssured;


public class RootApiUrl {
	public static Properties prop;
	private static String defaultConfigFile = "/src/test/resources/config/Config.properties";
	public static String projPath = System.getProperty("user.dir");

	public RootApiUrl() {
	}

	private static String ROOT_API_URL;

	static {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(projPath + defaultConfigFile);
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ROOT_API_URL = prop.getProperty("ROOT_API_URL");
	}

	public static void setRootApiUrl() {
		RestAssured.baseURI = ROOT_API_URL;
	}
}
