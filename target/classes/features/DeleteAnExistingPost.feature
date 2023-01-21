Feature: As a user of social network I want to update a post 

  Scenario Outline: Deleting the post 
    Given I am an existing user of the social network 
    And has the  header specification
    And when I get the existing resource values with user id  with title where post id is <post id>
    When delete where post id is <post id>
    Then the message status is 
    And I want to verify the post deleted 


    Examples: 
     | post id |
     | 1 |
     | 4 |
     | 2 |