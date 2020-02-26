@Service
Feature: Backend demo

  Scenario: Get greeting
    Given we are logged in
    When we ask for a greeting for "Toto" [PUT "/v2/greetings/{name}"]
    Then we get a greeting message
      """
        {
          "content": "Hello Toto!"
        }
      """

  Scenario: Get greeting when not logged in
    Given we are not logged in
    When we ask for a greeting for "Toto" [PUT "/v2/greetings/{name}"]
    Then we should get a 401 error
