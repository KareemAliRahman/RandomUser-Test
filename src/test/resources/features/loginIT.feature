Feature: Testing login
  Scenario: Login with valid credentials
    Given I am on AutomationPractice login page
    When I login with valid credentials with "testemail@example.com" and "password"
    Then the page title should start with "My account"
