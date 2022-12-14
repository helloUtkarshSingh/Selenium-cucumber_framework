
@tag
Feature: Purchase the order from E-commerce Website
  I want to use this template for my feature file

  Background:
  Given: I landed on E-commerce Website

  @tag2
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <product> to Cart and Checkout
    Then Provide the Cvv number <cvv_no> and Bank name <Bank_name>
    Then Select the country <country> and country_initial <country_initial>
    And "THANKYOU FOR THE ORDER." message is displayed on the Confirmation page

    Examples: 
      | username               | password      | product         |country|cvv_no |Bank_name      |country_initial|  
      | utkarshsingh@gmail.com | Utkarsh@12345 | ADIDAS ORIGINAL | India | 123   |Utkarsh Singh  |     Ind       |
      
