@Getcurrentprice
Feature: Coindesk API Automation

  Scenario: Verify current price of Bitcoin in different currencies
    Given I send a GET request to the Coindesk API
    Then the response should contain 3 BPIs: USD, GBP, and EUR
    And the GBP description should be "British Pound Sterling"
