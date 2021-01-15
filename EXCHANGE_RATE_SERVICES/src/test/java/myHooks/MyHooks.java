package myHooks;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import coreUtils.RootApiUrl;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MyHooks extends RootApiUrl {
	@Before
	public void setup() {
		DOMConfigurator.configure("src//test//resources//config//log4j.xml");
		PropertyConfigurator.configure("src//test//resources//config//log4j.properties");
		setRootApiUrl();
	}

	@After
	public void tearDown(Scenario result) {
		if ((result.getStatus().name() != "PASSED")) {
			throw new RuntimeException(result.getName() + " got failed");
		}
	}
}
