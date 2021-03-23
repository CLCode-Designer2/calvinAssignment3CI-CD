Feature: Test account signup function
  The feature I will sign up new users at mailchimp.com with various data input

  Scenario Outline: Register new users
    Given I have used "<browser>" as a browser
    And I have navigated to mailchimp
    When I enter "<email>" and "<username>" and "<password>"
    And I press the sign up button
    Then I verify the sign up status


    Examples:
    | browser |  email  | username  |  password  |
    | chrome  |goodgodlovin@bringit.com | Octopussy6 | Kissmy8grits$ |
