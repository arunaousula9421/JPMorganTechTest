Feature: JSONPlaceHolder Rest API

  Scenario Outline: Make a new post
    Given I set required headers for the request
    And pass required data
    When I send new <post> request
    Then the response code is <statusCode>
    And response contains required data sent

    Examples:
    |statusCode| post   |
    |   201    | /posts |

   Scenario Outline: Comment On Posts
     Given I set parameters for the request
     When I send a <get> request
     Then the response code is <statusCode>
     And response has size as <size>

     Examples:
       |statusCode| size | get       |
       |   200    | 5    | /comments |

    Scenario Outline: List Users
      Given I send a get request for users
      Then the response code is <statusCode>
      And list the users

      Examples:
        |statusCode|
        |   200    |

