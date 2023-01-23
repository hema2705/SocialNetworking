Feature: Read the comments based on postid and userid
// this scenario reads the comments of the user based on postid and userid
// the test data is validated as Serialization and Deserialization of json 
// the test data expected is passed at method level
// this scenario also validates for negative testing 
// one testcase fails -> which is expected 
  Scenario Outline: Verify the comments based on postid 
    Given I am an user of the social network 
    When I can access the posts
    Then I want to get the "comments" <postid>
    And  verify the details of comments made by userid <id> with values <postid> 
    
    
    Examples: 
      | postid | id      |
      | 1      |    1    |
      | 1      |    2    |