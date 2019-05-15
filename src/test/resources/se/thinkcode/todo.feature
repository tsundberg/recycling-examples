Feature: In order to remember what to do, one might need a todo list

  Scenario: Remember to buy food for the cat
    Given that Thomas is out of cat food
    When he adds buy cat food to his todo list
    Then should Thomas todo list contain buy cat food
