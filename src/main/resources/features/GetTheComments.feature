Feature: I want to read the comments based on postid and userid

  Scenario Outline: I want to read the comments based on postid 
    Given I am an user of the social network 
    When I can access the posts
    Then I want to get the "comments" <postid>
    And  I want to verify comments 
    And verify the details of comments made by userid <id> with values <postid> with "<name>" "<email>" "<body>"
    
    
    Examples: 
      | postid | id      |
      | 1      |    1    |
      | 1      |    2    |