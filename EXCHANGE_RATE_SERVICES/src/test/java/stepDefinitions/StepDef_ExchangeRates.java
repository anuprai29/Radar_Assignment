package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import coreUtils.ApiCore;
import coreUtils.LoggerUtil;
import io.restassured.response.Response;


public class StepDef_ExchangeRates extends ApiCore {
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Response response = null;
	protected LoggerUtil log;
	
	private Scenario scenario;

	@Before(value = "not @failure") 
	public void before(Scenario scenario) {
		 this.scenario = scenario;
	}
	
	
	@Given("Perform get operation for latest forgin exchange rates on url {string}")
	public void perform_get_operation_for_latest_exchange_rates(String URL) {
		response = getByPath(URL);
		LoggerUtil.info("get latest_exchange_rates:- " + response.prettyPrint());
	}

	@Then("Validate get status successful")
	public void validate_get_status_successful() {
		response.then().statusCode(200);
		scenario.log("Latest exchange rates, GET operation response '"+ response.getStatusLine() +"' is as expected.");
	}

	@And("Validate get response body contains following expected values")
	public void validate_get_response_body(List<Map<String, String>> dtTable) {
		String date = null;
		for (Map<String, String> map : dtTable) {
			if (map.containsKey("Node_A")) {
				Assert.assertTrue(response.asString().contains(map.get("Node_A")));
				scenario.log("Node '"+map.get("Node_A")+"' is exists in Get latest exchange rate response.");
			}
			
			if (map.containsKey("Node_B")) {
				Assert.assertTrue(response.asString().contains(map.get("Node_B")));
				scenario.log("Node '"+map.get("Node_B")+"' is exists in Get latest exchange rate response.");
			}

			if (map.containsKey("BASE")) {
				Assert.assertTrue(response.path("base").toString().contains(map.get("BASE")));
				scenario.log("Value of base '"+map.get("BASE")+"' is exists in Get latest exchange rate response.");
			}
			
			if (map.containsKey("Date")) {
				Assert.assertTrue(response.asString().contains(map.get("Date")));
				scenario.log("Today date '"+map.get("Date")+"' is matching in Get latest exchange rate response.");
			}

		}
	}

	
	@And("Validate rates from response body contains following expected values")
	public void validate_rate_response_from_body(List<Map<String, String>> dtTable) {
		for (Map<String, String> map : dtTable) {
			try{
				Assert.assertTrue(response.path("rates."+map.get("RateType")).toString().contains(map.get("RateValue")));
			}catch (Exception e){
				scenario.log("Rate type '"+map.get("RateType") +" and Rate value '" +map.get("RateValue")+ "' is not matching/exists in exchange rate response body.");
			}
			
		}
	}
	
	@Given("Perform get operation for latest forgin exchange rates on incorrect resource {string}")
	public void perform_get_operation_for_latest_exchange_rates_incorrect_resource(String URL) {
		response = getByPath(URL);
		LoggerUtil.info("get latest_exchange_rates response:- " + response.prettyPrint());
	}

	@Then("Validate expected response")
	public void validate_expected_response() {
		response.then().statusCode(400);
		scenario.log("Latest exchange rates, GET operation response '"+ response.getStatusLine() +"' is as expected successfully.");
	}
	
	
	@Given("Perform get operation for {string} date forgin exchange rates")
	public void perform_get_operation_for_specific_exchange_rates(String URL) {
		response = getByPath(URL);
		LoggerUtil.info("get exchange_rates on passed date :- " + response.prettyPrint());
	}
	
	@And("Validate get response body matching with current date get response body")
	public void validate_get_response_body_matching_with_current_date_response_body() {
		//get current date response
		Response currentDateresponse = getByPath("/"+(formatter.format(new Date())));
		LoggerUtil.info("get latest_exchange_rates:- " + currentDateresponse.prettyPrint());
		
		Assert.assertTrue(response.asString().contains(currentDateresponse.asString()));
		scenario.log("Validation passed as future date exchange rates response is matching with current date response.");
	}
}