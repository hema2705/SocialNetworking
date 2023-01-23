Feature: User post a message on social networking site
  I want to post an important message 
// Adding a new resource and verifies the  new postid is created 
  Scenario Outline: verify the post on social network
    Given I am an existing user of the social network 
    And has the  header specification
    When my user id <user id> with title "<title>" posts "<body>" 
    Then verify the response body
    And I want to verify the response status code  <status code>
    And confirm the message/post is as expected by retriving


    Examples: 
      | user id  |         title           |              body                    |   status code |
      | 1        |     First post          | I am very happy posting in community |    201        |
      | 2        |                         | post with empty title                |    201        |
      | 3        |   post with empty body  |                                      |    201        |
      