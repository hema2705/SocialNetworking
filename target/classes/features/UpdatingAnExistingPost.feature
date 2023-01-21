Feature: As a user of social network I want to update a post 

  Scenario Outline: Updating the message using put
    Given I am an existing user of the social network 
    And has the  header specification
    And when I get the existing resource values with user id  with title where post id is <post id>
    When my user id <user id> with title "<title>" posts "<body>" to modify with put where post id is <post id>
    Then the message status is
    And I want to verify the log
    


    Examples: 
      | user id  |     title       |         body                       |   post id   |
      | 1        |     Mango Wood  | Mango Wood is very hard            |    1        |
      | 2        |     Teak wood   | Teak wood is most durable          |    1        |
      | 2        |     Acacia Wood | Acacia is an extremely durable wood|    2        |
      