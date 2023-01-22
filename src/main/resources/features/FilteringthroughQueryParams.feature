Feature: I want to verify endpoint services using different query parameters

// the feature validates the response for specific users defined in datatable at steps 
// if we want to verify the for diiferent users change the userid to valid user 
// and also update the specific data in src/test/resources/TestData folder as .json files 

  Background:
		Given I am a user 
   
  Scenario: verify the comments under single post 
    When I want to read the comments under postid <postid>
    |postid|
    | 1    |
    Then I validate that <commentscount> comments are present 
   	|commentscount|
   	| 5 |
    And validate the response
    
  Scenario: verify the posts by a user
     When I want to read the users userid <userid> filter posts
    |userid|
    | 1    |
 		And I want to read the posts filter by user <userid>
  	|userid |
  	| 1 |
  	Then validate that total posts are <postscount> 
  	|postscount|
  	| 10  |
  	And validate the responses and both end points 

Scenario: verify the comments by a filtering postid

 When I want to read the comments with <postid> filter posts
    |postid|
    | 3    |
 		And I want to read the comments filter by postid <postid>
  	|userid |
  	| 3 |
  	Then validate that total posts are <postscount> 
  	|postscount|
  	| 5  |
  	And validate the responses and both end points 

