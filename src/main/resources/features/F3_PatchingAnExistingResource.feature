Feature:  User updating a post on social network  - patching

  Scenario Outline: Updating the message using patching
    Given I am an existing user of the social network 
    And has the  header specification
    And I get the existing resource values with user id  with title where post id is <post id>
    When my user id <user id> with title "<title>" posts "<body>" to modify with patch where post id is <post id> with mt height <height> values
    Then verify the response body 
    And I want to verify the response status code  <status code>
    And I want to verify the if patch is successful 


    Examples: 
      | user id  | title             | body                        | post id |  height |  status code |
      | 1        |     Asia          | Asia  has Mount Everest     | 1       |  8849   |   200        |
      | 1        |     NorthAmerica  | NorthAmerica has Denail     | 2       |  6190   |   200        |
      | 1        |     Africa        | Africa has KiliManjaro      | 3       |  5895   |   200        |
      | 2        |     Europe        | Europe has Mont Blanc			 | 11      |  4809   |   201        |
