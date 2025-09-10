Feature: Login Functionality

  Scenario: Login with valid credentials
    Given the user opens the login page
    When the user logs in using keys "usernameValid" and "passwordValid"
    And the user clicks the Login button
    Then the home page should be displayed

  Scenario: Login with valid username and invalid password
    Given the user opens the login page
    When the user logs in using keys "usernameValid" and "passwordInvalid"
    And the user clicks the Login button
    Then a validation message should appear

  Scenario: Login with invalid username and valid password
    Given the user opens the login page
    When the user logs in using keys "usernameInvalid" and "passwordValid"
    And the user clicks the Login button
    Then a validation message should appear

  Scenario: Login with empty username and valid password
    Given the user opens the login page
    When the user logs in using keys "emptyUserName" and "passwordValid"
    And the user clicks the Login button
    Then a validation message should appear

  Scenario: Login with empty username and valid password
    Given the user opens the login page
    When the user logs in using keys "usernameValid" and "emptyPassword"
    And the user clicks the Login button
    Then a validation message should appear
