@qa
Feature: I want to read the posts  
// this will read the response and conts number of users , posts  details 
  Scenario: I want to read the posts and the details 
    Given I am an user of the social network 
    When I can access the posts
    Then I want to read the posts  