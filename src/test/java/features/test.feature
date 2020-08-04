@MainScenario
Feature: Create a runnable test automation project
  Test should verify the following scenarios

  @Scenario1
    Scenario:
    Given I am a new customer
    And access to MoneyLion website
    When I hover on "About Us" and click "About Us" at the top of the webpage
    Then I should be redirected to the MoneyLion page
    And I should be able to see "Offices located in New York, San Francisco, Salt Lake City, and Kuala Lumpur" text displayed under "COME JOIN US"

  @Scenario2
    Scenario Outline: Able to verify the portfolio types
    Given I am a new customer
    And access to MoneyLion website
    Examples:
      |option  | portfolioName |
      |first  | Steady Income Portfolio |
      |fourth  | Moderate Portfolio |
      |seventh  | Equity Only Portfolio |

