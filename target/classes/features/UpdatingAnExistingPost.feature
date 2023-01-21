Feature: As a user of social network I want to update a post 

  Scenario Outline: Updating the message using put
    Given I am an existing user of the social network 
    And I want to post a new message with type header specification
    And when I get the existing resource values with user id  with title where post id is <post id>
    When my user id <user id> with title "<title>" posts "<body>" to update where post id is <post id>
    Then the response is posted 
    And I want to verify the log 


    Examples: 
      | user id  | title | body  | post id |
      | 1 |     my updated post 1 | I am very grateful to be see the goodness 1 | 1 |
      | 2 |     my updated post 2 | I am very grateful to be see the goodness 2 | 1 |
      | 2 |     my updated post 3 | I am very grateful to be see the goodness 3 | 2 |