Feature: I want to filter the users 

  Scenario Outline: I want to read the comments of a user and details
    Given I am an user of the social network 
    When I can access the posts
    Then I want to get the posts of user id <userId> 
    And  I want to verify comments 
    
    
    Examples: 
      | userId |
      | 1      |
      | 2      |