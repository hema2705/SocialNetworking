@qa
Feature: As a user of social network I want to post 
  I want to post an important message 
// Adding a new resource and verifies the  new postid is created 
  Scenario Outline: Posting the message 
    Given I am an existing user of the social network 
    And has the  header specification
    When my user id <user id> with title "<title>" posts "<body>" 
    Then the message status is 
    And I want to verify the log 
    And confirm the message/post is as expected by retriving


    Examples: 
      | user id  |         title      |              body                    |
      | 1        |     my first post  | I am very happy posting in community |