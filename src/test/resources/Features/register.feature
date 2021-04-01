Feature: Test account signup function
  The feature I will sign up new users at mailchimp.com with various data input

  Scenario Outline: Register new users
    Given I have used "<browser>" as a browser
    And I have navigated to mailchimp
    When I enter "<email>" and "<username>" and "<password>"
    And I press the sign up button
    Then I verify the sign up "<status>"



    Examples:
    | browser |  email  |  username  |  password  | status |
    | chrome  | alcalgone@yahoo.com | Blickson | Kissmy8grits$ | USED |
    | chrome  | | Glad3seeU | Kissmy8grits$ | BLANK                   |
    | chrome  | goodluv@smile.com | awu456yhuji1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiop | Kissmy8grits$ | LONG |
    | chrome  | goodluv@smile.com | Shithappens10U | Kissmy8grits$ | SUCCESS                                                                                        |
