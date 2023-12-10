@LoginTest
Feature: Test login functionalities

  Background:
    Given a user is on the login page

  @positive_test
  Scenario: Check login is successful with valid credentials
    When user enters username "standard_user" and password "secret_sauce"
    And click on login button
    Then user is navigated to home page

  @dataDriven_test
  Scenario Outline: Check login is successful with valid credentials for multiple users
    When user enters username "<username>" and password "<password>"
    And click on login button
    Then user is navigated to home page
    Examples:
      |username       |password    |
      |standard_user  |secret_sauce|
      |visual_user    |secret_sauce|
      |problem_user   |secret_sauce|

  @dataTable_test
  Scenario: Check login is successful using data table
    When user click on login button upon entering credentials
    |username        |password    |
    |standard_user   |secret_sauce|
    Then user is navigated to home page

  @negative_test
  Scenario: Check login is unsuccessful with invalid credentials
    When user enters username "standard_user" and password "abc1234"
    And click on login button
    Then user is failed to login