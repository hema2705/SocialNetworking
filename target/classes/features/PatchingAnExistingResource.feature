Feature: As a user of social network I want to update a post 

  Scenario Outline: Updating the message using patching
    Given I am an existing user of the social network 
    And I want to post a new message with type header specification
    And when I get the existing resource values with user id  with title where post id is <post id>
    When my user id <user id> with title "<title>" posts "<body>" to patch where post id is <post id>
    Then the response is posted 
    And I want to verify the if patch is successful 


    Examples: 
      | user id  | title | body  | post id |
      | 1 |     my updated post  | I am very grateful to be see the goodness | 1 |
      | 1 |     my updated post  | I am very grateful to be see the goodness | 2 |
      | 1 |     my updated post  | I am very grateful to be see the goodness | 3 |
      | 2 |     my updated post  | I am very grateful to be see the goodness | 11 |
      | 2 |     my updated post  | I am very grateful to be see the goodness | 12 |