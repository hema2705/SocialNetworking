Feature: As a user of social network I want to delete a post 
 
 deletes an existing resource with post id defined in Examples 
 this scenario verifies the satus code and verifies if the body is empty after delete operation
 to prove data driven capability to framework added data parameterization using scenario outline 

  Scenario Outline: Deleting the post 
    Given I am an existing user of the social network 
    And has the  header specification
    And I get the existing resource values with user id  with title where post id is <post id>
    When delete where post id is <post id>
    Then verify the response body
    And I want to verify the post deleted and verify staus code <status code>

    Examples: 
     | post id | status code |
     | 1       |  200        |
     | 4       |  200        |
     | 2       |  200        |