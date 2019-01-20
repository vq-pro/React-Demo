@Service
Feature: Backend demo

  Scenario: Get Greeting
    Given we are logged in
    When we ask for a greeting for "Patrick" [GET "/greeting/{name}"]
    Then we get a greeting message
      """
        {
          "content": "Hello Patrick!"
        }
      """
#    And we have a record of greetings for:
#      | Name    |
#      | Patrick |

  Scenario: Get Greeting - Without login
    Given we are not logged in
    When we ask for a greeting for "Patrick" [GET "/greeting/{name}"]
    Then we should get a 401 error
