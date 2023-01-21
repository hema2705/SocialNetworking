Feature: As a user of social network I want to update a post 

  Scenario Outline: Updating the message using patching
    Given I am an existing user of the social network 
    And has the  header specification
    And when I get the existing resource values with user id  with title where post id is <post id>
    When my user id <user id> with title "<title>" posts "<body>" to modify with patch where post id is <post id>
    Then the message status is 
    And I want to verify the if patch is successful 


    Examples: 
      | user id  | title             | body                        | post id |
      | 1        |     India         | India  has Mount Everest    | 1       |
      | 1        |     NorthAmerica  | NorthAmerica has Denail     | 2       | 
      | 1        |     Africa        | Africa has KiliManjaro      | 3       |
      | 2        |     Europe        | Europe has Mont Blanc			 | 11      |
