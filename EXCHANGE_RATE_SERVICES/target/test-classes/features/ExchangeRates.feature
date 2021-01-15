Feature: Use of the exchange rate for financial uses

  Scenario: Validate Rates API availablity for latest forgin exchange and assert response
    Given Perform get operation for latest forgin exchange rates on url "/latest"
    Then Validate get status successful
    And Validate get response body contains following expected values
      | Node_A | Node_B | Date    |
      | base   | rates  | 2021-01 |

  Scenario: Validate Rates API on incorrect url for latest forgin exchange rates and validate call is correct
    Given Perform get operation for latest forgin exchange rates on incorrect resource "/la"
    Then Validate expected response

  Scenario: Validate Rates API availablity for specific date forgin exchange rates and assert response
    Given Perform get operation for "2021-01-13" date forgin exchange rates
    Then Validate get status successful
    And Validate get response body contains following expected values
      | BASE | Date       |
      | EUR  | 2021-01-13 |
		And Validate rates from response body contains following expected values
			| RateType | RateValue |
			| USD | 1.2166  |
			| GBP | 0.88983 |
			| ZAR | 18.6329 |

  Scenario: Validate Rates API for future date forgin exchange rates macthes for the current date
    Given Perform get operation for "2021-01-18" date forgin exchange rates
    Then Validate get status successful
    And Validate get response body matching with current date get response body
