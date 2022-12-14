
@tag
Feature: Checking the order history
  I want to use this template for my feature file
  
  @tag2
   Scenario Outline: Checking the order history
   Given I landed on E-commerce Website
   When Logged in with username <username> and password <password>
   Then Check the order history with product <product>
   Then Close the Browser
    Examples: 
      | username               | password      | product         | 
      | utkarshsingh@gmail.com | Utkarsh@12345 | ADIDAS ORIGINAL | 
