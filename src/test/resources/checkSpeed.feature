Feature: CheckSpeed

  Scenario: checkSpeed01
    Given I have a fineEngine and a car
    When I ask it to compare 50 with 30
    Then it should answer with result false

  Scenario: checkSpeed01
    Given I have a fineEngine and a car
    When I ask it to compare 20 with 60
    Then it should answer with result false

  Scenario: checkSpeed01
    Given I have a fineEngine and a car
    When I ask it to compare 130 with 60
    Then it should answer with result true


  Scenario: checkSpeed01
    Given I have a fineEngine and a car
    When I ask it to compare 200 with 60
    Then it should answer with result true
