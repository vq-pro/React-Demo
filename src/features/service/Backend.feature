@Service
Feature: Backend demo

  Scenario: Get Greeting
    Given we are logged in
    When we ask for a greeting for "Toto" [GET "/greeting/{name}"]
    Then we get a greeting message
      """
        {
          "content": "Hello Toto!"
        }
      """
    And we should have a record of greetings for:
      | Name |
      | Toto |

  Scenario: Get Greeting - Without login
    Given we are not logged in
    When we ask for a greeting for "Toto" [GET "/greeting/{name}"]
    Then we should get a 401 error
