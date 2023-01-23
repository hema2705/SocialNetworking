Feature: Read ,Delete and Post the comments based on postid and userid
// this scenario reads the comments of the user based on postid and userid
// the test data is validated as Serialization and Deserialization of json 
// the test data expected is passed at method level
// this scenario also validates for negative testing 
// one testcase fails -> which is expected 
  Scenario Outline: Verify the comments based on postid 
    Given I am an user of the social network 
    When I want to get the "comments" <postid>
    Then  verify the details of comments made by userid <id> with values <postid> 
    
    
    Examples: 
      | postid | id      |
      | 1      |    1    |
      | 1      |    2    |
      
      
   Scenario Outline: Verify comments deleted based on post id
   
    Given I am an user of the social network 
    When I want to delete the comments <postid> and userid <id> 
    Then  verify the details of comments made by userid <id> with values <postid> deleted with empy respnse body   
    
    
    Examples: 
      | postid | id      |
      | 1      |    1    |
      | 1      |    2    |
      
    Scenario Outline: Verify new comments posted based on post id
   
    Given I am an user of the social network 
    When I want to post the comments  name "<name>" email "<email>" and body "<body>" made by userid <id> with values <postid> posted
    Then verify the details of comments name "<name>" email "<email>" and body "<body>" made by userid <id> with values <postid> posted 
   
    
    
    Examples: 
      | postid | id      | name     | email            | body                             |
      | 1      |    1    | Elephant | animals@zoo.biz  | Elephant is an intelligent animal |
      | 1      |    2    | Cheetah  | animal@zoo.user  | Fastest animal is Cheetah         |
      
    
   