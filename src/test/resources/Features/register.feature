Feature: Test account signup function
  The feature I will sign up new users at mailchimp.com with various data input

  Scenario Outline: Register new users
    Given I have the browser open
    And I have navigated to mailchimp
    When I enter "<email>" and "<username>" and "<password>"
    And I press the sign up button
    Then I verify the sign up status

    Examples:
    |  email  | username  |  password  |
    | alcalgone@yahoo.com | Blickson | Kissmy8grits$ |
    | alcalgone@yahoo.com | Blickson | Kissmy8grits$ |
    | alcalgone@yahoo.com | Blickson | Kissmy8grits$ |
    |  | Blickson | Kissmy8grits$ |