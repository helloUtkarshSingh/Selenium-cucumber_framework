
@tag
Feature: Error validation
  I want to use this template for my feature file


  @Regression
    Scenario Outline: Invalid Credentials and Product Error
    Given I landed on E-commerce Website
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed
    Then Close the Browser
       Examples: 
      | username               | password      | 
      | utkarshsingh@gmail.com | Utkarsh@1235 | 
